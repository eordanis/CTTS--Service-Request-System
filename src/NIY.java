/******************************************************************************************

This servlet is used when a form on a jsp is not implemented yet
   
******************************************************************************************/

package CTTS;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import CTTS.Shared;

public class NIY extends CTTS.Control
{
     
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   { 
      System.out.println("NIY servlet found");
      HttpSession session = request.getSession(); // returns the current session associated with this request, or if the request does not have a session, creates one.
      CTTS.Shared bean = (CTTS.Shared)session.getAttribute("shared"); // get bean from the session
	   bean.setMessage("Function Not Implemented Yet");		

           
      {
         gotoPage("/NIY.html", request, response); // dispatcher to ViewMyServiceRequests.jsp
      }
   }
   
  
      }
