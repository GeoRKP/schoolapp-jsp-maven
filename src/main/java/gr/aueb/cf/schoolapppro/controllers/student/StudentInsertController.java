package gr.aueb.cf.schoolapppro.controllers.student;

import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.dao.student.IStudentDAO;
import gr.aueb.cf.schoolapppro.dao.student.StudentDAOImpl;
import gr.aueb.cf.schoolapppro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapppro.model.Student;
import gr.aueb.cf.schoolapppro.service.exceptions.StudentServiceException;
import gr.aueb.cf.schoolapppro.service.student.IStudentService;
import gr.aueb.cf.schoolapppro.service.student.StudentServiceImpl;
import gr.aueb.cf.schoolapppro.validator.ValidationProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/schoolapppro/studentinsert")
public class StudentInsertController extends HttpServlet {
    private final IStudentDAO studentDAO = new StudentDAOImpl();
    private final IStudentService studentService = new  StudentServiceImpl(studentDAO);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String unformattedBirthdate = req.getParameter("birthdate");

        String firstname = req.getParameter("firstname").trim();
        String lastname = req.getParameter("lastname").trim();
        char gender = req.getParameter("gender").trim().charAt(0);
        LocalDate birthdate = LocalDate.parse(unformattedBirthdate, formatter);
        Integer cityId = Integer.parseInt(req.getParameter("cityid").trim());
        Integer userId = Integer.parseInt(req.getParameter("userid").trim());

        StudentInsertDTO dto = new StudentInsertDTO(firstname, lastname, gender, birthdate, cityId, userId);

        Boolean isValid = ValidationProvider.isStudentValid(dto);

        if (isValid) {
            try {
                Student student = studentService.insertStudent(dto);
                req.setAttribute("student", student);
                req.getRequestDispatcher("/schoolapppro/static/templates/studentinserted.jsp").forward(req, resp);
            } catch (StudentDAOException | StudentServiceException e) {
                req.setAttribute("error", true);
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("/schoolapppro/static/templates/studentinserted.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", true);
            req.setAttribute("message", "Invalid student..");
            req.getRequestDispatcher("/schoolapppro/static/templates/studentinserted.jsp").forward(req, resp);
        }



    }
}
