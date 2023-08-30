package gr.aueb.cf.schoolapppro.service.user;

import gr.aueb.cf.schoolapppro.dao.user.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.UserInsertDTO;
import gr.aueb.cf.schoolapppro.dto.UserRegisterDTO;
import gr.aueb.cf.schoolapppro.dto.UserLoginDTO;
import gr.aueb.cf.schoolapppro.dto.UserUpdateDTO;
import gr.aueb.cf.schoolapppro.model.User;
import gr.aueb.cf.schoolapppro.security.SecUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserServiceImpl implements IUserService{
    private final IUserDAO dao;

    public UserServiceImpl(IUserDAO dao) {
        this.dao = dao;
    }

    @Override
    public boolean isUserValid(UserLoginDTO userLoginDTO) throws UserDAOException {
        User user = dao.getUserByUsername(userLoginDTO.getUsername());
        return (user != null) && (BCrypt.checkpw(userLoginDTO.getPassword(), user.getPassword()));
    }

    @Override
    public boolean insertUser(UserRegisterDTO userRegisterDTO) throws UserDAOException {
        User user = dao.insertUser(map(userRegisterDTO));
        if (user != null) {
            return true;
        }else {
            return false;
        }
    }

    public User insertUser(UserInsertDTO userInsertDTO) throws UserDAOException {
        User user = dao.insertUser(map(userInsertDTO));
        if (user != null) {
            return user;
        }else {
            return null;
        }
    }

    @Override
    public User updateUser(UserUpdateDTO dto) throws  UserDAOException {
        User user = map(dto);
        return dao.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) throws UserDAOException {
        dao.deleteUser(id);
    }

    @Override
    public List<User> getUsersByUsername(String username) throws UserDAOException {
        return dao.getUsersByUsername(username);
    }

    private User map(UserRegisterDTO userRegisterDTO) {
        User user = new User(null, userRegisterDTO.getUsername(), SecUtil.hashPassword(userRegisterDTO.getPassword()));
        return user;
    }

    private User map(UserInsertDTO userInsertDTO) {
        User user = new User(null, userInsertDTO.getUsername(), SecUtil.hashPassword(userInsertDTO.getPassword()));
        return user;
    }

    private User map(UserUpdateDTO dto) {
        User user = new User(dto.getId(), dto.getUsername(), SecUtil.hashPassword(dto.getPassword()));
        return user;
    }
}
