<%@page import="Model.Audit"%>
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
            background-color: #364ba8;
            color: white;
        }   
    
    </style>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
   
    <title>View Users</title>

   
    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
   
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    
    <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

   
    <link href="css/main.css" rel="stylesheet" media="all">
   

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
                            <th>Full Name</th>
                            <th>Email</th>
                            <th>Password</th>
                            <th>User ID</th>
                            <th>Status</th>
                            <th>Is Active</th>
                            <th>Date/Time</th>
                            
                          </tr>
                        </thead>
                        <tbody>

                        <%
                            String email=request.getParameter("us").toString();
                            DbHandler db=new DbHandler();
                            ArrayList<Audit> list=db.getAudits(email);
                            for(int i=0;i<list.size();i++){
                                %>
                    <tr>
                        <td><%= list.get(i).getFullName() %></td>
                        <td><%= list.get(i).getEmail()%></td>
                        <td><%= list.get(i).getPassword() %></td>
                        <td><%= list.get(i).getUserId()%></td>
                        <td><%= list.get(i).getStatus() %></td>
                        <td><%= list.get(i).getActive() %></td>
                        <td><%= list.get(i).getChangeDate()%></td>
                        
                           
                    </tr>
                        
                        
                        <%
                            }
                        %>

                          
                          
                          
                        </tbody>
                                          </table>
                    </div>

  
                
                
            </div>
       
             
   
    <script src="vendor/jquery/jquery.min.js"></script>
   
    <script src="vendor/select2/select2.min.js"></script>
   
    
    <script src="js/global.js"></script>

</body>
</html>
