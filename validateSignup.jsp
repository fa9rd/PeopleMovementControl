<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.Date"%>
<%@page import="java.time.LocalDateTime"%>
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
            String userType=request.getParameter("userType");
            DbHandler db=new DbHandler();
            boolean check=db.register(new User(mobile,name,email,password,userType,true,new Date(Timestamp.valueOf(LocalDateTime.now()).getTime())));
            if(check){
                response.sendRedirect("/PeopleMovementRecord/login.jsp");
            }else{
                %>
        
                <h1>This ID is already registered</h1>
        
        <%
            }
        %>
    </body>
</html>
