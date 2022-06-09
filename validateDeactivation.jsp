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
            boolean activate=Boolean.parseBoolean(request.getParameter("activate"));
                
            DbHandler db=new DbHandler();

            if(db.getStatus(email).equals("admin")){
                %>
        
                <h1>Admin cannot deactivate another admin account</h1>
        <%
            }else{
                db.updateActivation(session.getAttribute("userId").toString(), email, activate);
                response.sendRedirect("/PeopleMovementRecord/displayAllUsers.jsp");
            }
        %>
    </body>
</html>
