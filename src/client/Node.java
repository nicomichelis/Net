package client;

import structs.Connection;

public class Node implements Runnable{
    private int id;
    private int port;
    private boolean exit;
    private NodeReceiver receiver;
    private String value;
    private Netw connections;
    
    public Node() {
        System.out.println("Node error 001");
    }
    
    public Node(int id) {
        this.id = id;
        this.exit = false;
        this.value = "";
        this.connections = new Netw();
        this.receiver = new NodeReceiver(this);
        this.port = this.receiver.getPort();
    }

    public Node(int id, int port) {
        this.id = id;
        this.port = port;
        this.exit = false;
        this.value = "";
        this.connections = new Netw();
        this.receiver = new NodeReceiver(port, this);
    }
    
    @Override
    public void run() {
        Thread receiverThread = new Thread(this.receiver);
        receiverThread.start();
        System.out.println("Node active " + this.id);
    }
    
    public synchronized void setValue(String receivedValue) {
        this.value = receivedValue;
    }

    public int getID() {
        return this.id;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public Netw getNet() {
        return this.connections;
    }
    
    public boolean connectNode(String addr, int port) {
        return this.connections.connectNode(addr, port);
    }
    
}
