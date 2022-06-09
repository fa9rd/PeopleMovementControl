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
            String status=request.getParameter("status");
        
            
            
            
            DbHandler db=new DbHandler();
            boolean check=db.register(new User(mobile,name,email,password,status,true));
            if(check){
                response.sendRedirect("/PeopleMovementRecord/displayAllUsers.jsp");
            }else{
                %>
        
                <h1>This email is already registered</h1>
        
        <%
            }
        %>
    </body>
</html>
