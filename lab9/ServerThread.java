package calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket client;
	String session;

	ServerThread(Socket socket) throws IOException {
		client = socket;
	}

	public void run() {
		try {
			System.out.println("\r\nAccepted");
			OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String request;
			do {

				writer.write("Write your example like:5 + 5\r\n");
				writer.flush();

				request = reader.readLine();

				writer.write(new Calculator().Calc(request) + "\r\n");
				writer.flush();
			} while (!request.equals("exit"));

			writer.close();
			reader.close();
			client.close();
		} catch (Exception e) {
			System.out.println("Fail:" + e.getMessage());
		}

	}
}
