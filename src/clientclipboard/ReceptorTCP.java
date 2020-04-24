/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientclipboard;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anakim
 */
public class ReceptorTCP extends Thread {
    
    private final String LOCAL_IP;
    private final String LOCAL_HOST_NAME;
    private boolean isEnabled;
    private ServerSocket serverSocket;
    private int PORT;
    private String TEXT;
    private messageHandler caller;
    public ReceptorTCP(int port, messageHandler caller) throws UnknownHostException{
        this.LOCAL_IP = InetAddress.getLocalHost().getHostAddress();
        this.LOCAL_HOST_NAME = InetAddress.getLocalHost().getHostName();
        this.isEnabled = false;
        this.PORT = port;
        this.caller = caller;
    }
    
    public void enableReception(){
        isEnabled = true;
        this.start();
    }
    public void disableReception(){
        isEnabled = false;
    }
    
    @Override
    public void run(){
        try {
            this.serverSocket=new ServerSocket(PORT);
            while(isEnabled){
                try (Socket receivedSocket = serverSocket.accept()) {
                    InputStream IS =  receivedSocket.getInputStream();
                    
                    switch(new String(IS.readNBytes(4))){
                        case "TEXT":
                            byte[] load = IS.readAllBytes();
                            caller.TextMessageReceiveFromClient(receivedSocket, load);
                            break;
                        case "FILE":
                            
                            break;
                        case "IMG":
                            
                            break;
                        default:
                            System.out.println("trash");
                            
                    }
                    IS.close();
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ReceptorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public String getLOCAL_IP() {
        return LOCAL_IP;
    }

    public String getLOCAL_HOST_NAME() {
        return LOCAL_HOST_NAME;
    }
    
    public String getReceivedText(){
        return TEXT;
    }
    
            
}
