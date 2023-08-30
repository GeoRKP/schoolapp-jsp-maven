package gr.aueb.cf.schoolapppro.controllers.user;


import gr.aueb.cf.schoolapppro.authentication.AuthenticationProvider;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.UserLoginDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String isError = request.getParameter("isError");

        if (isError != null && isError.equals("true")) {
            request.setAttribute("isError", isError);
        } else {
            request.setAttribute("isError", "false");
        }

        request.getRequestDispatcher("/schoolapppro/static/templates/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);

        boolean principleIsAuthenticated = false;
        try {
            principleIsAuthenticated = AuthenticationProvider.authenticate(userLoginDTO);
        } catch (UserDAOException e) {
            e.printStackTrace();
        }

        if (principleIsAuthenticated){
            HttpSession session = request.getSession(false);
            session.setAttribute("loginName", username);
            response.sendRedirect(request.getContextPath() + "/schoolapppro/menu");
        }else {
            response.sendRedirect(request.getContextPath() + "/login?isError=true");
        }
    }

}
