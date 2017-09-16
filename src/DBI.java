/*
 * Each servlet uses a Tomcat DB connection and closes it.
 *     So all of these methods assume the connection to be established already by the "connect" method
 * The servlets are also responsible for the commit of the data,
 *     because only the servlets know how many of these operations form a transaction
 * This class throws its exceptions to be caught/managed by the servlets
 * Resulting data is returned in the return variable, often an array
*/

package CTTS;

import javax.naming.*;
import javax.sql.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Vector;


public class DBI
{
   Connection conn = null;
   
   /* The arrays below hold string values that are used by the getString method to retirve information from ResultSet objects */
   String[] empFields = {"empId","empFirstName","empLastName","empAddress","empPhone","empEmail","empPosition","empCompany"};
   String[] clientFields = {"clientId","companyName","companyPhone","companyEmail","companyAddress","companyCity","companyCity","companyState","companyZip","contactFirstName","contactLastName","contactPhone","contactEmail"};
   String[] serviceRequestDetails = {"srId","reportDate","status","companyName","equipId","requestor","problemDescription","empFirstName","empLastName","empId"};
   String[] serviceWorkDetails = {"swId","startTime","finishTime","description","empFirstName","empLastName"};
   String[] technicians = {"empId","empFirstName","empLastName"};
   String[] clientEquipment = {"equipId"};
   String[] currentEmpName = {"firstname", "lastname"};
   String[] serviceRequestUnresolved = {"clientId","companyName","status","srID","reportDate","requestor","problemDescription","empName"};
       
   public void DBI() throws NamingException, SQLException {}
   
   public boolean connect() throws NamingException, SQLException
   {
      //Connect to the pinnacle database
      //Using the Tomcat Server.xml Context to locate the data
      Context initCtx = new InitialContext();
      Context envCtx = (Context)initCtx.lookup("java:comp/env");
      if(envCtx == null ) throw new NamingException("Boom - No Context");
         //Use jdbc to connect with sql statements
         DataSource ds = (DataSource)envCtx.lookup("jdbc/TestDB");
      //if DataSource ds is not null, the datasource was established (using Tomcat connection pool open with database)
      if(ds!=null)
      {
         //Get a connection from the Tomcat database connection pool with DataBase server
         conn = ds.getConnection();
         //If we get the connection ...
         if(conn != null) { return true; }
      }
      return false;
   }// end of connect method
   
   // Turn on/off autocommit 
   public boolean autocommit(boolean tf) throws SQLException
   {     
      if(conn != null)
   	{	conn.setAutoCommit(tf); // true =autocommit on, false = off
   		return true;
   	}
   	else return false;
   } // end autocommit
   
   // commit or rollback this transaction 
   public boolean commitTrans() throws SQLException
   {
      if(conn != null)
      {  
         conn.commit();
   		return true; // committ succeded
   	}
   	else return false;
   } // end commit
   
   public boolean rollbackTrans() throws SQLException
   {
      if(conn != null)
   	{
         conn.rollback(); // rollback succeeded
         return true;
   	}
   	else return false;
   } // end rollback
   
   public void close()
   {
      // give the connection back to Tomcat pool
      try
      {
         if(conn != null)
            conn.close();
      } 
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }//end of close method
   
   public ResultSet execQuery(String str) throws SQLException
   {
      //Build a jdbc statement shell for an SQL query string using the conn connection
      
      Statement stmt = conn.createStatement();
      //Execute the query and fetch the ResultSet from the database
      ResultSet rst = stmt.executeQuery(str);
      return rst;
   } //end of execQuery method
   
     
   /******************************** Employee or Client Information ******************************************************/
    
   public boolean checkPassword(String userId, String password) throws SQLException
   {
      // First gets the results of the Select query that matches the userid and password
      // Resultset is a datatype that allows  to handle a range of data (grid). This  datatype is part of java.sql
      ResultSet rst = this.execQuery("SELECT userId FROM UserTable WHERE userId = '"+userId+"' and password = '"+password+"'");
      
      // Go to first row of the ResultSet; if there are no rows, userid and password do not match the database
      boolean result=(rst.first());// if the record exists, the userid and password match, so return true, else return false
      return result;
   } // end of checkPassword method
   
