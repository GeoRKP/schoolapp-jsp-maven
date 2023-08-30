package gr.aueb.cf.schoolapppro.authentication;

import gr.aueb.cf.schoolapppro.dao.user.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.user.UserDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.UserLoginDTO;
import gr.aueb.cf.schoolapppro.service.user.IUserService;
import gr.aueb.cf.schoolapppro.service.user.UserServiceImpl;

public class AuthenticationProvider {
    private static final IUserDAO dao = new UserDAOImpl();
    private static final IUserService USER_SERVICE = new UserServiceImpl(dao);

    public static boolean authenticate(UserLoginDTO dto) throws UserDAOException {
        return USER_SERVICE.isUserValid(dto);
    }
}
