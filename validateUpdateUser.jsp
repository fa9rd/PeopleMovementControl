<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="JDBC.DbHandler" %>
<%@page import="Model.User"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>validation</title>
    </head>
    <body>
        <%
            String email=request.getParameter("email");
            String password=request.getParameter("password");
            String name=request.getParameter("name");
            String mobile=request.getParameter("number");
            
            DbHandler db=new DbHandler();
            db.updateUser(new User(mobile,name,email,password));
            response.sendRedirect("/PeopleMovementRecord/profile.jsp");
        %>
    </body>
</html>
