package org.example.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.example.model.Product;

public class ControllerProducts {
    
    private List<Product> listProducts = Collections.synchronizedList(new ArrayList<>());

    public String printProducts() {
        if (listProducts.isEmpty()) {
            return "List of products is currently empty";
        }

        String listToPrint = "";
        synchronized (listProducts) {
            for (Product p : listProducts) {
                listToPrint += p.toString();
            }
        }
        return listToPrint;
    }

    public Product searchProduct(int id) {
        synchronized (listProducts) {
            for (Product p : listProducts) {
                if (p.getId() == id) {
                    return p;
                }
            }
        }
        return null;
    }

    public void generateManualProducts() {
        listProducts.add(new Product(1, "TV SAMSUNG 75\" Pulgadas 190,5 cm 75U8000F 4K UHD LED Crystal Smart TV", 10, 3099900.00));
        listProducts.add(new Product(2, "Ainiuniu Zd Racing 1:12 Brushless Rapido Rc Coches Para Adul", 20, 737539.00));
        listProducts.add(new Product(3, "Consola Sony PlayStation 5 Slim Blanco 4K 825 GB Digital", 14, 2447000.00));
        listProducts.add(new Product(4, "Notebook Gamer Hp Victus 15-fa2013dx - Intel Core I5 13420h - 16gb Ram - RTX 3050 - SSD 512gb - 144 Hz - 15 Pulgadas", 5, 2999900.00));
        listProducts.add(new Product(5, "Apple 2025 iPad Pro de 11 pulgadas (Wi-Fi, 256 GB) - Negro espacial (M5)", 8, 4364900.00));
    }
}
