/************************************CreateServiceRequest.java***************************
The purpose of this servlet is to create service work for a service request  
+ This servlet is invoked by ??  or ??
   + This servlet dispatches to ?? or ??
 

******************************************************************************************/


package CTTS;

import java.io.*;

import javax.servlet.*;

import javax.servlet.http.*;

import CTTS.DBI;
import CTTS.Shared;



public class CreateServiceWork extends CTTS.Control

{
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   
		{
      HttpSession session = request.getSession();

      
			CTTS.Shared bean = (CTTS.Shared)session.getAttribute("shared"); 
       
		
			/* gets user inputs POSTed from   ??                  */
		
			String empId = request.getParameter("empId"); 
        
			String startTime = request.getParameter("startTime");
        
			String finishTime = request.getParameter("finishTime");
	
			String status = request.getParameter("statuschange"); 	
			String Description = request.getParameter("Description"); 
		
			String srId = request.getParameter("srId"); 
      
         
	
	submitServiceWork(bean, empId, startTime, finishTime, Description, srId);

  
        updateServiceRequestStatus(bean, srId, status); // call the updateServiceRequestStatus function and pass it the bean and selected service request ID and new status 
     
	this.gotoPage("/Confirmation_SW.jsp", request,response); // dispatcher to Confimation.jsp
         
  
		
		 }       
   
 
  
	// This method adds service work to db if connection is made   
	private void submitServiceWork(CTTS.Shared bean, String empId, String startTime, String finishTime, String Description, String srId) throws ServletException, IOException
   
		{
      
			CTTS.DBI dbi = new CTTS.DBI();  
      
      
				try
      
					{
         dbi = new CTTS.DBI();   
        
							 if(dbi.connect())
        
								 {
   
									 dbi.createServiceWork(empId, startTime, finishTime, Description, srId);
        
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


/****Chance Service Request Status Method****/

   private void updateServiceRequestStatus(CTTS.Shared bean, String srId, String status) throws ServletException, IOException
   {
      
      CTTS.DBI dbi = new CTTS.DBI(); // new DBI object to access the database. DBI (Data Base Interface)
      
      try // to connect to the database and adopt a service request
      {
         dbi = new CTTS.DBI(); //creates a new instance of dbi
         if(dbi.connect()) // get a database connection from Tomcat
         {

            dbi.updateServiceRequestStatus(srId, status); // call the updateServiceRequestStatus function in the DBI and pass it the serv req ID and the new status
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
   }//end method




}

