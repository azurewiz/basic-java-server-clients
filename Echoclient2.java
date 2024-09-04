import java.io.*;
import java.net.*;

class Echoclient2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Connect to the server on localhost:12345
            System.out.println("Connected to the server");

            // Create input and output streams for communication with the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Thread to handle receiving messages from the server
            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Server: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // Thread to handle sending messages to the server
            Thread sendThread = new Thread(() -> {
                try {
                    BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                    String message;
                    while ((message = userInput.readLine()) != null) {
                        out.println(message);
                        // Do not print the client's message here, only the server should see it
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

            // Close the socket
            socket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
