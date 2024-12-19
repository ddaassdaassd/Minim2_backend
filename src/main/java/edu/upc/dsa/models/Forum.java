package edu.upc.dsa.models;

public class Forum {
    String name;
    String comentario;

    public Forum() {
    }

    public Forum(String name, String comentario) {
        this.name = name;
        this.comentario = comentario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
