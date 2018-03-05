package client;

import java.util.Date;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.timer.Timer;
import structs.Netw;

public class Node implements Runnable, NotificationListener{
    private int id;
    private int port;
    private boolean exit;
    private NodeReceiver receiver;
    private String value;
    private Netw connections;
    
    private Timer timer;
    // TODO: fare parametrizzato
    private long ms = 2000;
    
    
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
        this.timer = new Timer();
    }

    public Node(int id, int port) {
        this.id = id;
        this.port = port;
        this.exit = false;
        this.value = "";
        this.connections = new Netw();
        this.receiver = new NodeReceiver(port, this);
        this.timer = new Timer();
    }
    
    @Override
    public void run() {
        Thread receiverThread = new Thread(this.receiver);
        receiverThread.start();
        this.timer.addNotificationListener(this, null, null);
        this.timer.addNotification("type", "message", this, 
                new Date(System.currentTimeMillis()), ms);
        this.timer.start();
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
    
    public String getValue() {
        return this.value;
    }
    
    public boolean connectNode(String addr, int port) {
        return this.connections.connectNode(addr, port);
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {
        this.getNet().sendValue(this.value);
    }

}
