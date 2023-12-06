<%@ page import="com.eventinvitations.eventinvitations.EventServlet" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <title>Create Event</title>
  <link rel='stylesheet' type='text/css' href='styles.css'>
</head>
<body>
<form id='eventForm' action='EventServlet' method='post'>
  <input type='hidden' name='action' value='Create'/>
  <div>
    <label for='eventName'>Event Name:</label>
    <input type='text' id='eventName' name='eventName' required>
  </div>
  <div>
    <label for='eventDate'>Date:</label>
    <input type='date' id='eventDate' name='eventDate' required>
  </div>
  <div>
    <label for='eventTime'>Time:</label>
    <input type='time' id='eventTime' name='eventTime' required>
  </div>
  <div>
    <label for='eventLocation'>Location:</label>
    <input type='text' id='eventLocation' name='eventLocation' required>
  </div>
  <div>
    <label for='eventDescription'>Description:</label>
    <textarea id='eventDescription' name='eventDescription' required></textarea>
  </div>
  <div>
    <input type='hidden' name='action' value='Create'/>
    <input type='submit' value='Create Event'>
  </div>
</form>

<a href="events.jsp">View All Events</a>

<script src='js/form.js'></script>
</body>
</html>
