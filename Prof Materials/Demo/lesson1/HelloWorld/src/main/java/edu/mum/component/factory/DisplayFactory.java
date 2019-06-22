package edu.mum.component.factory;

import edu.mum.component.MessageDisplay;

public class DisplayFactory {

 	private DisplayFactory() {	}

 	public static MessageDisplay getInstance(String messageDisplayName) {
		MessageDisplay messageDisplay = null;
 		
 		try {
 			// using the Reflection API get the class(forName); get the default Constructor; create an instance...
  			messageDisplay = (MessageDisplay)  Class.forName(messageDisplayName).getConstructor().newInstance();			
 		
 		}
 		catch (Exception e){
	    	e.printStackTrace();
 		}
 		
		return (messageDisplay);
 			

 	}
}
