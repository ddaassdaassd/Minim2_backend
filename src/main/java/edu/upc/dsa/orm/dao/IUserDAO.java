package edu.upc.dsa.orm.dao;

import edu.upc.dsa.models.*;

public interface IUserDAO {
    public int addUser(String name, String Password, String correu);
    public User getUser(int employeeID);
}
