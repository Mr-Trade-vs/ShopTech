package org.example.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientShop {
    
    private int port;
    private String ip;
    private Socket clientSocket;

    public ClientShop(int port, String ip) {
        this.port = port;
        this.ip = ip;

        try {
            this.clientSocket = new Socket(ip, port);
        } catch (IOException e) {
            System.out.println("Se ha presentado un error: " + e.getMessage());
        }
        
    }

    public void connection(String msg) {

        try {
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream()));

            br.write(msg);
            br.newLine();

            br.flush();
            

        } catch (IOException e) {
            System.out.println("The problem is: " + e.getMessage());;
        }
    }

    public void endCommunicationWithServer(BufferedWriter br, Socket clientSocket) {

        try {
            br.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al terminar la comunicación entre Cliente -> Servidor " + e.getMessage());
        }
    }
}
