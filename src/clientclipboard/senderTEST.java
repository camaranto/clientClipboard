/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientclipboard;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author carlos
 */
public class senderTEST {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("192.168.56.1",8080);
        ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
        os.writeUTF("TEXT/");
        os.writeUTF("hello gay");
        os.close();
        client.close();
    }
}
