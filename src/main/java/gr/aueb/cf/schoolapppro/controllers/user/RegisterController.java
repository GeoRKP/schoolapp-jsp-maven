package gr.aueb.cf.schoolapppro.controllers.user;

import gr.aueb.cf.schoolapppro.dao.user.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.user.UserDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.UserRegisterDTO;
import gr.aueb.cf.schoolapppro.service.user.IUserService;
import gr.aueb.cf.schoolapppro.service.user.UserServiceImpl;
import gr.aueb.cf.schoolapppro.validator.ValidationProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private final IUserDAO dao = new UserDAOImpl();
    private final IUserService service = new UserServiceImpl(dao);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String isError = request.getParameter("isError");

        if (isError != null && isError.equals("true")) {
            request.setAttribute("isError", isError);
        } else {
            request.setAttribute("isError", "false");
        }

        request.getRequestDispatcher("/schoolapppro/static/templates/register.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmationPassword = request.getParameter("confirmationpassword");

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO(username, password, confirmationPassword);

        boolean isUserValid = ValidationProvider.areUserRegisterCredentialsValid(userRegisterDTO);

        if (isUserValid) {
            try {
                service.insertUser(userRegisterDTO);
            } catch (UserDAOException e) {
                response.sendRedirect(request.getContextPath() + "/register?isError=true");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/register?isError=true");
        }
        response.sendRedirect(request.getContextPath() + "/login");

    }
}
