import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoClient {
	public static void main(String[] args)  {
		try {
			int port = 8080;
			Socket sock = new Socket("155.246.133.81", port);
			BufferedReader instream = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			BufferedWriter outstream = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
			BufferedReader userrequest = new BufferedReader(new InputStreamReader(System.in));
			String request = userrequest.readLine() + "\n";
			System.out.println("Sending Messages to the Server... : "+ request);
			System.out.println("Connecting to " + sock.getInetAddress() + " and port " + sock.getPort());
			System.out.println("Local Address :" + sock.getLocalAddress() +" Port : " + sock.getLocalPort());
			
	  		//write a message 
			outstream.write(request);
			outstream.flush();
			String response;
			
			while((response = instream.readLine() )!= null) {
				System.out.println("The server says: ");
				System.out.println(response);
				userrequest = new BufferedReader(new InputStreamReader(System.in));
				request = userrequest.readLine() + "\n";
				outstream.write(request);
				outstream.flush();
			}
			
			//write your code
			System.out.println("Connection Closing...");
			instream.close();
			outstream.close();
		   	sock.close();

		}
		catch (IOException ex) {
			System.out.println("Error during I/O");
			ex.getMessage();
			ex.printStackTrace();
		}
	}
}