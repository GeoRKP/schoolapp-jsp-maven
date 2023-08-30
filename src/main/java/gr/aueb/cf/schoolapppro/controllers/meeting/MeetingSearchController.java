package gr.aueb.cf.schoolapppro.controllers.meeting;

import gr.aueb.cf.schoolapppro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapppro.dao.meeting.IMeetingDAO;
import gr.aueb.cf.schoolapppro.dao.meeting.MeetingDAOImpl;
import gr.aueb.cf.schoolapppro.model.Meeting;
import gr.aueb.cf.schoolapppro.service.meeting.IMeetingService;
import gr.aueb.cf.schoolapppro.service.meeting.MeetingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shcoolapppro/meetingsearch")
public class MeetingSearchController extends HttpServlet {
    private final IMeetingDAO meetingDAO = new MeetingDAOImpl();
    private final IMeetingService meetingService = new MeetingServiceImpl(meetingDAO);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer teacherId = Integer.parseInt(req.getParameter("teacherid"));

        try {
            List<Meeting> meetings = meetingService.getMeetingsByTeacherIdElseAll(teacherId);
            req.setAttribute("meetings", meetings);
            req.getRequestDispatcher("/schoolapppro/static/templates/meetings.jsp").forward(req, resp);
        } catch (MeetingDAOException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/schoolapppro/static/templates/meetings.jsp").forward(req, resp);
        }
    }
}
