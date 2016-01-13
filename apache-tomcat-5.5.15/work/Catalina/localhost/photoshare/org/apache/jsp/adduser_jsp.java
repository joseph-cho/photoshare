package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import photoshare.NewUserDao;

public final class adduser_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("<head><title>Adding New User</title></head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
 
   String err = null;
   String email  = request.getParameter("email");
   String password1 = request.getParameter("password1");
   String password2 = request.getParameter("password2");
   String firstname = request.getParameter("firstname");
   String lastname = request.getParameter("lastname");
   String dob = request.getParameter("dob");
   String gender = request.getParameter("gender");
   String hometown = request.getParameter("hometown");


   if (!email.equals("")) {
     if (!password1.equals(password2)) {
       err = "Both password strings must match";

     }
     else if (password1.length() < 4) {
       err = "Your password must be at least four characters long";
     }
     else if (firstname.equals("") || lastname.equals("")) {
       err = "Please enter your name";
     }
     else if (dob == null) {
       err = "Please enter your birthday";
     } 
     else if (dob.length() != 8) {
       err = "Invalid date of birth entered";
     }
     else {
       // We have valid inputs, try to create the user
       NewUserDao newUserDao = new NewUserDao();

       boolean success = newUserDao.create(email, password1, firstname, lastname, dob, gender, hometown);
       if (!success) {
         err = "Couldn't create user (that email may already be in use)";
       }
     }
   } else {
	 err = "You have to provide an email";

   }

      out.write('\n');
      out.write('\n');
 if (err != null) { 
      out.write("\n");
      out.write("<font color=red><b>Error: ");
      out.print( err );
      out.write("</b></font>\n");
      out.write("<p> <a href=\"newuser2.jsp\">Go Back</a>\n");
 }
   else { 
      out.write("\n");
      out.write("\n");
      out.write("<h2>Success!</h2>\n");
      out.write("\n");
      out.write("<p>A new user has been created with email ");
      out.print( email );
      out.write(".\n");
      out.write("You can now return to the <a href=\"login.jsp\">login page</a>.\n");
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
