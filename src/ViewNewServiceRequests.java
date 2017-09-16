/******************************************************************************************

ViewNewServiceRequests.java

The purpose of this servlet is to view new service requests or requests that have
been escalated, but not yet adopted 

   + This servlet is invoked by ??
   + This servlet dispatches to ??
   
******************************************************************************************/

package CTTS;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import CTTS.DBI;
import CTTS.Shared;

public class ViewNewServiceRequests extends CTTS.Control
{ // This servlet needs to be especially carefully and thoroughly commented
     	protected DBI dbi;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
      HttpSession session = request.getSession(); 

      CTTS.Shared bean = (CTTS.Shared)session.getAttribute("shared"); 
		
		getNewServiceRequests(bean); 
      
      gotoPage("/ViewNewServiceRequests.jsp", request, response);  
   }
      
   private void getNewServiceRequests(CTTS.Shared bean) throws ServletException, IOException
   {
		String [][] newServiceRequests; 
		
		
      CTTS.DBI dbi = null;
      try
      {
         dbi = new CTTS.DBI();
         if(dbi.connect()) // get a connection from tomcat
         {
            newServiceRequests = dbi.getNewServiceRequests(); 
            bean.setNewServiceRequests(newServiceRequests); 
	    
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
