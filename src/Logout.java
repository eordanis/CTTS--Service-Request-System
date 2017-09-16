package CTTS;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import CTTS.Control;

public class Logout extends CTTS.Control
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {/* Invalidates the session and dereferences the bean */
	  HttpSession session = request.getSession(true); //Returns the current session associated with this request
	  CTTS.Shared bean = (CTTS.Shared)session.getAttribute("shared");
          bean.setEmpId(0); // empty the bean
          session.invalidate(); //invalidates this session and unbinds any objects bound to it
	  /* Dispatches to original "Control" servlet	*/
	  this.gotoPage("/Control", request,response); //dispatcher to the Control.java
    }
}
