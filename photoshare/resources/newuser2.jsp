<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><title>Another New User Example</title></head>


<body>

<h2>New user info</h2>

<form action="adduser.jsp" method="post">
  <input type="text" name="email" placeholder="Email"/><br>
  <input type="password" name="password1" placeholder="Password"/><br>
  <input type="password" name="password2" placeholder="Re-type password"/><br>
  <input type="text" name="firstname" placeholder="First name"/><br>
  <input type="text" name="lastname" placeholder="Last name"/><br>
  <input type = "text" name ="dob" placeholder="mmddyyyy"><br>
  <br>
  <h3>Optional:</h3>
  Gender: <input type="radio" name="gender" value="F"> Female
  <input type="radio" name="gender" value="M"> Male <br>
  <input type = "text" name ="hometown" placeholder="Hometown"><br>
  <input type="submit" value="Create"/><br/>
</form>

<a href="index.jsp">Go back</a><br>

</body>
</html>
