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
       int packSubId = Integer.parseInt(request.getParameter("packSubId"));
      
       DbHandler db=new DbHandler();
       if(status==true)
       { int allowSite = db.getTotalSitesAllow(packId);
       int sitesCount  = db.getSiteCount(packId);
       if(sitesCount<allowSite)
       {
        db.changeStatusSite(packId, status,packSubId);
         response.sendRedirect("/PeopleMovementRecord/showUserPackageSubscriber.jsp");
       }
       else
       {
           String mess="You have Already Activated Max Sites that is "+allowSite;
           %>
    <h1><%= mess %></h1>
           <%
       }
           
       }
       else
       {
        db.changeStatusSite(packId, status,packSubId);
         response.sendRedirect("/PeopleMovementRecord/showUserPackageSubscriber.jsp");
           
       }
        
        
        %>
    </body>
</html>
