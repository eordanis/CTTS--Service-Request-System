  //Get all of the inputs and check if empty and simple syntax
  function checkForm (form) {
    /* get input values from the html input boxes */
    var requestor = document.createServiceRequest.requestor.value;
    var clientId = document.createServiceRequest.clientId.value;
    var equipId = document.createServiceRequest.equipId.value;
    var problemDescription = document.createServiceRequest.problemDescription.value;

    /* check to see if any of our required values are blank */
    if (requestor == '') {
      alert('Please enter your first and last name.');
      document.createServiceRequest.requestor.focus();
      return false;
    }
    if (clientId == '') {
      alert('Please enter a client ID.');
      document.createServiceRequest.clientId.focus();
      return false;
    }
    if (equipId == '') {
      alert('Please enter an equipment ID.');
      document.createServiceRequest.equipId.focus();
      return false;
    }
    if (problemDescription == '') {
      alert('Please enter a description of your problem.');
      document.createServiceRequest.problemDescription.focus();
      return false;
    }
      
    /* check to make sure name is in normal format */
    var ok=requestor.search(/[a-zA-Z\-\']+[\s][a-zA-Z\-\'\s]+/);
    if(ok != 0) {
        alert('For the requestor, you typed an invalid character. Please enter the first and last name of the person experiencing a problem.');
        document.createServiceRequest.requestor.focus();
        return false;
    }
    
    /* check to make sure only a numeric is entered for numeric only values */
    var ok=clientId.search(/^\d+$/);
    if(ok != 0) {
      alert('For the client ID, you typed an invalid character. Please re-enter the client ID.');
      document.createServiceRequest.clientId.focus();
      return false;
    }

    var ok=equipId.search(/^\d+$/);
    if(ok != 0) {
      alert('For the equipment ID, you typed an invalid character. Please re-enter the equipment ID.');
      document.createServiceRequest.equipId.focus();
      return false;
    }
    return true; // if no other condition listed above already returned false, this statement will return the value of true.       
  }