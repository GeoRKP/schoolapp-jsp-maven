package gr.aueb.cf.schoolapppro.controllers.teacher;

import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dao.student.IStudentDAO;
import gr.aueb.cf.schoolapppro.dao.teacher.ITeacherDAO;
import gr.aueb.cf.schoolapppro.dao.teacher.TeacherDAOImpl;
import gr.aueb.cf.schoolapppro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapppro.model.Student;
import gr.aueb.cf.schoolapppro.model.Teacher;
import gr.aueb.cf.schoolapppro.service.exceptions.StudentServiceException;
import gr.aueb.cf.schoolapppro.service.exceptions.TeacherServiceException;
import gr.aueb.cf.schoolapppro.service.teacher.ITeacherService;
import gr.aueb.cf.schoolapppro.service.teacher.TeacherServiceImpl;
import gr.aueb.cf.schoolapppro.validator.ValidationProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/schoolapppro/teachersearch")
public class TeacherInsertController extends HttpServlet {
    private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        Integer ssn = Integer.parseInt(req.getParameter("ssn"));
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        Integer specialityId = Integer.parseInt(req.getParameter("specialityid"));
        Integer userId = Integer.parseInt(req.getParameter("userid"));

        TeacherInsertDTO dto = new TeacherInsertDTO(ssn, firstname, lastname, specialityId, userId);

        boolean isValid = ValidationProvider.isTeacherValid(dto);

        if (isValid) {
            try {
                Teacher teacher = teacherService.insertTeacher(dto);
                req.setAttribute("teacher", teacher);
                req.getRequestDispatcher("/schoolapppro/static/templates/teacherinserted.jsp").forward(req,resp);
            } catch (TeacherDAOException | TeacherServiceException e) {
                req.setAttribute("error", true);
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("/schoolapppro/static/templates/teacherinserted.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", true);
            req.setAttribute("message", "Invalid teacher..");
            req.getRequestDispatcher("/schoolapppro/static/templates/teacherinserted.jsp").forward(req,resp);
        }

    }
}
