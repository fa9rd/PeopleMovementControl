<%-- 
    Document   : validateRenewPackage
    Created on : Jul 19, 2020, 10:57:58 PM
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
        
        
                            String userid=(String)session.getAttribute("userId");
                    if(userid==null){
                        response.sendRedirect("/PeopleMovementRecord/login.jsp");
                    }else{
if(session.getAttribute("status").toString().compareTo("manager")==0)
{
    String packageId=request.getParameter("pkgid");
                DbHandler db=new DbHandler();
db.renewPackage(  Integer.parseInt(packageId));
 response.sendRedirect("/PeopleMovementRecord/showUserPackageSubscriber.jsp");   

}
else
{
 response.sendRedirect("/PeopleMovementRecord/login.jsp");   
}
%>
              
                
                
                <%
                    }%>

        %>
       
    </body>
</html>
