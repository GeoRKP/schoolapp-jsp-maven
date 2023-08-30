package gr.aueb.cf.schoolapppro.service.teacher;

import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dao.teacher.ITeacherDAO;
import gr.aueb.cf.schoolapppro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapppro.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapppro.model.Teacher;
import gr.aueb.cf.schoolapppro.service.exceptions.TeacherServiceException;

import java.util.List;

public class TeacherServiceImpl implements ITeacherService{
    private final ITeacherDAO dao;

    public TeacherServiceImpl(ITeacherDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Teacher> getTeachersListByLastname(String lastname) throws TeacherDAOException, TeacherServiceException {
        List<Teacher> teachers = dao.getTeachersListByLastname(lastname);
        if (teachers.isEmpty()) {
            throw new TeacherServiceException("No teachers found with lastname like: " + lastname);
        }
        return teachers;
    }

    @Override
    public Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDAOException, TeacherServiceException {
        if (dto == null) {
            throw new TeacherServiceException("DTO is null");
        }

        Teacher teacher = map(dto);

        return dao.insertTeacher(teacher);
    }

    @Override
    public Teacher updateTeacher(TeacherUpdateDTO dto) throws TeacherDAOException {
        Teacher teacher = map(dto);
        return dao.updateTeacher(teacher);
    }

    @Override
    public void deleteTeacher(Integer teacherId) throws TeacherDAOException {
        dao.deleteTeacher(teacherId);
    }

    private Teacher map(TeacherInsertDTO dto) {
        Teacher teacher = new Teacher(null, dto.getSsn(), dto.getFirstname(),
                        dto.getLastname(), dto.getSpecialityId(), dto.getUserId());
        return teacher;
    }

    private Teacher map(TeacherUpdateDTO dto) {
        Teacher teacher = new Teacher(dto.getId(), dto.getSsn(), dto.getFirstname(),
                dto.getLastname(), dto.getSpecialityId(), dto.getUserId());
        return teacher;
    }
}
