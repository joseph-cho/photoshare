package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import photoshare.NewUserDao;
import photoshare.AlbumDao;
import photoshare.TagDao;

public final class addTag_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("<head><title>Adding Tags</title></head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
 
   String err = null;
   String tagWords  = request.getParameter("tags");
   TagDao tagDao = new TagDao();

   String spicture_id = request.getParameter("pic_id3");
   int picture_id = Integer.parseInt(spicture_id);
   
   boolean success = tagDao.create(picture_id, tagWords);


   if (success != true) {
     err = "Could not add tags :/";
   }

      out.write('\n');
      out.write('\n');
 if (err != null) { 
      out.write("\n");
      out.write("<font color=red><b>Error: ");
      out.print( err );
      out.write("</b></font>\n");
      out.write("<p>\n");
 }
   else { 
      out.write("\n");
      out.write("\n");
      out.write("<h2>Success!</h2>\n");
      out.write("\n");
      out.write("<p>A your tags have been added!</p>\n");
      out.write("<a href=\"picture.jsp?picture_id=");
      out.print( picture_id );
      out.write("\">Go back</a>.\n");
      out.write("\n");
 } 
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
