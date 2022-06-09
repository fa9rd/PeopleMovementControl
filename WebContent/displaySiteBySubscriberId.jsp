<%@page import="Model.SitePackages"%>
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


            <%
                int packId = Integer.parseInt(request.getParameter("packId"));
            %>

            <div class="row row-space">


                <div class="p-t-20">
                    <a type="button" style="width:100%;font-size: 26px;" class="btn btn-danger" href="  <%= "addSites.jsp?packId=" + packId%>" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Add Sites&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>


                </div>
            </div>   
            <br>
            <br>

            <div class="table-responsive">
                <table class="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th>Site ID</th>
                            <th>Site Name</th>
                            <th>Package Subscribed Id</th>
                            <th>Is Active</th>
                            <th>Created On</th>
                            <th>Deactivate On</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            String userid = (String) session.getAttribute("userId");
                            DbHandler db = new DbHandler();
                            ArrayList<SitePackages> list = db.getAllSitesBySubsciberPackageId(packId);
                            for (int i = 0; i < list.size(); i++) {
                        %>
                        <tr>
                            <td><%= list.get(i).getId()%></td>
                            <td><%= list.get(i).getName()%></td>
                            <td><%= list.get(i).getPackageSubscriptionId()%></td>
                            <td><%= list.get(i).getIsActive()%></td>
                            <td><%= list.get(i).getActivateDate()%></td>

                            <%String mess = "";
                                if (list.get(i).getIsActive()) {
                                    mess = "Not Deactivated Yet";
                            %>
                            <td><%= mess%></td>

                            <%
                            } else {%>
                            <td><%= list.get(i).getDeactivateDate()%></td>

                            <%}
                            %>

                            <td>
                                <%
                                  
                                    if (list.get(i).isActive == true) {
                                %>
                                <a type="button" class="btn btn-danger" href="  <%= "validateSiteActivationStatusChange.jsp?packId=" + list.get(i).getId() + "&status=false&packSubId="+list.get(i).getPackageSubscriptionId() %>" >Deactivate</a>
                                <%
                                } else {
                                %>
                                <a type="button" class="btn btn-danger" href="  <%= "validateSiteActivationStatusChange.jsp?packId=" + list.get(i).getId() + "&status=true&packSubId="+list.get(i).getPackageSubscriptionId()%>" >Reactivate</a>
                                <%
                                    }
                                    if (list.get(i).isActive) {
                                %>

                                <a type="button" class="btn btn-danger" href="  <%= "displayCheckPointBySiteId.jsp?packId=" + list.get(i).getId()%>" >Check Points</a>
                                <%} else {
                                %>

                                <a type="button" class="btn btn-danger" href="" disabled>Check Points</a>
                                <%}%>
                            </td>

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
