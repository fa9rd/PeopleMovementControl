<%@page import="JDBC.DbHandler"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<!DOCTYPE html>
<html lang="en">

<head>
  
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    

    <title>Login</title>

   
    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">

    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    
    <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

    
    <link href="css/main.css" rel="stylesheet" media="all">
</head>

<body>

            <div class="card card-1">
                <jsp:include page="navbar.jsp"/>
                
                <%
                    String userid=(String)session.getAttribute("userId");
                    if(userid==null){
                        response.sendRedirect("/PeopleMovementRecord/login.jsp");
                    }else{
if(session.getAttribute("status").toString().compareTo("manager")==0)
{
    String packageId=request.getParameter("pkgid");
                DbHandler db=new DbHandler();
db.addPackageSubsriber(userid,  Integer.parseInt(packageId));
 response.sendRedirect("/PeopleMovementRecord/packages.jsp");   

}
else
{
 response.sendRedirect("/PeopleMovementRecord/login.jsp");   
}
%>
              
                
                
                <%
                    }%>
            </div>
      
    
    <script src="vendor/jquery/jquery.min.js"></script>
  
    <script src="vendor/select2/select2.min.js"></script>
    <script src="vendor/datepicker/moment.min.js"></script>
    <script src="vendor/datepicker/daterangepicker.js"></script>

    
    <script src="js/global.js"></script>

</body>
</html>