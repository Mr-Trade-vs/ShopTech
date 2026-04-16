package org.example.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.example.controller.ControllerProducts;

public class ClientsHandler implements Runnable {

    private Socket clientSocket;
    private ControllerProducts data;

    public ClientsHandler(Socket clientSocket, ControllerProducts data) {
        this.clientSocket = clientSocket;
        this.data = data;
    }

    @Override
    public void run() {

        try {

            BufferedReader rd = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String msg = "";

            while ((msg = rd.readLine()) != null) {

                if (msg.equals("EXIT")) {
                    rd.close();
                    wr.close();
                    clientSocket.close();
                    break;
                }

                String response = checkAndProcessRequest(msg, rd, wr);

                wr.write(response);
                wr.newLine();
                wr.flush();
            }

        } catch (IOException e) {
            System.out.println("Surgio un problema: " + e.getMessage());
        }
    }

    private String checkAndProcessRequest(String msg, BufferedReader rd, BufferedWriter wr) throws IOException {
        switch (msg) {
            case "1":
                return data.printProducts();

            case "2":
                wr.write("Enter the product ID to search:");
                wr.newLine();
                wr.flush();

                String idInput = rd.readLine();
                int id = Integer.parseInt(idInput);
                Product found = data.searchProduct(id);

                if (found != null) {
                    return found.toString();
                } else {
                    return "Product not found.";
                }

            case "3":
                wr.write("Enter the product ID to buy:");
                wr.newLine();
                wr.flush();

                String buyIdInput = rd.readLine();
                int buyId = Integer.parseInt(buyIdInput);
                Product toBuy = data.searchProduct(buyId);

                if (toBuy == null) {
                    return "Product not found.";
                }

                wr.write(toBuy.toString());
                wr.newLine();
                wr.write("Enter the quantity you want to buy:");
                wr.newLine();
                wr.flush();

                String qtyInput = rd.readLine();
                int qty = Integer.parseInt(qtyInput);

                wr.write("Total to pay: $" + toBuy.calculateTotalPrice(qty) + ". Confirm purchase? (yes/no):");
                wr.newLine();
                wr.flush();

                String confirm = rd.readLine();

                if (confirm.equalsIgnoreCase("yes")) {
                    return procesarCompra(toBuy, qty);
                } else {
                    return "Purchase cancelled.";
                }

            default:
                return "Invalid option. Please send 1, 2, 3 or EXIT.";
        }
    }

    private synchronized String procesarCompra(Product product, int qty) {
        double total = product.calculateTotalPrice(qty);

        if (total == 0) {
            return "Not enough stock. Available: " + product.getStock();
        }

        boolean success = product.reduceStock(qty);

        if (success) {
            return "Purchase successful! You paid $" + total;
        } else {
            return "Purchase failed. Stock changed during your request.";
        }
    }
}
