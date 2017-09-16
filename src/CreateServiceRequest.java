/******************************************************************************************

CreateServiceRequest.java

The purpose of this servlet is to create service requests, regardless of which jsp the
service request originated.

   + This servlet is invoked by ??  or ??
   + This servlet dispatches to ?? or ??
   
******************************************************************************************/

package CTTS;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import CTTS.DBI;
import CTTS.Shared;

public class CreateServiceRequest extends CTTS.Control
{  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	   {
	      HttpSession session = request.getSession(); // returns the current session associated with this request, or if the request does not have a session, creates one.
	      CTTS.Shared bean = (CTTS.Shared)session.getAttribute("shared"); // get bean from the session
			
	      getClientEquipment(bean); // fetch my service requests from database
	      
	      gotoPage("/CreateServiceRequest.jsp", request, response); // dispatcher to ViewMyServiceRequests.jsp
	      }
	
  
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
      HttpSession session = request.getSession();

      CTTS.Shared bean = (CTTS.Shared)session.getAttribute("shared"); 
       
		/* gets user inputs POSTed from   ??                  */
		String personReported = request.getParameter("requestor"); 
        String clientId = request.getParameter("clientId"); 
		String equipId = request.getParameter("equipId"); 
		String problemDescription = request.getParameter("problemDescription"); 
      
      if (validateUserInput(bean, clientId, equipId)) // call validateUserInput method to ensure ??  input is valid
      {
         submitServiceRequest(bean, personReported, clientId, equipId, problemDescription);
         
         if (bean.getUserIsEmployee())
            this.gotoPage("/MainPage.jsp", request,response); 
         else
            this.gotoPage("/Confirmation_SR.jsp", request,response); 
      }
      else // ???
      {
         if (bean.getUserIsEmployee())
            this.gotoPage("/CreateServiceRequest_Technician.jsp", request,response); 
         else
            this.gotoPage("/CreateServiceRequest.jsp", request,response); 
      }
   }
private void getClientEquipment(CTTS.Shared bean) throws ServletException, IOException
{// get the user service requests from the database
		String [][] clientEquipment;
		
   CTTS.DBI dbi = null;
   try
   {
      dbi = new CTTS.DBI();
      if(dbi.connect())
      {
         
         
         clientEquipment = dbi.getClientEquipment(bean.getClientId()); // call the getMyServiceRequestsTechnician method in the DBI and pass it the bean's value for the current user's employee ID
         
         bean.setClientEquipment(clientEquipment);}
      } // for any user call the setMyServiceRequests method in the bean and pass it the myServiceRequests string matrix with service requests from the database
  
  
   catch(Exception e)
   {
      e.printStackTrace();
      bean.setMessage("Exception caught : " + e);
      bean.setError("The database could not be connected");
   }    
   finally
   {
      dbi.close();}  //return connection to Tomcat
   }     
// This method ??
   private boolean validateUserInput(CTTS.Shared bean, String clientId, String equipId)
   {  
      CTTS.DBI dbi = new CTTS.DBI(); 
      
      try
      {
         if(dbi.connect())
         {
            if (dbi.clientExists(clientId))
            {
               if (dbi.equipIdBelongsToRequestorCompany(clientId, equipId))
               {
                  return true;
               }
               else
               {
                  bean.setError("The provided equipment ID is not recognized; it may not exist or does not belong to the provided client ID. Please enter a valid equipment ID.");
                  return false;
               }
            }
            else
            {
               bean.setError("The provided client ID is not recognized. Please enter a valid client ID.");
               return false;
            }
         }
         else
         {
            return false;
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
         bean.setMessage("Exception caught : " + e);
         bean.setError("The database could not be connected");
         return false;
      }
      finally
      {
         dbi.close();  //return connection to Tomcat
      }
   }
  // This method ?? 
   private void submitServiceRequest(CTTS.Shared bean, String requestor, String clientId, String equipId, String problemDescription) throws ServletException, IOException
   {
      CTTS.DBI dbi = new CTTS.DBI();  
      
      try
      {
         dbi = new CTTS.DBI();   
         if(dbi.connect())
         {
            dbi.createServiceRequest(requestor, clientId, equipId, problemDescription);
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
