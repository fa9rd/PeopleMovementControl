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
            String userid=request.getParameter("email");
            String password=request.getParameter("password");
            DbHandler db=new DbHandler();
            boolean check=db.isAlreadyExist(userid);
            if(!check){
        %>
        <h1>This ID is not registered</h1>
        <%    
            }else{
                boolean login=db.login(userid, password);
                if(login){
                    if(db.isActive(userid)){

 session.setAttribute("userId",userid);
                             session.setAttribute("status",db.getStatus(userid));
                        response.sendRedirect( "/PeopleMovementRecord/packages.jsp" );   

                    }else{
                            %>

        <h1>Your account is deactivated </h1>
        <%
                        }
                }else{
%>
                    <h1>You entered wrong password</h1>
                    <%
                }
            }
            %>
    </body>
</html>
