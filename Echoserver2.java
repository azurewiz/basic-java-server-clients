import java.io.*;
import java.net.*;

class Echoserver2 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Create a server socket on port 12345
            System.out.println("Echo Server is running...");

            Socket clientSocket = serverSocket.accept(); // Wait for a client to connect
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            // Create input and output streams for communication with the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Thread to handle receiving messages from the client
            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Client: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // Thread to handle sending messages to the client
            Thread sendThread = new Thread(() -> {
                try {
                    BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
                    String message;
                    while ((message = serverInput.readLine()) != null) {
                        out.println(message);
                        // Do not print the server's message here, only the client should see it
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            receiveThread.start();
            sendThread.start();

            // Wait for the threads to finish (which they won't until the chat ends)
            receiveThread.join();
            sendThread.join();

            // Close the client socket
            clientSocket.close();
            serverSocket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
