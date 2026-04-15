package org.example.model;

public class Product {
    
    private int id;
    private String name;
    private int stock;
    private double price;
    
    public Product(int id, String name, int stock, double price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double calculateTotalPrice(int totalRequest) {
        if (totalRequest > stock) {
            return 0;
        }

        return totalRequest * price;
    }

    public void reduceStock(int totalRequest) {

        if (totalRequest > stock) System.out.println("You're request it's bigger than stock");
        else this.stock =  this.stock - totalRequest;
    }

    @Override
    public String toString() {
        return  "_________________________\n" + 
                "Id: " + id + "\n" +
                "Nombre de Producto: " + name + "\n" +
                "Stock Actual: " + stock + "\n" +
                "Precio por Unidad: " + price + "\n" +
                "_________________________\n";
    }

}