   public boolean userIsEmployee(CTTS.Shared bean, String userId) throws SQLException
   {
      // First gets the results of the Select query that matches the userid
      // We want to see if empID is null so we know how to treat the user going forward.
      ResultSet rst = this.execQuery("SELECT empId FROM UserTable WHERE userId = '"+userId+"'");
      
      boolean result;
      
      int rstResult=0;
     // Go to first row of the ResultSet; if there are no rows, user is not an employee -- user is a client 
      while (rst.next())
      {
         rstResult = rst.getInt("empId");
      }
      
      if (rstResult!=0)
      {
         result=true;
      }
      else
      {
         result=false;
      }
      
      
      return result;
   } // end of checkIsEmployee method
   
   public String[] getClientInfo(String userId) throws SQLException
   {
      // get client data for one user when given userId
      String[] result = new String[12];
      ResultSet rst = this.execQuery("SELECT client.* FROM Client client, UserTable users WHERE users.userId = '"+userId+"' and users.clientId = client.clientID" );
      if(rst.first())
      {
         for (int i=0; i<8; i++)
         {
            result[i] = rst.getString(clientFields[i]);
         }
      }
      return result;
   }//end of getClientInfo method
   
   public String[] getEmplInfo(String userId) throws SQLException
   {
      // get employee data for one employee when given userId
      String[] result = new String[8];
      ResultSet rst = this.execQuery("SELECT emp.* FROM Employee emp, UserTable users WHERE users.userId = '"+userId+"' and users.empId = emp.empId" );
      if(rst.first())
      {
         for (int i=0; i<8; i++)
         {
            result[i] = rst.getString(empFields[i]);
         }
      }
      return result;
   }//end of getEmplInfo method
      
   public boolean clientExists(String clientId) throws SQLException
   {
      // First gets the results of the Select query that matches the userid and password
      // Resultset is a datatype that allows  to handle a range of data (grid). This  datatype is part of java.sql
      ResultSet rst = this.execQuery("SELECT clientID FROM Client WHERE clientID = '"+clientId+"'");
      
      // Go to first row of the ResultSet; if there are no rows, userid and password do not match the database
      boolean result=(rst.first());// if the record exists, the userid and password match, so return true, else return false
      return result;
   } // end of client Exists method
   
   
   public boolean equipIdBelongsToRequestorCompany(String clientId, String equipId) throws SQLException
   {
      // First gets the results of the Select query that matches the userid and password
      // Resultset is a datatype that allows  to handle a range of data (grid). This  datatype is part of java.sql
      ResultSet rst = this.execQuery("SELECT equipID FROM Equipment WHERE equipID = '"+equipId+"' and clientID = '"+clientId+"'");
      
      // Go to first row of the ResultSet; if there are no rows, userid and password do not match the database
      boolean result=(rst.first());// if the record exists, the userid and password match, so return true, else return false
      return result;
   } // end of check if equip belongs to the requesting user client method
   
 
   public String [][] getClientEquipment(int clientId) throws SQLException
   {
      ResultSet rst = this.execQuery("SELECT equipID FROM Equipment WHERE clientID = '"+clientId+"'");
      
      int columns = 1;
      int records = NumRecords(rst);
      String temp;
	   String[][] result = new String[records][columns];
		if(rst.first()) // if we managed to get results...
		{
		   for (int k=0; k<records; k++) // for every row
        {
			   for (int i=0; i<columns;i++) // for every column
           {
              temp = rst.getString(clientEquipment[i]);
              if(temp==null)
                 temp="";
				   result[k][i] = temp; // grab the service request details and store it in result.
				}
			   rst.next();
			}
     }
		return result;      
   } // end get Service Work details of one Service Request
 
  /*********************************************************************************************************/
  //Service Request Functions  
 //get all currently unresolved service requests for the report
   public String [][] getUnresolvedServiceRequests() throws SQLException
   {
      ResultSet rst = this.execQuery("SELECT client.clientId, client.companyName, sr.status, sr.srID, sr.reportDate,sr.requestor, sr.problemDescription, CONCAT(technician.empFirstName, ' ', technician.empLastName) AS empName FROM ServiceRequest sr LEFT JOIN Employee technician ON sr.assignedTech=technician.empID JOIN Client client ON sr.clientID=client.clientID WHERE (sr.status='Unresolved' OR sr.status='New' OR sr.status='On Hold'OR sr.status='Escalated') ORDER BY client.companyName, sr.status, sr.reportDate;");
      int columns = 8;
      int records = NumRecords(rst);
      String temp;
	   String[][] result = new String[records][columns];
		if(rst.first()) // if we managed to get results...
		{
		   for (int k=0; k<records; k++) // for every row
         {
			   for (int i=0; i<columns;i++) // for every column
            {
               temp = rst.getString(serviceRequestUnresolved[i]);
               if(temp==null)
                  temp="";
				   result[k][i] = temp; // grab the unresolved service request details and store it in result.
				}
			   rst.next();
			}
      }
		return result;      
   } // end get unresolved service requests for the report 


