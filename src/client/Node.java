package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import structs.Connection;
import structs.Value;

public class Node implements Runnable {
	private Value value = null;
	private Connection connectedNodes = null;
	
	public Node() {
		this.value = new Value();
		this.connectedNodes = new Connection();
	}
	
	public Node(Node node) {
		this.value = node.value;
		this.connectedNodes = node.connectedNodes;
	}
	
	public void onReceive(Value value) {
		if (this.value.time < value.time) {
			this.value.Copy(value);
		}
	}
	
	public void onTimeout() {
		connectedNodes.push(this.value);	
	}

	@Override
	public void run() {
		InputStreamReader reader = null;
		BufferedReader buffer = null;
		while (true) {
			reader = new InputStreamReader(System.in);
			buffer = new BufferedReader(reader);
			String line = "";
			try {
				line = buffer.readLine();
			} catch (Exception e) {
				System.out.println("Exception 001 - Input buffer");
				e.printStackTrace();
			}
			System.out.println(line);
			int v = -1;
			if (line.equals("quit"))
				break;
			else
				try {
					v = Integer.parseInt(line);
				} catch (Exception e) {
					System.out.println("Exception 002 - Parsing int value");
					e.printStackTrace();
					break;
				}
			this.value.newValue(v);
			System.out.print("Value: " + this.value.value + " Time: " + this.value.time);
		}
		
	}	
}
