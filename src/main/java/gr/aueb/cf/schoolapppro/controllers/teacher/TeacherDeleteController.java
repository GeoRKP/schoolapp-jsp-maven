package gr.aueb.cf.schoolapppro.controllers.teacher;

import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dao.teacher.ITeacherDAO;
import gr.aueb.cf.schoolapppro.dao.teacher.TeacherDAOImpl;
import gr.aueb.cf.schoolapppro.service.teacher.ITeacherService;
import gr.aueb.cf.schoolapppro.service.teacher.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/schoolapppro/teacherdelete")
public class TeacherDeleteController extends HttpServlet {
    private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer teacherId = Integer.parseInt(req.getParameter("id"));
        String lastname = req.getParameter("lastname");

        try {
            teacherService.deleteTeacher(teacherId);
            req.setAttribute("lastname", lastname);
            req.getRequestDispatcher("/schoolapppro/static/templates/teacherdeleted.jsp").forward(req, resp);
        } catch (TeacherDAOException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/schoolapppro/static/templates/teacherdeleted.jsp").forward(req, resp);
        }
    }
}
