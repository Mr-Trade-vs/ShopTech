package org.example.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientsHandler implements Runnable {

    private Socket clientSocket;

    

    public ClientsHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }



    @Override
    public void run() {

        try {
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String msg = "";

            while ((msg = rd.readLine()) != null) {

                if (msg == "EXIT") {
                    rd.close();
                    clientSocket.close();
                }

                System.out.println(clientSocket.getInetAddress()+ "\n" +
                clientSocket.getPort() + "\n" +
                msg);

            }

        } catch (IOException e) {
            System.out.println("Surgio un problema: " + e.getMessage());
        }

    }


    
}
