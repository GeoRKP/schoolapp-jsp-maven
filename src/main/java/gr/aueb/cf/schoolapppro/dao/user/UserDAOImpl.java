package gr.aueb.cf.schoolapppro.dao.user;

import gr.aueb.cf.schoolapppro.dao.dbutil.DBUtil;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.model.User;
import gr.aueb.cf.schoolapppro.security.SecUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO{
    public User insertUser(User user) throws UserDAOException {
        String sql = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (?, ?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            String username = user.getUsername();
            String password = user.getPassword();

            ps.setString(1, username);
            ps.setString(2, password);

            DBUtil.beginTransaction();

            ps.executeUpdate();

            DBUtil.commitTransaction();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                int generatedId = 0;
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }

                user.setId(generatedId);
            }

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new UserDAOException("Sql Error with user" + user);
        }
        return user;
    }

    @Override
    public User updateUser(User user) throws UserDAOException {
        String sql = "UPDATE USERS SET USERNAME = ?, PASSWORD = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, user.getUsername());
            ps.setString(2, SecUtil.hashPassword(user.getPassword()));
            ps.setInt(3, user.getId());

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();


        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new UserDAOException("Error in update in user with username: " + user.getUsername());
        }
        return user;
    }

    @Override
    public void deleteUser(int id) throws UserDAOException {
        String sql = "DELETE FROM USERS WHERE ID = ?;";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new UserDAOException("Error in delete");
        }
    }

    @Override
    public boolean isUserValid(String username, String password) throws UserDAOException {
        String sql = "SELECT USERNAME, PASSWORD FROM WHERE USERNAME = ?;";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);){
            ResultSet rs;
            ps.setString(1, username);

            rs = ps.executeQuery();
            if (rs.next() && SecUtil.checkPassword(password, rs.getString("PASSWORD"))) {
                return true;

            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new UserDAOException("Login Error");
        }

    }

    @Override
    public User getUserByUsername(String username) throws UserDAOException {
        String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                User user = null;
                if (rs.next()) {
                    user = new User(rs.getInt("ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"));
                }
                return user;
            }
        } catch (SQLException e) {
            throw new  UserDAOException("Error Searching for User");
        }
    }
    @Override
    public List<User> getUsersByUsername(String username) throws UserDAOException {
        String sql = "SELECT * FROM USERS WHERE USERNAME LIKE ?";
        List<User> userList = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "%" + username + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User(rs.getInt("ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"));
                    userList.add(user);
                }
                return userList;
            }
        } catch (SQLException e) {
            throw new UserDAOException("Error Searching for Users");
        }
    }

    @Override
    public List<User> getAllUsers() throws UserDAOException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM USERS";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User(rs.getInt("ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"));
                userList.add(user);
            }

        } catch (SQLException e) {
            throw new UserDAOException("Error while retrieving all users");
        }

        return userList;
    }
}


