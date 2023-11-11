<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <title>Event Form</title>
    <script src="js/form.js" defer></script>
</head>
<body>

<form id="event-form" action="#" method="POST">
    <div>
        <label for="event-name">Event Name:</label>
        <input type="text" id="event-name" name="event-name" placeholder="Example: Charity Run" required>
    </div>
    <div>
        <label for="event-date">Date:</label>
        <input type="date" id="event-date" name="event-date" placeholder="Example: 2023-09-30" required>
    </div>
    <div>
        <label for="event-time">Time:</label>
        <input type="time" id="event-time" name="event-time" placeholder="Example: 14:30" required>
    </div>
    <div>
        <label for="event-location">Location:</label>
        <input type="text" id="event-location" name="event-location" placeholder="Example: Central Park" required>
    </div>
    <div>
        <label for="event-description">Description:</label>
        <textarea id="event-description" name="event-description" rows="4" cols="50" placeholder="Example: A charity run event to raise funds for a local hospital." required></textarea>
    </div>
    <div>
        <button type="submit">Submit</button>
    </div>
</form>

</body>
</html>
