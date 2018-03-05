package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import static java.lang.System.exit;
import java.net.ServerSocket;
import java.net.Socket;

/* This class will handle the incoming messages from other nodes */
public class NodeReceiver implements Runnable {
    private ServerSocket server;
    private int port;
    private boolean exit;
    private Node node;
    
    public NodeReceiver() {
        System.out.println("NodeReceiver Error 001");
        exit(-1);
    }
    
    public NodeReceiver(Node node) {
        exit = false;
        this.node = node;
        try {
            server = new ServerSocket(0);
            this.port = server.getLocalPort();
        } catch (IOException ex) {
            System.out.println("NodeReceiver Error 002 - Node: " + this.node.getID());
            ex.printStackTrace();
            exit(-1);
        }
    }
    
    public NodeReceiver(int port, Node node) {
        this.port = port;
        exit = false;
        this.node = node;
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println("NodeReceiver Error 003 - Node: " + this.node.getID());
            ex.printStackTrace();
            exit(-1);
        }
    }

    @Override
    public void run() {
        System.out.println("Receiver started for node " + node.getID() + " on port " + server.getLocalPort());
        InputStream is;
        ObjectInputStream ois;
        while (!exit) {
            try {
                Socket s = server.accept();
                is = s.getInputStream();
		ois = new ObjectInputStream(is);
                // TODO: ora è uno string, poi dovrà essere un value
                String receivedValue = (String) ois.readObject();
                node.setValue(receivedValue);
                System.out.println("Received value [" + receivedValue + "] at node [" + node.getID() + "]");
                s.close();
                // TODO: per debug soltanto
                if (receivedValue.equals("exit"))
                    exit = true;
            } catch (Exception ex) {
                System.out.println("NodeReceiver Error 003");
                ex.printStackTrace();
            }
        }
    }
    
    public int getPort() {
        return this.port;
    }
   
}
