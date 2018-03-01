package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import static java.lang.System.exit;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import structs.Value;

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
        // TODO se non passo una porta se ne prende una libera e comunica su quale ascolta
        System.out.println("NodeReceiver Error 003");
    }
    
    public NodeReceiver(int port, Node node) {
        exit = false;
        this.node = node;
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println("NodeReceiver Error 002 - Node: " + this.node.getID());
            ex.printStackTrace();
            exit(-1);
        }
    }

    @Override
    public void run() {
        System.out.println("Receiver started for node " + node.getID() + " on port " + server.getLocalPort());
        InputStream is = null;
        ObjectInputStream ois = null;
        while (!exit) {
            try {
                Socket s = server.accept();
                System.out.println("Connection accepted");
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
}
