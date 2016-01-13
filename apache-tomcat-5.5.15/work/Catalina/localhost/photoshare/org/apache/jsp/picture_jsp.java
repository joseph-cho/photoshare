package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import photoshare.AlbumDao;
import photoshare.AlbumBean;
import photoshare.NewUserDao;
import photoshare.NewUserBean;
import photoshare.Picture;
import photoshare.PictureDao;
import photoshare.TagDao;
import photoshare.TagBean;
import photoshare.CommentBean;
import photoshare.CommentDao;
import org.apache.commons.fileupload.FileUploadException;
import java.util.List;
import java.sql.Date;

public final class picture_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      photoshare.ImageUploadBean imageUploadBean = null;
      synchronized (_jspx_page_context) {
        imageUploadBean = (photoshare.ImageUploadBean) _jspx_page_context.getAttribute("imageUploadBean", PageContext.PAGE_SCOPE);
        if (imageUploadBean == null){
          imageUploadBean = new photoshare.ImageUploadBean();
          _jspx_page_context.setAttribute("imageUploadBean", imageUploadBean, PageContext.PAGE_SCOPE);
          out.write('\n');
          org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("imageUploadBean"), request);
          out.write('\n');
        }
      }
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");

  String picId = request.getParameter("picture_id");
  int pic_id = Integer.parseInt(picId);
  NewUserDao user = new NewUserDao();

  String userEmail = request.getUserPrincipal().getName();
  int user_id = user.getUserId(userEmail);


      out.write("\n");
      out.write("<a href = \"index.jsp\">Go home</a><br>\n");
      out.write("\n");
      out.write("<h1>Picture ");
      out.print( pic_id );
      out.write("<br></h1>\n");
      out.write("\n");
      out.write("<img src=\"/photoshare/img?picture_id=");
      out.print(pic_id);
      out.write("\" height=\"400\"/>\n");

  PictureDao pictureDao = new PictureDao();
  Picture p = pictureDao.load(pic_id);
  CommentDao commentDao = new CommentDao();

      out.write("\n");
      out.write("<p>Caption: \"");
      out.print( p.getCaption() );
      out.write("\" </p>\n");
      out.write("\n");
      out.write("<p>\n");
      out.write("  <form action=\"addLike.jsp\" method=\"post\">\n");
      out.write("    <input type=\"hidden\" name=\"picid2\" value=\"");
      out.print(pic_id);
      out.write("\"/>\n");
      out.write("    <input type=\"hidden\" name=\"like\" value=\"addLike\"/>\n");
      out.write("    <input type=\"submit\" value=\"Like\"/><br/>\n");
      out.write("</form>\n");
      out.write("\n");
      out.write("<a href = \"likers.jsp?picture_id=");
      out.print(pic_id);
      out.write("\">Likes:</a> ");
      out.print(commentDao.countLikes(pic_id));
      out.write("</a>\n");
      out.write("\n");
      out.write("</p>\n");
      out.write("\n");
      out.write("<p>Tags:\n");
      out.write("  ");

    TagDao tagDao = new TagDao();
    List<String> picsTags = tagDao.getTagsOfPic(pic_id);

    for (String picTag : picsTags) {
  
      out.write("\n");
      out.write("\n");
      out.write("    <span>");
      out.print( picTag );
      out.write("</span>,\n");
      out.write("\n");
      out.write("  ");

    }
  
      out.write("\n");
      out.write("</p>\n");
      out.write("\n");
      out.write("<h2>Add a tag</h2>\n");
      out.write("<form action=\"addTag.jsp\" method=\"post\">\n");
      out.write("  <input type=\"hidden\" name=\"pic_id3\" value=\"");
      out.print(pic_id);
      out.write("\"/>\n");
      out.write("  Enter tags (separate by commas or spaces): <input type=\"text\" name=\"tags\"/>\n");
      out.write("  <input type=\"submit\" value=\"Add Tags\"/>\n");
      out.write("</form>\n");
      out.write("\n");
      out.write("<h2>Comments</h2>\n");
      out.write("\n");

  //this makes sure users can't comment on their own photos
  int owner_id = pictureDao.getPicsOwner(pic_id);
  if (user_id != owner_id) {

      out.write("\n");
      out.write("<form action=\"addComment.jsp\" method=\"post\">\n");
      out.write("  <input type=\"hidden\" name=\"picid\" value=\"");
      out.print(pic_id);
      out.write("\"/>\n");
      out.write("  <input type=\"text\" name=\"commentText\" placeholder=\"Type here\"/>\n");
      out.write("  <input type=\"submit\" value=\"Add Comment\"/><br>\n");
      out.write("</form>\n");

}

      out.write("\n");
      out.write("\n");
      out.write("<table id =\"commentsection\" width=\"400px\">\n");
      out.write("  <tr>\n");
      out.write("    <td>Commenter:</td>\n");
      out.write("    <td>Comment date:</td>\n");
      out.write("    <td>Comment:</td>\n");
      out.write("  </tr>\n");
      out.write("\n");
      out.write("  ");

    NewUserDao commenter = new NewUserDao();
  
    List<CommentBean> commentBeans = commentDao.getCommentsOfPic(pic_id);
  
    for(CommentBean c : commentBeans) {
  
      out.write("\n");
      out.write("\n");
      out.write("  \n");
      out.write("  <tr>\n");
      out.write("    <td>");
      out.print( commenter.getUsersName(c.getOwnerId()) );
      out.write("</td>\n");
      out.write("    <td>");
      out.print( c.getDateCreated() );
      out.write("</td>\n");
      out.write("    <td>");
      out.print( c.getCommentText() );
      out.write("</td>\n");
      out.write("  </tr>\n");
      out.write("  \n");
      out.write("\n");
      out.write("  ");

    }
  
      out.write("\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
