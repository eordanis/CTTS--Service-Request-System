<!--  this jsp displays service request details, prior service work, and add service work form  for a selected svrq  -->
<!--  ?? servlet dispatches to this jsp -->
<!DOCTYPE HTML>
<html>
<head>
    <jsp:useBean id="shared" scope="session" class="CTTS.Shared" />
    <title>
        Welcome : <jsp:getProperty name="shared" property="username"/>
    </title>
    <link rel="stylesheet" type="text/css" href="ctts.css">
    <script type="text/javascript" src="datetimepicker.js"></script>
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
<a href="CreateServiceRequest_Technician.jsp"><button type="button">Create Service Request</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp                    
<a href="ViewMyServiceRequests_Technician.jsp"><button type="button">View My Service Requests</button></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                        <button type="button" onclick="window.print()" style="float:right;">Print</button>
                        &nbsp;
                    <hr>
                    <!-- End of display options for the user -->
                    <!-- Begin display ???  from  ??? -->


		    <!-------- Begin Service Request Details Code Here-------->
                    <h2>My Service Request Details</h2>
                    <center>
                        <% 
			int count =0;
                        String[] mySRDetails = shared.getMyServiceRequestDetails();
			if (mySRDetails != null && mySRDetails.length != 0)
			{	
                        %>
                        <table id="genTable" align="center" cellspacing="5" cellpadding ="5" width="100%">
                            <tr>
                                <th>Service Request ID</th>
                                <th>Reported Date</th>
                                <th>Status</th>
				<th>Company Name</th>
				<th>Equipment ID</th>
                                <th>Requestor</th>
				<th>Description</th>
                                <th>Employee First Name</th>
				<th>Employee Last Name</th>
                                <th>Employee ID</th>

                            </tr>
				
                          	<tr BGCOLOR='<%= (count%2==0 ?"#77B1A9":"#B1E6D1") %>' > <!-- display Service Request Details table -->
                                    <td name="srId"><center><%=mySRDetails[0]%></center></td>
                                    <td name="reportDate"><center><%=mySRDetails[1]%></center></td>
                                    <td name="status"><center><%=mySRDetails[2]%></center></td>
                                    <td name="companyName"><center><%=mySRDetails[3]%></center></td>
                                    <td name="equipId"><center><%=mySRDetails[4]%></center></td>	
                                    <td name="requestor"><center><%=mySRDetails[5]%></center></td>	
                                    <td name="problemDescription"><center><%=mySRDetails[6]%></center></td>
                                    <td name="empFirstName"><center><%=mySRDetails[7]%></center></td>
                                    <td name="empLastName"><center><%=mySRDetails[8]%></center></td>
                                    <td name="empId"><center><%=mySRDetails[9]%></center></td>
                                    
                                </tr>

                        </table>
                            <%
			count++;
                        }
                        else 
                        {
                        %>
                         <p>There are no service request details for this service request at this time.</p>
                        <% 
                        }
                        %>
                        <br />
			</center>
                        <!-- End of display my service request details -->

		


			<!-------- Begin Service Work History Code Here-------->
			<h2>Service Work History</h2>
			<center>
			<% 
			int count2 =0;
                        String[][] mySRWork = shared.getServiceWork();
			if (mySRWork != null && mySRWork.length != 0)
			{	
                        %>
                        <table id="genTable" align="center" cellspacing="5" cellpadding ="5" width="100%">
                            <tr>
                                <th>Service Work ID</th>
                                <th>Start Time</th>
                                <th>Finish Time</th>
				<th>Description</th>
                                <th>Employee First Name</th>
				<th>Employee Last Name</th>

                            </tr>
				<%
				for(String[] mSRW : mySRWork)
                                 {
				%>
                         	<tr BGCOLOR='<%= (count2%2==0 ?"#77B1A9":"#B1E6D1") %>' > <!-- display Service Work History table -->
                                    <td name="srId"><center><%=mSRW[0]%></center></td>
                                    <td name="startTime"><center><%=mSRW[1]%></center></td>
                                    <td name="finishTime"><center><%=mSRW[2]%></center></td>
                                    <td name="description"><center><%=mSRW[3]%></center></td>
                                    <td name="empFirstName"><center><%=mSRW[4]%></center></td>	
                                    <td name="empLastName"><center><%=mSRW[5]%></center></td>	
                                    
                                </tr>
			    	<%
                            	} // end for loop
                           	 %>
                        </table>
                            <%
				count2++;
                        	}
                        	else 
                        	{
                        	%>
                         <p>There are no prior service work for this service request at this time.</p>
                        <% 
                        }
                        %>
                        <br />

			</center>
			<!-- End of display prior service work history -->



			<!-------- Begin enter New Service Work Form Here-------->
			
			
    			 <form name="createServiceWork" method="POST"  action="CreateServiceWork">
                  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <h2>Enter New Service Work</h2>
                   <br><br>
                &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input type="hidden" name="empId" value="<jsp:getProperty name="shared" property="empId"/>"> 
               Please use calendar control to set Start Time and Finish Time
                   <br><br>
                   &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspStart Time:<br/>
                   
                  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="Text" id="startTime" required="required" name="startTime"/>
                    <img src="images/cal.gif" onclick="javascript:NewCal('startTime','yyyyMMdd', true,'24')" style="cursor:pointer"/> 
                    
                    <br><br>
                    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspFinish Time:<br/>
                   
                  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="Text" id="finishTime" required="required" name="finishTime"/>
                    <img src="images/cal.gif" onclick="javascript:NewCal('finishTime','yyyyMMdd', true,'24')" style="cursor:pointer"/> 
                    <br><br>
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Status:&nbsp&nbsp&nbsp<select name="statuschange" required="required">
  			<option selected value="">---Please Select a Status---</option>
			<option value="Resolved">Resolved</option>
  			<option value="On Hold">On Hold</option>
 			<option value="Escalated">Escalated</option>
  			<option value="Unresolved">Unresolved</option>
			</select>
                    <br><br>
                    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspDescription:<br/>
                    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<textarea rows="4" cols="50" name="Description" maxlength="2000" required="required" value=""></textarea>
                   
                    <input type="hidden" name="srId" value="<%=mySRDetails[0]%>"/>
                    &nbsp&nbsp<input type="submit" id="Hbutton" name="Submit" value="Submit"/>
                      <br >
                 
                  </form>
             
                <!-- End of form to ??? -->
			
			<!-- End of display add new service work -->

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
