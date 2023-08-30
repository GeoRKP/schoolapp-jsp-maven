package gr.aueb.cf.schoolapppro.controllers.user;

import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dao.user.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.user.UserDAOImpl;
import gr.aueb.cf.schoolapppro.service.user.IUserService;
import gr.aueb.cf.schoolapppro.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/schoolapppro/userdelete")
public class UserDeleteController extends HttpServlet {
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");

        try {
            userService.deleteUser(userId);
            req.setAttribute("username", username);
            req.getRequestDispatcher("/schoolapppro/static/templates/userdeleted.jsp").forward(req, resp);
        } catch (UserDAOException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/schoolapppro/static/templates/userdeleted.jsp").forward(req, resp);
        }
    }
}
