<!--  this jsp displays confirmation to the employee user that the selected svrq is now escalated  -->
<!-- ?? dispatches to this jsp -->
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
                    <p><a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                    <a href="CreateServiceRequest_Technician.jsp"><button type="button">Create Service Request</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp                    
                        <a href="ViewMyServiceRequests_Technician.jsp"><button type="button">View My Service Requests</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                     <a href="ViewNewServiceRequests.jsp"><button type="button">View New Service Requests</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                    <button type="button" onclick="window.print()" style="float:right;">Print</button>
                    &nbsp;</p>
                <hr>
                <!-- End of display options for the user -->
                <!-- Begin of escalated Notification -->
		<br>
  
 		<!-------- Begin Service Request Details Code Here for escalated Service Request-------->
                       
                       <% 
			String currsrId = shared.getcurrSrId();		
                        %> 

                <h2>Service Request Number <%=currsrId%> Has Now Been Escalated</h2>
                    


                <!-- End of escalated Notification -->
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
