package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Item {
    int ID;
    String name;
    double cost;
    int velocidad;
    int forca;
    String item_url;

    public Item() {}

    public Item(String name, String url) {
        this();
        this.setName(name);
        this.setItem_url(url);
    }

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Item [name=" + name +"url: "+item_url+"]";
    }

}