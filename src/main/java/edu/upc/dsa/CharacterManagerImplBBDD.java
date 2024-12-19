package edu.upc.dsa;

import edu.upc.dsa.exceptions.ItemRepeatedException;
import edu.upc.dsa.models.GameCharacter;
import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.SessionBD;

import java.util.LinkedList;
import java.util.List;

public class CharacterManagerImplBBDD implements CharacterManager {
    private static CharacterManager instance;
    SessionBD session;


    private CharacterManagerImplBBDD() {
        session = FactorySession.openSession();
    }

    public static CharacterManager getInstance() {
        if (instance==null) instance = new CharacterManagerImplBBDD();
        return instance;
    }

    public List<GameCharacter> getAllCharacters(){
      return (List<GameCharacter>)session.findAll(GameCharacter.class);
    };
    public GameCharacter addCharacter(int stealth, int speed, int strength, String name, double cost) throws ItemRepeatedException {
        GameCharacter gameCharacter1 = new GameCharacter(stealth, speed, strength, name, cost);
        if(session.get(GameCharacter.class,"name", name) != null) throw new ItemRepeatedException();
        session.save(gameCharacter1);
        return gameCharacter1;
    };

    public GameCharacter getCharacter(String name){
        return (GameCharacter)session.get(GameCharacter.class, "name", name);
    };


    public void clear(){
        session.deleteAll(GameCharacter.class);
    };
    public int size(){
      return session.findAll(GameCharacter.class).size();
    };

    public List<GameCharacter> findAll(){
        return (List<GameCharacter>)session.findAll(GameCharacter.class);
    }
}
