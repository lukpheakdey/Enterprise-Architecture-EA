package edu.mum.validation.aop;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.groups.Default;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import edu.mum.domain.ProductionStatus;
import edu.mum.domain.UserCredentials;
import edu.mum.service.CredentialsService;
import edu.mum.validation.aop.dto.ValidationDto;
import edu.mum.validation.groups.Details;
import edu.mum.validation.groups.Production;

/*
 * ALL target objects to validate follow the same process.
 * SO here we are used GENERICS to re-use the process...
 * NOTE: the DESKTOP Version is called GenericValidator
 */
@Component
public  class ServiceValidationProcess<T extends ValidationDto> {


	@Autowired
	CredentialsService credentialsService;

 
     public void  doValidate( ProceedingJoinPoint joinPoint, T validationDto) throws Throwable {
     	
    	
     	ProductionStatus newStatus = null;
     	
     	System.out.println();
     	System.out.println("DOING Validation!");
     	
     	// Using Hibernate validator...
        javax.validation.Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();      	

        /*
         * Get the validation group to validate against
         * ...and Validate!!!
         */
  
        Class<?> group = this.getValidationGroup(validationDto);
         Set<ConstraintViolation<Object>> errors = validator.validate(validationDto.getValidationObject(), group);
         	        	

         /*
          * Any Validation errors?
          * If errors PRINT them...AND return
          * ELSE update status to next step in process AND PRINT success!!!
          */
        Boolean validationSuccess = errors.size() == 0 ? true : false;       
       	if (!validationSuccess) {
      	     for (ConstraintViolation<Object> error : errors) {
      	          System.out.println(error.getPropertyPath() + " " +error.getMessage());
      	     }
      	   	return;
      	}
       	
   		newStatus = this.setProductStatus(validationDto); 
    	System.out.println("Validation Success! setting Status to: " + newStatus);

     
       	/*
       	 * THIS is the AROUND part of the ASPECT
       	 * Upon Validation SUCCESS - Execute joinpoint  -- SAVE or  UPDATE
       	 */
       joinPoint.proceed(new Object[]{validationDto.getValidationObject()}); 
       
       /*
        *  If it is Valid in the current state [i.e., ProductStatus] 
        *  THEN move to the Status...to continue processing...
        */
       
      	if (validationSuccess) {
      		Long id = validationDto.getId();
      		this.manageWorkQueue(validationDto,id,newStatus);
      	}
       return;
   
    }
  
    /*
     *  Enter domain object on appropriate work queue
     */
    @SuppressWarnings("unchecked")
	private void manageWorkQueue(T object, Long id, ProductionStatus status) {
    	UserCredentials owner = this.determineOwner(id, status);
    	
    	if (status == ProductionStatus.BASIC)
    		owner.getMember().getWorkQueue().add( object.getId());  // add to admin queue
    	else 
    		if (status == ProductionStatus.DETAILS){
    	   	// Remove from Admin queue
        	owner.getMember().getWorkQueue().remove(object.getId());

        	//Add to Supervisor Queue
        	owner = owner.getMember().getSupervisor().getUserCredentials();
    		owner.getMember().getWorkQueue().add(object.getId())  ;
    	}
        	else 
        		if (status == ProductionStatus.PRODUCTION){
        	   	// Remove from SuperVisor queue
            	owner.getMember().getWorkQueue().remove(object.getId());
        	}

    }
    
    /*
     * Determine NEW owner...to associated with Domain Object
     */
    private UserCredentials determineOwner(Long id, ProductionStatus status) {
    	UserCredentials owner = null;
    
        switch (status) {
        // "Round robin" assignment to ROLE_ADMIN
      	case BASIC:
	    	List<UserCredentials> credentials = credentialsService.findAllAdmin();
	     	Long ownerIndex =  id % credentials.size();
	     	 owner = credentials.get(ownerIndex.intValue());
    		break;

  		case DETAILS:
  			// Logged in user is current owner - want to find him;
  			String userName = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
  			owner = credentialsService.findOne(userName);
  			break;
  		case PRODUCTION:
  		default:
  	    	 owner = null;

        }

     	return owner;
    }
    
    /*
     * Based on domain object being processed and it's current status, 
     *  return the current Validation Group class for the domain object
     */
    private Class<?> getValidationGroup(T object) throws Throwable {
    	
     	ProductionStatus currentStatus  = object.getStatus();
      
      Class<?> returnClass = Default.class;
      
      switch (currentStatus) {
      case INVALID:
    	  returnClass = Default.class;
   	   break;
   	case BASIC:
  	  returnClass = Details.class;
  		break;

		case DETAILS:
		case PRODUCTION:
	    	  returnClass = Production.class;

      }

      return returnClass;
    }
    	 
    /*
     * Move the status to NEXT state
     */
    private ProductionStatus setProductStatus(T object) throws Throwable {
        ProductionStatus returnStatus = null;
    	ProductionStatus currentStatus  = object.getStatus();
    
       switch (currentStatus) {
       case INVALID:
    	   returnStatus = ProductionStatus.BASIC;
    	   break;
    	case BASIC:
    		returnStatus = ProductionStatus.DETAILS;
    		break;
 
		case DETAILS:
		case PRODUCTION:
			returnStatus =  ProductionStatus.PRODUCTION;
 
       }

       object.setStatus(returnStatus);
 
       return returnStatus;
 
    }
}

 


