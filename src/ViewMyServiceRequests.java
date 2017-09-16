/******************************************************************************************

ViewMyServiceRequests.java

The purpose of this servlet is to retrieve all service requests that belong to the current
user. Whether they own/created the service request or are currently assigned to it.

   + This servlet is invoked by MainPage.jsp or MainPage_Client.jsp
   + This servlet dispatches to ViewMyServiceRequests.jsp
     or ViewMyServiceRequests_Technician.jsp depending on the current user.
   
******************************************************************************************/

package CTTS;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import CTTS.DBI;
import CTTS.Shared;

public class ViewMyServiceRequests extends CTTS.Control
{
   protected DBI dbi;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
      HttpSession session = request.getSession(); // returns the current session associated with this request, or if the request does not have a session, creates one.
      CTTS.Shared bean = (CTTS.Shared)session.getAttribute("shared"); // get bean from the session
		
		getMyServiceRequests(bean); // fetch my service requests from database
      if ((bean.getUserIsEmployee()==true) && (bean.getEmpPosition()).equals("Technician")) // if the current user is an employee and a technician
      {
         gotoPage("/ViewMyServiceRequests_Technician.jsp", request, response); // dispatcher to ViewMyServiceRequests_Technician.jsp
      }
      else // current user is not a technician, may or may not be an employee
      {
         gotoPage("/ViewMyServiceRequests.jsp", request, response); // dispatcher to ViewMyServiceRequests.jsp
      }
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   { // get user inputs posted from a future jsp, but currently not used
      HttpSession session = request.getSession(); // returns the current session associated with this request, or if the request does not have a session, creates one.
      CTTS.Shared bean = (CTTS.Shared)session.getAttribute("shared"); // get bean from the session
		
		getMyServiceRequests(bean);
      
      if ((bean.getUserIsEmployee()==true) && (bean.getEmpPosition()).equals("Technician")) // if the current user is an employee and a technician
      {
         gotoPage("/ViewMyServiceRequests_Technician.jsp", request, response); // dispatcher to ViewMyServiceRequests_Technician.jsp
      }
      else // current user is not a technician, may or may not be an employee
      {
         gotoPage("/ViewMyServiceRequests.jsp", request, response); // dispatcher to ViewMyServiceRequests.jsp
      }
   }
   
   private void getMyServiceRequests(CTTS.Shared bean) throws ServletException, IOException
   {// get the user service requests from the database
		String [][] myServiceRequests;
		
      CTTS.DBI dbi = null;
      try
      {
         dbi = new CTTS.DBI();
         if(dbi.connect())
         {
            if ((bean.getUserIsEmployee()==true) && (bean.getEmpPosition()).equals("Technician")) // if the current user is an employee and a technician
            {
               myServiceRequests = dbi.getMyServiceRequestsTechnician(bean.getEmpId()); // call the getMyServiceRequestsTechnician method in the DBI and pass it the bean's value for the current user's employee ID
            }
            else // current user is not a technician, may or may not be an employee
            {
               myServiceRequests = dbi.getMyServiceRequestsClient(bean.getClientId()); // call the getMyServiceRequestsClient method in the DBI and pass it the bean's value for the current user's client ID
            }
            bean.setMyServiceRequests(myServiceRequests); // for any user call the setMyServiceRequests method in the bean and pass it the myServiceRequests string matrix with service requests from the database
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
