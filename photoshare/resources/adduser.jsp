<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.NewUserDao" %>

<html>
<head><title>Adding New User</title></head>

<body>

<% 
   String err = null;
   String email  = request.getParameter("email");
   String password1 = request.getParameter("password1");
   String password2 = request.getParameter("password2");
   String firstname = request.getParameter("firstname");
   String lastname = request.getParameter("lastname");
   String dob = request.getParameter("dob");
   String gender = request.getParameter("gender");
   String hometown = request.getParameter("hometown");


   if (!email.equals("")) {
     if (!password1.equals(password2)) {
       err = "Both password strings must match";

     }
     else if (password1.length() < 4) {
       err = "Your password must be at least four characters long";
     }
     else if (firstname.equals("") || lastname.equals("")) {
       err = "Please enter your name";
     }
     else if (dob == null) {
       err = "Please enter your birthday";
     } 
     else if (dob.length() != 8) {
       err = "Invalid date of birth entered";
     }
     else {
       // We have valid inputs, try to create the user
       NewUserDao newUserDao = new NewUserDao();

       boolean success = newUserDao.create(email, password1, firstname, lastname, dob, gender, hometown);
       if (!success) {
         err = "Couldn't create user (that email may already be in use)";
       }
     }
   } else {
	 err = "You have to provide an email";

   }
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<p> <a href="newuser2.jsp">Go Back</a>
<% }
   else { %>

<h2>Success!</h2>

<p>A new user has been created with email <%= email %>.
You can now return to the <a href="login.jsp">login page</a>.

<% } %>

</body>
</html>
