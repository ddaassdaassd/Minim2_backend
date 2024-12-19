package edu.upc.dsa;

import edu.upc.dsa.models.*;
import edu.upc.dsa.orm.*;
import edu.upc.dsa.orm.SessionBD;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class ORMTEST {
    Item item1 = new Item("Cizalla","http://10.0.2.2:8080/itemsIcons/cizalla.png");
    Item item2 = new Item("Sierra Electrica","http://10.0.2.2:8080/itemsIcons/sierraelec.png");
    Item item3 = new Item("PelaCables2000","http://10.0.2.2:8080/itemsIcons/pelacables.png");
    Item item4 = new Item("Sierra","http://10.0.2.2:8080/itemsIcons/sierra.png");
    User u1 = new User("Blau", "Blau2002","emailBlau");
    User u2 = new User("Lluc", "Falco12","emailLluc");
    User u3 = new User("David", "1234","emailDavid");
    User u4 = new User("Marcel", "1234","marcel.guim@estudiantat.upc.edu");
    GameCharacter c1 = new GameCharacter(1, 1, 1, "primer", 10);
    GameCharacter c2 = new GameCharacter(1, 1, 1, "segon", 60);
    GameCharacter c3 = new GameCharacter(1, 1, 1, "tercer", 50);
    useritemcharacterrelation r1 = new useritemcharacterrelation();
    useritemcharacterrelation r2 = new useritemcharacterrelation();
    useritemcharacterrelation r3 = new useritemcharacterrelation();
    @Before
    public void setUp() {
        u4.setMoney(50);
        r1.setID_User(3);
        r1.setID_Item(1);
        r2.setID_User(3);
        r2.setID_GameCharacter(1);
        r3.setID_User(3);
        r3.setID_Item(4);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void TestAddUsers(){
        SessionBD session = FactorySession.openSession(); //url, user, password);
        //session.save(u1);
        //session.save(u2);
        session.save(u3);
        u4.setMoney(100);
        //session.save(u4);
    }
    @Test
    public void TestAddItems(){
        SessionBD session = FactorySession.openSession(); //url, user, password);
        session.save(item1);
        session.save(item2);
        session.save(item3);
        session.save(item4);
    }
    @Test
    public void TestAddCharacters(){
        SessionBD session = FactorySession.openSession(); //url, user, password);
        session.save(c1);
        session.save(c2);
        session.save(c3);
    }

    @Test
    public void TestAddRelations(){
        SessionBD session = FactorySession.openSession(); //url, user, password);
        session.save(r1);
        session.save(r2);
        session.save(r3);
        List<useritemcharacterrelation> relations = (List<useritemcharacterrelation>) session.findAll(r1.getClass());
        Assert.assertEquals(3,relations.size());
    }

    @Test
    public void TestDeleteRelations(){
        SessionBD session = FactorySession.openSession(); //url, user, password);
        session.deleteAll(r1.getClass());
        List<useritemcharacterrelation> relations = (List<useritemcharacterrelation>)session.findAll(r1.getClass());
        Assert.assertEquals(0,relations.size());
    }

    @Test
    public void TestGet(){
        SessionBD session = FactorySession.openSession(); //url, user, password);
        User u5 = (User)session.get(u1.getClass(), "ID",45);
        User u6 = (User)session.get(u1.getClass(),"name", "Marcel");
    }

    @Test
    public void TestDelete(){
        SessionBD session = FactorySession.openSession(); //url, user, password);
        session.delete(User.class,"name", u2.getName());
    }

    @Test
    public void TestUpdate(){
        SessionBD session = FactorySession.openSession();
        u1.setCobre(8673);
        session.update(u1,"name", u1.getName());
    }

    @Test
    public void TestFindAllUsers(){
        SessionBD session = FactorySession.openSession();
        List<User> users =(List<User>) session.findAll(u1.getClass());
    }

    @Test
    public void TestFindAllRelations(){
        SessionBD session = FactorySession.openSession();
        List<useritemcharacterrelation> relations = (List<useritemcharacterrelation>) session.findAll(r1.getClass());
    }

    @Test
    public void TestGetWithCorreu(){
        SessionBD session = FactorySession.openSession();
        User u43 = (User)session.get(u1.getClass(),"correo",u1.getCorreo());
    }

    @Test
    public void TestUpdateWithName(){
        SessionBD session = FactorySession.openSession();
        item1.setCost(3471);
        session.update(item1,"name",item1.getName());
    }

    @Test
    public void TestGenericGet(){
        SessionBD session = FactorySession.openSession();
        User u4 = (User)session.get(User.class, "name", "Marcel");
        Item i4 = (Item)session.get(Item.class,"name","Sierra");
        GameCharacter g4 = (GameCharacter)session.get(GameCharacter.class,"name","primer");
    }

    @Test
    public void FindAllWithCOnditions(){
        SessionBD session = FactorySession.openSession();
        HashMap<String,String> values = new HashMap<>();
        values.put("name =","Marcel");
        values.put("money >","50");
        List<User> users = (List<User>)session.findAllWithConditionsAND(User.class,values);
    }

    @Test
    public void TestDeleteAll(){
        SessionBD session = FactorySession.openSession();
        session.deleteAll(Item.class);
        session.deleteAll(User.class);
        session.deleteAll(GameCharacter.class);
        session.deleteAll(useritemcharacterrelation.class);
    }

    @Test
    public void TestGetItemsOfUser(){
        SessionBD session = FactorySession.openSession();
        List<GameCharacter> respuesta = (List<GameCharacter>)session.getRelaciones(GameCharacter.class,"name","Marcel");
        List<Item> respuesta2 = (List<Item>)session.getRelaciones(Item.class,"name","Marcel");

        int k = 12;
    }

    @Test
    public void AddComentariXat(){
        SessionBD session = FactorySession.openSession();
        session.save(new Forum("Marcel","hola que tal va tot?"));
        session.save(new Forum("Lluc","molt bé"));
        session.save(new Forum("Blau","jo també"));
    }

    @Test
    public void GetAllXats(){
        SessionBD session = FactorySession.openSession();
        List<Forum> xats = (List<Forum>) session.findAll(Forum.class);
        int k = 12;
    }

    @Test
    public void SaveChatIndividual(){
        SessionBD session = FactorySession.openSession();
        ChatIndividual chat1 = new ChatIndividual("Marcel","Lluc","Hey, com estas?");
        ChatIndividual chat2 = new ChatIndividual("Marcel","Blau","AAAAAAAAAAA");
        ChatIndividual chat3 = new ChatIndividual("Joan","Lluc","aekjkrjbg");
        session.save(chat1);
        session.save(chat2);
        session.save(chat3);
    }

    @Test
    public void GetNamesWithWhomIChat(){
        SessionBD session = FactorySession.openSession();
        HashMap<String, String> condiciones = new HashMap<>();
        condiciones.put("nameTo = ","Marcel");
        condiciones.put("nameFrom = ","Marcel");
        List<ChatIndividual> respuesta1 = (List<ChatIndividual>) session.findAllWithConditionsOR(ChatIndividual.class, condiciones);
        int k = 12;
    }
    @Test
    public void GetChatIndividual(){
        SessionBD session = FactorySession.openSession();
        HashMap<String,String> condiciones = new HashMap<>();
        condiciones.put("nameFrom = ","Marcel");
        condiciones.put("nameTo =  ","Blau");
        List<ChatIndividual> respuesta = (List<ChatIndividual>) session.findAllWithConditionsAND(ChatIndividual.class, condiciones);
        int k = 12;
    }

}
