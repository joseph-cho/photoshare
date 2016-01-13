<%--
  Author: Giorgos Zervas <cs460tf@cs.bu.edu>
--%>
<%@ page import="photoshare.AlbumDao" %>
<%@ page import="photoshare.AlbumBean" %>
<%@ page import="photoshare.Picture" %>
<%@ page import="photoshare.PictureDao" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.NewUserBean" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="imageUploadBean"
             class="photoshare.ImageUploadBean">
    <jsp:setProperty name="imageUploadBean" property="*"/>
</jsp:useBean>

<jsp:useBean id="AlbumBean"
             class="photoshare.AlbumBean" />
<jsp:setProperty name="AlbumBean" property="*"/>

<html>
<head><title>PhotoShare</title></head>

<body>
<h1>PhotoShare</h1>

Welcome, <b><code><%= request.getUserPrincipal().getName()  %></code></b><br>

<a href="/photoshare/logout.jsp">log out</a>

<%
    NewUserDao person = new NewUserDao();
    String user_email = request.getUserPrincipal().getName();
    int user_id = person.getUserId(user_email);
%>

<h2>Browse PhotoShare!</h2>

<p>Pictures</p>
<table>
    <tr>
        <%
            PictureDao pictureDao = new PictureDao();
            List<Integer> pictureIds = pictureDao.allPicturesIds();
            for (Integer pictureId : pictureIds) {
        %>
        <td><a href="picture.jsp?picture_id=<%= pictureId %>">
            <img src="/photoshare/img?t=1&picture_id=<%= pictureId %>"/>
        </a>
        </td>
        <%
            }
        %>
    </tr>
</table><br>

<p>Albums</p>
<table>
    <td>
        <%
            AlbumDao albumDao = new AlbumDao();
            List<AlbumBean> albums = albumDao.getAllAlbums();
            for (AlbumBean al : albums) {
        %>
        <td><a href="album.jsp?albumid=<%= al.getAlbumId() %>"><%= al.getName() %>
        </a>
        </td>
        <%
            }
        %>
    </td>
</table>


<%--<h2>Upload a new picture</h2>

<form action="index.jsp" enctype="multipart/form-data" method="post">
    Filename: <input type="file" name="filename"/>
    <input type="submit" value="Upload"/><br/>
</form>

<%
    PictureDao pictureDao = new PictureDao();
    try {
        Picture picture = imageUploadBean.upload(request);
        if (picture != null) {
            pictureDao.save(picture);
        }
    } catch (FileUploadException e) {
        e.printStackTrace();
    }
%>
--%>

<h2>Your Albums</h2>
<a href = "createalbum.jsp">Create new album</a><br>

    <%
        AlbumDao album = new AlbumDao();
        List<AlbumBean> usersAlbums = album.getAlbumsOfUser(user_id);
    %>

<table width="400">
    <tr>
        <td>Name</td>
        <td>Date</td>
    </tr>

    <%
        for (AlbumBean al : usersAlbums) {
    %>

    <tr>
        <td><a href="album.jsp?albumid=<%= al.getAlbumId() %>"><%= al.getName() %></a></td>
        <td><%= al.getDateCreated() %></td>
        <td>
            <form action = "index.jsp" method="post">
                <input type="hidden" name="action" value="deleteAlbum"/>
                 <input type="hidden" name="albumId" value="<%= al.getAlbumId() %>"/>
                <input type="submit" value="Delete"/>
            </form>
        </td>
    </tr>

    <%
        }
    %>
</table>

<%
    if (request.getParameter("action") != null && request.getParameter("action").equals("deleteAlbum")) {
        Integer albumId = Integer.parseInt(request.getParameter("albumId"));
        album.deleteAlbum(albumId);
    }
%>

<h2>Your Pictures</h2>

<table width="400px">

    <%
        PictureDao picture = new PictureDao();
        List<Integer> usersPictures = picture.usersPicturesIds(user_id);
        for (Integer picId : usersPictures) {
    %>
    <tr>
        <td><a href="picture.jsp?picture_id=<%= picId %>">
            <img src="/photoshare/img?t=1&picture_id=<%= picId %>"/>
            <% Picture pic = picture.load(picId); %>
        </a></td>
        <td><%= pic.getCaption() %></td>
        <%
            if (request.getParameter("action") != null && request.getParameter("action").equals("deletePicture")) {
                int picturesId = Integer.parseInt(request.getParameter("picID"));
                picture.delete(picturesId);
            } 
        %>
        <td>
            <form action="index.jsp" method="post">
                <input type="hidden" name="action" value="deletePicture"/>
                <input type="hidden" name="picID" value="<%= pic.getId() %>" />
                <input type="submit" value="Delete"/>
        </td>
    </tr>
    <%
        }
    %>

</table>


<%--<h2>All Pictures</h2>
<table>
    <tr>
        <%
            List<Integer> pictureIds = pictureDao.allPicturesIds();
            for (Integer pictureId : pictureIds) {
        %>
        <td><a href="/photoshare/img?picture_id=<%= pictureId %>">
            <img src="/photoshare/img?t=1&picture_id=<%= pictureId %>"/>
        </a>
        </td>
        <%
            }
        %>
    </tr>
</table>
--%>

<h2>Friends</h2>

<a href="friendFinder.jsp">See all users</a><br>
    <%
        List<NewUserBean> friends = person.getFriendsOfUser(user_id);
    %>

<p>Your friends</p>
<table width="500px">
    <tr>
        <td>First Names</td>
        <td>Last Names</td>
        <td>Emails</td>
    </tr>

<%
    for (NewUserBean friend : friends) {
%>

    <tr>
        <td><%= friend.getFirstname() %></td>
        <td><%= friend.getLastname() %></td>
        <td><%= friend.getEmail() %></td>

        <td> 
            <form action="index.jsp" method="post">
                <input type="hidden" name="action" value="deleteFriend"/>
                <input type="hidden" name="email" value="<%= friend.getEmail() %>"/>
                <input type="submit" value="Delete"/>
            </form>
        </td>
    </tr>
<%
    }
%>
</table>

<%
    if (request.getParameter("action") != null && request.getParameter("action").equals("deleteFriend")) {
        NewUserDao currentFriend = new NewUserDao();
        String friendEmail = request.getParameter("email");
        int friend_id = currentFriend.getUserId(friendEmail);
        person.deleteFriend(user_id, friend_id);

    }
%>


</body>
</html>
