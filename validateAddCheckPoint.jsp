<%@page import="JDBC.DbHandler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <%
       
       
       String siteName = (String)request.getParameter("checkPoint");
 String packId = request.getParameter("packId");
 DbHandler db=new DbHandler();
       int allowSite = db.getTotalCheckPointsAllow(Integer.parseInt( packId));
       int sitesCount  = db.getCheckPointCount(Integer.parseInt(packId));
       if(sitesCount<allowSite)
       {
        db.addCheckPoint(Integer.parseInt( packId), siteName);
         response.sendRedirect("/PeopleMovementRecord/showUserPackageSubscriber.jsp");
       }
       else
       {
           String mess="You have create Max Check Points that is "+allowSite;
           %>
    <h1><%= mess %></h1>
           <%
       }
 %>      
       
    </body>
</html>
