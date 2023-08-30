package gr.aueb.cf.schoolapppro.controllers.user;

import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dao.user.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.user.UserDAOImpl;
import gr.aueb.cf.schoolapppro.dto.UserInsertDTO;
import gr.aueb.cf.schoolapppro.model.User;
import gr.aueb.cf.schoolapppro.service.user.IUserService;
import gr.aueb.cf.schoolapppro.service.user.UserServiceImpl;
import gr.aueb.cf.schoolapppro.validator.ValidationProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/schoolapppro/userinsert")
public class UserInsertController extends HttpServlet {
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserInsertDTO dto = new UserInsertDTO(username, password);

        boolean isValid = ValidationProvider.areUserInsertCredentialsValid(dto);

        if (isValid) {
            try {
                User user = userService.insertUser(dto);
                req.setAttribute("user", user);
                req.getRequestDispatcher("/schoolapppro/static/templates/userinserted.jsp").forward(req, resp);
            } catch (UserDAOException e) {
                req.setAttribute("error", true);
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("/schoolapppro/static/templates/userinserted.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", true);
            req.setAttribute("message", "Invalid User Credentials");
            req.getRequestDispatcher("/schoolapppro/static/templates/userinserted.jsp").forward(req, resp);
        }
    }
}
