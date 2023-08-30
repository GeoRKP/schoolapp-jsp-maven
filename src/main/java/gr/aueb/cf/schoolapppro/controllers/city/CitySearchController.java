package gr.aueb.cf.schoolapppro.controllers.city;

import gr.aueb.cf.schoolapppro.dao.city.CityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.city.ICityDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.model.City;
import gr.aueb.cf.schoolapppro.service.city.CityServiceImpl;
import gr.aueb.cf.schoolapppro.service.city.ICityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shcoolapppro/citysearch")
public class CitySearchController extends HttpServlet {
    private final ICityDAO cityDAO = new CityDAOImpl();
    private final ICityService cityService = new CityServiceImpl(cityDAO);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<City> cities = cityService.getAllCities();
            req.setAttribute("cities", cities);
            req.getRequestDispatcher("/schoolapppro/static/templates/cities.jsp").forward(req, resp);
        } catch (CityDAOException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/schoolapppro/static/templates/cities.jsp").forward(req, resp);
        }
    }
}
