package client;

import java.util.ArrayList;
import java.util.Random;
import structs.Connection;

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
        System.out.println("Connected " + addr + ":"+ port);
        return connections.add(conn);
    }
    
    public boolean sendValue(String value) {
        int node = rand.nextInt(connections.size());
        this.connections.get(node).sendValue(value);
        return false;
    }
}
