package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import edu.upc.dsa.util.RandomUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class StoreManagerTest {
    StoreManager sm;
    ItemManager im;
    UserManager um;
    CharacterManager cm;

//    @Before
//    public void setUp() {
//        this.im = ItemManagerImpl.getInstance();
//        this.sm = StoreManagerImpl.getInstance();
//        this.um = UserManagerImpl.getInstance();
//        this.cm = CharacterManagerImpl.getInstance();
//        if (im.size() == 0) {
//            Item item1 = new Item("Cizalla","http://10.0.2.2:8080/itemsIcons/cizalla.png");
//            Item item2 = new Item("Sierra Electrica","http://10.0.2.2:8080/itemsIcons/sierraelec.png");
//            Item item3 = new Item("PelaCables2000","http://10.0.2.2:8080/itemsIcons/pelacables.png");
//            Item item4 = new Item("Sierra","http://10.0.2.2:8080/itemsIcons/sierra.png");
//            this.im.addItem(item1);
//            this.im.addItem(item2);
//            this.im.addItem(item3);
//            this.im.addItem(item4);
//            this.sm.addAllItems(this.im.findAll());
//            User u1 = new User("Blau", "Blau2002","emailBlau");
//            User u2 = new User("Lluc", "Falco12","emailLluc");
//            User u3 = new User("David", "1234","emailDavid");
//            User u4 = new User("Marcel", "1234","marcel.guim@estudiantat.upc.edu");
//            u4.setMoney(50);
//            this.cm.addCharacter(1, 1, 1, "primer", 10);
//            this.cm.addCharacter(1, 1, 1, "segon", 60);
//            this.cm.addCharacter(1, 1, 1, "tercer", 50);
//            this.sm.addAllCharacters(this.cm.findAll());
//            try {
//                this.um.addUser(u1);
//                this.um.addUser(u2);
//                this.um.addUser(u3);
//                this.um.addUser(u4);
//                this.sm.addAllUsers(this.um.findAll());
//            } catch (UserRepeatedException ex) {
//
//            }
//        }
//    }

    @After
    public void tearDown() {
        // És un Singleton
        this.sm.clear();
        this.cm.clear();
        this.um.clear();
        this.im.clear();
    }
    @Test
    public void listofUsers(){
//        List<User> users  = sm.listAllUsers();
//        Assert.assertEquals(4, users.size());
//        Assert.assertEquals("Blau", users.get(0).getName());
//        Assert.assertEquals("David", users.get(1).getName());
//        Assert.assertEquals("Lluc", users.get(2).getName());

    }
    @Test
    public void ItemUser(){
        List<Item> itemsUser;
        List<Item> itemsUser2;
        try{
            itemsUser = this.sm.BuyItemUser("s123", "Andreu");
            Assert.assertEquals(1, itemsUser.size());
            Assert.assertEquals("s123", itemsUser.get(0).getName());
            itemsUser2 = this.sm.BuyItemUser("s124", "Andreu");
            Assert.assertEquals(2, itemsUser2.size());
            Assert.assertEquals("s124", itemsUser2.get(1).getName());
        }
        catch(UserNotFoundException e){

        }
        catch(ItemNotFoundException ex){

        }
        catch(NotEnoughMoneyException ex){

        }
        catch(UserHasNoItemsException ex){

        }
    }
}
