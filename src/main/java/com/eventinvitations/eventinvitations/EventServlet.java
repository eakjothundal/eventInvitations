package com.eventinvitations.eventinvitations;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {

    // Variables to keep track of event information and confirmations
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    private String eventDescription;
    private int countAttend = 0;
    private int countNotAttend = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String actionType = request.getParameter("actionType");

        // Retrieve event information or keep the previous one if already set
        String newEventName = request.getParameter("eventName");
        if (newEventName != null && !newEventName.isEmpty()) {
            eventName = newEventName;
        }

        String newEventDate = request.getParameter("eventDate");
        if (newEventDate != null && !newEventDate.isEmpty()) {
            eventDate = newEventDate;
        }

        String newEventTime = request.getParameter("eventTime");
        if (newEventTime != null && !newEventTime.isEmpty()) {
            eventTime = newEventTime;
        }

        String newEventLocation = request.getParameter("eventLocation");
        if (newEventLocation != null && !newEventLocation.isEmpty()) {
            eventLocation = newEventLocation;
        }

        String newEventDescription = request.getParameter("eventDescription");
        if (newEventDescription != null && !newEventDescription.isEmpty()) {
            eventDescription = newEventDescription;
        }

        // Increment counters based on actionType received
        if ("Attend".equals(actionType)) {
            countAttend++;
        } else if ("Not Attend".equals(actionType)) {
            countNotAttend++;
        }

        // Reset event and counts if "Restart" is chosen
        if ("Restart".equals(actionType)) {
            eventName = null;
            eventDate = null;
            eventTime = null;
            eventLocation = null;
            eventDescription = null;
            countAttend = 0;
            countNotAttend = 0;
            response.sendRedirect("eventForm.jsp"); // Redirect to the blank event form JSP
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html><head><title>Event Confirmation</title>");
            out.println("<link rel='stylesheet' type='text/css' href='styles.css'></head><body>");
            out.println("<div class='event-confirmation'>");
            out.println("<h2>Event Confirmation</h2>");
            // Render event details
            out.println("<p><strong>Name:</strong> " + (eventName != null ? eventName : "") + "</p>");
            out.println("<p><strong>Date:</strong> " + (eventDate != null ? eventDate : "") + "</p>");
            out.println("<p><strong>Time:</strong> " + (eventTime != null ? eventTime : "") + "</p>");
            out.println("<p><strong>Location:</strong> " + (eventLocation != null ? eventLocation : "") + "</p>");
            out.println("<p><strong>Description:</strong> " + (eventDescription != null ? eventDescription : "") + "</p>");
            // Only display counts if at least one of them is non-zero
            if (countAttend > 0 || countNotAttend > 0) {
                out.println("<p><strong>Attendees:</strong> " + countAttend + "</p>");
                out.println("<p><strong>Not Attending:</strong> " + countNotAttend + "</p>");
            }
            // Display buttons
            out.println("<form action='EventServlet' method='POST'>");
            out.println("<input type='submit' name='actionType' value='Attend'>");
            out.println("<input type='submit' name='actionType' value='Not Attend'>");
            out.println("<input type='submit' name='actionType' value='Restart'>");
            out.println("</form>");
            out.println("</div></body></html>");
        }
    }
}