<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.Date"%>
<%@page import="java.time.LocalDateTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="JDBC.DbHandler" %>
<%@page import="Model.Package"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>validation</title>
    </head>
    <body>
        <%
            String name=request.getParameter("name");
            float cost=Float.parseFloat(request.getParameter("cost"));
            int  site=Integer.parseInt(request.getParameter("site"));
            int checkPoint= Integer.parseInt( request.getParameter("checkpoint"));
            int duration=Integer.parseInt( request.getParameter("Duration"));
            Date validitydate= Date.valueOf(request.getParameter("validitydate"));
            DbHandler db=new DbHandler();
            boolean check=db.addPackage(new Package(name,site,checkPoint,validitydate,cost,duration,true,session.getAttribute("userId").toString()));
                response.sendRedirect("/PeopleMovementRecord/packages.jsp");
         %>
    </body>
</html>
