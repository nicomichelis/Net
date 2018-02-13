package server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static int port = 4000;
	private static ServerSocket server;
	
	public static void main(String args[]){
		try {
			System.out.println("Server Started");
			server = new ServerSocket(port);
			while (true) {
				Socket s = server.accept();
				System.out.println("Connection accepted");
				ServerThread serverThread = new ServerThread(s);
				Thread serverThreadT = new Thread(serverThread);
				serverThreadT.start();
			}
		} catch (Exception e) {
			System.out.println("Exception 1");
			e.printStackTrace();
		}
	}
}
