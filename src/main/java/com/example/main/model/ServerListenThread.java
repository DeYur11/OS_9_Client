package com.example.main.model;

import tools.messages.EndAcceptingMessage;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerListenThread extends Thread{
    private ObjectInputStream ideaInputStream;
    private ClientSocket clientSocket;
    private Socket socket;
    public ServerListenThread(Socket socket, ClientSocket clientSocket) {

        this.clientSocket = clientSocket;
        this.socket = socket;
        try {
            ideaInputStream = new ObjectInputStream(socket.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        System.out.println("Listen started");
        while(true){
            try{
                Object message = ideaInputStream.readObject();
                System.out.println("Accec");
                if(message.getClass().equals(EndAcceptingMessage.class)){
                    clientSocket.endOfAccepting();
                }else if(message instanceof  String){
                    System.out.println("Message");
                    System.out.println((String)message);;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}