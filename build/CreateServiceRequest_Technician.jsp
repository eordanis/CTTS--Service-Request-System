<!-- ??  dispatches to this jsp -->
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
                    <a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>
                    <button type="button" onclick="window.print()" style="float:right;">Print</button>
                    &nbsp;
                <hr>
                <!-- End of display options for the user -->
                <!-- Begin form to ??? -->
                <form name="createServiceRequest" method="POST" onSubmit="return checkForm(this);" action="CreateServiceRequest">
                    <h2>Create Service Request</h2>
                    Requestor:<br>
                    <input type="text" name="requestor" required="required" value="<jsp:getProperty name="shared" property="empFirstName"/>&nbsp;<jsp:getProperty name="shared" property="empLastName"/>"><br>
                    Client ID:<br>
                    <input type="text" name="clientId" required="required" value="<jsp:getProperty name="shared" property="empCompany"/>"><br>
                    Equipment ID:<br>
                    <input type="text" name="equipId" required="required" value=""><br>
                    Problem:<br>
                    <textarea rows="4" cols="50" name="problemDescription" maxlength="2000" required="required" value=""></textarea>
                    &nbsp&nbsp&nbsp;<input type="submit" id="Hbutton" name="Submit" value="Submit" style="float:right;"/>
                </form>
                <!-- End of form to ??? -->
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