   //get new and escalated service requests and order by status and date in ascending order
   public String [][] getNewServiceRequests() throws SQLException
   {
      ResultSet rst = this.execQuery("SELECT sr.srID, sr.reportDate, sr.status, client.companyName, sr.equipID, sr.requestor, sr.problemDescription FROM ServiceRequest sr JOIN Client client ON client.clientID=sr.clientID WHERE sr.status='New'OR sr.status='Escalated'ORDER BY sr.status ASC, sr.reportDate;");
      int columns = 7;
      int records = NumRecords(rst);
      String temp;
	   String[][] result = new String[records][columns];
		if(rst.first()) // if we managed to get results...
		{
		   for (int k=0; k<records; k++) // for every row
         {
			   for (int i=0; i<columns;i++) // for every column
            {
               temp = rst.getString(serviceRequestDetails[i]);
               if(temp==null)
                  temp="";
				   result[k][i] = temp; // grab the new service request details and store it in result.
				}
			   rst.next();
			}
      }
		return result;      
   } // end get New Service requests for this client 


  
   
   public String [][] getMyServiceRequestsTechnician(int empId) throws SQLException
   {
      ResultSet rst = this.execQuery("SELECT sr.srID, sr.reportDate, sr.status, client.companyName, sr.equipID, sr.requestor, sr.problemDescription, technician.empFirstName, technician.empLastName  FROM ServiceRequest sr JOIN Employee technician ON sr.assignedTech=technician.empID JOIN Client client ON sr.clientID=client.clientID WHERE sr.assignedTech='"+empId+"' AND (sr.status='Unresolved' OR sr.status='On Hold' OR sr.status='Escalated');");
      int columns = 9;
      int records = NumRecords(rst);
      String temp;
	   String[][] result = new String[records][columns];
		if(rst.first()) // if we managed to get results...
		{
		   for (int k=0; k<records; k++) // for every row
         {
			   for (int i=0; i<columns;i++) // for every column
            {
               temp = rst.getString(serviceRequestDetails[i]);
               if(temp==null)
                  temp="";
				   result[k][i] = temp; // grab the service request details and store it in result.
				}
			   rst.next();
			}
      }
		return result;      
   } // end get My Service request for Tech
   

   public String [][] getMyServiceRequestsClient(int clientId) throws SQLException
   {
      ResultSet rst = this.execQuery("SELECT sr.srID, sr.reportDate, sr.status, client.companyName, sr.equipID, sr.requestor, sr.problemDescription, technician.empFirstName, technician.empLastName  FROM ServiceRequest sr LEFT OUTER JOIN Employee technician ON sr.assignedTech=technician.empID JOIN Client client ON client.clientID=sr.clientID WHERE sr.clientId='"+clientId+"' AND (sr.status!='Cancelled') ORDER BY sr.status ASC, sr.reportDate;");
      int columns = 9;
      int records = NumRecords(rst);
	   String temp;
	   String[][] result = new String[records][columns];
		if(rst.first()) // if we managed to get results...
		{
		   for (int k=0; k<records; k++) // for every row
         {
			   for (int i=0; i<columns;i++) // for every column
            {
               temp = rst.getString(serviceRequestDetails[i]);
               if(temp==null)
                  temp="";
				   result[k][i] = temp; // grab the service request details and store it in result.
				}
			   rst.next();
			}
      }
		return result;      
   } // end get My Service Requests for Client



   public String [] getServiceRequestDetails(String srId) throws SQLException
   {
      ResultSet rst = this.execQuery("SELECT sr.srID, sr.reportDate, sr.status, client.companyName, sr.equipID, sr.requestor, sr.problemDescription, technician.empFirstName, technician.empLastName, technician.empID  FROM ServiceRequest sr JOIN Employee technician ON sr.assignedTech=technician.empID JOIN Client client WHERE sr.srID='"+srId+"';");
      int records = NumRecords(rst);
      String[] result = new String[10];
		if(rst.first()) // if we managed to get results...
		{
		   for (int i=0; i<10; i++) // for every row
         {
            result[i] = rst.getString(serviceRequestDetails[i]); // grab the service request details and store it in result.
			}
      }
		return result;  
   } // end get Service Requests details of one Service Request


