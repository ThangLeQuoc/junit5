package com.github.thanglequoc.junit5.junit5;

public class Fish {
    private String name;
    private int price;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setPrice(int price) {
        if (price < 0) throw new IllegalArgumentException("Illegal Price");
        this.price = price;
    }
    
    public int getPrice() {
        return price;
    }
    
}
