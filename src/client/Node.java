package client;

import java.util.List;

import structs.Connection;
import structs.Value;

public class Node implements Runnable {
    private final int WAIT = 2000;
    private Value value = null;
    private Connection connectedNodes = null;
    private int id;

    public Node() {
        this.value = new Value();
        this.connectedNodes = new Connection();
        this.id = -1;
    }
    
    public Node(int id) {
        this.value = new Value();
        this.connectedNodes = new Connection();
        this.id = id;
    }

    public Node(Node node) {
        this.value = node.value;
        this.connectedNodes = node.connectedNodes;
        this.id = node.id;
    }

    public void onReceive(Value value) {
        if (this.value.time < value.time) {
            this.value.Copy(value);
        }
    }

    public boolean onTimeout() {
        Value temp = connectedNodes.getValue();
        if (this.value.time < temp.time) {
            this.value.Copy(temp);
            return true;
        }
        return false;
    }
    
    public Value getValue() {
        return this.value;
    }
    
    public void setConnections(List<Node> n) {
        this.connectedNodes.copy(n);
    }

    @Override
    public void run() {
        int j = 0;
        while (true) {
            j++;
            try {
                Thread.sleep(WAIT);
            } catch (InterruptedException ex) {
                System.out.println("Node exception 001");
            }
            System.out.println("N: "+this.id+" - v: "+this.value.value+" t: "+this.value.time);
            if (onTimeout()) {
               System.out.println("N: "+this.id+" - v: "+this.value.value+" t: "+this.value.time+" *"); 
            }
            if (this.id == 1 && j == 2) {
                this.value.newValue(2);
            }
            if (this.id == 0 && j == 4) {
                this.value.newValue(23);
            }
        }

    }
}


/* OLD run()
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
            if (line.equals("quit")) {
                break;
            } else {
                try {
                    v = Integer.parseInt(line);
                } catch (Exception e) {
                    System.out.println("Exception 002 - Parsing int value");
                    e.printStackTrace();
                    break;
                }
            }
            this.value.newValue(v);
            System.out.print("Value: " + this.value.value + " Time: " + this.value.time);
        }


*/