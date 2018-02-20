package structs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import client.Node;

public class Connection {
	public List<Node> node = null;
	
	public Connection() {
		this.node = new ArrayList<Node>();
	}
	
	public boolean connectNode(Node node) {
		return this.node.add(node);
	}
	
	public boolean push(Value value) {
		//Select random node from list and push the value to it
		Random rand = new Random();
		int r = rand.nextInt(node.size());
		node.get(r).onReceive(value);
		return true;
	}

}
