package gr.aueb.cf.schoolapppro.controllers.speciality;

import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.dao.speciality.ISpecialityDAO;
import gr.aueb.cf.schoolapppro.dao.speciality.SpecialityDAOImpl;
import gr.aueb.cf.schoolapppro.service.speciality.ISpecialityService;
import gr.aueb.cf.schoolapppro.service.speciality.SpecialityServiceImpl;
import gr.aueb.cf.schoolapppro.service.student.IStudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("schoolapppro/specialitydelete")
public class SpecialityDeleteController extends HttpServlet {
    private final ISpecialityDAO specialityDAO = new SpecialityDAOImpl();
    private final ISpecialityService specialityService = new SpecialityServiceImpl(specialityDAO);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer specialityId = Integer.parseInt(req.getParameter("id"));
        String specialityName = req.getParameter("name");

        try {
            specialityService.deleteSpeciality(specialityId);
            req.setAttribute("name", specialityName);
            req.getRequestDispatcher("/schoolapppro/static/templates/specialitydeleted.jsp").forward(req, resp);
        } catch (SpecialityDAOException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/schoolapppro/static/templates/specialitydeleted.jsp").forward(req, resp);
        }
    }
}
