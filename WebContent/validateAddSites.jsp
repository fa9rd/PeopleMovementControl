<%-- 
    Document   : validAddSites
    Created on : Jul 19, 2020, 8:03:42 PM
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
       
       
       String siteName = (String)request.getParameter("siteName");
 String packId = request.getParameter("packId");
 DbHandler db=new DbHandler();
       int allowSite = db.getTotalSitesAllow(Integer.parseInt( packId));
       int sitesCount  = db.getSiteCount(Integer.parseInt(packId));
       if(sitesCount<allowSite)
       {
        db.addSite(Integer.parseInt( packId), siteName);
         response.sendRedirect("/PeopleMovementRecord/showUserPackageSubscriber.jsp");
       }
       else
       {
           String mess="You have create Max Sites that is "+allowSite;
           %>
    <h1><%= mess %></h1>
           <%
       }
 %>      
       
    </body>
</html>
