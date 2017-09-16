<!-- ?? dispatches to this jsp -->
<!DOCTYPE HTML>
<html>
<head>
	<jsp:useBean id="shared" scope="session" class="CTTS.Shared" />
	<title>
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
                    <a href="Login.jsp"><button type="button" style="float:left;">Login Page</button></a>

                <hr>
                <!-- End of display options for the user -->
                <!-- Begin of registration Notification -->
		<br>
  
 		<!-------- Begin registration confirmation-------->
                <%
               int count =0;
	       String empFirstName = shared.getNewEmpFirstName();
      

               String empLastName = shared.getNewEmpLastName();
   
		

               String empAddress = shared.getNewEmpAddress();
  
		

               String empPhone = shared.getNewEmpPhone();
  

  
       
               String empEmail = shared.getNewEmpEmail();
  

              
               String empPosition = shared.getNewEmpPosition();
  
  
              
               String empCompany = shared.getNewEmpCompany();
  
    

                %>       

                <h2>Registration Complete</h2>
                 <center>   
                <table id="genTable" align="center" cellspacing="5" cellpadding ="5" width="100%">
                 
<tr bgcolor="#77B1A9"><td align="left"><b>First Name:</b></td><td align="left"><center><%=empFirstName%></center></td></tr>
<tr bgcolor="#B1E6D1"><td align="left"><b>Last Name:</b><td align="left"><center><%=empLastName%></center></td></tr>
<tr bgcolor="#77B1A9"><td align="left"><b>Address:</b></td><td align="left"><center><%=empAddress%></center></td></tr>
<tr bgcolor="#B1E6D1"><td align="left"><b>Phone:</b></td><td align="left"><center><%=empPhone%></center></td></tr>
<tr bgcolor="#77B1A9"><td align="left"><b>Email:</b></td><td align="left"><center><%=empEmail%></center></td></tr>
<tr bgcolor="#B1E6D1"><td align="left"><b>Position:</b></td><td align="left"><center><%=empPosition%></center></td></tr>
<tr bgcolor="#77B1A9"><td align="left"><b>Company:</b></td><td align="left"><center><%=empCompany%></center></td></tr>
                        </table>
                    </center>


                <!-- End of registration Notification -->

            </td>
        </tr>
    </table>
</center>
</body>
</html>