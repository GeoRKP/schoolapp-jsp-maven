package gr.aueb.cf.schoolapppro.dto;

import java.time.LocalDate;

public class MeetingUpdateDTO {
    private Integer teacherId;
    private Integer studentId;
    private LocalDate meetingDate;
    private String meetingRoom;

    public MeetingUpdateDTO() {}

    public MeetingUpdateDTO(Integer teacherId, Integer studentId, LocalDate meetingDate, String meetingRoom) {
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.meetingDate = meetingDate;
        this.meetingRoom = meetingRoom;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(LocalDate meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }
}
