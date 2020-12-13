/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruitservice.models;

import java.io.Serializable;

/**
 *
 * @author haamani
 */
public class Fruit implements Serializable {    
    private String name;
    private int price;
    private int quantity = 0;    

    public Fruit(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    } 

    @Override
    public String toString() {
        return "Fruit{" + "name=" + name + ", price=" + price + ", quantity=" + quantity + '}';
    }    
}
