package gr.aueb.cf.schoolapppro.controllers.meeting;

import gr.aueb.cf.schoolapppro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapppro.dao.meeting.IMeetingDAO;
import gr.aueb.cf.schoolapppro.dao.meeting.MeetingDAOImpl;
import gr.aueb.cf.schoolapppro.service.meeting.IMeetingService;
import gr.aueb.cf.schoolapppro.service.meeting.MeetingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/schoolapppro/meetingdelete")
public class MeetingDeleteController extends HttpServlet {
    private final IMeetingDAO meetingDAO = new MeetingDAOImpl();
    private final IMeetingService meetingService = new MeetingServiceImpl(meetingDAO);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer teacherId = Integer.parseInt(req.getParameter("teacherid"));
        Integer studentId =Integer.parseInt(req.getParameter("studentid"));

        try {
            meetingService.deleteMeeting(teacherId, studentId);
            req.getRequestDispatcher("/schoolapppro/static/templates/meetingdeleted.jsp").forward(req, resp);
        } catch (MeetingDAOException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/schoolapppro/static/templates/meetingdeleted.jsp").forward(req, resp);
        }
    }
}
