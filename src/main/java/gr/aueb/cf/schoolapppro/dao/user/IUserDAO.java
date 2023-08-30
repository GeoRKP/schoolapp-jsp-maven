package gr.aueb.cf.schoolapppro.dao.user;

import gr.aueb.cf.schoolapppro.dao.dbutil.DBUtil;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    User insertUser (User user) throws UserDAOException;
    User updateUser (User user) throws UserDAOException;
    void deleteUser (int id) throws UserDAOException;
    boolean isUserValid(String username, String password) throws UserDAOException;
    User getUserByUsername(String username) throws  UserDAOException;
    List<User> getUsersByUsername(String username) throws UserDAOException;
    List<User> getAllUsers() throws UserDAOException;
}
