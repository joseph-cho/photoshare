<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>PhotoShare</title></head>

<body>

<%--This will log the user in to the anonymous account--%>
<h2>Stay anonymous</h2>
<p>Log in as the public anonymous account</p>
<form method="POST" action="j_security_check">
    <input type="hidden" name="j_username" value="anon@anon.com">
    <input type="hidden" name="j_password" value="anon">
    <input type="submit" value="Browse"/>
</form>

<h2>Returning user?</h2>

<form method="POST" action="j_security_check">
    <table>
        <tr><th>Email</th><td><input type="text" name="j_username"></td></tr>
        <tr><th>Password</th><td><input type="password" name="j_password"></td>
        </tr>
        <tr><td colspan="2" align="right"><input type="submit" value="Login"/>
        </td></tr>
    </table>
</form>


<h2>Don't have an account?</h2>
<a href="newuser2.jsp">Sign up</a>


</body>
</html>