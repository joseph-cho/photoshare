package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import photoshare.NewUserDao;
import photoshare.NewUserBean;
import java.util.List;

public final class friendFinder_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<head><title>Find Finder</title></head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<h2>Showing all registered users</h2>\n");
      out.write("<a href=\"index.jsp\">Go back</a><br>\n");
      out.write("\n");

    NewUserDao user = new NewUserDao();
    String userEmail = request.getUserPrincipal().getName();
    int user_id = user.getUserId(userEmail);

    NewUserDao friend = new NewUserDao();
    List<NewUserBean> friends = friend.getAllUsers(user_id);

      out.write("\n");
      out.write("\n");
      out.write("<form action=\"friendSearch.jsp\" method=\"post\">\n");
      out.write("    <input type=\"text\" name=\"friendSearch\" placeholder=\"name or email\">\n");
      out.write("    <input type=\"submit\" value=\"search\">\n");
      out.write("</form>\n");
      out.write("\n");
      out.write("<table width=\"500px\">\n");
      out.write("    <tr>\n");
      out.write("        <td>First Name</td>\n");
      out.write("        <td>Last Name</td>\n");
      out.write("        <td>Email</td>\n");
      out.write("    </tr>\n");
      out.write("\n");

    for (NewUserBean f : friends) {

      out.write("\n");
      out.write("\n");
      out.write("    <tr>\n");
      out.write("        <td>");
      out.print( f.getFirstname() );
      out.write("</td>\n");
      out.write("        <td>");
      out.print( f.getLastname() );
      out.write("</td>\n");
      out.write("        <td>");
      out.print( f.getEmail() );
      out.write("</td>\n");
      out.write("\n");
      out.write("        <td> \n");
      out.write("            <form action=\"friendFinder.jsp\" method=\"post\">\n");
      out.write("                <input type=\"hidden\" name=\"action\" value=\"add\"/>\n");
      out.write("                <input type=\"hidden\" name=\"email\" value=\"");
      out.print( f.getEmail() );
      out.write("\"/>\n");
      out.write("                <input type=\"submit\" value=\"Add\"/>\n");
      out.write("            </form>\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");

    }

      out.write("\n");
      out.write("</table>\n");
      out.write("\n");

    if (request.getParameter("action") != null && request.getParameter("action").equals("add")) {
        String friendEmail = request.getParameter("email");
        int friend_id = friend.getUserId(friendEmail);
        friend.addFriend(user_id, friend_id);

    }

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
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
