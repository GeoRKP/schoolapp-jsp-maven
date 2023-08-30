package gr.aueb.cf.schoolapppro.service.meeting;

import gr.aueb.cf.schoolapppro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapppro.dao.meeting.IMeetingDAO;
import gr.aueb.cf.schoolapppro.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolapppro.dto.MeetingUpdateDTO;
import gr.aueb.cf.schoolapppro.model.Meeting;
import gr.aueb.cf.schoolapppro.service.exceptions.MeetingServiceException;

import java.util.List;

public class MeetingServiceImpl implements IMeetingService {
    private final IMeetingDAO meetingDAO;

    public MeetingServiceImpl(IMeetingDAO meetingDAO) {
        this.meetingDAO = meetingDAO;
    }

    @Override
    public Meeting insertMeeting(MeetingInsertDTO dto) throws MeetingDAOException, MeetingServiceException {
        return null;
    }

    @Override
    public List<Meeting> getMeetingsByTeacherIdElseAll(Integer teacherId) throws MeetingDAOException {
        List<Meeting> meetings;
        if (teacherId == null) {
            meetings = meetingDAO.getAllMeetings();
        } else {
            meetings = meetingDAO.getMeetingsByTeacherId(teacherId);
        }
        return meetings;
    }

    @Override
    public void deleteMeeting(Integer teacherId, Integer studentId) throws MeetingDAOException {
        meetingDAO.deleteMeetingBy(teacherId, studentId);
    }

    @Override
    public Meeting updateMeeting(MeetingUpdateDTO dto) throws MeetingDAOException {
        Meeting meeting = map(dto);
        return meetingDAO.updateMeeting(meeting);
    }

    private Meeting map(MeetingInsertDTO dto) {
        Meeting meeting = new Meeting(dto.getTeacherId(), dto.getStudentId(),
                dto.getMeetingRoom(), dto.getMeetingDate());
        return meeting;
    }

    private Meeting map(MeetingUpdateDTO dto) {
        Meeting meeting = new Meeting(dto.getTeacherId(), dto.getStudentId(),
                dto.getMeetingRoom(), dto.getMeetingDate());
        return meeting;
    }
}
