<%-- 
    Document   : validateSiteActivationStatusChange
    Created on : Jul 19, 2020, 9:06:57 PM
    Author     : Shah
--%>

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
       int packId = Integer.parseInt(request.getParameter("packId"));
       Boolean status = Boolean.parseBoolean(request.getParameter("status"));
       DbHandler db=new DbHandler();
       if(status==true)
       { int allowSite = db.getTotalCheckPointsAllow(packId);
       int sitesCount  = db.getCheckPointCount(packId);
       if(sitesCount<allowSite)
       {
        db.changeStatusCheckpoint(packId, status);
         response.sendRedirect("/PeopleMovementRecord/showUserPackageSubscriber.jsp");
       }
       else
       {
           String mess="You have Already Activated Max Check Points that is "+allowSite;
           %>
    <h1><%= mess %></h1>
           <%
       }
           
       }
       else
       {
        db.changeStatusCheckpoint(packId, status);
         response.sendRedirect("/PeopleMovementRecord/showUserPackageSubscriber.jsp");
           
       }
        
        
        %>
    </body>
</html>
