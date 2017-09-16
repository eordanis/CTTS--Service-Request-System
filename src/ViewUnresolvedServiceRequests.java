/******************************************************************************************

ViewUnresolvedServiceRequests.java

The purpose of this servlet is to generate the report for all unresolved service requests (grouped by client) in order for the jsp to display to user

   + This servlet is invoked by Report.jsp
   + This servlet dispatches to UnresolvedServiceRequestsReport.jsp
   
******************************************************************************************/

package CTTS;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import CTTS.DBI;
import CTTS.Shared;

public class ViewUnresolvedServiceRequests extends CTTS.Control
{//get unresolved service requests data from the bean and send it to jsp for processing 
     	protected DBI dbi;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
      HttpSession session = request.getSession(); 

      CTTS.Shared bean = (CTTS.Shared)session.getAttribute("shared"); 
		
		getUnresolvedServiceRequests(bean); 
      
      gotoPage("/UnresolvedServiceRequestsReport.jsp", request, response);  
   }
      
   private void getUnresolvedServiceRequests(CTTS.Shared bean) throws ServletException, IOException
   {// get Unresolved Service Requests from the database and set results into bean
		String [][] UnresolvedServiceRequests; 
		
		
      CTTS.DBI dbi = null;
      try
      {
         dbi = new CTTS.DBI();
         if(dbi.connect()) // get a connection from tomcat
         {
            UnresolvedServiceRequests = dbi.getUnresolvedServiceRequests(); 
            bean.setUnresolvedServiceRequests(UnresolvedServiceRequests); 
	    
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
         bean.setMessage("Exception caught : " + e);
         bean.setError("The database could not be connected");
      }    
      finally
      {
         dbi.close();  //return connection to Tomcat
      }  
   }
 

}
