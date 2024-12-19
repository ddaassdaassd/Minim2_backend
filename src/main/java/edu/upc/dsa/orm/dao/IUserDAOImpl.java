package edu.upc.dsa.orm.dao;

import edu.upc.dsa.models.*;
import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.SessionBD;

public class IUserDAOImpl implements IUserDAO{
    public int addUser(String name, String password, String correu) {
        SessionBD sessionBD = null;
        int UserID = 2;
        try {
            sessionBD = FactorySession.openSession();
            User user = new User(name, password, correu);
            sessionBD.save(user);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            sessionBD.close();
        }

        return UserID;
    };
    public User getUser(int UserID){
        SessionBD sessionBD = null;
        User user = null;
        try {
            sessionBD = FactorySession.openSession();
            user = (User) sessionBD.get(User.class,"ID", UserID);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            sessionBD.close();
        }

        return user;
    };
}
