package client;

import java.net.InetSocketAddress;
import java.net.Socket;

public class Node {
	private static int port = 4000;
	private static String ipaddr = "127.0.0.1";
	
	public static void main(String[] args) {
		InetSocketAddress addr = new InetSocketAddress(ipaddr, port);
		Socket s = new Socket();
		try {
			s.connect(addr);
			System.out.println("Connesso");
			Thread.sleep(1000);
			s.close();
			System.out.println("Disconnesso");
		} catch (Exception e) {
			System.out.println("Exception 2");
			e.printStackTrace();
		}
	}
}
