/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prog.distribuida.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author Administrador
 */
public class TCPServiceManager extends Thread implements TCPServiceManagerCallerInterface{
    
    ServerSocket serverSocket;
    private int port;
    private TCPServiceManagerCallerInterface caller;
    boolean isEnabled=true;
    Vector<ClientSocketManager> clients=new Vector<ClientSocketManager>();
    final int NUMBER_OF_THREADS=50;
    
    public TCPServiceManager(int port, 
            TCPServiceManagerCallerInterface caller) {
        this.port = port;
        this.caller = caller;

    }

    @Override
    public void run(){
        try{
            this.serverSocket=new ServerSocket(port);
            while(this.isEnabled){
                //clients.add( new ClientSocketManager( serverSocket.accept(),this));
                Socket receivedSocket= serverSocket.accept();
                
            }
        }catch(Exception error){
            this.caller.ErrorHasBeenThrown(error);
        }
    }

    @Override
    public void MessageReceiveFromClient(Socket clientSocket, byte[] data) {
        
    }

    @Override
    public void ErrorHasBeenThrown(Exception error) {
        
    }
    
    
    
    
    
}
