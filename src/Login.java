//
/*****************************************************************************
This servlet is called when the user logs in the system. The screen program that calls this program is Login.jsp
That program contains EmployeeID, PIN and Send Button.
This program has a boolean variable that determines the following:
proceed = TRUE : Call mainPage.jsp
proceed = FALSE: this means login failed and goes back to the Login.jsp


javax.servlet :  Defines an object that receives requests from the client and sends them to any resource
                     (such as a servlet, HTML file, or JSP file) on the server.
                     Defines methods that all servlets must implement.
                     A servlet configuration object used by a servlet container used to pass information to a servlet during initialization.
                     Defines a set of methods that a servlet uses to communicate with its servlet container, for example,
                     to get the MIME type of a file, dispatch requests, or write to a log file
                     Defines an object to provide client request information to a servlet.
                     Def	ines an object to assist a servlet in sending a response to the client


javax.servlet.http: Provides an abstract class to be subclassed to create an HTTP servlet suitable for a Web site.
                          A subclass of HttpServlet must override at least one method, usually one of these:
                                                  - doGet, if the servlet supports HTTP GET requests
                                                  - doPost, for HTTP POST requests
                                                  - doPut, for HTTP PUT requests
                                                  - doDelete, for HTTP DELETE requests
                                                  - init and destroy, to manage resources that are held for the life of the servlet
                                                  - getServletInfo, which the servlet uses to provide information about itself

*****************************************************************************/

package CTTS;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import CTTS.DBI;
import CTTS.Shared;
import CTTS.Control;

public class Login extends CTTS.Control
{
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {   
      // get emplId and password parameters from client request
      String user = request.getParameter("USER");  // This function reads the input parameters array and gets the field
      String password = request.getParameter("PASSWORD"); //based on requests from DO_POST
      // get the current session from Tomcat
      HttpSession session = request.getSession(true);
      
      CTTS.Shared bean = (CTTS.Shared)session.getAttribute("shared"); // get bean from the session

      // login to the database with user password, and the bean
      // load a little piece in the bean and use the emplID and password to see if user is valid in function login
      boolean proceed = this.login(user, password, bean);
      if(proceed)
      {
         // login accepted
         // initialize the bean error message and dispatch to mainPage
         //bean.setError("");
         //bean.setMessage("");
         if (bean.getUserIsEmployee())
         {
            gotoPage("/MainPage.jsp", request, response);  //gotoPage is implemented in Control.java
         }
         else // user is client
         {
            gotoPage("/MainPage_Client.jsp", request, response);  //gotoPage is implemented in Control.java
         }
      }
      else 
      {
         // Login failed... so return to the login page.
         gotoPage("/Login.jsp", request, response); // dispatcher to Login.jsp
      }
   }

   // This procedure Logs user into the system

   private boolean login(String userid, String password, CTTS.Shared bean) throws ServletException
   {
      //login Method performs actual login attempt

      boolean oklogin = false;
      
      // new DBI object to access the database. DBI (Data Base Interface)
     CTTS.DBI dbi = new CTTS.DBI();  //creates a new instance of dbi
      
      try
      {
         //Checking if we got a database connection from Tomcat
         if(dbi.connect())
         {
            // Testing if userId and password match in database
            if(dbi.checkPassword(userid, password))
            {
               bean.setUsername(userid);
               oklogin =  true;
               
               if(dbi.userIsEmployee(bean, userid))
               {
                  bean.setUserIsEmployee(true);
                  
                  String[] emplInfo = dbi.getEmplInfo(userid);
                  
                  bean.setEmpId(Integer.parseInt(emplInfo[0]));
                  bean.setEmpFirstName(emplInfo[1]);
                  bean.setEmpLastName(emplInfo[2]);
                  bean.setEmpAddress(emplInfo[3]);
                  bean.setEmpPhone(emplInfo[4]);
                  bean.setEmpEmail(emplInfo[5]);
                  bean.setEmpPosition(emplInfo[6]);
                  bean.setEmpCompany(emplInfo[7]);
               }
               else // you are a client
               {
                  bean.setUserIsEmployee(false);
                  
                  String[] clientInfo = dbi.getClientInfo(userid);
                  
                  bean.setClientId(Integer.parseInt(clientInfo[0]));
                  bean.setClientCompanyName(clientInfo[1]);
               }
            }
            else
            {
               bean.setError("Username or password not recognized.");
               oklogin = false;
            }
         } // end dbi.connect() if statement
         else // we were not able to connect
         {
            bean.setError("Failed to connect to the database, please try again later.");
            oklogin = false;
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
         bean.setError("Servlet exception encountered, please contact site administrators.");
         oklogin = false;
      }  // End catch of try
      finally
      {
         dbi.close(); // return the connection to Tomcat connection pool
      }
      return oklogin;
   } // End login function
} // End of class



