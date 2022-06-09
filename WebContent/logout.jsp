<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">

<body>
          <%  
              session.removeAttribute("userId");
              session.removeAttribute("status");
              response.sendRedirect("/PeopleMovementRecord/packages.jsp");     
          %>   
            
</body>
</html>
