package edu.upc.dsa;

import edu.upc.dsa.models.Item;
import edu.upc.dsa.exceptions.ItemNotFoundException;

import java.util.List;

public interface ItemManager {

    public Item addItem(String name, String url);
    //Funciona
    public Item addItem(Item i);
    //Funciona però falta retornar Exception
    public Item getItem(String name) throws ItemNotFoundException;
    //Funciona
    public List<Item> findAll();
    //Funciona
    public void deleteItem(String name) throws ItemNotFoundException;
    //Funciona
    public Item updateItem(Item i) throws ItemNotFoundException;
    //Funciona
    public void clear();
    //Funciona
    public int size();
    //Funciona
}
