<div class="card-heading"></div>
<div class="topnav">
    <%
        String email = (String) session.getAttribute("email");
        String status = (String) session.getAttribute("status");

        if (email == null && status == null) {
    %>
    <a href="packages.jsp">Available Packages</a>

    <a type="button"  href="signup.jsp" style="float: right;">Register</a>
    <a type="button"  href="login.jsp" style="float: right;">Login</a>
<a href="addCheckIn.jsp">Check In</a>
    <a href="addCheckout.jsp">Check Out</a>

    <%
    } else if (status.equals("admin")) {

    %>
    <a href="packages.jsp">Available Packages</a>
    <a href="definePackage.jsp">Define New Package</a>
    <a href="addUser.jsp">Add User</a>
    <a href="displayAllUsers.jsp">All Users</a>
 <a href="takeInforForRevenueReport.jsp">Revenue Report</a>
<a href="displayCompleteCheckInData.jsp">Check In Details</a>
    <a href="displayCompleteCheckOutData.jsp">Check Out Details</a>



    <a type="button"  href="logout.jsp" style="float: right;">Logout</a>
    <a type="button"  href="profile.jsp" style="float: right;">My Profile</a>


    <%
    } else if (status.equals("manager")) {

    %>
    <a href="packages.jsp">Available Packages</a>
    <a href="showUserPackageSubscriber.jsp">My Packages</a>   


    <a type="button"  href="logout.jsp" style="float: right;">Logout</a>
    <a type="button"  href="profile.jsp" style="float: right;">My Profile</a>


    <%
    } else {

    %>
    <a href="packages.jsp">Packages</a>
<a href="DisplayCompleteCheckInDataByUserId.jsp">Check In Details</a>
    <a href="DisplayCompleteCheckOutDataByUserId.jsp">Check Out Details</a>
    

    <a type="button"  href="logout.jsp" style="float: right;">Logout</a>

    <a type="button"  href="profile.jsp?>" style="float: right;">My Profile</a>



    <%
        }
    %>




</div>
