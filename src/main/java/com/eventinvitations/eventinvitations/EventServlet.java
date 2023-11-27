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
            out.println("<form id='eventForm'>");
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
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("doPost called");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("try called");
            String eventName = request.getParameter("eventName");
            String eventDate = request.getParameter("eventDate");
            String eventTime = request.getParameter("eventTime");
            String eventLocation = request.getParameter("eventLocation");
            String eventDescription = request.getParameter("eventDescription");
            String actionType = request.getParameter("actionType");

            System.out.println("actionType: " + actionType);

            if ("submit".equals(actionType)) {
                System.out.println("submit called");
//                 Check if any of the event details are null or empty
                if (eventName == null || eventDate == null || eventTime == null || eventLocation == null || eventDescription == null) {
                    out.println("<html><head><title>Error</title></head><body>");
                    out.println("<h2>Error: Missing event details.</h2>");
                    out.println("<p>Please go back and fill in all the fields.</p>");
                    out.println("</body></html>");
                    return;
                }

                // Process the form submission and display event details with confirmation buttons
                out.println("<!DOCTYPE html><html><head><title>Event Details</title><link rel='stylesheet' type='text/css' href='styles.css'></head><body>");
                out.println("<h2>Event Details</h2>");
                out.println("<p>Name: " + eventName + "</p>");
                out.println("<p>Date: " + eventDate + "</p>");
                out.println("<p>Time: " + eventTime + "</p>");
                out.println("<p>Location: " + eventLocation + "</p>");
                out.println("<p>Description: " + eventDescription + "</p>");
                out.println("<form action='EventServlet' method='POST'>");
                out.println("<input type='hidden' name='eventName' value='" + eventName + "'>");
                out.println("<input type='hidden' name='eventDate' value='" + eventDate + "'>");
                out.println("<input type='hidden' name='eventTime' value='" + eventTime + "'>");
                out.println("<input type='hidden' name='eventLocation' value='" + eventLocation + "'>");
                out.println("<input type='hidden' name='eventDescription' value='" + eventDescription + "'>");
                out.println("<input type='submit' name='actionType' value='Will be there!'>");
                out.println("<input type='submit' name='actionType' value='Will not make it...'>");
                out.println("</form>");
                out.println("</body></html>");
            } else if ("Will be there!".equals(actionType) || "Will not make it...".equals(actionType)) {
                // Increment the counts accordingly
                if ("Will be there!".equals(actionType)) {
                    countAttend++;
                } else {
                    countNotAttend++;
                }

                // Redisplay event details with updated confirmation count
                out.println("<html><head><title>Event Confirmation</title><link rel='stylesheet' type='text/css' href='styles.css'></head><body>");
                out.println("<h2>Event Confirmation</h2>");
                out.println("<p>Event: " + eventName + "</p>");
                out.println("<p>Attendees: " + countAttend + "</p>");
                out.println("<p>Not Attending: " + countNotAttend + "</p>");
                out.println("</body></html>");
            }
        }
    }
}