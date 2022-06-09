<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="JDBC.DbHandler" %>
<%@page import="Model.User"%>
<!DOCTYPE html>
<html lang="en">

<head>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
    
    <title>Update</title>


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
                    <h2 class="title">Update Info</h2>
                    <%
                        DbHandler db=new DbHandler();
                        String email=request.getParameter("email");
                        User user=db.getUser(email);
                        
                    %>
                                                   
                 <form method="GET" action="validateUpdateUser.jsp">
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="logintext input--style-1" type="text" placeholder="FULL NAME" name="name" required="true"  value="<%if(user.getName()!=null){%><%=user.getName()%><%}%>"  >
                                    
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="logintext input--style-1" type="text" placeholder="Mobile Number" name="number" required="true" value="<%if(user.getMobile()!=null){%><%=user.getMobile()%><%}%>" >
                                    
                                </div>
                            </div>
                        </div>
                        
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <input disabled="true" class="input--style-1" type="email" placeholder="EMAIL" name="email" required="true"  value="<%if(user.getEmail()!=null){%><%=user.getEmail()%><%}%>"  >
                                    <input type="hidden" name="hiddenemail" value="<%if(user.getEmail()!=null){%><%=user.getEmail()%><%}%>">
                                    
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="input--style-1" type="password" placeholder="PASSWORD" name="password" required="true"  value="<%if(user.getPassword()!=null){%><%=user.getPassword()%><%}%>" >
                                </div>
                            </div>
                        </div>
                                 
                               
                        <div class="p-t-20">
                            <button class="btn btn--radius btn--green" type="submit">Update</button>
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
