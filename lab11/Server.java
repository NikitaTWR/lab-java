package calc;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			List<ServerThread> threads = new LinkedList<>();

			Closer closer = new Closer(threads);
			closer.start();

			ServerSocket serverSocket = new ServerSocket(8888);
			while (true) {
				ServerThread thread = new ServerThread(serverSocket.accept(), new Date());
				if (thread.client.isConnected())
				{
					threads.add(thread);
					thread.start();
				}
			}
				
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// serverSocket.close();
	}

}
