/*
 * Copyright (c) 2011, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote users derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package edu.mum.controller;
 
import java.util.List;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import edu.mum.domain.Address;
import edu.mum.domain.User;
import edu.mum.exception.ValidationException;
import edu.mum.exception.ValidationExceptionGroup;
import edu.mum.main.ViewManager;
import edu.mum.service.UserCredentialsService;
import edu.mum.service.UserService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
@Component
@Scope("prototype")
public class UserController  {

	@Autowired
	ApplicationContext context;
 
	@Autowired
	UserCredentialsService userCredentialsService;

	@Autowired
	UserService userService;

	@Autowired
	MessageSourceAccessor messageAccessor;
	
	User user;
	
	@FXML private Text actiontarget;
    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField ratingField;
     @FXML
    TextField emailField;
      @FXML
     TextField zipCodeField;
     @FXML
     TextField idField;
 
    @FXML
    ChoiceBox<Choice> userChoice;
     
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
    	
/*
 * TODO: FX data binding...
 */		
      	user.setFirstName(firstNameField.getText());
      	user.setLastName(lastNameField.getText());
      	user.setEmail(emailField.getText());
       	if (!ratingField.getText().isEmpty())
      		user.setRating(new Integer(ratingField.getText()));
 
       	if (user.getAddresses().isEmpty())
        	 user.addAddress(new Address());
      	user.getAddresses().get(0).setZipcode(zipCodeField.getText());
 //    	user.getAddress().setUser(user);
     	        
        actiontarget.setText("Success!");

     	try {
     		if (idField.getText().isEmpty()) {
     		 userService.save(user); 
     		 addUserToList();
     		}
     		else {
     	     	user.setId(new Long(idField.getText()));  	     	 
     			userService.update(user); 

     		}
     	}
     	catch (AccessDeniedException e) {
     		actiontarget.setText(e.getMessage() + " - You are not a Supervisor!");
     	}
     	catch (ValidationException e) {
	     		String text  = " \n";
 	         	  List<FieldError> fieldErrors =  e.getErrors().getFieldErrors();
	        	  for (FieldError fieldError : fieldErrors) {
 		         	text += messageAccessor.getMessage(fieldError) + "\n";
 		         	actiontarget.setText(text);
	         	}
     	}
     	catch (ValidationExceptionGroup e) {
     		String text  = " \n";
          	for (ConstraintViolation<Object> error : e.getErrors()) {
         	   text += error.getPropertyPath() + " " +error.getMessage() + "\n";
         	   actiontarget.setText(text);
         	}
        } catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   

    }
    
    @FXML
    protected void handlelogoutButtonAction() {
    	
  	  // Clears the context for the current user/thread
  	  SecurityContextHolder.clearContext();

        Stage stage=(Stage) userChoice.getScene().getWindow();
        ViewManager viewManager = new ViewManager();
    	try {
			viewManager.displayView(getClass().getResource("/view/login.fxml"), stage, context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
    
	@FXML
	public void initialize(){

		setupChoiceList();

 	    // Listener for pick of User from drop down list...
 	   // We want to display user...
 	    userChoice.getSelectionModel().selectedItemProperty().addListener(choiceListener);
 	   	   
	}
	
	private void setupChoiceList() {
 	    List<User> users = userService.findAll();
	    
	    ObservableList<Choice> usersFX = FXCollections.observableArrayList();
	    usersFX.add(new Choice(null, "Choose User"));
	    for ( User user : users) {
	    	usersFX.add(new Choice(user.getId(), user.getFirstName()));
	      }

	    // Set @FXML field
	   userChoice.setItems(usersFX);
 	   userChoice.getSelectionModel().select(0);
 	     
 	   // Default to if NO choice from list then it is a new one...
 	   user = new User();

	}


	// Listener for User Selection
	// Drop down user list 
	ChangeListener<Choice> choiceListener = new ChangeListener<Choice>() {
	 	   
    	@Override 
	    	public void changed(ObservableValue<? extends Choice> observableValue, Choice oldChoice, Choice newChoice) {
	        if (newChoice.id == null) {
	      	       // If NO choice from list then new one...
	      	       user = new User();

		           /*
		            * TODO: FX data binding...
		            */
	        	// Clear text...
		            firstNameField.setText("");
		            lastNameField.setText("");
		            idField.setText("");	            
		            emailField.setText("");
		            ratingField.setText("");
 		            zipCodeField.setText("");

	        	actiontarget.setText("No Selection - that means add a NEW one!");
	        } else {
	           
	        	Long id =userChoice.getValue().getId();	          
	            user = userService.findOne(id);

	            // KLUDGE to handle BAD BATCH data
//	            if (user.getAddresses() == null) user.setAddress(new Address());
	           /*
	            * TODO: FX data binding...
	            */
	            // Convert from Domain Object to FX View
	            firstNameField.setText(user.getFirstName());
	            lastNameField.setText(user.getLastName());
	            emailField.setText(user.getEmail());
	            idField.setText(user.getId().toString());	            
 	            ratingField.setText(user.getRating().toString());
 	            if (!user.getAddresses().isEmpty())
 	            	zipCodeField.setText(user.getAddresses().get(0).getZipcode());
 	            else zipCodeField.setText("");
            actiontarget.setText("");

	        }
	      }


	    };	
	    
	    private void addUserToList() {
	    	//Disable[Remove] listener
	    	userChoice.getSelectionModel().selectedItemProperty().removeListener(choiceListener);
    		// re-establish list
	    	this.setupChoiceList();    	
	    	// re -establish listener
	    	userChoice.getSelectionModel().selectedItemProperty().addListener(choiceListener);
	    	
 	    }


}
