<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <title>Event Form</title>
</head>
<body>
<form id="event-form" method="post">
    <div>
        <label for="eventName">Event Name:</label>
        <input type="text" id="eventName" name="eventName" placeholder="Example: Charity Run" required>
    </div>
    <div>
        <label for="eventDate">Date:</label>
        <input type="date" id="eventDate" name="eventDate" placeholder="Example: 2023-09-30" required>
    </div>
    <div>
        <label for="eventTime">Time:</label>
        <input type="time" id="eventTime" name="eventTime" placeholder="Example: 07:30" required>
    </div>
    <div>
        <label for="eventLocation">Location:</label>
        <input type="text" id="eventLocation" name="eventLocation" placeholder="Example: Central Park" required>
    </div>
    <div>
        <label for="eventDescription">Description:</label>
        <textarea id="eventDescription" name="eventDescription" rows="4" cols="50"
                  placeholder="Example: A charity run event to raise funds for a local hospital." required></textarea>
    </div>
    <div>
        <button type="submit">Submit</button>
    </div>
</form>


</body>

<script src="js/form.js"></script>
</html>