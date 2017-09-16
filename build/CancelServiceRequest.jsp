<!--this jsp displays a confirmation to the client user after successfully canceling a service request>
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
                    <p><a href="MainPage_Client.jsp"><button type="button" style="float:left;">Main Page</button></a>
                    <button type="button" onclick="window.print()" style="float:right;">Print</button>
                    &nbsp;</p>
                <hr>
                <!-- End of display options for the user -->
                <!-- Begin of Cancel Notification -->
		<br>
  
 		<!-------- Begin Service Request Details Code Here for cancelled Service Request-------->
                       
                       <% 
			String currsrId = shared.getcurrSrId();		
                        %> 

                <h2>Service Request Number <%=currsrId%> Has Been Cancelled</h2>
                    


                <!-- End of Cancel Notification -->
                <!-- Begin display client information -->
                    <hr>
		    <div style="float:right;">Client ID: <jsp:getProperty name="shared" property="clientId"/> 
			    | Company: <jsp:getProperty name="shared" property="clientCompanyName"/></div>
                    <!-- End of display client information -->
            </td>
        </tr>
    </table>
</center>
</body>
</html>
