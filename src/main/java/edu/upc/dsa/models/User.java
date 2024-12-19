package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class User {
    int ID;
    String name;
    String password;
    String correo;
    double money;
    double cobre;

    public User() {
    }

    public User(String user, String password, String correo) {
        this();
        this.setName(user);
        this.setPassword(password);
        this.setCorreo(correo);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }


    public double getCobre() {
        return cobre;
    }

    public void setCobre(double cobre) {
        this.cobre = cobre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "User [user=" + name + ", password=" + password +"]";
    }

}
