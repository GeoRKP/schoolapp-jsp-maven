package gr.aueb.cf.schoolapppro.service.meeting;

import gr.aueb.cf.schoolapppro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapppro.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolapppro.dto.MeetingUpdateDTO;
import gr.aueb.cf.schoolapppro.model.Meeting;
import gr.aueb.cf.schoolapppro.service.exceptions.MeetingServiceException;

import java.util.List;

public interface IMeetingService {
    Meeting insertMeeting(MeetingInsertDTO dto) throws MeetingDAOException, MeetingServiceException;
    List<Meeting> getMeetingsByTeacherIdElseAll(Integer teacherId) throws MeetingDAOException;
    void deleteMeeting(Integer teacherId, Integer studentId) throws MeetingDAOException;
    Meeting updateMeeting(MeetingUpdateDTO dto) throws  MeetingDAOException;
}
