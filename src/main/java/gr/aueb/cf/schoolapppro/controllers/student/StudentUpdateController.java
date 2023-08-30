package gr.aueb.cf.schoolapppro.controllers.student;

import gr.aueb.cf.schoolapppro.dao.city.CityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.city.ICityDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.dao.student.IStudentDAO;
import gr.aueb.cf.schoolapppro.dao.student.StudentDAOImpl;
import gr.aueb.cf.schoolapppro.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapppro.model.City;
import gr.aueb.cf.schoolapppro.model.Student;
import gr.aueb.cf.schoolapppro.service.city.CityServiceImpl;
import gr.aueb.cf.schoolapppro.service.city.ICityService;
import gr.aueb.cf.schoolapppro.service.student.IStudentService;
import gr.aueb.cf.schoolapppro.service.student.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/schoolapppro/studentupdate")
public class StudentUpdateController extends HttpServlet {
    private final IStudentDAO studentDAO = new StudentDAOImpl();
    private final IStudentService studentService = new StudentServiceImpl(studentDAO);
    ICityDAO cityDAO = new CityDAOImpl();
    ICityService cityService = new CityServiceImpl(cityDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<City> cities = cityService.getAllCities();
            request.setAttribute("cities", cities);
        } catch (CityDAOException e) {
            throw new RuntimeException(e);
        }

        // Get attributes from the URL
        Integer id = Integer.parseInt(request.getParameter("id"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String gender = request.getParameter("gender");
        String birthdate = request.getParameter("birthdate");
        Integer cityId = Integer.parseInt(request.getParameter("cityid"));
        Integer userId = Integer.parseInt(request.getParameter("userid"));

        // Set attributes in request scope
        request.setAttribute("id", id);
        request.setAttribute("firstname", firstname);
        request.setAttribute("lastname", lastname);
        request.setAttribute("gender", gender);
        request.setAttribute("birthdate", birthdate);
        request.setAttribute("cityid", cityId);
        request.setAttribute("userid", userId);

        request.getRequestDispatcher("/schoolapppro/static/templates/studentupdate.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String unformattedBirthdate = req.getParameter("birthdate");

        Integer id = Integer.parseInt(req.getParameter("id"));
        String firstname = req.getParameter("firstname").trim();
        String lastname = req.getParameter("lastname").trim();
        char gender = req.getParameter("gender").trim().charAt(0);
        LocalDate birthdate = LocalDate.parse(unformattedBirthdate, formatter);
        Integer cityId = Integer.parseInt(req.getParameter("cityid").trim());
        Integer userId = Integer.parseInt(req.getParameter("userid").trim());

        StudentUpdateDTO dto = new StudentUpdateDTO(id, firstname, lastname, gender, birthdate, cityId, userId);

        try {
            Student student = studentService.updateStudent(dto);
            req.setAttribute("student", student);
            req.getRequestDispatcher("/schoolapppro/static/templates/studentupdated.jsp").forward(req, resp);
        } catch (StudentDAOException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/schoolapppro/static/templates/studentupdated.jsp").forward(req, resp);
        }


    }
}
