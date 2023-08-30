package gr.aueb.cf.schoolapppro.service.student;

import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapppro.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapppro.model.Student;
import gr.aueb.cf.schoolapppro.service.exceptions.StudentServiceException;

import java.util.List;

public interface IStudentService {
    Student insertStudent(StudentInsertDTO dto) throws StudentDAOException, StudentServiceException;
    Student updateStudent(StudentUpdateDTO dto) throws StudentDAOException;
    List<Student> getStudentsByLastname(String lastname) throws StudentDAOException;
    void deleteStudent(Integer studentId) throws StudentDAOException;
}
