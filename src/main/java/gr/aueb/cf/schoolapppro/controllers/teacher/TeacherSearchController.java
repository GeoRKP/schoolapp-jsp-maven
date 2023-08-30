package gr.aueb.cf.schoolapppro.controllers.teacher;

import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dao.teacher.ITeacherDAO;
import gr.aueb.cf.schoolapppro.dao.teacher.TeacherDAOImpl;
import gr.aueb.cf.schoolapppro.model.Teacher;
import gr.aueb.cf.schoolapppro.service.exceptions.TeacherServiceException;
import gr.aueb.cf.schoolapppro.service.teacher.ITeacherService;
import gr.aueb.cf.schoolapppro.service.teacher.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shcoolapppro/teachersearch")
public class TeacherSearchController extends HttpServlet {
    private final ITeacherDAO dao = new TeacherDAOImpl();
    private final ITeacherService service = new TeacherServiceImpl(dao);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String lastname = req.getParameter("lastname");

        try {
            List<Teacher> teachers = service.getTeachersListByLastname(lastname);
            req.setAttribute("teachers", teachers);
            req.getRequestDispatcher("/schoolapppro/static/templates/teachers.jsp").forward(req, resp);
        } catch (TeacherServiceException | TeacherDAOException e) {
            String message = e.getMessage();
            req.setAttribute("error", true);
            req.setAttribute("message", message);
            req.getRequestDispatcher("/schoolapppro/static/templates/teachers.jsp").forward(req, resp);
        }

    }
}
