package gr.aueb.cf.schoolapppro.controllers.meeting;

import gr.aueb.cf.schoolapppro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapppro.dao.meeting.IMeetingDAO;
import gr.aueb.cf.schoolapppro.dao.meeting.MeetingDAOImpl;
import gr.aueb.cf.schoolapppro.dto.MeetingUpdateDTO;
import gr.aueb.cf.schoolapppro.model.Meeting;
import gr.aueb.cf.schoolapppro.service.meeting.IMeetingService;
import gr.aueb.cf.schoolapppro.service.meeting.MeetingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/schoolapppro/meetingupdate")
public class MeetingUpdateController extends HttpServlet {
    private final IMeetingDAO meetingDAO = new MeetingDAOImpl();
    private final IMeetingService meetingService = new MeetingServiceImpl(meetingDAO);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String unformattedMeetingDate = req.getParameter("meetingdate");

        Integer teacherId = Integer.parseInt(req.getParameter("teacherid"));
        Integer studentId = Integer.parseInt(req.getParameter("studentid"));
        LocalDate meetingDate = LocalDate.parse(unformattedMeetingDate, formatter);
        String meetingRoom = req.getParameter("meetingroom");

        MeetingUpdateDTO dto = new MeetingUpdateDTO(teacherId, studentId, meetingDate, meetingRoom);

        try {
            Meeting meeting = meetingService.updateMeeting(dto);
            req.setAttribute("meeting", meeting);
            req.getRequestDispatcher("/schoolapppro/static/templates/meetingupdated.jsp").forward(req, resp);
        } catch (MeetingDAOException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/schoolapppro/static/templates/meetingupdated.jsp").forward(req, resp);
        }
    }
}
