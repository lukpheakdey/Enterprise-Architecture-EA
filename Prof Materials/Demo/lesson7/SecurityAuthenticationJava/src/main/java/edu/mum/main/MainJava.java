package edu.mum.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

import edu.mum.configuration.Persistence;
import edu.mum.configuration.Security;
import edu.mum.domain.Address;
import edu.mum.domain.Member;
import edu.mum.security.AuthenticateUser;
import edu.mum.service.MemberService;

@Component
public class MainJava {
	
	@Autowired
    AuthenticationManager authenticationManager;
    
    
    @Autowired
    MemberService memberService;
    
    public static void main(String[] args) throws Exception {
    	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Persistence.class,Security.class);
        applicationContext.getBean(MainJava.class).mainInternal(applicationContext);
    }
      
    private void mainInternal(AnnotationConfigApplicationContext applicationContext) throws Exception {

 //   	AuthenticationConfiguration authenticationConfiguration =  (AuthenticationConfiguration) applicationContext.getBean("authenticationConfiguration");
 //   	AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
    	//       AuthenticationManager authenticationManager = (AuthenticationManager) applicationContext.getBean("authenticationConfiguration");

//         MemberService memberService = (MemberService) applicationContext.getBean("memberServiceImpl");
      
         new TestData().setup(memberService);
         
	     try {
	     List<Member> members = memberService.findAll();
	     }
	     catch (AuthenticationCredentialsNotFoundException e) {
	    	 System.out.println( );
	    	 System.out.println( " ******** ANONYMOUS USER Attempted to access a secure resource *********"  );
	       	 System.out.println( );
	     }
	 
	 while (true)  {    
	     AuthenticateUser authenticateUser = new AuthenticateUser();
	     try {
	  		authenticateUser.authenticate(authenticationManager);
	  	} catch (Exception e) {
	  		// TODO Auto-generated catch block
	  		e.printStackTrace();
	  	}
	     
//	     authenticateUser.logout();
	     
	  // ANYBODY but Bill will get access Denied - AS Bill is An ADMIN & 
	  // findAll requires Admin
	      try {
	    	 List<Member> members = memberService.findAll();
	    	   
	    	 for (Member membere : members) {
	    		   
	    		   System.out.println( );
	    		   System.out.println("Member Name : " + membere.getFirstName() + "  " +  membere.getLastName() );
	    		
	    		   for (Address addresse : membere.getAddresses()) {
	    		       System.out.println("Address : " + addresse.getCity() + 
	    						"   " + addresse.getState());
	    		   }
	    	   }
	    	 
	     }
	     catch ( AccessDeniedException e) {
	 		   System.out.println( );
	 		   System.out.println("****** ACCESS DENIED ! You Need ROLE_USER to access Member findAll()  **********");
			   System.out.println( );

	     }
	 
	   }
    }
}
