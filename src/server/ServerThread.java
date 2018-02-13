package server;

import java.net.Socket;

public class ServerThread implements Runnable {
	private Socket client = null;
	
	public ServerThread(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		System.out.println("Yeas");
	}

}
