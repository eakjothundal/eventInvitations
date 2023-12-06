package com.eventinvitations.eventinvitations;

import com.eventinvitations.eventinvitations.dao.EventDAO;
import com.eventinvitations.eventinvitations.dao.UserDAO;
import com.eventinvitations.eventinvitations.model.Event;
import com.eventinvitations.eventinvitations.model.User;
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
        String action = request.getParameter("action");

        // Connect to the MongoDB database
        try (MongoClient mongoClient = MongoClients.create()) {
            UserDAO userDAO = new UserDAO(mongoClient, "eventSystemDB");
            EventDAO eventDAO = new EventDAO(mongoClient, "eventSystemDB");

            HttpSession session = request.getSession(true);

            List<Event> events = eventDAO.getAllEvents();
            request.getServletContext().setAttribute("events", events);

            if ("Login".equals(action)) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                if (userDAO.authenticateUser(username, password)) {
                    session.setAttribute("username", username);
                    response.sendRedirect("form.jsp");
                    return;
                } else {
                    response.sendRedirect("index.jsp?error=invalid_credentials");
                    return;
                }
            } else if ("Sign Up".equals(action)) {
                String newUsername = request.getParameter("newUsername");
                String newPassword = request.getParameter("newPassword");
                if (userDAO.findUser(newUsername) == null) {
                    userDAO.addUser(new User(newUsername, newPassword));
                    session.setAttribute("username", newUsername);
                    response.sendRedirect("form.jsp");
                    return;
                } else {
                    response.sendRedirect("index.jsp?error=username_exists");
                    return;
                }
            } else if ("Create".equals(action)) {
                System.out.println("Creating event");
                String eventName = request.getParameter("eventName");
                String eventDate = request.getParameter("eventDate");
                String eventTime = request.getParameter("eventTime");
                String eventLocation = request.getParameter("eventLocation");
                String eventDescription = request.getParameter("eventDescription");

                Event event = new Event(session.getAttribute("username").toString(), eventName, eventDate, eventTime, eventLocation, eventDescription, 0);
                eventDAO.createEvent(event);
                events = eventDAO.getAllEvents();
                request.getServletContext().setAttribute("events", events);
            } else if ("Attend".equals(action)) {
                String eventName = request.getParameter("eventNameToAttend");
                String attendee = request.getParameter("attendee");

                System.out.println("Event name: " + eventName);
                if (!session.getAttribute("username").toString().equals(attendee)) {
                    eventDAO.incrementAttendance(eventName);
                }
            } else if ("Logout".equals(action)) {
                session.invalidate();
                response.sendRedirect("index.jsp");
                return;
            } else if ("Delete".equals(action)) {
                String eventName = request.getParameter("eventNameToDelete");
                String eventCreator = request.getParameter("eventCreator");

                if (session.getAttribute("username").toString() != null && session.getAttribute("username").toString().equals(eventCreator)) {
                    eventDAO.deleteEvent(eventName);
                } else {
                    response.sendRedirect("events.jsp?error=not_creator");
                    return;
                }
            }

            if ("Create".equals(action) || "Attend".equals(action) || "Delete".equals(action)) {
                events = eventDAO.getAllEvents();
                session.setAttribute("events", events);
                request.getServletContext().setAttribute("events", events);
            }

            if ("Attend".equals(action) || "Delete".equals(action)) {
                response.sendRedirect("events.jsp");
            } else if ("Create".equals(action)) {
                response.sendRedirect("form.jsp");
            }
        }
    }
}