   public String [][] getServiceWorkDetails(String srId) throws SQLException
   {
      ResultSet rst = this.execQuery("SELECT sw.swID, sw.startTime, sw.finishTime, sw.description, technician.empFirstName, technician.empLastName FROM ServiceWork sw LEFT OUTER JOIN Employee technician ON sw.empID=technician.empID WHERE sw.srID='"+srId+"';");
      
      int columns = 6;
      int records = NumRecords(rst);
	   String[][] result = new String[records][columns];
		if(rst.first()) // if we managed to get results...
		{
		   for (int k=0; k<records; k++) // for every row
         {
			   for (int i=0; i<columns;i++) // for every column
            {
				   result[k][i] = rst.getString(serviceWorkDetails[i]); // grab the service request details and store it in result.
				}
			   rst.next();
			}
      }
		return result;      
   } // end get Service Work details of one Service Request


   // *************** Update and Insert  Functions ***********************************
   public void createServiceRequest(String requestor, String clientId, String equipId, String problemDescription) throws SQLException
   {
      Statement stmt = conn.createStatement();
		String insertString ="INSERT INTO ServiceRequest (equipID, clientID, reportDate, problemDescription, status, requestor) VALUES ('"+equipId+"', '"+clientId+"', CURRENT_TIMESTAMP(), '"+problemDescription+"', 'New','"+requestor+"');";
		stmt.executeUpdate(insertString);
   } // end create new Service Request method
   
   public void createServiceWork(String empId, String startTime, String finishTime, String Description, String srId) throws SQLException
   {
      Statement stmt = conn.createStatement();
		String insertString ="INSERT INTO ServiceWork (empID, startTime, finishTime, Description, srID) VALUES ('"+empId+"', '"+startTime+"', '"+finishTime+"', '"+Description+"', '"+srId+"');";
		stmt.executeUpdate(insertString);
   } // end create new Service Work method
   public void adoptServiceRequest(String srId, int empId) throws SQLException
   {
      Statement stmt = conn.createStatement();
		String insertString ="UPDATE ServiceRequest SET status='Unresolved', assignedTech='"+empId+"' WHERE srID='"+srId+"';";
		stmt.executeUpdate(insertString);
   } // end adopt new Service Request by a Tech

 public void addServiceWork(String srId, int empId, String startTime, String finishTime, String description) throws SQLException
   {
      Statement stmt = conn.createStatement();
		String insertString ="INSERT INTO ServiceWork (srID, empID, startTime, finishTime, description) VALUES ('"+srId+"', '"+empId+"', '"+startTime+"', '"+finishTime+"', '"+description+"');";
		stmt.executeUpdate(insertString);
   } // end add Service Work record for this Service Request by a Tech
 
  public void addEmployee(String empFirstName, String empLastName, String empAddress, String empPhone, String empEmail, String empPosition, String empCompany) throws SQLException
   {
      Statement stmt = conn.createStatement();
		String insertString ="INSERT INTO Employee (empFirstName, empLastName, empAddress, empPhone, empEmail, empPosition, empCompany) VALUES ('"+empFirstName+"', '"+empLastName+"', '"+empAddress+"', '"+empPhone+"', '"+empEmail+"', '"+empPosition+"', '"+empCompany+"' );";
		stmt.executeUpdate(insertString);
   } // end add employee
  

  public void escalateServiceRequest(String srId, String assignedTechnician) throws SQLException
   {
      Statement stmt = conn.createStatement();
		String insertString ="UPDATE ServiceRequest SET status='Escalated', assignedTech='"+assignedTechnician+"' WHERE srID='"+srId+"';";
		stmt.executeUpdate(insertString);
   } // end escalate a Service Request by a tech
   
   public void updateServiceRequestStatus(String srId, String status) throws SQLException
   {
      Statement stmt = conn.createStatement();
		String insertString ="UPDATE ServiceRequest SET status='"+status+"' WHERE srID='"+srId+"';";
		stmt.executeUpdate(insertString);
   } // end update Service Request changed Status for a Service Request
   
   
  
   /*********************************************************************************************************/
   
 
   
   public int NumRecords(ResultSet rst) throws SQLException
   {
      // get the number of records in the rst ResultSet
      int recordCounter=0;
      if(rst.first())
      {
         do {
            recordCounter++;
         } while(rst.next());
      }
      return recordCounter;
   }// end of NumRecords method




}// End of DBI

