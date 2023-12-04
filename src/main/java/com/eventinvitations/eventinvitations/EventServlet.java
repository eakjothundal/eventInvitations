package com.eventinvitations.eventinvitations;

import com.eventinvitations.eventinvitations.dao.EventDAO;
import com.eventinvitations.eventinvitations.dao.UserDAO;
import com.eventinvitations.eventinvitations.model.Event;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Connect to the MongoDB database
        MongoClient mongoClient = MongoClients.create();
        UserDAO userDAO = new UserDAO(mongoClient, "event_database");  // Replace event_database with your database name
        EventDAO eventDAO = new EventDAO(mongoClient, "event_database");

        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = request.getParameter("username");
            session.setAttribute("username", username);
        }

        String eventName = request.getParameter("eventName");
        String eventDate = request.getParameter("eventDate");
        String eventTime = request.getParameter("eventTime");
        String eventLocation = request.getParameter("eventLocation");
        String eventDescription = request.getParameter("eventDescription");

        // If the action sent from the form is "Create", create a new event
        String action = request.getParameter("action");
        if ("Create".equals(action)) {
            Event event = new Event(username, eventName, eventDate, eventTime, eventLocation, eventDescription);
            eventDAO.createEvent(event);
        }
        // If the action is "Attend", add user to the event's attendees list
        else if ("Attend".equals(action)) {
            String attendee = request.getParameter("attendee");
            eventDAO.confirmEvent(eventName, attendee);
        }

        List<Event> events = eventDAO.getAllEvents();
        session.setAttribute("events", events);
        response.sendRedirect("events.jsp");
    }
}