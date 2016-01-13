<%-- Haohan Zhu --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.DateDao" %>
<%@ page import="java.sql.Date" %>
<jsp:useBean id="dateBean"
             class="photoshare.DateBean" />
<jsp:setProperty name="dateBean" property="*"/>

<html>
<head><title>Insert Date</title></head>

<body>

<% boolean showForm = true;
   String err = null; %>

<%  if (!dateBean.getDate().equals("")) {
        DateDao dateDao = new DateDao();
        boolean success = dateDao.create(Date.valueOf(dateBean.getDate()));
        if (success) {
            showForm = false;
        } else {
            err = "Couldn't add the Date (Already in Database)";
        }
    }
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

<% if (showForm) { %>

<h2>Insert a Date</h2>

<form  method="post">
  Date: <input type="date" name="date"/>
    <br></br>
  <input type="submit" value="Submit"/>
</form>

<% }
   else { %>

<h2>Success!</h2>

<p>A new Date: <%= dateBean.getDate() %> has been inserted into database.</p>

<p> Back to <a href="date.jsp"> date.jsp </a> </p>

<% } %>

</body>
</html>
