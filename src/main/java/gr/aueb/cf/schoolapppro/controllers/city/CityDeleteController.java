package gr.aueb.cf.schoolapppro.controllers.city;

import gr.aueb.cf.schoolapppro.dao.city.CityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.city.ICityDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.dao.teacher.ITeacherDAO;
import gr.aueb.cf.schoolapppro.service.city.CityServiceImpl;
import gr.aueb.cf.schoolapppro.service.city.ICityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/schoolapppro/citydelete")
public class CityDeleteController extends HttpServlet {
    private final ICityDAO cityDAO = new CityDAOImpl();
    private final ICityService cityService = new CityServiceImpl(cityDAO);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer cityId = Integer.parseInt(req.getParameter("cityid"));
        String cityName = req.getParameter("name");

        try {
            cityService.deleteCity(cityId);
            req.setAttribute("name", cityName);
            req.getRequestDispatcher("/schoolapppro/static/templates/citydeleted.jsp").forward(req, resp);
        } catch (CityDAOException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/schoolapppro/static/templates/citydeleted.jsp").forward(req, resp);
        }
    }
}
