package gr.aueb.cf.schoolapppro.service.student;

import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.dao.speciality.ISpecialityDAO;
import gr.aueb.cf.schoolapppro.dao.student.IStudentDAO;
import gr.aueb.cf.schoolapppro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapppro.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapppro.model.Student;
import gr.aueb.cf.schoolapppro.service.exceptions.StudentServiceException;

import java.util.List;

public class StudentServiceImpl implements IStudentService {

    private final IStudentDAO dao;

    public StudentServiceImpl(IStudentDAO dao) {
        this.dao = dao;
    }

    @Override
    public Student insertStudent(StudentInsertDTO dto) throws StudentDAOException, StudentServiceException {
        if (dto == null){
            throw new StudentServiceException("DTO is null.. Insertion Failed");
        }

        Student student = map(dto);
        return dao.insertStudent(student);
    }

    @Override
    public Student updateStudent(StudentUpdateDTO dto) throws StudentDAOException {
        Student student = map(dto);
        return dao.updateStudent(student);
    }

    @Override
    public List<Student> getStudentsByLastname(String lastname) throws StudentDAOException {
        List<Student> students = dao.getStudentsListByLastname(lastname);
        return students;
    }

    @Override
    public void deleteStudent(Integer studentId) throws StudentDAOException {
        dao.deleteStudent(studentId);
    }

    private Student map(StudentInsertDTO dto) {
        Student student = new Student(null, dto.getFirstname(), dto.getLastname(), dto.getGender(),
                dto.getBirthDate(), dto.getCityId(), dto.getUserId());

        return student;
    }

    private Student map(StudentUpdateDTO dto) {
        Student student  = new Student(dto.getId(), dto.getFirstname(), dto.getLastname(),
                dto.getGender(), dto.getBirthDate(), dto.getCityId(), dto.getUserId());
        return student;
    }
}
