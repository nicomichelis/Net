package client;

import java.util.ArrayList;
import java.util.List;

public class NodeLauncher {
    private static final int MAXNODES = 2;
    public static void main(String args[]){
        List<Node> totalNodes = new ArrayList<Node>();
        for (int i = 0; i < MAXNODES; i++) {
            try {
                Node n = new Node(i,2000+i);
                totalNodes.add(n);
                Thread nThread = new Thread(n);
                nThread.start();
            } catch (Exception e) {
                System.out.println("Exception NodeLauncher 001");
                e.printStackTrace();
            }
        }
    }
}
