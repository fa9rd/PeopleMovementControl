<%@page import="Model.RevenueReport"%>
<%@page import="java.sql.Date"%>
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

            <%
                Date fromDate = Date.valueOf(request.getParameter("fromDate"));
                Date toDate = Date.valueOf(request.getParameter("toDate"));
                int days = Integer.parseInt(request.getParameter("duration"));

                DbHandler db1 = new DbHandler();
                ArrayList<RevenueReport> temp = db1.getRevenueReport(fromDate, toDate, days);
                for (RevenueReport dataTo : temp) {
            %>

            <div class="table-responsive">
                <h5 style="text-align:center">From :  <%=dataTo.fromDate%>  </h5>
                <h5 style="text-align:center">To :  <%=dataTo.toDate%>  </h5>

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
                            double total = 0;
                            ArrayList<PackageSubscription> list = dataTo.detail;

                            for (int i = 0; i < list.size(); i++) {
                        %>
                        <tr>
                            <td><%= list.get(i).getPackageId()%></td>
                            <td><%= list.get(i).userId%></td>
                            <td><%= list.get(i).getSubscriptionDate()%></td>
                            <td><%= list.get(i).getSubscriptionIsActive()%></td>
                            <%String mess = "";
                                total += list.get(i).subscriptionTotalCost;
                                if (list.get(i).getSubscriptionIsActive()) {
                                    mess = "Not Deactivated Yet";
                            %>
                            <td><%= mess%></td>

                            <%
                        } else {%>
                            <td><%= list.get(i).getSubsciptionDeactivateDate()%></td>

                            <%}
                            %>
                            <td><%= list.get(i).subscriptionTotalCost%></td>
                            <td><%= list.get(i).getSubscriptionEstimateDeactivateDate()%></td>

                        </tr>
                        <%}
                        %>






                    </tbody>

                </table>
                <%
    if (list.size() == 0) {%>
                <h3 style="text-align:center"> No Data To Display </h3>

                <%
                                }%>
                <hr>

                <h3 style="text-align:center">Total :  <%=total%>  </h3>

                <hr>                        <br>


            </div>
            <%  }%> 



        </div>
      

       
        <script src="vendor/jquery/jquery.min.js"></script>
      
        <script src="vendor/select2/select2.min.js"></script>

      
        <script src="js/global.js"></script>

    </body>
</html>
