package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class newuser2_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("<head><title>Another New User Example</title></head>\n");
      out.write("\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<h2>New user info</h2>\n");
      out.write("\n");
      out.write("<form action=\"adduser.jsp\" method=\"post\">\n");
      out.write("  <input type=\"text\" name=\"email\" placeholder=\"Email\"/><br>\n");
      out.write("  <input type=\"password\" name=\"password1\" placeholder=\"Password\"/><br>\n");
      out.write("  <input type=\"password\" name=\"password2\" placeholder=\"Re-type password\"/><br>\n");
      out.write("  <input type=\"text\" name=\"firstname\" placeholder=\"First name\"/><br>\n");
      out.write("  <input type=\"text\" name=\"lastname\" placeholder=\"Last name\"/><br>\n");
      out.write("  <input type = \"text\" name =\"dob\" placeholder=\"mmddyyyy\"><br>\n");
      out.write("  <br>\n");
      out.write("  <h3>Optional:</h3>\n");
      out.write("  Gender: <input type=\"radio\" name=\"gender\" value=\"F\"> Female\n");
      out.write("  <input type=\"radio\" name=\"gender\" value=\"M\"> Male <br>\n");
      out.write("  <input type = \"text\" name =\"hometown\" placeholder=\"Hometown\"><br>\n");
      out.write("  <input type=\"submit\" value=\"Create\"/><br/>\n");
      out.write("</form>\n");
      out.write("\n");
      out.write("<a href=\"index.jsp\">Go back</a><br>\n");
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
