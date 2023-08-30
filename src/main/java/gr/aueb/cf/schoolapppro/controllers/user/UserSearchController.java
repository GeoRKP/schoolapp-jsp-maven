package gr.aueb.cf.schoolapppro.controllers.user;

import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dao.user.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.user.UserDAOImpl;
import gr.aueb.cf.schoolapppro.model.User;
import gr.aueb.cf.schoolapppro.service.user.IUserService;
import gr.aueb.cf.schoolapppro.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/schoolapppro/usersearch")
public class UserSearchController extends HttpServlet {
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        try {
            List<User> users = userService.getUsersByUsername(username);
            req.setAttribute("users", users);
            req.getRequestDispatcher("/schoolapppro/static/templates/users.jsp").forward(req, resp);
        } catch (UserDAOException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/schoolapppro/static/templates/users.jsp").forward(req, resp);
        }
    }
}
