package org.example.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

import org.example.model.ClientShop;
import org.example.model.ServerTechShop;

public class ControllerCommunication {
    
    private ClientShop client;
    private ServerTechShop server;

    public void startClient(String ip) {
        client = new ClientShop(5000, ip);
    }

    public void startCommunicationServer(int connections) {
        try {
            server = new ServerTechShop(Executors.newFixedThreadPool(connections), new ServerSocket(5000));
            server.execute();
        } catch (IOException e) {
            System.out.println("Ha surgido un error: " + e.getMessage());
        }
    }

    public void sendToServer(String msg) {
        if (client != null) client.sendMessage(msg);
    }

    public String receiveFromServer() {
        if (client != null) return client.receiveMessage();
        return null;
    }

    public void endClient() {
        if (client != null) client.endCommunication();
    }
}
