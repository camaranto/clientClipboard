/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientclipboard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public class receptorTEST {
    public static void main(String[] args) {
        try {
            int port = 8080;
            ServerSocket server = new ServerSocket(port);
            System.out.println("listening on port" + port);
            Socket client = server.accept();
            System.out.println("client from " + client.getInetAddress().getHostAddress());
            ObjectInputStream os = new ObjectInputStream(client.getInputStream());
            os.readUTF();
            System.out.println(os.readUTF());
            os.close();
            client.close();
            server.close();
        } catch (IOException ex) {
            Logger.getLogger(receptorTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
