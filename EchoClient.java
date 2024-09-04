import java.io.*; 
import java.net.*;

class EchoClient {
	public static void main(String[] args) {
 	try {
		Socket socket = new Socket("localhost", 12345); // Connect to the server on localhost:12345
		// Create input and output streams for communication with the server 
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
		String message;

		while (true) {
			System.out.print("Enter a message (or 'exit' to quit): "); 
			message = userInput.readLine();
			if (message.equalsIgnoreCase("exit")) { 
				break;
			}
 
			out.println(message); // Send the message to the server
			@SuppressWarnings("unused")
			String response = in.readLine(); // Receive the server's response System.out.println("Server response: " + response);
		}//while

	      // Close the socket 
             socket.close();
	} //try

	catch (IOException e) { 
		e.printStackTrace();
	}//catch
	
	}//main
}//class
