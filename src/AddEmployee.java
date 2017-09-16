/*********************************

AddEmployee.java******************************************


The purpose of this servlet is to create a new employee   
+ This servlet is invoked by ??  or ??
   + This servlet dispatches to ?? or ??
  
 
******************************************************************************************/


package CTTS;


import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import CTTS.DBI;

import CTTS.Shared;



public class AddEmployee extends CTTS.Control

{
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  
		{
      HttpSession session = request.getSession();

     
                       CTTS.Shared bean = (CTTS.Shared)session.getAttribute("shared"); 
 

      
		/* gets user inputs POSTed from   AddEmployee.java  */
		
               String empFirstName = request.getParameter("empFirstName");
	       bean.setNewEmpFirstName(empFirstName);
      
               String empLastName = request.getParameter("empLastName");
               bean.setNewEmpLastName(empLastName);
   
		
               String empAddress = request.getParameter("empAddress"); 
               bean.setNewEmpAddress(empAddress);
  
		
               String empPhone = request.getParameter("empPhone"); 
               bean.setNewEmpPhone(empPhone);
  

  
               String empEmail = request.getParameter("empEmail");
               bean.setNewEmpEmail(empEmail);
  

               String empPosition = request.getParameter("empPosition");  
               bean.setNewEmpPosition(empPosition);
  
  
               String empCompany = request.getParameter("empCompany");
  
               bean.setNewEmpCompany(empCompany);
  
    
              
               addEmployee(bean, empFirstName, empLastName, empAddress, empPhone, empEmail, empPosition, empCompany);
               this.gotoPage("/RegistrationConfirmation.jsp", request,response); 
 

	      }//end dopost method

     /****add employee Method****/

   private void addEmployee(CTTS.Shared bean, String empFirstName, String empLastName, String empAddress, String empPhone, String empEmail, String empPosition, String empCompany) throws ServletException, IOException
   {
      
      CTTS.DBI dbi = new CTTS.DBI(); // new DBI object to access the database. DBI (Data Base Interface)
      
      try // to connect to the database and add a new employee
      {
         dbi = new CTTS.DBI(); //creates a new instance of dbi
         if(dbi.connect()) // get a database connection from Tomcat
         {

            dbi.addEmployee(empFirstName, empLastName, empAddress, empPhone, empEmail, empPosition, empCompany); // call the addEmployee function in the DBI and pass it the posted values
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
}//end class
       