package client;

import java.util.ArrayList;
import java.util.List;
import structs.Connection;

public class NodeLauncher {
    private static final int MAXNODES = 4;
    public static void main(String args[]){
        List<Node> totalNodes = new ArrayList<Node>();
        for (int i = 0; i < MAXNODES; i++) {
            try {
                Node n = new Node(i);
                totalNodes.add(n);
                Thread nThread = new Thread(n);
                nThread.start();
            } catch (Exception e) {
                System.out.println("Exception NodeLauncher 001");
                e.printStackTrace();
            }
        }
        
        List<Integer> initialPorts = new ArrayList<>();
        for (Node nod:totalNodes) {
            initialPorts.add(nod.getPort());
            System.out.println(nod.getID() + " " + nod.getPort());
        }
        for (Node nod:totalNodes) {
            for (int i=0; i<initialPorts.size();i++) {
                nod.connectNode("127.0.0.1",initialPorts.get(i));
                System.out.println(nod.getID() + " " + nod.getPort());
            }
        }
        totalNodes.get(0).getNet().sendValue("KEEEK");
    }
}
