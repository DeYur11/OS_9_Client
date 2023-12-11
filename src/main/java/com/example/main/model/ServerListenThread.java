package com.example.main.model;

import com.example.main.MainWindow;
import com.example.main.VoteWindow;
import tools.messages.EndAcceptingMessage;

import tools.messages.StartVoteMessage;
import tools.messages.VoteResultMessage;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Vector;

public class ServerListenThread extends Thread{
    private ObjectInputStream ideaInputStream;
    private static final int END_OF_WORK = 66;
    private static final int END_OF_CLIENT = 55;
    private static final int CONNECT_REQUEST = 1;
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
                System.out.println("Message accepted");
                System.out.println(message.getClass());
                if(message.getClass().equals(EndAcceptingMessage.class)){
                    clientSocket.endOfAccepting();
                }else if(message instanceof  String){
                    System.out.println("Message");
                    System.out.println((String)message);;
                }else if(message instanceof Integer){
                    switch ((Integer) message) {
                        case END_OF_WORK -> socket.getOutputStream().write(END_OF_WORK);
                        case END_OF_CLIENT -> {
                            System.out.println("End of client");
                            return;
                        }
                    }

                }
                else if(message instanceof Idea){

                    if(MainWindow.getIdeaList().isEmpty()) {
                        Idea.ideaAmount++;
                        System.out.println("New idea arrived! Idea: "+ ((Idea) message).getIdeaText());
                        MainWindow.getIdeaList().add((Idea)message);
                    }else {
                        if((!(MainWindow.getIdeaList().get(Idea.ideaAmount-1).getIdeaID() == ((Idea) message).getIdeaID()))){
                            Idea.ideaAmount++;
                            System.out.println("New idea arrived! Idea: "+ ((Idea) message).getIdeaText());
                            Idea toAdd = (Idea)message;
                            toAdd.setIsVoted(false);
                            MainWindow.getIdeaList().add((Idea)message);
                        }
                        else{
                            System.out.println("Idea with this ID already exists! Idea: "+ ((Idea) message).getIdeaText());
                        }
                    }
                }
                else if(message instanceof StartVoteMessage){
                    System.out.println("Vote started!");
                    clientSocket.startOfVote();
                }
                else if(message instanceof VoteResultMessage){

                    clientSocket.sendVoteResults();
                    System.out.println("Vote results sent!");
                }
                else if(message instanceof Vector<?>){
                    System.out.println("Results received!");

                    Vector<Idea> ideasVector  = (Vector<Idea>) message;
                    System.out.println(ideasVector);
                    clientSocket.sendFinalMessage(ideasVector);
                }
            }
            catch (Exception e){
                System.out.println(e);
                System.out.println("Ended listen thread");
                clientSocket.endOfWork();
                return;
            }
        }
    }




}
