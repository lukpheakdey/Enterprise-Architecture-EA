Generic batch-config.xml & user-job.xml has been converted to JavaConfig

Batch part of validation process.
Does ETL for "rough" Product data
Followed by "Interactive" process [ValidationGroupsDesktop] for data cleanup...
Validation OF core data.

Composite validation:   EmptyOrSize; NullMinNumber

AOP to run validation based on Service methods
Methods annotated with @ServiceValidation : e.g. Save; Update

ServiceValidationProcess is build to handle other Domain Object besides Product...
see generic type on declaration <T extends ValidationDto] 
BY EXAMPLE:  "potential" Member code already exists in ServiceValidationAspect...
 

Validation has also been put in to Hierarchical groups ...See Product...

(1,'ROLE_ADMIN','Sean','Sean'),
 (2,'ROLE_ADMIN','Bill','Bill'),
 (3,'ROLE_SUPERVISOR','Pete','Pete'),
 (4,'ROLE_SUPERVISOR','Paul','Paul');
 (5,'ROLE_SUPERVISOR','Batch','Batch')
 
 Pete SUPERVISES Bill
 Paul SUPERVISES Sean
  
	 EOD Batch job Loads the products with INVALID Status
	 If product "passes" default validation
	 	product is set to BASIC status & assigned to an Admin [Sean or Bill]
	 Admin "fixes" product
	  If product "passes" details validation
	 	   product is set to DETAILS  status & assigned to the Admin's Supervisor
	 Supervisor "fixes" product
	       product is set to Production status
 