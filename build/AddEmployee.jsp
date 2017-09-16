<!-- login.jsp dispatches to this jsp -->
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

                <!-- Begin form to add a new employee -->
                <form name="AddEmployee" method="POST" action="AddEmployee">
                    <h2>Add Employee</h2>
                    First Name:<br>
                    <input type="text" name="empFirstName" value=""><br>
                    Last Name:<br>
                    <input type="text" name="empLastName" value=""><br>
                    Address:<br>
                    <input type="text" name="empAddress" value=""><br>
                    Phone:<br>
                    <input type="text" name="empPhone" value=""><br>
                    Email:<br>
                    <input type="text" name="empEmail" value=""><br>
                    Position:<br>
                    <input type="text" name="empPosition" value=""><br>
                    Company:<br>
                    <input type="text" name="empCompany" value=""><br>
                    &nbsp&nbsp&nbsp;<input type="submit" name="Submit" value="Submit" style="float:right;"/>
                </form>
                <!-- End of form to add new employee -->

            </td>
        </tr>
    </table>
</center>
</body>
</html>