package calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

public class ServerThread extends Thread {
	Socket client;
	String session;
	Date lastAction;
	OutputStreamWriter writer;
	BufferedReader reader;

	ServerThread(Socket socket, Date time) throws IOException {
		client = socket;
		lastAction = time;
		session = Long.toString(lastAction.getTime());
		writer = new OutputStreamWriter(client.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
	}

	public void run() {
		try {
			System.out.println("\r\nAccepted\r\nSession:" + session);
			writer.write("Write your example like:5 + 5\r\n");
			writer.flush();

			String request;
			do {
				request = reader.readLine();
				this.updateLastAction();
				writer.write(new Calculator().Calc(request) + "\r\n");
				writer.flush();
			} while (!request.equals("exit"));

			writer.close();
			reader.close();
			client.close();
		} catch (Exception e) {
			System.out.println("Fail thread:" + e.getMessage());
		}

	}

	public void bb() {
		try {
			writer.write("\r\nYou were inactive for 1 minute, bye bye :C");
			writer.flush();
			writer.close();
			reader.close();
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fail:" + e.getMessage());
		}
	}

	public void updateLastAction() {
		lastAction = new Date();
	}
}
