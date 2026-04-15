package org.example.controller;

import java.util.LinkedList;

import org.example.model.Product;

public class ControllerProducts {
    
    LinkedList<Product> listProducts = new LinkedList<Product>();

    public String printProducts() {
        if (listProducts != null) {
            return printProducts(listProducts);
        } else return "List of products actually is empty";
    }

    private String printProducts(LinkedList<Product> products) {
        String listToPrint = "";
        if (products != null) {
            listToPrint += products.pop();
            printProducts(products);
        } return listToPrint;
    }

    public Product searchProduct(int id) {

        if (listProducts != null) return searchProduct(id, listProducts);
        else return null;
    }

    private Product searchProduct(int id, LinkedList<Product> products) {
        
        if (products != null) {
            Product currentProduct = products.pop();

            if (currentProduct.getId() == id) {
                return currentProduct;
            } else return searchProduct(id, products);

        } else return null;
    }

    /*
    I know this isn't the best way, but only to complete the task. We'll need
    some info in the system
    */
    public void generateManualProducts() {
        listProducts.addFirst(new Product(1, "TV SAMSUNG 75\" Pulgadas 190,5 cm 75U8000F 4K UHD LED Crystal Smart TV", 10, 3099900.00));
        listProducts.addFirst(new Product(2, "Ainiuniu Zd Racing 1:12 Brushless Rápido Rc Coches Para Adul", 20, 737539.00));
        listProducts.addFirst(new Product(3, "Consola Sony PlayStation 5 Slim Blanco 4K 825 GB Digital", 14, 2447000.00));
        listProducts.addFirst(new Product(4, "Notebook Gamer Hp Victus 15-fa2013dx - Intel Core I5 13420h - 16gb Ram (2x8gb) - Placa De Video Rtx 3050 - Ssd Nvme 512gb - Pantalla Ips 144 Hz - 15 Pulgadas", 5, 2999900.00));
        listProducts.addFirst(new Product(5, "Apple 2025 iPad Pro de 11 pulgadas (Wi-Fi, 256 GB, Vidrio estándar) - Negro espacial (M5)", 8, 4364900.00));
    }
}
