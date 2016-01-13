<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.AlbumDao" %>
<%@ page import="photoshare.CommentBean"%>
<%@ page import="photoshare.CommentDao"%>
<%@ page import="java.sql.Date"%>
<%--
private static final String ADD_COMMENT_STMT = "INSERT INTO " + 
    "comments (owner_id, picture_id, comment_text, date_created) VALUES (?, ?, ?, now())";
--%>

<%--
public boolean create(int owner_id, int picture_id, String comment_text)
--%>

<%--
this page adds a comment to the DB 
--%>
<html>
<head><title>Adding comment to picture</title></head>

<body>

<% 
   String err = null;
   NewUserDao commenter = new NewUserDao();
   String userEmail = request.getUserPrincipal().getName();

   int owner_id = commenter.getUserId(userEmail);

   String comment_text = request.getParameter("commentText");
   CommentDao comment = new CommentDao();
   int picture_id;
   String pic_id = request.getParameter("picid");
   picture_id = Integer.parseInt(pic_id);

   boolean success = comment.create(owner_id, picture_id, comment_text);
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
