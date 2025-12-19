import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer {

    private static Set<ClientHandler> clients = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Server started on port 1234...");

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                ClientHandler client = new ClientHandler(socket);
                clients.add(client);
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    static class ClientHandler extends Thread {
        Socket socket;
        PrintWriter out;
        BufferedReader in;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println("Client says: " + msg);
                    broadcast(msg, this);
                }

            } catch (IOException e) {
                System.out.println("Client disconnected");
            }
        }

        void sendMessage(String msg) {
            out.println(msg);
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

public class ChatClient {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to chat server");

            BufferedReader serverInput =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter serverOutput =
                    new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput =
                    new BufferedReader(new InputStreamReader(System.in));

            // Ask username
            System.out.print("Enter your name: ");
            String username = userInput.readLine();
            serverOutput.println(username + " joined the chat");

            // Thread to receive messages from server
            Thread receiveThread = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = serverInput.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed");
                }
            });
            receiveThread.start();

            // Send messages to server
            String message;
            while (true) {
                message = userInput.readLine();

                if (message.equalsIgnoreCase("exit")) {
                    serverOutput.println(username + " left the chat");
                    socket.close();
                    break;
                }

                serverOutput.println(username + ": " + message);
            }

        } catch (IOException e) {
            System.out.println("Unable to connect to server");
        }
    }
}
