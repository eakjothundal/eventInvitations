package com.eventinvitations.eventinvitations;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {

    // Variables to keep track of confirmations
    private int countAttend = 0;
    private int countNotAttend = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Write the HTML form directly from the servlet
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Event Form</title>");
            out.println("<link rel='stylesheet' type='text/css' href='styles.css'>");
            out.println("<script src='js/form.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form id='eventForm' action='EventServlet' method='post'>");
            out.println("<div><label for='eventName'>Event Name:</label>");
            out.println("<input type='text' id='eventName' name='eventName' required></div>");
            out.println("<div><label for='eventDate'>Date:</label>");
            out.println("<input type='date' id='eventDate' name='eventDate' required></div>");
            out.println("<div><label for='eventTime'>Time:</label>");
            out.println("<input type='time' id='eventTime' name='eventTime' required></div>");
            out.println("<div><label for='eventLocation'>Location:</label>");
            out.println("<input type='text' id='eventLocation' name='eventLocation' required></div>");
            out.println("<div><label for='eventDescription'>Description:</label>");
            out.println("<textarea id='eventDescription' name='eventDescription' required></textarea></div>");
            out.println("<div><input type='submit' name='actionType' value='Submit'></div>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String actionType = request.getParameter("actionType");
        if (actionType != null) {
            if (actionType.equals("Attend")) {
                countAttend++;
            } else if (actionType.equals("Not Attend")) {
                countNotAttend++;
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String eventName = request.getParameter("eventName");
            String eventDate = request.getParameter("eventDate");
            String eventTime = request.getParameter("eventTime");
            String eventLocation = request.getParameter("eventLocation");
            String eventDescription = request.getParameter("eventDescription");

            // Start HTML output
            out.println("<!DOCTYPE html><html><head><title>Event Confirmation</title>");
            out.println("<link rel='stylesheet' type='text/css' href='styles.css'></head><body>");
            out.println("<div class='event-confirmation'>");

            // Event details with specific class names
            out.println("<h2 class='event-confirmation-title'>Event Confirmation</h2>");
            out.println("<p class='event-confirmation-detail'><strong>Name:</strong> " + eventName + "</p>");
            out.println("<p class='event-confirmation-detail'><strong>Date:</strong> " + eventDate + "</p>");
            out.println("<p class='event-confirmation-detail'><strong>Time:</strong> " + eventTime + "</p>");
            out.println("<p class='event-confirmation-detail'><strong>Location:</strong> " + eventLocation + "</p>");
            out.println("<p class='event-confirmation-detail'><strong>Description:</strong> " + eventDescription + "</p>");

            // Attendee count with specific class names
            out.println("<p class='event-confirmation-count'><strong>Attendees:</strong> " + countAttend + "</p>");
            out.println("<p class='event-confirmation-count'><strong>Not Attending:</strong> " + countNotAttend + "</p>");

            // Form with hidden input fields and confirmation buttons
            out.println("<form action='EventServlet' method='POST' class='confirmation-container'>");
            out.println("<input type='hidden' name='eventName' value='" + eventName + "'>");
            out.println("<input type='hidden' name='eventDate' value='" + eventDate + "'>");
            out.println("<input type='hidden' name='eventTime' value='" + eventTime + "'>");
            out.println("<input type='hidden' name='eventLocation' value='" + eventLocation + "'>");
            out.println("<input type='hidden' name='eventDescription' value='" + eventDescription + "'>");
            out.println("<div class='event-confirmation-buttons'>");
            out.println("<button type='submit' name='actionType' value='Attend' class='event-confirmation-button'>Will be there!</button>");
            out.println("<button type='submit' name='actionType' value='Not Attend' class='event-confirmation-button'>Will not make it...</button>");
            out.println("</div>");
            out.println("</form>");

            out.println("</div>");
            out.println("</body></html>");
        }
    }

}