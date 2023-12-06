<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login to Event Invitation</title>
    <link rel='stylesheet' type='text/css' href='styles.css'>
</head>
<body>
<form id='loginForm' action='EventServlet' method='post'>
    <div>
        <label for='username'>Username:</label>
        <input type='text' id='username' name='username' required>
    </div>
    <div>
        <label for='password'>Password:</label>
        <input type='password' id='password' name='password' required>
    </div>
    <div>
        <input type='submit' name='action' value='Login'>
    </div>
</form>

<h2>Sign Up</h2>
<form id='signupForm' action='EventServlet' method='post'>
    <div>
        <label for='newUsername'>New Username:</label>
        <input type='text' id='newUsername' name='newUsername' required>
    </div>
    <div>
        <label for='newPassword'>New Password:</label>
        <input type='password' id='newPassword' name='newPassword' required>
    </div>
    <div>
        <input type='submit' name='action' value='Sign Up'>
    </div>
</form>

<%
    // Display error message if any
    String error = request.getParameter("error");
    if (error != null) {
        if (error.equals("invalid_credentials")) {
%>
<p>Error: Invalid username or password.</p>
<%
} else if (error.equals("username_exists")) {
%>
<p>Error: Username already exists.</p>
<%
        }
    }
%>
</body>
</html>
