package structs;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Connection {
        private String address;
        private int port;
        
        public Connection() {
            System.out.println("Connection Error 001");
        }
        
        public Connection(String address, int port) {
            this.address = address;
            this.port = port;
        }
        
        // TODO: mandare un valore invece che una stringa
        public boolean sendValue(String value) {
            OutputStream os = null;			
            ObjectOutputStream oos = null;
            InetSocketAddress addr = new InetSocketAddress(this.address, this.port);
            Socket s = new Socket();
            try {
                s.connect(addr);
                os = s.getOutputStream();			
                oos = new ObjectOutputStream(os);
                oos.writeObject(value);
                oos.flush();
                s.close();
                System.out.println("Sent value [" + value + "] to "
                        + this.address + ":" + this.port);
            } catch (Exception ex) {
                System.out.println("Connection error 002");
                return false;
            }
            return true;
        }
}
