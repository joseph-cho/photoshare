<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.AlbumDao" %>
<%@ page import="photoshare.TagDao" %>

<html>
<head><title>Adding Tags</title></head>

<body>

<% 
   String err = null;
   String tagWords  = request.getParameter("tags");
   TagDao tagDao = new TagDao();

   String spicture_id = request.getParameter("pic_id3");
   int picture_id = Integer.parseInt(spicture_id);
   
   boolean success = tagDao.create(picture_id, tagWords);


   if (success != true) {
     err = "Could not add tags :/";
   }
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<p>
<% }
   else { %>

<h2>Success!</h2>

<p>A your tags have been added!</p>
<a href="picture.jsp?picture_id=<%= picture_id %>">Go back</a>.

<% } %>

</body>
</html>
