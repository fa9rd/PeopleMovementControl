<%@page import="Model.CheckOut"%>
<%@page import="Model.CheckIn"%>
<%@page import="Model.PackageSubscription"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="JDBC.DbHandler" %>
<%@page import="Model.User"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <style>
        th{
            background-color: #4CAF50;
            color: white;
        }   
    
    </style>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
    <!-- Title Page-->
    <title>View Users</title>

    <!-- Icons font CSS-->
    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Vendor CSS-->
    <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="css/main.css" rel="stylesheet" media="all">
   
    <!-- Bootstrap link-->
   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    
</head>

<body>
            <div class="card card-1">
                
                <jsp:include page="navbar.jsp"/>
                
  
                <div class="table-responsive">
                      <table class="table table-hover table-bordered">
                        <thead>
                          <tr>
                            <th>Check In ID</th>
                            <th>User ID</th>
                            <th>Check Point</th>
                            <th> Body Temperature </th>
                            <th>Date</th>
                            <th>Time</th>
                          </tr>
                        </thead>
                        <tbody>

                        <%
                            String userid=(String)session.getAttribute("userId");
                            DbHandler db=new DbHandler();
                            ArrayList<CheckOut> list=db.getAllCheckOutDate();
                            for(int i=0;i<list.size();i++){
                                %>
                    <tr>
                        <td><%= list.get(i).getId()%></td>
                        <td><%= list.get(i).userId %></td>
                        <td><%= list.get(i).getCheckPointId()%></td>
                        <td><%= list.get(i).getBodyTemperature()%></td>   
<td><%= list.get(i).getDate() %></td>
<td><%= list.get(i).getTime()%></td>
                        
                    </tr>
                  <%}%>
                          
                        </tbody>
                                          </table>
                    </div>

  
                
                
            </div>
       <jsp:include page="footer.html"/>
             
    <!-- Jquery JS-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <!-- Vendor JS-->
    <script src="vendor/select2/select2.min.js"></script>
   
    <!-- Main JS-->
    <script src="js/global.js"></script>

</body>
</html>
