package gr.aueb.cf.schoolapppro.service.user;

import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.UserInsertDTO;
import gr.aueb.cf.schoolapppro.dto.UserRegisterDTO;
import gr.aueb.cf.schoolapppro.dto.UserLoginDTO;
import gr.aueb.cf.schoolapppro.dto.UserUpdateDTO;
import gr.aueb.cf.schoolapppro.model.User;

import java.util.List;

public interface IUserService {
    boolean isUserValid(UserLoginDTO userLoginDTO) throws UserDAOException;
    boolean insertUser(UserRegisterDTO userRegisterDTO) throws UserDAOException;
    User insertUser(UserInsertDTO userInsertDTO) throws UserDAOException;
    List<User> getUsersByUsername(String username) throws UserDAOException;

    User updateUser(UserUpdateDTO dto) throws  UserDAOException;

    public void deleteUser(Integer id) throws UserDAOException;

}
