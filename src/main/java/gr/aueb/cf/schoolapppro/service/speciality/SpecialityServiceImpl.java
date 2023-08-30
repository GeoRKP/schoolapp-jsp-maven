package gr.aueb.cf.schoolapppro.service.speciality;

import gr.aueb.cf.schoolapppro.dao.speciality.ISpecialityDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.dto.SpecialityInsertDTO;
import gr.aueb.cf.schoolapppro.dto.SpecialityUpdateDTO;
import gr.aueb.cf.schoolapppro.model.Speciality;

import java.util.List;

public class SpecialityServiceImpl implements ISpecialityService{

    private final ISpecialityDAO dao;

    public SpecialityServiceImpl(ISpecialityDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Speciality> getAllSpecialities() throws SpecialityDAOException {
       return dao.getAllSpecialities();
    }

    @Override
    public Speciality insertSpeciality(SpecialityInsertDTO dto) throws SpecialityDAOException {
        Speciality speciality = map(dto);
        return dao.insertSpeciality(speciality);
    }

    @Override
    public void deleteSpeciality(Integer specialityId) throws SpecialityDAOException {
        dao.deleteSpecialityById(specialityId);
    }

    @Override
    public Speciality updateSpeciality(SpecialityUpdateDTO dto) throws SpecialityDAOException {
       Speciality speciality = map(dto);
       return dao.updateSpeciality(speciality);
    }

    private Speciality map(SpecialityInsertDTO dto) {
        Speciality speciality = new Speciality(null, dto.getSpecialityName());
        return speciality;
    }

    private Speciality map(SpecialityUpdateDTO dto) {
        Speciality speciality = new Speciality(dto.getId(), dto.getSpecialityName());
        return speciality;
    }
}
