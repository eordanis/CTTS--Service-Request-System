<!--  this jsp displays the service requests owned by current employee user -->
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
                        <a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>
                        <button type="button" onclick="window.print()" style="float:right;">Print</button>
                        &nbsp;
                    <hr>
                    <!-- End of display options for the user -->
                    <!-- Begin display ???  from  ??? -->
                    <h2>My Service Requests</h2>
                    <center>
                        <% 
                        String[][] myServiceRequests = shared.getMyServiceRequests();
                        if (myServiceRequests != null && myServiceRequests.length != 0)
                        {	
                        %>
                        <table id="genTable" align="center" cellspacing="5" cellpadding ="5" width="100%">
                            <tr>
                                <th>Request ID</th>
                                <th>Date Entered</th>
                                <th>Status</th>
                                <th>Requestor</th>
                                <th>Client</th>
                                <th>Equipment ID</th>
                                <th>Description</th>
                                <th>Details </th>
                                <th>Escalate </th>
                            </tr>
                            <%
                            int count=0;
                            for(String[] mSR : myServiceRequests)
                            { 
                            %>

                                <tr BGCOLOR='<%= (count%2==0 ?"#77B1A9":"#B1E6D1") %>' >
                                    <td name="srId"><%=mSR[0]%></td>
                                    <td name="reportDate"><%=mSR[1]%></td>
                                    <td name="status"><%=mSR[2]%></td>
                                    <td name="requestor"><%=mSR[5]%></td>
                                    <td name="companyName"><%=mSR[3]%></td>
                                    <td name="equipId"><%=mSR[4]%></td>				
                                    <td name="problemDescription"><%=mSR[6]%></td>
                                    <td>
					    <form id="viewServiceRequestDetails<%=count%>" method="POST" action="ViewServiceRequestDetails"> <!-- ViewServiceRequestDetails NIY -->
                                            <input type="submit" name="Submit" value="Edit"/>
					    <input type="hidden" name="srId" value="<%=count%>"/> <!-- send index of selected SR --> 
                                        </form>
                                    </td>
                                    		                    <td>
					<form id="escalateServiceRequestStatus<%=count%>" method="POST" action="EscalatedServiceRequest"> <!-- each SR has its own form -->
					<input type="submit" name="Submit" value="Escalate"/> <!-- submit SR selected to EscalatedServiceRequest servlet-->
					<input type="hidden" name="srId" value="<%=count%>" /> <!-- send row index of the selected srId -->
			
                                        </form>
                                    </td>

                                </tr>
                            <%
                            count++;
                            } // end for loop
                            %>
                        </table>
                        <%
                        }
                        else 
                        {
                        %>
                         <p>There are no service requests at this time.</p>
                        <% 
                        }
                        %>
                        <br />
                        <!-- End of display my service requests -->
                    </center>
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
