package gr.aueb.cf.schoolapppro.model;


import java.time.LocalDate;

public class Meeting {
    private Integer teacherId;
    private Integer studentId;
    private String meetingRoom;
    private LocalDate meetingDate;

    public Meeting () {}

    public Meeting(Integer teacherId, Integer studentId, String meetingRoom, LocalDate meetingDate) {
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.meetingRoom = meetingRoom;
        this.meetingDate = meetingDate;
    }

    public int getTeacherId() {
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

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(LocalDate meetingDate) {
        this.meetingDate = meetingDate;
    }
}
