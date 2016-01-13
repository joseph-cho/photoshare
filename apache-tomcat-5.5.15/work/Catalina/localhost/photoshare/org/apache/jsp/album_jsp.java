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

public final class album_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<head><title>Your albums</title></head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");

  String albumsId = request.getParameter("albumid");
  int album_id = Integer.parseInt(albumsId);
  NewUserDao user = new NewUserDao();
  String userEmail = request.getUserPrincipal().getName();
  int user_id = user.getUserId(userEmail);

      out.write("\n");
      out.write("<a href = \"index.jsp\">Back</a><br>\n");
      out.write("\n");
      out.write("<h2>Upload a new picture</h2>\n");
      out.write("\n");
      out.write("<form action=\"album.jsp?albumid=");
      out.print( album_id );
      out.write("\" enctype=\"multipart/form-data\" method=\"post\">\n");
      out.write("    Filename: <input type=\"file\" name=\"filename\"/><br>\n");
      out.write("    Caption: <input type=\"text\" name=\"caption\"/><br>\n");
      out.write("    <input type=\"submit\" value=\"Upload\"/><br/>\n");
      out.write("</form>\n");
      out.write("\n");

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

      out.write("\n");
      out.write("\n");
      out.write("<h2>Pictures</h2>\n");
      out.write("<table>\n");
      out.write("  <tr>\n");
      out.write("    <td>Picture</td>\n");
      out.write("    <td>Caption</td>\n");
      out.write("    <td>Comments</td>\n");
      out.write("  </tr>\n");
      out.write("\n");

  AlbumDao album = new AlbumDao();
  List<Integer> picsOfAlbum = album.getPicsOfAlbum(album_id);

  for (Integer picId : picsOfAlbum) {

      out.write("\n");
      out.write("\n");
      out.write("  <tr>\n");
      out.write("    <td>\n");
      out.write("      <a href=\"picture.jsp?picture_id=");
      out.print( picId );
      out.write("\">\n");
      out.write("        <img src=\"/photoshare/img?t=1&picture_id=");
      out.print( picId );
      out.write("\"/>\n");
      out.write("        ");
 Picture p = pictureDao.load(picId); 
      out.write("\n");
      out.write("      </a>\n");
      out.write("    </td>\n");
      out.write("\n");
      out.write("    <td>");
      out.print( p.getCaption() );
      out.write("</td>\n");
      out.write("\n");
      out.write("    <td>\n");
      out.write("      <table id=\"comments\" width=\"350px\">\n");
      out.write("        <tr>\n");
      out.write("          <td>Commenter</td>\n");
      out.write("          <td>Comment posted on</td>\n");
      out.write("          <td>Comment</td>\n");
      out.write("        </tr>\n");
      out.write("\n");
      out.write("        ");

          NewUserDao commenter = new NewUserDao();
          CommentDao comment = new CommentDao();
          List<CommentBean> commentBeans = comment.getCommentsOfPic(picId);
          for(CommentBean c : commentBeans) {
        
      out.write("\n");
      out.write("        <tr>\n");
      out.write("          <td>");
      out.print( commenter.getUsersName(c.getOwnerId()) );
      out.write("</td>\n");
      out.write("          <td>");
      out.print( c.getDateCreated() );
      out.write("</td>\n");
      out.write("          <td>");
      out.print( c.getCommentText() );
      out.write("</td>\n");
      out.write("        </tr>\n");
      out.write("\n");
      out.write("        ");

          }
        
      out.write("\n");
      out.write("      </table>\n");
      out.write("    </td>\n");
      out.write("  </tr>\n");
      out.write("  ");

    }
  
      out.write("\n");
      out.write("</table>\n");
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
