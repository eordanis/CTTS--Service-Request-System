<html>
<head>
    <jsp:useBean id="shared" scope="session" class="CTTS.Shared" />   
    <title>Kean University - CTTS - Login</title>
    <link rel="stylesheet" type="text/css" href="ctts.css">
    <script language="JavaScript">
        //Get the employee userID and password and check for existence and simple syntax
        function checkForm (form)
        {
            //get input values
            var usr = document.login.USER.value;
            var pwd = document.login.PASSWORD.value;

            //check to see if empid and password are blank
            if (usr == '')
            {
                alert('Please enter your username.');
                document.login.USER.focus();
                return false;
            }
            if (pwd == '')
            {
                alert('Please enter your password.');
                document.login.PASSWORD.focus();
                return false;
            }

            //check to make sure alphanumeric is entered for user
            var ok=usr.search(/\w+/);
            if(ok != 0)
            {
                alert('For employee, you typed an invalid character');
                document.login.USER.focus();
                return false;
            }

            //check to make sure alphanumeric is entered for pwd
            var ok=pwd.search(/\w+/);
            if(ok != 0)
            {
                alert('For password, you typed an invalid character');
                document.login.PASSWORD.focus();
                return false;
            }

            return true;          
        }
    </script>
</head>
    
<body>                   
    <div align="center">
        <center>   
            <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="600" id="AutoNumber1" height="480">
                <tr>
                    <td height="100" colspan="5">
                        <div id="message">
                            <jsp:getProperty name="shared" property="message"/>  <!--retrieves the error message from the shared bean -->
                            <jsp:getProperty name="shared" property="error"/>  <!--retrieves the error data from the shared bean -->
                            <jsp:setProperty name="shared" property="message" value=""/><!-- empty error message from the shared bean -->
                            <jsp:setProperty name="shared" property="error" value=""/> <!-- empty error data from the shared bean -->
		    </div>
		    	<center><img src="images/CTTSLOGO.png"></center>
                        <h1 align="center">CTTS USER LOGIN</h1></td>
                </tr>
                <tr>
                    <td width="150" height="148" valign="top" rowspan="3"></td>
                    <td width="4" height="5" align="right" valign="top">&nbsp;</td>
                    <td width="291" height="32" align="left" valign="top">
                    <div align="left"><img border="0" src="images/top_frame_center.jpg" width="305" height="18"></div></td>
                    <td width="4" height="5" align="left" valign="top">&nbsp;</td>
                    <td width="151" height="50" rowspan="3"></td>
                </tr>
                <tr>    
                    <td width="4" height="54" align="left" valign="top" bgcolor="#96B49C">&nbsp;</td>
                    <td width="291" height="54" align="left" valign="top">
                    <!-- ***************************************   F O R M   **************************************-->
                    <form method="POST" name="login" onSubmit="return checkForm(this);" action="Login"> <!-- sends parameters to Login.java servlet -->
                        <font face="Verdana" size="2">
                            <span class="style1">Username:</span><br>
                            <input type="text" name="USER" size="35"><br> <!--userid parameter requested from the user -->
                            <!--parameter requested from the user -->
                            <span class="style1">Password:</span><br>
                            <input type="password" name="PASSWORD" size="20"><!--password parameter requested from the user -->
                        </font>
                        <input type="submit" value="Submit" name="Submit" style="float: right">
                        <!--tells the browser to send the request -->
                    </form>
                    <!-- ***************************************   F O R M   **************************************-->      
                    </td>
                    <td width="4" height="74" align="left" valign="top">&nbsp;</td>
                </tr>
                <tr>
                    <td width="4" height="34" align="left" valign="top">&nbsp;</td>
                    <td width="291" height="34" align="left" valign="top">
                        <img border="0" src="images/top_frame_center.jpg" width="303" height="16"><br>
                        <font size="1" face="Verdana" color="#000080"><a href="NIY.html">Reset password</a> | <a href="AddEmployee.jsp">Register new Employee</a></font>
                    </td>
                    <td width="4" height="34" align="left" valign="top">&nbsp;</td>
                </tr>
                <tr>
                    <td height="86" valign="top" colspan="5">
                        <center>
                            <p>
                                <b>
                                    <img border="0" src="images/keanlogo.jpg" height="60" />
                                    <div>
                                        <font size="2" face="Verdana" color="#808080">Kean University<br>
                                        Department of Computer Science<br>
                                        CPS 4931 - Distributed Systems<br>
                                        Prof. <a href="mailto:cstewart@kean.edu"><font color="#808080">Stewart-Gardiner</font></a></font>
                                    </div>
                                </b>
                            </p>
                        </center>
                    </td>
                </tr>
            </table>
        </center>
    </div>  
</body>     
</html>


       
