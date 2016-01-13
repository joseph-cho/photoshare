
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.AlbumDao" %>
<%@ page import="photoshare.AlbumBean" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.NewUserBean" %>
<%@ page import="photoshare.Picture" %>
<%@ page import="photoshare.PictureDao" %>
<%@ page import="photoshare.TagDao" %>
<%@ page import="photoshare.TagBean" %>
<%@ page import="photoshare.CommentBean" %>
<%@ page import="photoshare.CommentDao" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="imageUploadBean" class="photoshare.ImageUploadBean">
<jsp:setProperty name="imageUploadBean" property="*"/>
</jsp:useBean>


<html>
<head><title>Your albums</title></head>

<body>

<%
  String albumsId = request.getParameter("albumid");
  int album_id = Integer.parseInt(albumsId);
  NewUserDao user = new NewUserDao();
  String userEmail = request.getUserPrincipal().getName();
  int user_id = user.getUserId(userEmail);
%>
<a href = "index.jsp">Back</a><br>

<h2>Upload a new picture</h2>

<form action="album.jsp?albumid=<%= album_id %>" enctype="multipart/form-data" method="post">
    Filename: <input type="file" name="filename"/><br>
    Caption: <input type="text" name="caption"/><br>
    <input type="submit" value="Upload"/><br/>
</form>

<%
    PictureDao pictureDao = new PictureDao();
    TagDao tag = new TagDao();
    try {
        Picture picture = imageUploadBean.upload(request);
        int owner_id = user_id;

        if (picture != null) {
            pictureDao.save(picture, album_id, user_id);
        }
    } catch (FileUploadException e) {
        e.printStackTrace();
    }
%>

<h2>Pictures</h2>
<table>
  <tr>
    <td>Picture</td>
    <td>Caption</td>
    <td>Comments</td>
  </tr>

<%
  AlbumDao album = new AlbumDao();
  List<Integer> picsOfAlbum = album.getPicsOfAlbum(album_id);

  for (Integer picId : picsOfAlbum) {
%>

  <tr>
    <td>
      <a href="picture.jsp?picture_id=<%= picId %>">
        <img src="/photoshare/img?t=1&picture_id=<%= picId %>"/>
        <% Picture p = pictureDao.load(picId); %>
      </a>
    </td>

    <td><%= p.getCaption() %></td>

    <td>
      <table id="comments" width="350px">
        <tr>
          <td>Commenter</td>
          <td>Comment posted on</td>
          <td>Comment</td>
        </tr>

        <%
          NewUserDao commenter = new NewUserDao();
          CommentDao comment = new CommentDao();
          List<CommentBean> commentBeans = comment.getCommentsOfPic(picId);
          for(CommentBean c : commentBeans) {
        %>
        <tr>
          <td><%= commenter.getUsersName(c.getOwnerId()) %></td>
          <td><%= c.getDateCreated() %></td>
          <td><%= c.getCommentText() %></td>
        </tr>

        <%
          }
        %>
      </table>
    </td>
  </tr>
  <%
    }
  %>
</table>

</body>
</html>
