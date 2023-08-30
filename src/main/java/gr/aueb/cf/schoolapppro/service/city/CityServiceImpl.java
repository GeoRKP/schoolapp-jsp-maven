package gr.aueb.cf.schoolapppro.service.city;

import gr.aueb.cf.schoolapppro.dao.city.ICityDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.dto.CityInsertDTO;
import gr.aueb.cf.schoolapppro.dto.CityUpdateDTO;
import gr.aueb.cf.schoolapppro.model.City;

import java.util.List;

public class CityServiceImpl implements ICityService {
    private final ICityDAO dao;

    public CityServiceImpl(ICityDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<City> getAllCities() throws CityDAOException {
        return dao.getAllCities();
    }

    @Override
    public City insertCity(CityInsertDTO dto) throws CityDAOException {
        City city = map(dto);
        return dao.insertCity(city);
    }

    @Override
    public void deleteCity(Integer cityId) throws CityDAOException {
        dao.deleteCity(cityId);
    }

    @Override
    public City updateCity(CityUpdateDTO dto) throws CityDAOException {
        City city = map(dto);
        return dao.updateCity(city);
    }

    City map(CityInsertDTO dto) {
        City city = new City(null,dto.getCityName());
        return city;
    }

    City map(CityUpdateDTO dto) {
        City city = new City(dto.getId(), dto.getCityName());
        return city;
    }
}
