package calc;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			ServerSocket serverSocket = new ServerSocket(2003);
			while (true) {
				ServerThread thread = new ServerThread(serverSocket.accept());
				if (thread.client.isConnected())
					thread.start();
			}
				
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// serverSocket.close();
	}

}
