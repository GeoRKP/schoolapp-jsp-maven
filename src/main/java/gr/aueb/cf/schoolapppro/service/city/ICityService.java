package gr.aueb.cf.schoolapppro.service.city;

import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.dto.CityInsertDTO;
import gr.aueb.cf.schoolapppro.dto.CityUpdateDTO;
import gr.aueb.cf.schoolapppro.model.City;

import java.util.List;

public interface ICityService {
    List<City> getAllCities() throws CityDAOException;
    City insertCity(CityInsertDTO dto) throws CityDAOException;
    void deleteCity(Integer cityId) throws CityDAOException;
    City updateCity(CityUpdateDTO dto) throws CityDAOException;
}
