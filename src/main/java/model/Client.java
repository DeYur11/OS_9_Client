package model;

import java.net.Socket;

public class Client {
    private Socket clientSocket;

    public Client(Socket clientSocket) {

        this.clientSocket = clientSocket;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

}
