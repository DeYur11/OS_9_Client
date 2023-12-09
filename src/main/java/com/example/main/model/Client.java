package com.example.main.model;

import java.io.Serializable;
import java.net.Socket;

public class Client implements Serializable {
    private Socket clientSocket;

    public Client(Socket clientSocket) {

        this.clientSocket = clientSocket;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

}
