package gr.aueb.cf.schoolapppro.controllers.speciality;

import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.dao.speciality.ISpecialityDAO;
import gr.aueb.cf.schoolapppro.dao.speciality.SpecialityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.student.IStudentDAO;
import gr.aueb.cf.schoolapppro.model.Speciality;
import gr.aueb.cf.schoolapppro.service.speciality.ISpecialityService;
import gr.aueb.cf.schoolapppro.service.speciality.SpecialityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shcoolapppro/allspecialities")
public class SpecialitySearchController extends HttpServlet {
    private final ISpecialityDAO specialityDAO = new SpecialityDAOImpl();
    private final ISpecialityService specialityService = new SpecialityServiceImpl(specialityDAO);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Speciality> specialities = specialityService.getAllSpecialities();
            req.setAttribute("specialities", specialities);
            req.getRequestDispatcher("/schoolapppro/static/templates/specialities.jsp").forward(req, resp);
        } catch (SpecialityDAOException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/schoolapppro/static/templates/specialities.jsp").forward(req, resp);
        }
    }
}
