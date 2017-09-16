/******************************************************************************************

ViewEscalatedServiceRequestDetails.java

The purpose of this servlet is to process the escalation of a service request for the 
current technician.

   + This servlet is invoked by ViewMyServiceRequests_Technician.jsp
   + This servlet dispatches to MainPage.jsp
   
******************************************************************************************/

package CTTS;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import CTTS.DBI;
import CTTS.Shared;

public class ViewEscalatedServiceRequestDetails extends CTTS.Control
{
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   { 
      HttpSession session = request.getSession(); //Returns the current session associated with this request, or if the request does not have a session, creates one
      CTTS.Shared bean = (CTTS.Shared)session.getAttribute("shared"); // get bean from the session

         	String[][] mySRDetails = bean.getNewServiceRequests();

		/* gets user inputs POSTed from the HttpServletRequest, request */
		int mySRDetailsIndex=Integer.parseInt(request.getParameter("srId"));

		// get the  service request selected by the user index
		String srId = mySRDetails[mySRDetailsIndex][0]; 
	
      viewServiceRequestDetails(bean, srId); // call the viewServiceRequestDetails function and pass it the bean and selected service request ID
      viewServiceWorkDetails(bean, srId); // call the viewServiceWorkDetails function and pass it the bean and selected service request ID
     
      this.gotoPage("/ViewEscalatedServiceRequestDetails.jsp", request,response); // dispatcher to ViewServiceRequestDetails.jsp
   }
   


/******Service Request Details Method********/

   private void viewServiceRequestDetails(CTTS.Shared bean, String srId) throws ServletException, IOException
   {
      String []mySRDetails;
      CTTS.DBI dbi = new CTTS.DBI(); // new DBI object to access the database. DBI (Data Base Interface)
      
      try // to connect to the database and get service request details
      {
         dbi = new CTTS.DBI(); //creates a new instance of dbi
         if(dbi.connect()) // get a database connection from Tomcat
         {
           
            mySRDetails=dbi.getServiceRequestDetails(srId); // call the getServiceRequestDetails function in the DBI and pass it the serv req ID and place results in string array
            bean.setMyServiceRequestDetails(mySRDetails);
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
   }//end service work details method


/******Service Work  Method********/

   private void viewServiceWorkDetails(CTTS.Shared bean, String srId) throws ServletException, IOException
   {
      String [][]mySRWork;
      CTTS.DBI dbi = new CTTS.DBI(); // new DBI object to access the database. DBI (Data Base Interface)
      
      try // to connect to the database and adopt a service request
      {
         dbi = new CTTS.DBI(); //creates a new instance of dbi
         if(dbi.connect()) // get a database connection from Tomcat
         {
           
            mySRWork=dbi.getServiceWorkDetails(srId); // call the getServiceWorkDetails function in the DBI and pass it the serv req ID and place results in string array
            bean.setServiceWork(mySRWork);
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
   }//end service work method

}//end class

