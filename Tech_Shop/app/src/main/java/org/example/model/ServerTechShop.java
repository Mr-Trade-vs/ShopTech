package org.example.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTechShop {
    
    private ExecutorService pool;
    private ServerSocket serverSocket;

    private int solutionToClient;

    public ServerTechShop(ExecutorService pool, ServerSocket serverSocket) {
        this.pool = pool;
        this.serverSocket = serverSocket;
        this.solutionToClient = 0;
    }

    public void execute() {

        try {
            if (pool == null) this.pool = Executors.newFixedThreadPool(3);
            if (serverSocket == null) this.serverSocket = new ServerSocket(5000);

            pool.execute(new ClientsHandler(serverSocket.accept()));

        } catch (IOException e) {
            pool.shutdown();
            System.out.println("Surgio un error: " + e.getMessage());
        }
    }

    public int validateRequestClient(String msg) {
        switch (msg) {
            case "1":
                return this.solutionToClient = 1;
        
            case "2":
                return this.solutionToClient = 2;

            case "3":
                return this.solutionToClient = 3;
        }
        return 0;
    }
}
