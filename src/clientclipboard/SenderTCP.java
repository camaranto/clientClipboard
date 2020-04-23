/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientclipboard;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anakim
 */
public class SenderTCP {
    
    String RECEPTOR_IP;
    int RECEPTOR_PORT;
    private final int LOCAL_PORT;
    public static boolean isSending;
    private Socket client;
    
    
    public SenderTCP(String rIP, int rPORT){
        this.LOCAL_PORT = 8081;
        this.RECEPTOR_IP = rIP;
        this.RECEPTOR_PORT = rPORT;
        SenderTCP.isSending = false;
    }
    
    
    
    
    public void send(String type, Socket client, Object load){
        try {
            OutputStream os = client.getOutputStream();
            os.write((type + "/").getBytes());
            os.flush();
            sendHandler(type, load, os);
        } catch (IOException ex) {
            Logger.getLogger(SenderTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void sendHandler(String type, Object load, OutputStream os) throws IOException{
        switch(type){
            case "TEXT":
                os.write(load.toString().getBytes());
                os.flush();
                break;
            case "FILE":

                break;
            case "IMG":

                break;
            default:
                System.out.println("trash");
        }
    }
    
    public void close(){
        try {
            this.client.close();
        } catch (IOException ex) {
            Logger.getLogger(SenderTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean SYNC(String clientIP, int clientPORT){
        String HEADER = null;
        try {
            client = new Socket(clientIP, clientPORT);
            OutputStream OS = client.getOutputStream();
            OS.write("SYNC/".getBytes());
            byte[] header = new byte[100];
            client.getInputStream().read(header);
            HEADER = Base64.getEncoder().encodeToString(header).split("/")[0];
            OS.flush();
        } catch (IOException ex) {
            Logger.getLogger(SenderTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return HEADER.equals("SYNC");
        
    }
    

}
