<html>
<head>
	<jsp:useBean id="shared" scope="session" class="CTTS.Shared" />
	<title>Welcome : <jsp:getProperty name="shared" property="username"/></title>
	<link rel="stylesheet" type="text/css" href="ctts.css">
</head>

<body>
    <center>
        <table id="MenuTable">
            <tr>
                <td> 
                    <center><img src="images/ctts_banner.png"></center>
                    <span style="float:right;">Hello <jsp:getProperty name="shared" property="username"/>!</span>
                </td>
            </tr>
            <tr>
                <td>
                    <table  id="genTable">
                        <jsp:include page="Client.html" flush = "true"/>
                    </table>
                </td>
            </tr>
        </table> <!-- End top Table -->
        <div id="message">
            <jsp:getProperty name="shared" property="message"/>  <!--retrieves the error message from the shared bean -->
            <jsp:getProperty name="shared" property="error"/>  <!--retrieves the error data from the shared bean -->
            <jsp:setProperty name="shared" property="message" value=""/><!-- empty error message from the shared bean -->
            <jsp:setProperty name="shared" property="error" value=""/> <!-- empty error data from the shared bean -->
        </div>
    </center>
</body>
</html>