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
	
	public boolean pushValue(Value value) {
		// Select a random node from the list
		Random rand = new Random();
		int r = rand.nextInt(node.size());
		// Push value to the selected node
		node.get(r).onReceive(value);
		return true;
	}
        
        public synchronized Value getValue() {
            // Select a random node from the list
		Random rand = new Random();
		int r = rand.nextInt(node.size());
                // Get value from that random node
                return node.get(r).getValue();
        }
        
        public void copy(List<Node> n) {
            for (Node n1 : n) {
                this.node.add(n1);
            }
        }
}
