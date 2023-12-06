<%@ page import="com.eventinvitations.eventinvitations.EventServlet" %>
<%@ page import="java.util.List" %>
<%@page import="com.eventinvitations.eventinvitations.model.Event" %>
<!DOCTYPE html>
<html>
<head>
    <title>Event List</title>
    <link rel='stylesheet' type='text/css' href='styles.css'>
</head>
<body>
<h2>Current Events</h2>
<%
    @SuppressWarnings("unchecked")
    List<Event> events = (List<Event>) application.getAttribute("events");
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
    <p><strong>Confirmed Attendees:</strong> <%= event.getConfirmations().size() %>
    </p>
    <form action='EventServlet' method='post' class='attend-form' data-creator='<%= event.getUsername() %>'>
        <input type='hidden' name='eventNameToConfirm' value='<%= event.getEventName() %>'>
        <input type='submit' name='action' value='Attend'>
    </form>
</div>
<%
        }
    }
%>

<a href="form.jsp">Back to Create Event</a>

<script src='js/attend.js'></script>
</body>
</html>