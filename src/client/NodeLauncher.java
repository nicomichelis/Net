package client;

import java.util.ArrayList;
import java.util.List;

public class NodeLauncher {
    private static final int MAXNODES = 10;
    public static void main(String args[]){
        List<Node> totalNodes = new ArrayList<Node>();
        for (int i = 0; i < MAXNODES; i++) {
            try {
                Node n = new Node(i);
                totalNodes.add(n);
                Thread nThread = new Thread(n);
                nThread.start();
            } catch (Exception e) {
                System.out.println("Exception 001 - Thread Start");
                e.printStackTrace();
            }
        }
        for (int i = 0; i < MAXNODES; i++) {
            totalNodes.get(i).setConnections(totalNodes);
        }
    }
}
