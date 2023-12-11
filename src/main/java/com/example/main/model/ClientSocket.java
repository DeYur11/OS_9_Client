package com.example.main.model;

import com.example.main.ClientListener;
import com.example.main.VoteWindow;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class ClientSocket {
    private static final byte END_WAITING = 66;
    private static final Integer END_OF_CLIENT = 55;
    private static final byte CONNECT_REQUEST = 1;
    private Socket socket;
    private ServerListenThread serverListenThread;
    private ClientListener clientListener;
    private ObjectOutputStream outStream;
    public ClientSocket(ClientListener clientListener){
        try {
            socket = new Socket("192.168.104.247", 150);
            socket.getOutputStream().write(CONNECT_REQUEST);
            socket.getOutputStream().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.clientListener = clientListener;
        // Важливо щоб спочатку був Output а потім Input

        serverListenThread = new ServerListenThread(socket, this);
        try {
            this.outStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        serverListenThread.start();
    }

    public void endOfAccepting(){
        clientListener.end();
        System.out.println("End");
    }

    public void startOfVote(){
        System.out.println(clientListener.getClass());
        clientListener.startVote();
    }

    public void sendIdea(Idea idea){
        try {
            System.out.println(idea);
            outStream.writeObject(idea);
            outStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void sendVoteResults(){
        try {

            this.clientListener.end();
            Thread.sleep(1000);
            Vector<Integer> selectedIdeas = new Vector<>();
            for(int i = 0; i< VoteWindow.getSelectedIdeas().size(); i++){
                selectedIdeas.add(VoteWindow.getSelectedIdeas().get(i).getIdeaID());
            }
            System.out.println(selectedIdeas);
            outStream.writeObject(selectedIdeas);
            outStream.flush();
            this.clientListener.nextStage();
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendFinalMessage(Vector<Idea> bestIdeas){
        System.out.println(this.clientListener.getClass());
        System.out.println(bestIdeas);
        this.clientListener.sendFinalMessage(bestIdeas);
    }
    public Socket getSocket() {
        return socket;
    }
    public void endOfWork(){
        this.clientListener.endWork();
    }
    public void sendDeathMessage(){
        try {
            this.outStream.writeObject(END_OF_CLIENT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setClientListener(ClientListener clientListener) {
        this.clientListener = clientListener;
    }
}
