package structs;

import java.util.ArrayList;
import java.util.Random;

public class Netw {
    private ArrayList<Connection> connections;
    private Random rand;
    
    public Netw() {
        this.connections = new ArrayList<>();
        this.rand = new Random();
    }
    
    public boolean connectNode(String addr, int port) {
        // TODO controllo se già inserito nodo
        Connection conn = new Connection(addr, port);
        return connections.add(conn);
    }
    
    public boolean sendValue(String value) {
        int node = rand.nextInt(connections.size());
        return this.connections.get(node).sendValue(value);
        /*for (Connection n: this.connections) {
            n.sendValue(value);
        }
        return true;
        */
    }
}
