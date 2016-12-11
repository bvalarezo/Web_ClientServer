import java.io.*;
import java.net.*;
import java.util.Date;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer implements Runnable {
	Socket csocket;

	MultiThreadServer(Socket csocket) {
		this.csocket = csocket;
	}

	public static void main(String args[]) throws Exception {
		ServerSocket ssock = new ServerSocket(8080);
		System.out.println("Listening");
		while (true) {
			Socket sock = ssock.accept();
			System.out.println("Connected");
			new Thread(new MultiThreadServer(sock)).start();
		}
	}

	public void run() {
		try {

			BufferedReader instream = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
			BufferedWriter outstream = new BufferedWriter(new OutputStreamWriter(csocket.getOutputStream()));
			boolean welcomed = false;
			//String strin = instream.readLine();
			String strin;
			while ((strin = instream.readLine()) != null) {
				if(welcomed == false){
					if (strin.equals("Hello")) {
						outstream.write("Welcome\n");
						outstream.flush();
						welcomed = true;
						continue;
					}
					else  {
						outstream.write("Not Welcomed\n");
						outstream.flush();
						continue;
					}
				}
				if (welcomed == true) {
					if (strin.equals("bye")) {
						outstream.write("bye\n");
						outstream.flush();
						welcomed = false;
						continue;
					}
					else {
					outstream.write(strin+"\n");
					outstream.flush();
					continue;
					}
				}
			}
			csocket.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}