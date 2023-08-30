package gr.aueb.cf.schoolapppro.service.teacher;

import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapppro.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapppro.model.Teacher;
import gr.aueb.cf.schoolapppro.service.exceptions.TeacherServiceException;

import java.util.List;

public interface ITeacherService {
    List<Teacher> getTeachersListByLastname(String lastname) throws TeacherDAOException, TeacherServiceException;
    Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDAOException, TeacherServiceException;
    Teacher updateTeacher(TeacherUpdateDTO dto) throws TeacherDAOException;
    void deleteTeacher(Integer teacherId) throws TeacherDAOException;
}
