package model;

import com.example.os_9_client.ClientListener;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket {
    private Socket socket;
    private ServerListenThread serverListenThread;
    private ClientListener clientListener;

    public ClientSocket(ClientListener clientListener){
        try {
            socket = new Socket("localhost", 999);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.clientListener = clientListener;
        // Важливо щоб спочатку був Output а потім Input

        serverListenThread = new ServerListenThread(socket, this);
        try {
            ObjectOutputStream some = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        serverListenThread.start();
    }

    public void endOfAccepting(){
        clientListener.endAccepting();
        System.out.println("End");
    }
}