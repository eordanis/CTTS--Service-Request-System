//local
package CTTS;

import java.util.*;
import java.io.Serializable;


public class Shared implements Serializable // session bean named "shared" Tomcat keeps alive between login sessions
{
	private String error = "";       // string to hold error message
	private String message = "";     // string to hold the message
   private String currentDate;
   
   private String  username;        // username for the current user
   private boolean userIsEmployee;  // keeps track of whether current user is an employee or client
	
	private int    empId;            // employee's unique id number
	private String empLastName;      // employee's last name
   private String empFirstName;     // employee's first name
   private String empAddress;       // employee's address
	private String empPhone;         // employee's phone number
	private String empEmail;         // employee's email address
	private String empPosition;      // employee's position at the company
   private String empCompany;       // employee's company
   
   private int clientId;                  // company's client ID
   private String clientCompanyName;      // company's name

   private String srId;                       // The ID of the current service request
   private String currsrId;                       // The ID of the current service request
   private String equipId;                    // The equipment ID the service request is about
   private String dateEntered;                // Date the current service request was created
   private String companyName;                // Client company's name for the current service request
   private String status;                     // The status of the current service request
   private String requestor;                  // The name of the requestor for the current service request
   private String problemDescription;         // The ID of the current service request
   private String assignedTechnicianFirstName;
   private String assignedTechnicianLastName;
   private String assignedTechnician;         // The ID of the currently assigned technician for the current service request
   
   private String [][] newServiceRequests;
   private String [][] unresolvedServiceRequests;
   private String [][] escServiceRequests;
   private String [][] myServiceRequests;
   private String [] myServiceRequestDetails;
   private String [] serviceRequest;
   private String [][] serviceWork;
   private String [][] clientEquipment;
	private String [][] technicians;

   
	private String currentEmpFirstName;
	private String currentEmpLastName;
	private String currentEmpEmail;
	
   private String newempLastName;      // employee's last name
   private String newempFirstName;     // employee's first name
   private String newempAddress;       // employee's address
   private String newempPhone;         // employee's phone number
   private String newempEmail;         // employee's email address
   private String newempPosition;      // employee's position at the company
   private String newempCompany;       // employee's company
   
   //reports
   
   
   /****************/
   /* Constructors */
   /****************/

   public void Shared(){} // Beans must have a zero argument constructor

   /**********************************************/
   /* Public Accessor Methods, required get/sets */
   /* for jsp:getProperty, jsp:setProperty       */  
   /**********************************************/

   public String getUsername() 
   {
      return(this.username);
   }
   public void setUsername(String value) 
   {
      this.username = value;
   }
   
   public boolean getUserIsEmployee() 
   {
      return(this.userIsEmployee);
   }
   public void setUserIsEmployee(boolean value) 
   {
      this.userIsEmployee = value;
   }

/********************** Employee *********************************/ 
   public int getEmpId() 
   {
      return(this.empId);
   }
   public void setEmpId(int value) 
   {
      this.empId = value;
   }
   
   public String getEmpFirstName() 
   {
      return(this.empFirstName);
   }
   public void setEmpFirstName(String value) 
   {
      this.empFirstName = value;
   }
   
   public String getEmpLastName() 
   {
      return(this.empLastName);
   }
   public void setEmpLastName(String value) 
   {
      this.empLastName = value;
   }
      
   public String getEmpAddress() 
   {
      return(this.empAddress);
   }
   public void setEmpAddress(String value) 
   {
      this.empAddress = value;
   }
   
	public String getEmpPhone() 
   {
      return(this.empPhone);
   }
   public void setEmpPhone(String value) 
   {
      this.empPhone = value;
   } 
     
   public String getEmpEmail() 
   {
      return(this.empEmail);
   }
   public void setEmpEmail(String value) 
   {
      this.empEmail = value;
   }
   
	public String getEmpPosition() 
   {
      return(this.empPosition);
   }
   public void setEmpPosition(String value) 
   {
      this.empPosition = value;
   }
   
   public String getEmpCompany() 
   {
      return(this.empCompany);
   }
   public void setEmpCompany(String value) 
   {
      this.empCompany = value;
   }
public String getNewEmpFirstName() 
   {
      return(this.newempFirstName);
   }
   public void setNewEmpFirstName(String value) 
   {
      this.newempFirstName = value;
   }
   
   public String getNewEmpLastName() 
   {
      return(this.newempLastName);
   }
   public void setNewEmpLastName(String value) 
   {
      this.newempLastName = value;
   }
      
   public String getNewEmpAddress() 
   {
      return(this.newempAddress);
   }
   public void setNewEmpAddress(String value) 
   {
      this.newempAddress = value;
   }
   
	public String getNewEmpPhone() 
   {
      return(this.newempPhone);
   }
   public void setNewEmpPhone(String value) 
   {
      this.newempPhone = value;
   } 
     
