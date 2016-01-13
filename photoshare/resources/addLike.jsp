<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.AlbumDao" %>
<%@ page import="photoshare.CommentBean"%>
<%@ page import="photoshare.CommentDao"%>
<%@ page import="java.sql.Date"%>

<%--
this page adds a "like" comment to the DB that functions like
adding a like to the picture
--%>
<html>
<head><title>Liking the picture</title></head>

<body>

<% 
   String err = null;
   NewUserDao liker = new NewUserDao();
   String userEmail = request.getUserPrincipal().getName();

   int owner_id = liker.getUserId(userEmail);

   String possibleLike = request.getParameter("like");
   CommentDao comment = new CommentDao();
   int picture_id;
   String pic_id = request.getParameter("picid2");
   picture_id = Integer.parseInt(pic_id);

   boolean success = comment.create(owner_id, picture_id, possibleLike);
   if (success != true) {
     err = "error... :/";
   }
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<a href="browsealbums.jsp">Browse albums</a>
<% } else { %>

<h2>Success!</h2>

You can now return to <a href="picture.jsp?picture_id=<%= picture_id %>">the picture</a>.

<% } %>

</body>
</html>
