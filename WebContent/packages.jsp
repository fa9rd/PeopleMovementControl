
<%@page import="Model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="JDBC.DbHandler" %>
<%@page import="Model.Package"%>

<!DOCTYPE html>
<html>
    <head>

        <style>
            td a {
                color: black;
                font-size: 17px;
            }
            td a:hover {
                background-color: #ddd;
                color: black;
                font-weight: 400;
            }
            td a { 
                display: block; 
            }
            th{
                background-color: #4CAF50;
                color: white;
            }    

        </style>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Title Page-->
        <title>Available Packages</title>

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

        <%

            String name = "", status = "";
            if (session.getAttribute("userId") == null) {

            } else {
                name = session.getAttribute("userId").toString();
                status = session.getAttribute("status").toString();

            }

            DbHandler db = new DbHandler();
            db.updatePackageExpire();
            db.updateSubscriptionExpire();
            db.updateSitesExpire();
            db.updateCheckPointsExpire();

            db.register(new User("admin", "admin", "admin", "admin", "admin", true));

//               session = request.getSession(true);
//             String name="";
//                        if(session.getAttribute("userId")==null){
//                            
//                         session.setAttribute("userId","user1");
//                      }
//                        else
//                        {
//                             name=session.getAttribute("userId").toString();
//                        }
//              
//                if(request.getParameter("status")!=null && request.getParameter("email")!=null){
//                    session.setAttribute("status",    request.getParameter("status") );
//                    session.setAttribute("email",    request.getParameter("email") );
//
//

        %>

        <div class="card card-1">

            <jsp:include page="navbar.jsp"/>

            <%      if (status.compareTo("admin") == 0) {
            %>




            <div class="row row-space">
                <div class="col-2">
                    <div class="p-t-20">
                        <a type="button" style="width:100%;font-size: 26px;" class="btn btn-danger" href="  <%= "DisplayAllPackages.jsp"%>" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Display All Packages&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>


                    </div>
                </div>
                <div class="col-2">
                    <div class="p-t-20">
                        <a type="button" style="width:100%;font-size: 26px;" class="btn btn-danger" href="  <%= "showAllsubscribers.jsp"%>" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Display All Subscriber&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>


                    </div>
                </div>

            </div>



            <br>



            <div class="row row-space">


                <div class="p-t-20">
                    <a type="button" style="width:100%;font-size: 26px;" class="btn btn-danger" href="  <%= "showAllActiveSubscriberPackage.jsp"%>" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Display All Active Subscriber&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>


                </div>
            </div>   
            <br>



            <%}%>                

            <div class="table-responsive">
                <table class="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Sites</th>
                            <th>Checkpoints</th>
                            <th>Validity</th>
                            <th>Cost</th>
                            <th>Duration</th>
                            <th>Action</th>                            
                        </tr>
                    </thead>

                    <tbody>

                        <%

                            ArrayList<Package> list = db.getPackages();
                            for (int i = 0; i < list.size(); i++) {
                        %>
                        <tr>
                            <td><%= list.get(i).getPackageName()%></td>
                            <td><%= list.get(i).getSites()%></td>
                            <td><%= list.get(i).getCheckpoints()%></td>
                            <td><%= list.get(i).getValidity()%></td>
                            <td><%= list.get(i).getCost()%></td>
                            <td><%= list.get(i).getDuration()%></td>
                            <td>
                                <a type="button" class="btn btn-danger" href="  <%= "subscribe.jsp?pkgid=" + list.get(i).getPackageId()%>" >Subscribe</a></td>



                        </tr>


                        <%
                            }
                        %>




                    </tbody>
                </table>
            </div>




        </div>
        <jsp:include page="footer.html"/>

        <!-- Jquery JS-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <!-- Vendor JS-->
        <script src="vendor/select2/select2.min.js"></script>
        <script src="vendor/datepicker/moment.min.js"></script>
        <script src="vendor/datepicker/daterangepicker.js"></script>

        <!-- Main JS-->
        <script src="js/global.js"></script>

    </body>

</html>
