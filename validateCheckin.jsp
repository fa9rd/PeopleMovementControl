
<%@page import="Model.CheckIn"%>
<%@page import="JDBC.DbHandler"%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%

            String id = request.getParameter("userId");
            int checkPointNumber = Integer.parseInt(request.getParameter("checkpoint"));
            float temp = Float.parseFloat(request.getParameter("temperature"));
            Date date = Date.valueOf(request.getParameter("validitydate"));
            DbHandler db = new DbHandler();
            if (db.isAlreadyExist(id)) {
                if (db.isActive(id)) {
                    if (temp < 37.5) {
                        CheckIn c = new CheckIn();
                        c.bodyTemperature = temp;
                        c.checkPointId = checkPointNumber;
                        c.userId = id;
                        c.date = date;
                        db.addCheckIn(c);

        %>  
        <h1>Access Granted</h1>
        <%    } else {%>
        <h1>Body Temperature Is High.</h1>
        <h1>Access Denied</h1>

        <%}
        } else {%>

        <h1>The User is Deactivated.</h1>
        <h1>Access Denied</h1>
        <%}%>


        <%} else {        %>

        <h1>No Record Found Against User ID.</h1>
        <h1>Access Denied</h1>
        <%}%>
    </body>
</html>
