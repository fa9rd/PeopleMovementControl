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
                            <th>Package ID</th>
                            <th>Subscriber Id</th>
                            <th>Subscribe On</th>
                            <th>Is Active</th>
                            <th>Deactive On</th>
                            <th>Cost</th>
                            <th>Estimation Exp Date</th>
                          </tr>
                        </thead>
                        <tbody>

                        <%
                            String userid=(String)session.getAttribute("userId");
                            DbHandler db=new DbHandler();
                            ArrayList<PackageSubscription> list=db.getAllActivePackageSubsriber();
                            for(int i=0;i<list.size();i++){
                                %>
                    <tr>
                        <td><%= list.get(i).getPackageId()%></td>
                        <td><%= list.get(i).userId %></td>
                        <td><%= list.get(i).getSubscriptionDate()%></td>
                        <td><%= list.get(i).getSubscriptionIsActive()%></td>
                        <%String mess="";
                            if(list.get(i).getSubscriptionIsActive())
                        {
                             mess = "Not Deactivated Yet";
%>
                                             <td><%= mess %></td>
   
                        <%
                        }
else
{%>
                        <td><%= list.get(i).getSubsciptionDeactivateDate()%></td>

<%}
                        %>
<td><%= list.get(i).subscriptionTotalCost%></td>
<td><%= list.get(i).getSubscriptionEstimateDeactivateDate()%></td>
                        
                    </tr>
                  <%}%>
                          
                        </tbody>
                                          </table>
                    </div>

  
                
                
            </div>
   
             
    
    <script src="vendor/jquery/jquery.min.js"></script>

    <script src="vendor/select2/select2.min.js"></script>
   
    
    <script src="js/global.js"></script>

</body>
</html>
