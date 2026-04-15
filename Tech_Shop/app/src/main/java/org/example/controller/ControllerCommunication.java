package org.example.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

import org.example.model.ClientShop;
import org.example.model.ServerTechShop;

public class ControllerCommunication {
    
    private ClientShop client;
    private ServerTechShop server;

    public void startClient() {

        client = new ClientShop(5000, "192.168.1.19");
    }

    public void startCommunicationServer(int connections) {

        try {
            server = new ServerTechShop(Executors.newFixedThreadPool(connections) , new ServerSocket(5000));
            server.execute();
        } catch (IOException e) {
            System.out.println("Ha surgido un error: " + e.getMessage());
        }   
    }

    /*
    At this points I see it's a good way modify the controller and separate to make
    ServerControllerCommunication | ClientControllerCommunication
    I'll make that after implement the request of the task :D
    */
    public void clientRequest(String msg) {

        if (client == null) client = new ClientShop(5000, "192.168.1.19");
        client.connection(msg);

    }
}
