package client;

public class NodeLauncher {
	public static void main(String args[]){
		try {
			Node n = new Node();
			Thread nThread = new Thread(n);
			nThread.start();
		} catch (Exception e) {
			System.out.println("Exception 001 - Thread Start");
			e.printStackTrace();
		}
	}
}
