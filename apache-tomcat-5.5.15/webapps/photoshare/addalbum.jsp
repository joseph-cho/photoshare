<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.AlbumDao" %>

<html>
<head><title>Adding new album</title></head>

<body>

<% 
   String err = null;
   String albumName  = request.getParameter("albumname");
   String userEmail = request.getUserPrincipal().getName(); 


   if (!albumName.equals("")) {
     AlbumDao album = new AlbumDao();
     NewUserDao user = new NewUserDao();
     int owner_id = user.getUserId(userEmail);
     boolean success = album.create(albumName, owner_id);

     if (!success) {
       err = "Couldn't create album";
     }

     } else {
       err = "Please provide a name for your album";
     }
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<p> <a href="createalbum.jsp">Go Back</a>
<% }
   else { %>

<h2>Success!</h2>

<p>A new album has been created with the name <%= albumName %>.
You can now return to your <a href="index.jsp">home page</a>.

<% } %>

</body>
</html>
