package gr.aueb.cf.schoolapppro.controllers.meeting;

import gr.aueb.cf.schoolapppro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapppro.dao.meeting.IMeetingDAO;
import gr.aueb.cf.schoolapppro.dao.meeting.MeetingDAOImpl;
import gr.aueb.cf.schoolapppro.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolapppro.model.Meeting;
import gr.aueb.cf.schoolapppro.service.exceptions.MeetingServiceException;
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

@WebServlet("/shcoolapppro/meetinginsert")
public class MeetingInsertController extends HttpServlet {
    private final IMeetingDAO meetingDAO = new MeetingDAOImpl();
    private final IMeetingService meetingService = new MeetingServiceImpl(meetingDAO);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String unformattedMeetingDate = req.getParameter("date");

        Integer teacherId = Integer.parseInt(req.getParameter("teacherid"));
        Integer studentId = Integer.parseInt(req.getParameter("studentid"));
        LocalDate meetingDate = LocalDate.parse(unformattedMeetingDate, formatter);
        String meetingRoom = req.getParameter("meetingroom");

        MeetingInsertDTO meetingInsertDTO = new MeetingInsertDTO(teacherId, studentId, meetingDate, meetingRoom);

        try {
            Meeting meeting = meetingService.insertMeeting(meetingInsertDTO);
            req.setAttribute("meeting", meeting);
            req.getRequestDispatcher("/schoolapppro/static/templates/meetingInserted.jsp").forward(req, resp);
        } catch (MeetingDAOException | MeetingServiceException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/schoolapppro/static/templates/meetingInserted.jsp").forward(req, resp);
        }

    }
}
