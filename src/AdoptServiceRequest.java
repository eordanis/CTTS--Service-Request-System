/******************************************************************************************

AdoptServiceRequest.java

The purpose of this servlet is to process the adoption of a service request for the 
current technician.

   + This servlet is invoked by ViewNewServiceRequests.jsp
   + This servlet dispatches to MainPage.jsp
   
******************************************************************************************/

package CTTS;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import CTTS.DBI;
import CTTS.Shared;

public class AdoptServiceRequest extends CTTS.Control
{
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   { 
      HttpSession session = request.getSession(); //Returns the current session associated with this request, or if the request does not have a session, creates one
      CTTS.Shared bean = (CTTS.Shared)session.getAttribute("shared"); // get bean from the session
         	String[][] newServiceRequests = bean.getNewServiceRequests();
		/* gets user inputs POSTed from the HttpServletRequest, request */
       	
		int newServiceRequestIndex=Integer.parseInt(request.getParameter("srId"));
		// get the new service request selected by the user index
		String srId = newServiceRequests[newServiceRequestIndex][0]; 

      adoptServiceRequest(bean, srId); // call the adoptServiceRequest function and pass it the bean and selected service request ID
      
     
      this.gotoPage("/MainPage.jsp", request,response); // dispatcher to MainPage.jsp
   }
   
   private void adoptServiceRequest(CTTS.Shared bean, String srId) throws ServletException, IOException
   {
      
      CTTS.DBI dbi = new CTTS.DBI(); // new DBI object to access the database. DBI (Data Base Interface)
      
      try // to connect to the database and adopt a service request
      {
         dbi = new CTTS.DBI(); //creates a new instance of dbi
         if(dbi.connect()) // get a database connection from Tomcat
         {
            int empId = bean.getEmpId(); // get the current employee id from the bean
            dbi.adoptServiceRequest(srId, empId); // call the adoptServiceRequest function in the DBI and pass it the serv req ID and the current empl ID
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
