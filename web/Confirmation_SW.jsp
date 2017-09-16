<!--  ?? servlet dispatches to this jsp -->
<!DOCTYPE HTML>
<html>
<head>
    <jsp:useBean id="shared" scope="session" class="CTTS.Shared" />
    <title>
        Welcome : <jsp:getProperty name="shared" property="username"/>
    </title>
    <link rel="stylesheet" type="text/css" href="ctts.css">
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
                        <a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                        <a href="CreateServiceRequest_Technician.jsp"><button type="button" >Create Service Requests</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                    <a href="ViewMyServiceRequests_Technician.jsp"><button type="button">View My Service Requests</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                        <button type="button" onclick="window.print()" style="float:right;">Print</button>
                        &nbsp;
                    <hr>
                    <!-- End of display options for the user -->
                <!-- Begin Service Work Notification -->
		<p></p><p></p><br>
                <p><center><p></p>Your Service Work Has Been Added to Database</a></center></b></p>
		<p></p><p></p><p></p><br>
		<p><a href="NIY.html"><center><b><h2>Component Replacement</a></h2></b></center></p>
		<br>
                <!-- End of Service Work Notification -->
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