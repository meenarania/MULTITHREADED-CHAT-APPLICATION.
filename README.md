# MULTITHREADED-CHAT-APPLICATION.

#COMPANY#:CODTECH IT SOLUTIONS

#NAME#:MEENARANI A

#INTERN ID#:CT04DR2761

#DOMAIN#:JAVA PROGRAMMING

#DURATION#:4 WEEKS

#MENTOR#:NEELA SANTHOSH KUMAR

#Project Overview#: Multithreaded Chat Application

The Multithreaded Chat Application is a Java-based client–server application designed to enable real-time text communication between multiple users over a network. The primary objective of this project is to demonstrate the practical use of network programming, socket communication, and multithreading concepts in Java. By implementing this application, users can understand how concurrent client connections are handled efficiently by a server without blocking or interruption.

In this project, communication is established using TCP sockets, which provide reliable, ordered, and error-checked delivery of data between connected systems. The application follows a client–server architecture, where a central server manages multiple client connections, and each client can send and receive messages through the server.

System Architecture

The project consists of two main components:

Chat Server

Chat Client

The Chat Server acts as the core of the application. It listens for incoming client connection requests on a specific port number. When a client connects, the server accepts the connection and assigns a separate thread to handle that client. This multithreading approach allows the server to manage multiple clients simultaneously without causing delays or blocking other users.

Each connected client is managed using a ClientHandler thread. The server maintains a collection of all active client handlers. When one client sends a message, the server broadcasts that message to all other connected clients. This ensures real-time message sharing among participants.

The Chat Client is responsible for connecting to the server, sending messages entered by the user, and displaying messages received from the server. To achieve smooth communication, the client also uses multithreading. One thread continuously listens for incoming messages from the server, while the main thread handles user input. This prevents the client application from freezing while waiting for messages.

Working Principle:

The server starts and listens on a predefined port.

A client connects to the server using the server’s IP address and port number.

Upon connection, the server creates a new thread dedicated to that client.

Each client can send messages to the server.

The server receives the message and broadcasts it to all other connected clients.

Clients receive and display the messages in real time.

When a client disconnects, the server continues running and serves remaining clients.

Key Features:

Multithreading Support: Each client runs in an independent thread, enabling concurrent communication.

Real-Time Messaging: Messages are instantly delivered to connected users.

Client–Server Model: Centralized message handling through the server.

Scalable Design: Multiple clients can join without affecting server performance.

Console-Based Interface: Simple and lightweight implementation using command-line input/output.

Technologies Used

Programming Language: Java

Concepts:

Socket Programming

Multithreading

Input/Output Streams

Client–Server Architecture

Tools:

Java Development Kit (JDK)

VS Code / Command Prompt

Applications of the Project

This project provides a strong foundation for building:

Online chat systems

Collaborative tools

Messaging platforms

Multiplayer game communication modules

Real-time notification systems

Conclusion:

The Multithreaded Chat Application successfully demonstrates how Java can be used to build a reliable and scalable real-time communication system.

#OUTPUT#

<img width="1273" height="176" alt="Image" src="https://github.com/user-attachments/assets/62203c2e-afda-4628-8e10-7d442a70292e" />
