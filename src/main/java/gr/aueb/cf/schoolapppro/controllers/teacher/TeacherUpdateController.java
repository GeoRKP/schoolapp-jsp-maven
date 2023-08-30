package gr.aueb.cf.schoolapppro.controllers.teacher;

import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dao.speciality.ISpecialityDAO;
import gr.aueb.cf.schoolapppro.dao.speciality.SpecialityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.teacher.ITeacherDAO;
import gr.aueb.cf.schoolapppro.dao.teacher.TeacherDAOImpl;
import gr.aueb.cf.schoolapppro.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapppro.model.Speciality;
import gr.aueb.cf.schoolapppro.model.Teacher;
import gr.aueb.cf.schoolapppro.service.speciality.ISpecialityService;
import gr.aueb.cf.schoolapppro.service.speciality.SpecialityServiceImpl;
import gr.aueb.cf.schoolapppro.service.teacher.ITeacherService;
import gr.aueb.cf.schoolapppro.service.teacher.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/schoolapppro/teacherupdate")
public class TeacherUpdateController extends HttpServlet {
    private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);
    ISpecialityDAO specialityDAO = new SpecialityDAOImpl();
    ISpecialityService specialityService = new SpecialityServiceImpl(specialityDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Speciality> specialities = specialityService.getAllSpecialities();
            request.setAttribute("specialities", specialities);
        } catch (SpecialityDAOException e) {
            throw new RuntimeException(e);
        }

        // Get attributes from the URL
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer ssn = Integer.parseInt(request.getParameter("ssn"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        Integer specialityId = Integer.parseInt(request.getParameter("specialityid"));
        Integer userId = Integer.parseInt(request.getParameter("userid"));

        // Set attributes in request scope
        request.setAttribute("id", id);
        request.setAttribute("ssn", ssn);
        request.setAttribute("firstname", firstname);
        request.setAttribute("lastname", lastname);
        request.setAttribute("specialityid", specialityId);
        request.setAttribute("userid", userId);

        request.getRequestDispatcher("/schoolapppro/static/templates/teacherupdate.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer ssn = Integer.parseInt(req.getParameter("ssn"));
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        Integer specialityId = Integer.parseInt(req.getParameter("specialityid"));
        Integer userId = Integer.parseInt(req.getParameter("userid"));

        TeacherUpdateDTO dto = new TeacherUpdateDTO(id, ssn, firstname, lastname, specialityId, userId);

        try {
            Teacher teacher = teacherService.updateTeacher(dto);
            req.setAttribute("teacher", teacher);
            req.getRequestDispatcher("/schoolapppro/static/templates/teacherupdated.jsp").forward(req, resp);
        } catch (TeacherDAOException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/schoolapppro/static/templates/teacherupdated.jsp").forward(req, resp);
        }

    }
}
