package com.example.main.model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TestConnection {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 150);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        while (true){
            Scanner scanner = new Scanner(System.in);
            outputStream.writeObject(scanner.nextLine());
        }
    }
}
