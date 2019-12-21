package calc;

import java.util.Date;
import java.util.List;

public class Closer extends Thread {
	List<ServerThread> threads;

	Closer(List<ServerThread> ts) {
		threads = ts;
	}

	@SuppressWarnings("deprecation")
	public void run() {
		try {
			while (true) {
				Thread.sleep(1000);
				long now = new Date().getTime();
				for (ServerThread thread : threads) {
					long milliseconds = now - thread.lastAction.getTime();
					if (milliseconds > 60000)
					{
						thread.bb();
						System.out.println("Session closed:" + thread.session + " Reason:Be away.");
						thread.stop();
						threads.remove(thread);
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println("Fail Closer:" + e.getMessage());
		}

	}

}
