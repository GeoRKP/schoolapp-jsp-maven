package gr.aueb.cf.schoolapppro.controllers.speciality;

import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.dao.speciality.ISpecialityDAO;
import gr.aueb.cf.schoolapppro.dao.speciality.SpecialityDAOImpl;
import gr.aueb.cf.schoolapppro.dto.SpecialityInsertDTO;
import gr.aueb.cf.schoolapppro.model.Speciality;
import gr.aueb.cf.schoolapppro.service.speciality.ISpecialityService;
import gr.aueb.cf.schoolapppro.service.speciality.SpecialityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shcoolapppro/specialityinsert")
public class SpecialityInsertController extends HttpServlet {

    private final ISpecialityDAO specialityDAO = new SpecialityDAOImpl();
    private final ISpecialityService specialityService = new SpecialityServiceImpl(specialityDAO);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String specialityName = req.getParameter("speciality");

        SpecialityInsertDTO dto = new SpecialityInsertDTO(specialityName);

        try {
            Speciality speciality = specialityService.insertSpeciality(dto);
            req.setAttribute("speciality", speciality);
            req.getRequestDispatcher("/schoolapppro/static/templates/specialityinserted.jsp").forward(req, resp);
        } catch (SpecialityDAOException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/schoolapppro/static/templates/specialityinserted.jsp").forward(req, resp);
        }
    }
}
