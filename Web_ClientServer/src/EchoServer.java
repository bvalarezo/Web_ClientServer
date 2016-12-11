import java.net.*;
import java.io.*;

public class EchoServer {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8080); // listen to port 8080
			System.out.println("Waiting Incoming Connection...");
			Socket sock = server.accept();
			BufferedReader instream = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			BufferedWriter outstream = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
			String strin = instream.readLine();
			if (strin.equals("Hello")) {
				outstream.write("Welcome");
			} else {
				outstream.write(strin);
			}
			instream.close();
			outstream.close();
			sock.close();
			System.out.println("Connection Closing...");
			server.close();
		} catch (Exception ex) {
			System.out.println("Error during I/O");
			ex.getMessage();
			ex.printStackTrace();
		}
	}
}