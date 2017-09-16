
<!-- ViewUnresolvedServiceRequests.java dispatches to this jsp -->
<!DOCTYPE HTML>
<html>
<head>
	<jsp:useBean id="shared" scope="session" class="CTTS.Shared" /> <!-- get data from bean deposited by servlet-->
    <title>
        Welcome : <jsp:getProperty name="shared" property="username"/>
    </title>
    <link rel="stylesheet" type="text/css" href="ctts.css">
    <%@ page import="java.io.*,java.util.*" %>
    <%@ page import="javax.servlet.*,java.text.*" %>
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
                        <a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>
                        <button type="button" onclick="window.print()" style="float:right;">Print</button>
                        &nbsp;
                    <hr></p></p><p></p>
                    <!-- End of display options for the user -->
                    <!-- Begin display service requests report -->
                    <p></p><p><p><br></p></p><p></p>
                    <center> 
                            <% 
                             String[][] unresolvedServiceRequests = shared.getUnresolvedServiceRequests(); 
                    		
                             if (unresolvedServiceRequests != null && unresolvedServiceRequests.length != 0)  
{	
                            %>
                        
                        <table  id="genTable" BGCOLOR="white" align="center" cellspacing="5" cellpadding ="10" width="100%">
                        <%  //get current date
                             Date dNow = new Date( );
                             SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd ");
                             String currentDate = ft.format(dNow);
                         %>
                        <tr>
                               <th colspan="6" style="height:60px"><h2><font color="#000">Unresolved Service Requests Report </font></h2></th></tr>
                         <tr><th colspan="5" align="right"><font color="#000"><%=currentDate%></font></th>
                        </tr>
				        <tr><th colspan="6"><hr></th></tr>
                            
             <% //initialize varibles used for report
			    int cId = 0;
			    int total = 0;
			    int grandTotal = 0;
			    int statusTotal = 0;
			    String status = "";
			    //loop thru records
			    for(String[] uSR : unresolvedServiceRequests)
           {
			    
				if (cId == 0 || cId != Integer.parseInt(uSR[0]) ) 
				{
					if (statusTotal!=0) {%>
					
				       <tr><th colspan="5" align="right"><font color="#000"><%=status%> Total: <%=statusTotal%></font></th></tr>
				       	
				<% statusTotal = 0;} 
					if (total!=0) {%>
					       <tr><th colspan="5" align="right"><font color="#000">Unresolved Service Requests Total: <%=total%></font></th></tr>
					       <tr><th colspan="6"><hr></th></tr>	
					<%} %>
				<tr>
                                <th colspan="6" align="left" style="height:60px"><h2><font color="#000"><%=uSR[1]%></font></h2></th></tr>
                                <tr><th colspan="6"><hr></th></tr>
                 <tr><!-- field headings for user to view -->
                                
                                <th><font color="#000">Status</font></th>
				                <th><font color="#000">ID</font></th>
                                <th><font color="#000">Date</font></th>
                                <th><font color="#000">Requestor</font></th>
                                <th><font color="#000">Description</font></th>
                                <th><font color="#000">Technician</font></th>
                                
                     </tr>	
				<%cId = Integer.parseInt(uSR[0]);
                		 total = 0;
                		 
                status = uSR[2].toString();		 
                }
                 %>
			    <!-- display Service Request rows in table -->
		<% 	   if (!status.equals(uSR[2].toString())) {  %>
		       <tr ><th colspan="5" align="right"><font color="#000"><%=status%> Total: <%=statusTotal%></font></th></tr> 
		        
		     <% status = uSR[2].toString();
		     statusTotal = 0;}%>    <tr>
			                        <td name="status" align="center" width="15%"><font color="#000"><%=uSR[2]%></font></td>
                                    <td name="srID" align="center" width="5%"><font color="#000"><%=uSR[3]%></font></td>
                                    <td name="reportDate" align="center" width="15%"><font color="#000"><%=uSR[4]%></font></td>
                                    <td name="requestor" align="center" width="20%"><font color="#000"><%=uSR[5]%></font></td>
                                    <td name="problemDescription" align="center" width="30%"><font color="#000"><%=uSR[6]%></font></td>
                                    <td name="empName" align="center" width="15%"><font color="#000"><%=uSR[7]%></font></td>
				
                 <%total++;
                 grandTotal++;
                 statusTotal++;%></tr>
				
  
                        <% } // end for loop %>
                 <tr ><th colspan="5" align="right"><font color="#000"><%=status%> Total: <%=statusTotal%></font></th></tr> 
                 <tr><th colspan="5" align="right"><font color="#000">Unresolved Service Requests Total: <%=total%></font></th></tr>
                 <tr><th colspan="6"><hr></th></tr>
                 <tr><th colspan="6"><hr></th></tr>	
                 <tr><th colspan="5" align="right"><font color="#000">Unresolved Service Requests Grand Total: <%=grandTotal%></font></th></tr>
                 <tr><th colspan="6"><hr></th></tr>
                        </table>
                        
                  <%} else {%>
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
