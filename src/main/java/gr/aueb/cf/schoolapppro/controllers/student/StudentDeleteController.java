package gr.aueb.cf.schoolapppro.controllers.student;

import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.dao.student.IStudentDAO;
import gr.aueb.cf.schoolapppro.dao.student.StudentDAOImpl;
import gr.aueb.cf.schoolapppro.service.student.IStudentService;
import gr.aueb.cf.schoolapppro.service.student.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("schoolapppro/studentdelete")
public class StudentDeleteController extends HttpServlet {
    private final IStudentDAO studentDAO = new StudentDAOImpl();
    private final IStudentService studentService = new StudentServiceImpl(studentDAO);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer studentId = Integer.parseInt(req.getParameter("id"));
        String lastname = req.getParameter("lastname");

        try {
            studentService.deleteStudent(studentId);
            req.setAttribute("lastname", lastname);
            req.getRequestDispatcher("/schoolapppro/static/templates/studentdeleted.jsp").forward(req, resp);
        } catch (StudentDAOException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/schoolapppro/static/templates/studentdeleted.jsp").forward(req, resp);

        }

    }
}
