<%@ page import="com.eventinvitations.eventinvitations.model.Event" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Event List</title>
    <link rel='stylesheet' type='text/css' href='styles.css'>
</head>
<body>
<h2>Current Events</h2>
<%
    // Fetching events from the session or application context
    List<Event> events = (List<Event>) session.getAttribute("events");
    if (events == null) {
        events = (List<Event>) application.getAttribute("events");
    }
    String loggedInUser = (String) session.getAttribute("username");

    if (events == null || events.isEmpty()) {
%>
<p>No upcoming events found.</p>
<%
} else {
    for (Event event : events) {
%>
<div class="event">
    <p><strong>Event Name:</strong> <%= event.getEventName() %>
    </p>
    <p><strong>Date:</strong> <%= event.getEventDate() %>
    </p>
    <p><strong>Time:</strong> <%= event.getEventTime() %>
    </p>
    <p><strong>Location:</strong> <%= event.getEventLocation() %>
    </p>
    <p><strong>Description:</strong> <%= event.getEventDescription() %>
    </p>
    <p><strong>Created by:</strong> <%= event.getUsername() %>
    </p>
    <p><strong>Confirmed Attendees:</strong> <%= event.getAttendance() %>
    </p>
    <form action='EventServlet' method='post' class='attend-form' data-creator='<%= event.getUsername() %>'>
        <input type='hidden' name='eventNameToAttend' value='<%= event.getEventName() %>'>
        <input type='hidden' name='action' value='Attend'>
        <input type='submit' value='Attend Event'>
    </form>
    <% if (loggedInUser != null && loggedInUser.equals(event.getUsername())) { %>
    <form action='EventServlet' method='post'>
        <input type='hidden' name='eventNameToDelete' value='<%= event.getEventName() %>'>
        <input type='hidden' name='eventCreator' value='<%= event.getUsername() %>'>
        <input type='hidden' name='action' value='Delete'>
        <input type='submit' value='Delete Event'>
    </form>
    <% } %>
</div>
<%
        }
    }
%>

<a href="form.jsp">Back to Create Event</a>

<script src='js/attend.js'></script>
</body>
</html>
