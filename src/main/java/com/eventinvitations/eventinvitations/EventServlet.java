package com.eventinvitations.eventinvitations;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = request.getParameter("username");
            session.setAttribute("username", username);
        }

        String actionType = request.getParameter("actionType");
        System.out.println("Action type: " + actionType);
        List<Event> events = (List<Event>) getServletContext().getAttribute("events");

        if ("Create".equals(actionType)) {
            String eventName = request.getParameter("eventName");
            String eventDate = request.getParameter("eventDate");
            String eventTime = request.getParameter("eventTime");
            String eventLocation = request.getParameter("eventLocation");
            String eventDescription = request.getParameter("eventDescription");

            Event newEvent = new Event(username, eventName, eventDate, eventTime, eventLocation, eventDescription);
            if (events == null) {
                events = new ArrayList<>();
                getServletContext().setAttribute("events", events);
            }
            events.add(newEvent);
        } else if ("Attend".equals(actionType)) {
            System.out.println("Attend");

            String eventNameToConfirm = request.getParameter("eventNameToConfirm");
            String attendee = request.getParameter("attendee"); // Retrieve the attendee from the request
            if (events != null) {
                for (Event event : events) {
                    if (event.getEventName().equals(eventNameToConfirm) && !event.getUsername().equals(attendee)) {
                        event.addConfirmation(attendee);
                        break;
                    }
                }
                response.sendRedirect("events.jsp");
                return;
            }
        } else if ("Restart".equals(actionType)) {
            session.invalidate();
        }

        response.sendRedirect("index.jsp");
    }

    public static class Event {
        String username;
        String eventName;
        String eventDate;
        String eventTime;
        String eventLocation;
        String eventDescription;
        List<String> confirmations;

        public Event(String username, String eventName, String eventDate, String eventTime, String eventLocation, String eventDescription) {
            this.username = username;
            this.eventName = eventName;
            this.eventDate = eventDate;
            this.eventTime = eventTime;
            this.eventLocation = eventLocation;
            this.eventDescription = eventDescription;
            this.confirmations = new ArrayList<>();
        }

        // Public getter methods
        public String getUsername() {
            return username;
        }

        public String getEventName() {
            return eventName;
        }

        public String getEventDate() {
            return eventDate;
        }

        public String getEventTime() {
            return eventTime;
        }

        public String getEventLocation() {
            return eventLocation;
        }

        public String getEventDescription() {
            return eventDescription;
        }

        public List<String> getConfirmations() {
            return confirmations;
        }

        public void addConfirmation(String username) {
            if (!this.username.equals(username) && !confirmations.contains(username)) {
                confirmations.add(username);
            }
        }
    }
}
