package gr.aueb.cf.schoolapppro.controllers.menu;

import gr.aueb.cf.schoolapppro.dao.city.CityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.city.ICityDAO;
import gr.aueb.cf.schoolapppro.dao.speciality.ISpecialityDAO;
import gr.aueb.cf.schoolapppro.dao.speciality.SpecialityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.model.City;
import gr.aueb.cf.schoolapppro.model.Speciality;
import gr.aueb.cf.schoolapppro.service.city.CityServiceImpl;
import gr.aueb.cf.schoolapppro.service.city.ICityService;
import gr.aueb.cf.schoolapppro.service.speciality.ISpecialityService;
import gr.aueb.cf.schoolapppro.service.speciality.SpecialityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shcoolapppro/menu")
public class MenuController extends HttpServlet {
    ISpecialityDAO specialityDAO = new SpecialityDAOImpl();
    ISpecialityService specialityService = new SpecialityServiceImpl(specialityDAO);
    ICityDAO cityDAO = new CityDAOImpl();
    ICityService cityService = new CityServiceImpl(cityDAO);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Speciality> specialities = specialityService.getAllSpecialities();
            request.setAttribute("specialities", specialities);
        } catch (SpecialityDAOException e) {
            throw new RuntimeException(e);
        }

        try {
            List<City> cities = cityService.getAllCities();
            request.setAttribute("cities", cities);
        } catch (CityDAOException e) {
            throw new RuntimeException(e);
        }


        request.getRequestDispatcher("/schoolapppro/static/templates/menu.jsp").forward(request,response);
    }
}
