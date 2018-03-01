package client;

import structs.Connection;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Node implements Runnable{
    private int id;
    private int port;
    private boolean exit;
    private NodeReceiver receiver;
    private String value;
    private ArrayList<Connection> connections;
    
    public Node() {
        System.out.println("Node error 001");
    }

    public Node(int id, int port) {
        this.id = id;
        this.port = port;
        this.exit = false;
        this.value = "";
        this.connections = new ArrayList<Connection>();
        this.receiver = new NodeReceiver(port, this);
    }
    
    @Override
    public void run() {
        Thread receiverThread = new Thread(this.receiver);
        receiverThread.start();
        System.out.println("Node active " + this.id);
        boolean test = true;
        while (!exit) {
            // TODO fai cose
            // TEST
            if (this.id == 0 && test) {
                connectNode("127.0.0.1",2001);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
                }
                sendValue();
            }
            if (this.id == 1 && test) {
                connectNode("127.0.0.1",2000);
                this.value = "LILS";
                sendValue();
            }
            if (this.id == 0 && test) {
                connectNode("127.0.0.1",2001);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.value="EHU";
                sendValue();
            }
            if (this.id == 1 && test) {
                connectNode("127.0.0.1",2000);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
                }
                sendValue();
            }
            test = false;
            // ENDTEST
        }
    }
    
    public synchronized void setValue(String receivedValue) {
        this.value = receivedValue;
    }

    public int getID() {
        return this.id;
    }
    
    public boolean sendValue() {
        // TODO mando valore alla rete
        this.connections.get(0).sendValue(this.value);
        return false;
    }
    
    public boolean connectNode(String addr, int port) {
        Connection conn = new Connection(addr, port);
        return connections.add(conn);
    }
}
