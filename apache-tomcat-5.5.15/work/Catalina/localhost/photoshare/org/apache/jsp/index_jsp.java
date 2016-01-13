package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import photoshare.AlbumDao;
import photoshare.AlbumBean;
import photoshare.Picture;
import photoshare.PictureDao;
import photoshare.NewUserDao;
import photoshare.NewUserBean;
import org.apache.commons.fileupload.FileUploadException;
import java.util.List;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      photoshare.ImageUploadBean imageUploadBean = null;
      synchronized (_jspx_page_context) {
        imageUploadBean = (photoshare.ImageUploadBean) _jspx_page_context.getAttribute("imageUploadBean", PageContext.PAGE_SCOPE);
        if (imageUploadBean == null){
          imageUploadBean = new photoshare.ImageUploadBean();
          _jspx_page_context.setAttribute("imageUploadBean", imageUploadBean, PageContext.PAGE_SCOPE);
          out.write("\n");
          out.write("    ");
          org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("imageUploadBean"), request);
          out.write('\n');
        }
      }
      out.write('\n');
      out.write('\n');
      photoshare.AlbumBean AlbumBean = null;
      synchronized (_jspx_page_context) {
        AlbumBean = (photoshare.AlbumBean) _jspx_page_context.getAttribute("AlbumBean", PageContext.PAGE_SCOPE);
        if (AlbumBean == null){
          AlbumBean = new photoshare.AlbumBean();
          _jspx_page_context.setAttribute("AlbumBean", AlbumBean, PageContext.PAGE_SCOPE);
        }
      }
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("AlbumBean"), request);
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head><title>PhotoShare</title></head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("<h1>PhotoShare</h1>\n");
      out.write("\n");
      out.write("Welcome, <b><code>");
      out.print( request.getUserPrincipal().getName()  );
      out.write("</code></b><br>\n");
      out.write("\n");
      out.write("<a href=\"/photoshare/logout.jsp\">log out</a>\n");
      out.write("\n");

    NewUserDao person = new NewUserDao();
    String user_email = request.getUserPrincipal().getName();
    int user_id = person.getUserId(user_email);

      out.write("\n");
      out.write("\n");
      out.write("<h2>Browse PhotoShare!</h2>\n");
      out.write("\n");
      out.write("<p>Pictures</p>\n");
      out.write("<table>\n");
      out.write("    <tr>\n");
      out.write("        ");

            PictureDao pictureDao = new PictureDao();
            List<Integer> pictureIds = pictureDao.allPicturesIds();
            for (Integer pictureId : pictureIds) {
        
      out.write("\n");
      out.write("        <td><a href=\"picture.jsp?picture_id=");
      out.print( pictureId );
      out.write("\">\n");
      out.write("            <img src=\"/photoshare/img?t=1&picture_id=");
      out.print( pictureId );
      out.write("\"/>\n");
      out.write("        </a>\n");
      out.write("        </td>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("    </tr>\n");
      out.write("</table><br>\n");
      out.write("\n");
      out.write("<p>Albums</p>\n");
      out.write("<table>\n");
      out.write("    <td>\n");
      out.write("        ");

            AlbumDao albumDao = new AlbumDao();
            List<AlbumBean> albums = albumDao.getAllAlbums();
            for (AlbumBean al : albums) {
        
      out.write("\n");
      out.write("        <td><a href=\"album.jsp?albumid=");
      out.print( al.getAlbumId() );
      out.write('"');
      out.write('>');
      out.print( al.getName() );
      out.write("\n");
      out.write("        </a>\n");
      out.write("        </td>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("    </td>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<h2>Your Albums</h2>\n");
      out.write("<a href = \"createalbum.jsp\">Create new album</a><br>\n");
      out.write("\n");
      out.write("    ");

        AlbumDao album = new AlbumDao();
        List<AlbumBean> usersAlbums = album.getAlbumsOfUser(user_id);
    
      out.write("\n");
      out.write("\n");
      out.write("<table width=\"400\">\n");
      out.write("    <tr>\n");
      out.write("        <td>Name</td>\n");
      out.write("        <td>Date</td>\n");
      out.write("    </tr>\n");
      out.write("\n");
      out.write("    ");

        for (AlbumBean al : usersAlbums) {
    
      out.write("\n");
      out.write("\n");
      out.write("    <tr>\n");
      out.write("        <td><a href=\"album.jsp?albumid=");
      out.print( al.getAlbumId() );
      out.write('"');
      out.write('>');
      out.print( al.getName() );
      out.write("</a></td>\n");
      out.write("        <td>");
      out.print( al.getDateCreated() );
      out.write("</td>\n");
      out.write("        <td>\n");
      out.write("            <form action = \"index.jsp\" method=\"post\">\n");
      out.write("                <input type=\"hidden\" name=\"action\" value=\"deleteAlbum\"/>\n");
      out.write("                 <input type=\"hidden\" name=\"albumId\" value=\"");
      out.print( al.getAlbumId() );
      out.write("\"/>\n");
      out.write("                <input type=\"submit\" value=\"Delete\"/>\n");
      out.write("            </form>\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("\n");
      out.write("    ");

        }
    
      out.write("\n");
      out.write("</table>\n");
      out.write("\n");

    if (request.getParameter("action") != null && request.getParameter("action").equals("deleteAlbum")) {
        Integer albumId = Integer.parseInt(request.getParameter("albumId"));
        album.deleteAlbum(albumId);
    }

      out.write("\n");
      out.write("\n");
      out.write("<h2>Your Pictures</h2>\n");
      out.write("\n");
      out.write("<table width=\"400px\">\n");
      out.write("\n");
      out.write("    ");

        PictureDao picture = new PictureDao();
        List<Integer> usersPictures = picture.usersPicturesIds(user_id);
        for (Integer picId : usersPictures) {
    
      out.write("\n");
      out.write("    <tr>\n");
      out.write("        <td><a href=\"picture.jsp?picture_id=");
      out.print( picId );
      out.write("\">\n");
      out.write("            <img src=\"/photoshare/img?t=1&picture_id=");
      out.print( picId );
      out.write("\"/>\n");
      out.write("            ");
 Picture pic = picture.load(picId); 
      out.write("\n");
      out.write("        </a></td>\n");
      out.write("        <td>");
      out.print( pic.getCaption() );
      out.write("</td>\n");
      out.write("        ");

            if (request.getParameter("action") != null && request.getParameter("action").equals("deletePicture")) {
                int picturesId = Integer.parseInt(request.getParameter("picID"));
                picture.delete(picturesId);
            } 
        
      out.write("\n");
      out.write("        <td>\n");
      out.write("            <form action=\"index.jsp\" method=\"post\">\n");
      out.write("                <input type=\"hidden\" name=\"action\" value=\"deletePicture\"/>\n");
      out.write("                <input type=\"hidden\" name=\"picID\" value=\"");
      out.print( pic.getId() );
      out.write("\" />\n");
      out.write("                <input type=\"submit\" value=\"Delete\"/>\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("    ");

        }
    
      out.write("\n");
      out.write("\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<h2>Friends</h2>\n");
      out.write("\n");
      out.write("<a href=\"friendFinder.jsp\">See all users</a><br>\n");
      out.write("    ");

        List<NewUserBean> friends = person.getFriendsOfUser(user_id);
    
      out.write("\n");
      out.write("\n");
      out.write("<p>Your friends</p>\n");
      out.write("<table width=\"500px\">\n");
      out.write("    <tr>\n");
      out.write("        <td>First Names</td>\n");
      out.write("        <td>Last Names</td>\n");
      out.write("        <td>Emails</td>\n");
      out.write("    </tr>\n");
      out.write("\n");

    for (NewUserBean friend : friends) {

      out.write("\n");
      out.write("\n");
      out.write("    <tr>\n");
      out.write("        <td>");
      out.print( friend.getFirstname() );
      out.write("</td>\n");
      out.write("        <td>");
      out.print( friend.getLastname() );
      out.write("</td>\n");
      out.write("        <td>");
      out.print( friend.getEmail() );
      out.write("</td>\n");
      out.write("\n");
      out.write("        <td> \n");
      out.write("            <form action=\"index.jsp\" method=\"post\">\n");
      out.write("                <input type=\"hidden\" name=\"action\" value=\"deleteFriend\"/>\n");
      out.write("                <input type=\"hidden\" name=\"email\" value=\"");
      out.print( friend.getEmail() );
      out.write("\"/>\n");
      out.write("                <input type=\"submit\" value=\"Delete\"/>\n");
      out.write("            </form>\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");

    }

      out.write("\n");
      out.write("</table>\n");
      out.write("\n");

    if (request.getParameter("action") != null && request.getParameter("action").equals("deleteFriend")) {
        NewUserDao currentFriend = new NewUserDao();
        String friendEmail = request.getParameter("email");
        int friend_id = currentFriend.getUserId(friendEmail);
        person.deleteFriend(user_id, friend_id);

    }

      out.write("\n");
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
