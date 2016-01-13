<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.AlbumDao" %>
<%@ page import="photoshare.CommentDao" %>
<%@ page import="photoshare.CommentBean" %>
<%@ page import="java.util.List" %>

<html>
<head><title>Displaying users who likes this photo</title></head>

<body>

<h2>People who liked this picture</h2>

<% 
   String err = null;
   String picIdString  = request.getParameter("picture_id");
   int picIdInt = Integer.parseInt(picIdString);
   NewUserDao liker = new NewUserDao();
 
   //String userEmail = request.getUserPrincipal().getName();
   //int user_id = liker.getUserId(userEmail);
   
%>

<%
  List<Integer> likers = liker.getLikers(picIdInt);
  for(int likersId : likers) {
%>
<table>
  <tr>
    <td><%= liker.getUsersName(likersId) %></td>
  </tr>
</table>
<%
  }
%>


<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

</body>
</html>
