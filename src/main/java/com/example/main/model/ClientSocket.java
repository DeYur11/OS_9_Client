package com.example.main.model;

import com.example.main.ClientListener;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket {
    private Socket socket;
    private ServerListenThread serverListenThread;
    private ClientListener clientListener;
    private ObjectOutputStream outStream;
    public ClientSocket(ClientListener clientListener){
        try {
            socket = new Socket("localhost", 150);
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
        clientListener.endAccepting();
        System.out.println("End");
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

    public Socket getSocket() {
        return socket;
    }
}
