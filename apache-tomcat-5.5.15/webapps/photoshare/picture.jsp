
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
<%@ page import="java.sql.Date" %>

<html>

<body>

<%
  String picId = request.getParameter("picture_id");
  int pic_id = Integer.parseInt(picId);
  NewUserDao user = new NewUserDao();

  String userEmail = request.getUserPrincipal().getName();
  int user_id = user.getUserId(userEmail);

%>
<a href = "index.jsp">Go home</a><br>

<h1>Picture <%= pic_id %><br></h1>

<img src="/photoshare/img?picture_id=<%=pic_id%>" height="400"/>
<%
  PictureDao pictureDao = new PictureDao();
  Picture p = pictureDao.load(pic_id);
  CommentDao commentDao = new CommentDao();
%>
<p>Caption: "<%= p.getCaption() %>" </p>

<p>
  <form action="addLike.jsp" method="post">
    <input type="hidden" name="picid2" value="<%=pic_id%>"/>
    <input type="hidden" name="like" value="addLike"/>
    <input type="submit" value="Like"/><br/>
</form>

<a href = "likers.jsp?picture_id=<%=pic_id%>">Likes:</a> <%=commentDao.countLikes(pic_id)%></a>

</p>

<p>Tags:
  <%
    TagDao tagDao = new TagDao();
    List<String> picsTags = tagDao.getTagsOfPic(pic_id);

    for (String picTag : picsTags) {
  %>

    <span><%= picTag %></span>,

  <%
    }
  %>
</p>

<h2>Add a tag</h2>
<form action="addTag.jsp" method="post">
  <input type="hidden" name="pic_id3" value="<%=pic_id%>"/>
  Enter tags (separate by commas or spaces): <input type="text" name="tags"/>
  <input type="submit" value="Add Tags"/>
</form>

<h2>Comments</h2>

<%
  //this makes sure users can't comment on their own photos
  int owner_id = pictureDao.getPicsOwner(pic_id);
  if (user_id != owner_id) {
%>
<form action="addComment.jsp" method="post">
  <input type="hidden" name="picid" value="<%=pic_id%>"/>
  <input type="text" name="commentText" placeholder="Type here"/>
  <input type="submit" value="Add Comment"/><br>
</form>
<%
}
%>

<table id ="commentsection" width="400px">
  <tr>
    <td>Commenter:</td>
    <td>Comment date:</td>
    <td>Comment:</td>
  </tr>

  <%
    NewUserDao commenter = new NewUserDao();
  
    List<CommentBean> commentBeans = commentDao.getCommentsOfPic(pic_id);
  
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


</body>
</html>
