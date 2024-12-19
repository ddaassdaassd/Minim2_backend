package edu.upc.dsa;

import edu.upc.dsa.exceptions.ItemNotFoundException;
import edu.upc.dsa.exceptions.UserRepeatedException;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.SessionBD;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class ItemManagerImplBBDD implements ItemManager {
    private static ItemManager instance;
    final static Logger logger = Logger.getLogger(ItemManagerImplBBDD.class);
    SessionBD session;
    public ItemManagerImplBBDD() {
        session = FactorySession.openSession();
    }

    public int size() {
        int ret = session.findAll(Item.class).size();
        logger.info("size " + ret);
        return ret;
    }

    public Item addItem(Item i) {
        logger.info("new Item " + i);
        if(session.get(i.getClass(),"name",i.getName()) == null)
        {
            session.save(i);
            logger.info("new Item added");
            return i;
        }
        else{
            logger.warn("Item already exists with that name");
            return null;
        }
    }

    public Item addItem(String name,String url) {
        return this.addItem(new Item(name,url));
    }

    public Item getItem(String Name)throws ItemNotFoundException {
        logger.info("getItem("+Name+")");
        Item i = (Item)session.get(Item.class,"name",Name);
        if(i!=null)return i;
        logger.warn("not found " + Name);
        throw new ItemNotFoundException();
    }


    public List<Item> findAll() {
        return session.findAll(Item.class);
    }

    @Override
    public void deleteItem(String Name) throws ItemNotFoundException {
        Item i = (Item)session.get(Item.class, "name",Name);
        if (i==null) {
            logger.warn("Item = " + Name + " not found");
        }
        else logger.info(i+" deleted ");
        session.delete(Item.class, "name",Name);
    }

    @Override
    public Item updateItem(Item i)  throws ItemNotFoundException {
        Item t = (Item) session.get(i.getClass(),"name",i.getName());
        if (t!=null) {
            logger.info(i+" rebut!!!! ");
            session.update(i,"name", i.getName());
            logger.info(t+" updated ");
        }
        else {
            logger.warn("not found "+i);
        }
        return i;
    }

    public void clear() {
        session.deleteAll(Item.class);
    }

}