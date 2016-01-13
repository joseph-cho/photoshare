<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.NewUserBean" %>
<%@ page import="photoshare.AlbumDao" %>
<%@ page import="photoshare.CommentDao" %>
<%@ page import="photoshare.CommentBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"%>
 
<html>
<head><title>Searching for your friend</title></head>

<body>

<% 
   //List<NewUserBean> getAllUsers(int user_id)
   String err = null;
   NewUserDao user = new NewUserDao();
   String search = request.getParameter("friendSearch");

   String userEmail = request.getUserPrincipal().getName();
   int user_id = user.getUserId(userEmail);

   List<NewUserBean> friends = user.getAllUsers(user_id);

   for (NewUserBean f : friends) {

   if (f.getFirstname().equals(search) || f.getLastname().equals(search) || f.getEmail().equals(search)) {
%>
  <p>Search result: </p>
   <table>
    <td><%= f.getFirstname() %></td>
    <td><%= f.getLastname() %></td>
    <td><%= f.getEmail() %></td>
    <td>
      <form action="friendFinder.jsp" method="post">
                <input type="hidden" name="action" value="add"/>
                <input type="hidden" name="email" value="<%= f.getEmail() %>"/>
                <input type="submit" value="Add"/>
            </form>
    </td>
  
  

  <%
  }
 }
%>
</table>

<%
    if (request.getParameter("action") != null && request.getParameter("action").equals("add")) {
        String friendEmail = request.getParameter("email");
        int friend_id = user.getUserId(friendEmail);
        user.addFriend(user_id, friend_id);

    }
%>

<a href="friendFinder.jsp">Go Back</a>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<p> <a href="friendFinder.jsp">Go Back</a>
<% } %>

</body>
</html>