<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.NewUserBean" %>
<%@ page import="java.util.List" %>

<html>
<head><title>Find Finder</title></head>

<body>

<h2>Showing all registered users</h2>
<a href="index.jsp">Go back</a><br>

<%
    NewUserDao user = new NewUserDao();
    String userEmail = request.getUserPrincipal().getName();
    int user_id = user.getUserId(userEmail);

    NewUserDao friend = new NewUserDao();
    List<NewUserBean> friends = friend.getAllUsers(user_id);
%>

<form action="friendSearch.jsp" method="post">
    <input type="text" name="friendSearch" placeholder="name or email">
    <input type="submit" value="search">
</form>

<table width="500px">
    <tr>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Email</td>
    </tr>

<%
    for (NewUserBean f : friends) {
%>

    <tr>
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
    </tr>
<%
    }
%>
</table>

<%
    if (request.getParameter("action") != null && request.getParameter("action").equals("add")) {
        String friendEmail = request.getParameter("email");
        int friend_id = friend.getUserId(friendEmail);
        friend.addFriend(user_id, friend_id);

    }
%>


</body>
</html>