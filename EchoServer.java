import java.io.*; 
import java.net.*;

class EchoServer {
	public static void main(String[] args) { 
	try {
		ServerSocket serverSocket = new ServerSocket(12345); // Create a server socket on port 12345
		System.out.println("Echo Server is running..."); 
		while (true) {
			Socket clientSocket = serverSocket.accept(); // Wait for a client to connect
			System.out.println("Client connected: " + clientSocket.getInetAddress());
			// Create input and output streams for communication with the client 
                	BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); 
                	String message;
			while ((message = in.readLine()) != null) {
				System.out.println("Received: " + message);
				out.println("Server: " + message); // Echo the message back to the client
			}//inner while
 
		// Close the client socket 
		serverSocket.close();
		}//outer while
	}//try 
	catch (IOException e) { 
		e.printStackTrace();
	}//catch
	}//main
}//class


