package gr.aueb.cf.schoolapppro.service.speciality;

import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.dto.SpecialityInsertDTO;
import gr.aueb.cf.schoolapppro.dto.SpecialityUpdateDTO;
import gr.aueb.cf.schoolapppro.model.Speciality;

import java.util.List;

public interface ISpecialityService {
    List<Speciality> getAllSpecialities() throws SpecialityDAOException;
    Speciality insertSpeciality(SpecialityInsertDTO dto) throws SpecialityDAOException;
    void deleteSpeciality(Integer specialityId) throws SpecialityDAOException;
    Speciality updateSpeciality(SpecialityUpdateDTO dto) throws SpecialityDAOException;

}
