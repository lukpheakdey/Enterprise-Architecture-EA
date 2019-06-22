package edu.mum.component.factory;

import edu.mum.component.MessageSource;

public class SourceFactory {

	private SourceFactory() {
		
	}
	/**
	 * @param messageSourceName
	 * @return
	 */

 	public static final MessageSource getInstance(String messageSourceName) {
		MessageSource messageSource = null;
 		
 		try {
 			// using the Reflection API get the class(forName); get the default Constructor; create an instance...
  			messageSource = (MessageSource)  Class.forName(messageSourceName).getConstructor().newInstance();			
 		
 		}
 		catch (Exception e){
	    	e.printStackTrace();
 		}
 		
		return (messageSource);
 			

 	}
}
