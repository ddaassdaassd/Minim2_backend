package edu.upc.dsa;

import edu.upc.dsa.exceptions.ItemNotFoundException;
import edu.upc.dsa.models.Item;

import java.util.LinkedList;
import java.util.List;

import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

public class ItemManagerImpl implements ItemManager {
    private static ItemManager instance;
    protected List<Item> items;
    final static Logger logger = Logger.getLogger(ItemManagerImpl.class);

    private ItemManagerImpl() {
        this.items = new LinkedList<>();
    }

    public static ItemManager getInstance() {
        if (instance==null) instance = new ItemManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.items.size();
        logger.info("size " + ret);

        return ret;
    }

    public Item addItem(Item i) {
        logger.info("new Item " + i);

        this.items.add (i);
        logger.info("new Item added");
        return i;
    }

    public Item addItem(String name,String url) {
        return this.addItem(new Item(name,url));
    }

    public Item getItem(String Name)throws ItemNotFoundException {
        logger.info("getItem("+Name+")");

        for (Item i: this.items) {
            if (i.getName().equals(Name)) {
                logger.info("getItem("+Name+"): "+i);

                return i;
            }
        }
        logger.warn("not found " + Name);
        throw new ItemNotFoundException();
    }


    public List<Item> findAll() {
        return this.items;
    }

    @Override
    public void deleteItem(String Name) throws ItemNotFoundException {

        Item i = this.getItem(Name);
        if (i==null) {
            logger.warn("not found " + i);
        }
        else logger.info(i+" deleted ");

        this.items.remove(i);
    }

    @Override
    public Item updateItem(Item i)  throws ItemNotFoundException {
        Item t = this.getItem(i.getName());

        if (t!=null) {
            logger.info(i+" rebut!!!! ");

            t.setName(i.getName());
            t.setCost(i.getCost());
            logger.info(t+" updated ");
        }
        else {
            logger.warn("not found "+i);
        }

        return t;
    }

    public void clear() {
        this.items.clear();
    }

}