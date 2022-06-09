<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
<head>
   
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
   
    <title>New Package</title>

    
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
                <div class="card-body">
                    <h2 class="title">Create New Package</h2>
                    <form method="GET" action="validateAddNewPackage.jsp">
                         <div class="row row-space">
                        <div class="col-2">
                        <div class="input-group">
                            <input class="logintext" type="text" placeholder="Package Name" name="name" required>
                        </div>
                        </div>
                       <div class="col-2">
                        <div class="input-group">
                            <input class="logintext" type="number" placeholder="Cost" name="cost" required>
                        </div>
                       </div>
                        </div>
                        
                        
                        
                        <div class="row row-space">
                        <div class="col-2">
                        <div class="input-group">
                            <input class="logintext" type="number" placeholder="Number of Sites" name="site" required>
                        </div>
                       </div>
                        
                            <div class="col-2">
                         <div class="input-group">
                            <input class="logintext" type="number" placeholder="Number of Checkpoints" name="checkpoint" required>
                        </div>
                         </div>
                        </div>
                        
                        
                         
                        <div class="row row-space">
                            <div class="col-2">
                         <div class="input-group">
                            <input class="logintext" type="number" placeholder="Enter Number Of Days" name="Duration" required>
                        </div>
                            </div>
                            <div class="col-2">
                         <div class="input-group">
                            <input class="logintext" type="date" placeholder="Enter Expiry Date" name="validitydate" required>
                        </div>
                         </div>
                        
                        </div>
                           
                                 
                               
                        <div class="p-t-20">
                            <button class="btn btn--radius btn--green" type="submit">Create Package</button>
                        </div>
                    </form>
                    
                </div>
            </div>
               
       
             
    
    <script src="vendor/jquery/jquery.min.js"></script>
    
    <script src="vendor/select2/select2.min.js"></script>
    <script src="vendor/datepicker/moment.min.js"></script>
    <script src="vendor/datepicker/daterangepicker.js"></script>

  
    <script src="js/global.js"></script>

</body>
</html>
