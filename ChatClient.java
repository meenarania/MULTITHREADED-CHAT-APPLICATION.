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