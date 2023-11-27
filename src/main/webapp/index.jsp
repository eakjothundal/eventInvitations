<!DOCTYPE html>
<html>
<head>
    <title>Welcome to Event Invitation</title>
    <link rel='stylesheet' type='text/css' href='styles.css'>
    <script src='js/form.js'></script>
</head>
<body>
<h1>Welcome to the Event Invitation System</h1>
<form id='eventForm' action='EventServlet' method='post'>
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
        <input type='submit' name='actionType' value='Submit'>
    </div>
</form>
</body>
</html>