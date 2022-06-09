<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
    <!-- Title Page-->
    <title>Add User</title>

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
</head>

<body>

            <div class="card card-1">
                
                <jsp:include page="navbar.jsp"/>
                <div class="card-body">
                    <h2 class="title">Add Information</h2>
                    <form method="GET" action="validateAddition.jsp">
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="logintext input--style-1" type="text" placeholder="FULL NAME" name="name" required="true">
                                    
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="logintext input--style-1" type="text" placeholder="Mobile Number" name="number" required="true">
                                    
                                </div>
                            </div>
                        </div>
                        
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="input--style-1" type="email" placeholder="EMAIL" name="email" required="true">
                                    
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="input--style-1" type="password" placeholder="PASSWORD" name="password" required="true">
                                </div>
                            </div>
                        </div>
                        <div class="col-2">
                                <div class="input-group" >
                                    <div class="rs-select2 js-select-simple select--no-search">
                                        <label>Status</label><select name="status" required>
                                            <option value="user">User</option>
                                            <option value="admin">Admin</option>
                                            <option value="manager">MCO Manager</option>
                                  
                                        </select>
                                        <div class="select-dropdown"></div>
                                    </div>
                                </div>
                        </div>
                               
                        <div class="p-t-20">
                            <button class="btn btn--radius btn--green" type="submit">Add</button>
                        </div>
                    </form>
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
