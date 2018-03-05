package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
                System.out.println("NodeLauncher exception 001");
                e.printStackTrace();
            }
        }
        
        List<Integer> initialPorts = new ArrayList<>();
        for (Node nod:totalNodes) {
            initialPorts.add(nod.getPort());
        }
        for (Node nod:totalNodes) {
            for (int i=0; i<initialPorts.size();i++) {
                nod.connectNode("127.0.0.1",initialPorts.get(i));
            }
        }
        while (true) {
            System.out.println("Insert: ");
            InputStreamReader reader = new InputStreamReader(System.in);
            BufferedReader buffer = new BufferedReader(reader);
            String line = "";
            try {
                line = buffer.readLine();
            } catch (IOException ex) {
                System.out.println("NodeLauncher exception 002");
                break;
            }
            switch (line) {
                case "quit":
                    System.exit(-1);
                    break;
                case "get":
                    System.out.println("Node: 0.." + (totalNodes.size()-1));
                    InputStreamReader reader2 = new InputStreamReader(System.in);
                    BufferedReader buffer2 = new BufferedReader(reader2);
                    int node = 0;
                    String line2 = "";
                    try {
                        line2 = buffer.readLine();
                        node = Integer.parseInt(line2);
                    } catch (IOException ex) {
                        System.out.println("NodeLauncher exception 003");
                        break;
                    }
                    System.out.println(totalNodes.get(node).getValue());
                    break;
                default:
                    System.out.println("Node: 0.." + (totalNodes.size()-1));
                    InputStreamReader reader3 = new InputStreamReader(System.in);
                    BufferedReader buffer3 = new BufferedReader(reader3);
                    int node3 = 0;
                    String line3 = "";
                    try {
                        line3 = buffer.readLine();
                        node = Integer.parseInt(line3);
                    } catch (IOException ex) {
                        System.out.println("NodeLauncher exception 004");
                        break;
                    }
                    totalNodes.get(node).getNet().sendValue(line);
            }    
        }
    }
}
