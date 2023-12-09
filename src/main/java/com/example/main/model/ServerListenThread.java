package com.example.main.model;

import com.example.main.MainWindow;
import javafx.collections.ObservableList;
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
                else if(message instanceof Idea){
                    if(!(MainWindow.getIdeaList().get(Idea.ideaAmount-1).getIdeaID() == ((Idea) message).getIdeaID())){
                        Idea.ideaAmount++;
                        System.out.println("New idea arrived! Idea: "+ ((Idea) message).getIdeaText());
                        MainWindow.getIdeaList().add((Idea)message);
                    }
                    else{
                        System.out.println("Idea with this ID already exists! Idea: "+ ((Idea) message).getIdeaText());
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }



}
