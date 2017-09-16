<!--  this jsp displays new and escalated svrq for the employee user -->
<!-- ViewNewServiceRequests.java dispatches to this jsp -->
<!DOCTYPE HTML>
<html>
<head>
	<jsp:useBean id="shared" scope="session" class="CTTS.Shared" /> <!-- get data from bean deposited by servlet-->
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
                    <!-- Begin conclusion options for the user -->
                    <p></p><p><p><hr>
                        <a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                        <a href="CreateServiceRequest_Technician.jsp"><button type="button">Create Service Request</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp                    
                        <a href="ViewMyServiceRequests_Technician.jsp"><button type="button">View My Service Requests</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                        <button type="button" onclick="window.print()" style="float:right;">Print</button>
                        &nbsp;
                    <hr></p></p><p></p>
                    <!-- End of display options for the user -->
                    <!-- Begin display new service requests  or Technician -->
                    <p></p><p><p><br><h2>Escalated Service Requests </h2></p></p><p></p>
                    <center> 
                            <% 
                             String[][] newServiceRequests = shared.getNewServiceRequests(); 
			
                             if (newServiceRequests != null && newServiceRequests.length != 0)// check if new/esc service requests exist  
			     {	
                            %>
                        <table id="genTable" align="center" cellspacing="5" cellpadding ="10" width="100%">
				<tr> <!-- field headings for user to view -->
                                <th>Request ID</th>
                                <th>Date Entered</th>
				<th>Status</th>
                                <th>Client</th>
                                <th>Equipment ID</th>
                                <th>Requestor</th>
                                <th>Description</th>
                                <th>Adopt</th>
                                <th>Details </th>
                            </tr>
                            <%
			    int count=0; 
			    int headerCount=0;
			    
			    for(String[] nSR : newServiceRequests)
                            {
				
				
                            %>
			 
			     <%
				//service request are ordered by status and ascending. if nSR[2] contains New, then the start of the New service request begins. add extra table rows to seperate and add new header for new service request
				if (nSR[2].contains("New"))
				{
				if (headerCount==0){
				out.println("<tr></tr><tr></tr><tr></tr><tr><th><br><h2>New Service Requests</h2></th></tr><tr><th>Request ID</th><th>Date Entered</th><th>Status</th><th>Client</th><th>Equipment ID</th><th>Requestor</th><th>Description</th></tr><tr></tr>");
				headerCount++;
				}
				}
				%>
				
			   <tr BGCOLOR='<%= (count%2==0 ?"#77B1A9":"#B1E6D1") %>' > <!-- display Service Request rows in table -->
			      				

                                    <td name="srId" align="center"><%=nSR[0]%></td>
                                    <td name="reportDate" align="center"><%=nSR[1]%></td>
 				    <td name="status" align="center"><%=nSR[2]%></td>
                                    <td name="companyName" align="center"><%=nSR[3]%></td>
                                    <td name="equipId" align="center"><%=nSR[4]%></td>
                                    <td name="requestor" align="center"><%=nSR[5]%></td>
                                    <td name="problemDescription" align="center"><%=nSR[6]%></td>
				
                                    <td>
					<form id="adoptServiceRequest<%=count%>" method="POST" action="AdoptServiceRequest"> <!-- each SR has its own form -->
					       <input type="submit" name="Submit" value="Adopt"/> <!-- submit SR selected to AdoptServiceRequest servlet-->
					       <input type="hidden" name="srId" value="<%=count%>" /> <!-- send row index of the selected srId -->
			
                                        </form>
                                    </td>

				<%
				// if nSR[2] contains Escalated, then display option to view details
				if (nSR[2].contains("Escalated"))
				{
				%>

				<td>
					<form id="viewEscalatedServiceRequestDetails<%=count%>" method="POST" action="ViewEscalatedServiceRequestDetails"> <!-- each SR has its own form -->
					<input type="submit" name="Submit" value="Details"/> <!-- submit SR selected to ViewEscalatedServiceRequestDetails servlet-->
					<input type="hidden" name="srId" value="<%=count%>" /> <!-- send row index of the selected srId -->
			
                                        </form>
                                    </td>
				<%
				}//end iff
				%>




                                </tr>
                            <%
				
				count++;
                            } // end for loop
                            %>
                        </table>
                        <%}
                       else {%>
                         <p>There are no new service requests at this time.</p>
                        <%}%>
                        <br />
                        <!-- End of display new service requests -->
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
