package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.GameCharacter;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StoreManagerImpl implements StoreManager {
    private static StoreManager instance;
    protected HashMap<String,List<Item>> itemsOfUsers; //Key=user name
    protected HashMap<String,List<User>> usersOfItems; //Key=item id
    protected HashMap<String,List<GameCharacter>> charactersOfUsers; //Key=user name
    protected HashMap<String,List<User>> usersOfCharacter; //Key=character name
    protected List<GameCharacter> gameCharacters;
    protected List<User> users;
    protected List<Item> items;
    final static Logger logger = Logger.getLogger(StoreManagerImpl.class);
    private UserManager um;
    private ItemManager im;
    private CharacterManager cm;

    private StoreManagerImpl() {
        this.usersOfItems = new HashMap<>();
        this.itemsOfUsers = new HashMap<>();
        this.charactersOfUsers = new HashMap<>();
        this.usersOfCharacter = new HashMap<>();
        this.gameCharacters = new ArrayList<>();
        this.users = new ArrayList<>();
        this.items = new ArrayList<>();
        this.im = ItemManagerImpl.getInstance();
        this.um = UserManagerImpl.getInstance();
        this.cm = CharacterManagerImpl.getInstance();
    }

    public static StoreManager getInstance() {
        if (instance==null) instance = new StoreManagerImpl();
        return instance;
    }
    public void addAllUsers(List<User> u)
    {
        users = u;
        for(User us: u){
            itemsOfUsers.computeIfAbsent(us.getName(), k -> new ArrayList<>());
            charactersOfUsers.computeIfAbsent(us.getName(), k -> new ArrayList<>());
        }
    }
    public void addAllItems(List<Item> i)
    {
        items = i;
        for(Item it : i){
            usersOfItems.computeIfAbsent(it.getName(), k -> new ArrayList<>());
        }
    }
    public void addAllCharacters(List<GameCharacter> c){
        gameCharacters = c;
        for(GameCharacter ca : c){
            usersOfCharacter.computeIfAbsent(ca.getName(), k -> new ArrayList<>());
        }
    }

    public void addUser(User user){
        users.add(user);
        itemsOfUsers.put(user.getName(),new ArrayList<>());
    };
    public void addItem(Item item){
        items.add(item);
        usersOfItems.put(item.getName(),new ArrayList<>());
    };


    public List<Item> BuyItemUser(String ItemName, String nameUser) throws UserNotFoundException, ItemNotFoundException, NotEnoughMoneyException, UserHasNoItemsException {
        User u = um.getUserFromUsername(nameUser);
        if (u==null) throw new UserNotFoundException();
        Item i = im.getItem(ItemName);
        if (i == null) throw new ItemNotFoundException();
        if(u.getMoney()>=i.getCost()){
            itemsOfUsers.get(u.getName()).add(i);
            usersOfItems.get(ItemName).add(u);
            u.setMoney(u.getMoney()-i.getCost());
            logger.info(u.getName()+" HA COMPRADO "+i.getName());
            try{
                return getItemsUserCanBuy(u);
            } catch (UserHasNoItemsException e) {
                throw new UserHasNoItemsException();
            }
        }
        else throw new NotEnoughMoneyException();
    };

    public List<GameCharacter> BuyCharacter(String nameUser, String nameCharacter) throws UserNotFoundException, CharacterNotFoundException, NotEnoughMoneyException, UserHasNoCharacterException{
        GameCharacter c = cm.getCharacter(nameCharacter);
        if (c== null) throw new CharacterNotFoundException();
        User u = um.getUserFromUsername(nameUser);
        if (u == null) throw new UserNotFoundException();
        if(u.getMoney()>=c.getCost()){
            charactersOfUsers.get(u.getName()).add(c);
            usersOfCharacter.get(c.getName()).add(u);
            u.setMoney(u.getMoney()-c.getCost());
            try{
                return getCharacterUserCanBuy(u);
            } catch (UserHasNoCharacterException e) {
                throw new UserHasNoCharacterException();
            }
        }
        else throw new NotEnoughMoneyException();
    }

    public List<Item> getItemUser(String userName) throws UserNotFoundException, UserHasNoItemsException{
        if(itemsOfUsers.get(userName) ==null) throw new UserNotFoundException();
        if(itemsOfUsers.get(userName).size() == 0) throw new UserHasNoItemsException();
        return itemsOfUsers.get(userName);
    };

    public List<GameCharacter> getCharacterUser(String userName) throws UserNotFoundException, UserHasNoCharacterException{
        if(charactersOfUsers.get(userName) ==null) throw new UserNotFoundException();
        if(charactersOfUsers.get(userName).size() == 0) throw new UserHasNoCharacterException();
        return charactersOfUsers.get(userName);
    }
    public void clear(){
        itemsOfUsers.clear(); //Key=user name
        usersOfItems.clear(); //Key=item id
        charactersOfUsers.clear(); //Key=user name
        usersOfCharacter.clear(); //Key=character name
        gameCharacters.clear();
        users.clear();
        items.clear();
    }

    public List<Item> getItemsUserCanBuy(User u) throws NotEnoughMoneyException, UserHasNoItemsException{
        //Ha de retornar una llista que exclogui els objectes que ja ha comprat
        List<Item> userItems = itemsOfUsers.get(u.getName());
        if (userItems == null) {
            userItems = new ArrayList<>();
        }
        List<Item> itemsNotBoughtByUser = new ArrayList<>();
        for (Item item : items) {
            if (!userItems.contains(item)) {
                itemsNotBoughtByUser.add(item);
            }
        }
        if(itemsNotBoughtByUser.isEmpty()){
            throw new UserHasNoItemsException();
        }
        else{
            for (Item i : itemsNotBoughtByUser) {
                logger.info("Item not bought by user: " + i.getName());
            }
            return itemsNotBoughtByUser;
        }

    };
    public List<GameCharacter> getCharacterUserCanBuy(User u) throws NotEnoughMoneyException,UserHasNoCharacterException{
        //Ha de retornar una llista que exclogui els characters que ja ha comprat/aconseguit
        List<GameCharacter> userCharacters = charactersOfUsers.get(u.getName());
        if (userCharacters == null) {
            userCharacters = new ArrayList<>();
        }
        List<GameCharacter> charactersNotBoughtByUser = new ArrayList<>();
        for (GameCharacter character : gameCharacters) {
            if (!userCharacters.contains(character)) {
                charactersNotBoughtByUser.add(character);
            }
        }
        if(charactersNotBoughtByUser.isEmpty()){
            throw new UserHasNoCharacterException();
        }
        else{
            for (GameCharacter c : charactersNotBoughtByUser) {
                logger.info("Characters not bought by user: " + c.getName());
            }
            return charactersNotBoughtByUser;
        }
    };

}
