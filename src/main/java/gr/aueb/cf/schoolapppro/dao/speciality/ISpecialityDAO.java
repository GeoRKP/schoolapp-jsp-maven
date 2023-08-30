package gr.aueb.cf.schoolapppro.dao.speciality;

import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.model.Speciality;

import java.util.List;

public interface ISpecialityDAO {
    Speciality insertSpeciality(Speciality speciality) throws SpecialityDAOException;
    Speciality updateSpeciality(Speciality speciality) throws SpecialityDAOException;

    void deleteSpecialityById(int id) throws SpecialityDAOException;
    Speciality getSpecialityByName(String speciality) throws SpecialityDAOException;
    List<Speciality> getAllSpecialities() throws SpecialityDAOException;
}
