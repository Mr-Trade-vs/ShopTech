package org.example.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientShop {
    
    private int port;
    private String ip;
    private Socket clientSocket;
    private BufferedWriter wr;
    private BufferedReader rd;

    public ClientShop(int port, String ip) {
        this.port = port;
        this.ip = ip;

        try {
            this.clientSocket = new Socket(ip, port);
            this.wr = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            this.rd = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Se ha presentado un error: " + e.getMessage());
        }
    }

    public void sendMessage(String msg) {
        try {
            wr.write(msg);
            wr.newLine();
            wr.flush();
        } catch (IOException e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
    }

    public String receiveMessage() {
        try {
            return rd.readLine();
        } catch (IOException e) {
            System.out.println("Error receiving message: " + e.getMessage());
            return null;
        }
    }

    public void endCommunication() {
        try {
            wr.close();
            rd.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al terminar la comunicacion: " + e.getMessage());
        }
    }
}