   public String getNewEmpEmail() 
   {
      return(this.newempEmail);
   }
   public void setNewEmpEmail(String value) 
   {
      this.newempEmail = value;
   }
   
	public String getNewEmpPosition() 
   {
      return(this.newempPosition);
   }
   public void setNewEmpPosition(String value) 
   {
      this.newempPosition = value;
   }
   
   public String getNewEmpCompany() 
   {
      return(this.newempCompany);
   }
   public void setNewEmpCompany(String value) 
   {
      this.newempCompany = value;
   }
/************************** Company *************************************/ 

   public int getClientId() 
   {
      return(this.clientId);
   }
   public void setClientId(int value) 
   {
      this.clientId = value;
   }
   
   public String getClientCompanyName() 
   {
      return(this.clientCompanyName);
   }
   public void setClientCompanyName(String value) 
   {
      this.clientCompanyName = value;
   }

/********************** Service Request *********************************/ 

   public String [][] getUnresolvedServiceRequests() 
   {
      return(this.unresolvedServiceRequests);
   }

   public void setUnresolvedServiceRequests(String [][] value) 
   {
      this.unresolvedServiceRequests = value;
   }
   public String [][] getNewServiceRequests() 
   {
      return(this.newServiceRequests);
   }

   public void setNewServiceRequests(String [][] value) 
   {
      this.newServiceRequests = value;
   }
  
   public String [][] getMyServiceRequests() 
   {
      return(this.myServiceRequests);
   }
   public void setMyServiceRequests(String [][] value) 
   {
      this.myServiceRequests = value;
   }

 public String [] getMyServiceRequestDetails() 
   {
      return(this.myServiceRequestDetails);
   }

   public void setMyServiceRequestDetails(String [] value) 
   {
      this.myServiceRequestDetails = value;
   }
   
   public String [] getServiceRequest() 
   {
      return(this.serviceRequest);
   }
   public void setServiceRequest(String [] value) 
   {
      this.serviceRequest = value;
   }
   
   public String [][] getServiceWork() 
   {
      return(this.serviceWork);
   }
   public void setServiceWork(String [][] value) 
   {
      this.serviceWork = value;
   }
   
   public String [][] getTechnicians()
   {
      return(this.technicians);
   }
   public void setTechnicians(String [][] value)
   {
      this.technicians = value;
   }

/********* Individual Service Request Details **********/

   public String getSrId() 
   {
      return(this.srId);
   }
   public void setSrId(String value) 
   {
      this.srId = value;
   }
    public String getcurrSrId() 
   {
      return(this.currsrId);
   }
   public void setcurrSrId(String value) 
   {
      this.currsrId = value;
   }
   public String [][] getClientEquipment() 
   {
      return(this.clientEquipment);
   }
   public void setClientEquipment(String [][] value) 
   {
      this.clientEquipment = value;
   }
   public String getEquipId() 
   {
      return(this.equipId);
   }
   public void setEquipId(String value) 
   {
      this.equipId = value;
   }
   
   public String getDateEntered() 
   {
      return(this.dateEntered);
   }
   public void setDateEntered(String value) 
   {
      this.dateEntered = value;
   }
   
   public String getCompanyName() 
   {
      return(this.companyName);
   }
   public void setCompanyName(String value) 
   {
      this.companyName = value;
   }
      
   public String getStatus() 
   {
      return(this.status);
   }
   public void setStatus(String value) 
   {
      this.status = value;
   }
      
   public String getRequestor() 
   {
      return(this.requestor);
   }
   public void setRequestor(String value) 
   {
      this.requestor = value;
   }
   
   public String getProblemDescription() 
   {
      return(this.problemDescription);
   }
   public void setProblemDescription(String value) 
   {
      this.problemDescription = value;
   }
   
   public String getAssignedTechnician() 
   {
      return(this.assignedTechnician);
   }
   public void setAssignedTechnician(String value) 
   {
      this.assignedTechnician = value;
   }
      
   public String getAssignedTechnicianFirstName() 
   {
      return(this.assignedTechnicianFirstName);
   }
   public void setAssignedTechnicianFirstName(String value) 
   {
      this.assignedTechnicianFirstName = value;
   }
      
   public String getAssignedTechnicianLastName() 
   {
      return(this.assignedTechnicianLastName);
   }
   public void setAssignedTechnicianLastName(String value) 
   {
      this.assignedTechnicianLastName = value;
   }
   
   
/*******************************************************/


   public String getError()
   {
       return(this.error);
   }
   public void setError(String value) // set error saves the error value from an exception
   {
       this.error = value;
   }
        
	public String getMessage()
   {
        return(this.message);
   }
    
	public void setMessage(String value) // set any message the user needs to see from a servlet
   {
        this.message = value;
   }
   
   public void setCurrentDate(String value)
   {
        this.currentDate = value;
   }
         
   public String getCurrentDate()
   {
       return(this.currentDate);
   }
     
} // end of shared bean

