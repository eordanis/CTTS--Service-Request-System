<!-- ?? dispatches to this jsp -->
<!DOCTYPE HTML>
<html>
<head>
	<jsp:useBean id="shared" scope="session" class="CTTS.Shared" />
	<title>
		Welcome : <jsp:getProperty name="shared" property="username"/>
	</title>
	<link rel="stylesheet" type="text/css" href="ctts.css">
	<script type="text/javascript" src="ServiceRequestCreationValidation.js"></script>
</head>

<body>
<center>
    <table>
        <tr>
        	<td>
                <center><img src="images/ctts_banner.png" class="banner"></center>
                
                <div id="message">
                    <jsp:getProperty name="shared" property="message"/>  <!--retrieves the error message from the shared bean -->
                    <jsp:getProperty name="shared" property="error"/>  <!--retrieves the error data from the shared bean -->
                    <jsp:setProperty name="shared" property="message" value=""/><!-- empty error message from the shared bean -->
                    <jsp:setProperty name="shared" property="error" value=""/> <!-- empty error data from the shared bean -->
                </div>
                <!-- Begin display options for the user -->
                <hr>
                    <p><a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 
                    <a href="CreateServiceRequest_Technician.jsp"><button type="button">Create Service Request</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp                    
                    <a href="ViewMyServiceRequests_Technician.jsp"><button type="button">View My Service Requests</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                    <a href="ViewNewServiceRequests.jsp"><button type="button">View New Service Requests</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                    <button type="button" onclick="window.print()" style="float:right;">Print</button>
                    &nbsp;</p>
                <hr>
                <!-- End of display options for the user -->
                <!-- Begin Links to Reports -->
		<p></p><p></p><br>
		        <h2><a href="ViewUnresolvedServiceRequests"><center>Unresolved Service Requests Report</a></center></h2>
		<p></p><p></p><p></p><br>
		<h2><a href="NIY.html"><center>Overdue Service Requests Report</a></center></h2>
		<p></p><p></p><p></p><br>
		<h2><a href="NIY.html"><center>Qarterly Service Requests Report</a></center></h2>
		<p></p><p></p><p></p><br>
                
                <!-- End of Report Links -->
                <!-- Begin display Technician information -->
                <hr>
                <div style="float:right;">Technician ID: <jsp:getProperty name="shared" property="empId"/> | Company: <jsp:getProperty name="shared" property="empCompany"/></div>
                <!-- End of display Technician information -->
            </td>
        </tr>
    </table>
</center>
</body>
</html>
