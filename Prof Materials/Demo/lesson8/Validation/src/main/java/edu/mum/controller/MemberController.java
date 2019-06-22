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
 *    contributors may be used to endorse or promote members derived
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import edu.mum.domain.Address;
import edu.mum.domain.Member;
import edu.mum.exception.ValidationException;
import edu.mum.main.ViewManager;
import edu.mum.service.CredentialsService;
import edu.mum.service.MemberService;
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
public class MemberController  {

	@Autowired
	ApplicationContext context;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CredentialsService userCredentialsService;

	@Autowired
	MemberService memberService;

	@Autowired
	MessageSourceAccessor messageAccessor;
	
	Member member;
	
	@FXML private Text actiontarget;
    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField ageField;
     @FXML
    TextField memberNumberField;
     @FXML
     TextField titleField;
     @FXML
     TextField stateField;
     @FXML
     TextField zipCodeField;
     @FXML
     TextField idField;
 
    @FXML
    ChoiceBox<Choice> memberChoice;
     
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
    	
/*
 * TODO: FX data binding...
 */		
      	member.setFirstName(firstNameField.getText());
      	member.setLastName(lastNameField.getText());
      	if (!memberNumberField.getText().isEmpty())
      		member.setMemberNumber(new Integer(memberNumberField.getText()));
      	if (!ageField.getText().isEmpty())
      		member.setAge(new Integer(ageField.getText()));
      	member.setTitle(titleField.getText());       
      	member.getAddress().setState(stateField.getText());
     	member.getAddress().setZipCode(zipCodeField.getText());
     	member.getAddress().setMember(member);
     	        
        actiontarget.setText("Success!");

     	try {
     		if (idField.getText().isEmpty()) {
     		 memberService.save(member); 
     		 addMemberToList();
     		}
     		else {
     	     	member.setId(new Long(idField.getText()));  	     	 
     			memberService.update(member); 

     		}
     	}
     	catch (AccessDeniedException e) {
     		actiontarget.setText(e.getMessage() + " - You are not the Owner!");
     	}
     	catch (ValidationException e) {
	     		String text  = " \n";
 	         	  List<FieldError> fieldErrors =  e.getErrors().getFieldErrors();
	        	  for (FieldError fieldError : fieldErrors) {
 		         	text += messageAccessor.getMessage(fieldError) + "\n";
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

        Stage stage=(Stage) memberChoice.getScene().getWindow();
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

 	    // Listener for pick of Member from drop down list...
 	   // We want to display member...
 	    memberChoice.getSelectionModel().selectedItemProperty().addListener(choiceListener);
 	   	   
	}
	
	private void setupChoiceList() {
 	    List<Member> members = memberService.findAll();
	    
	    ObservableList<Choice> membersFX = FXCollections.observableArrayList();
	    membersFX.add(new Choice(null, "Choose Member"));
	    for ( Member member : members) {
	    	membersFX.add(new Choice(member.getId(), member.getFirstName()));
	      }

	    // Set @FXML field
	   memberChoice.setItems(membersFX);
 	   memberChoice.getSelectionModel().select(0);
 	     
 	   // Default to if NO choice from list then it is a new one...
 	   member = new Member();

	}


	// Listener for Member Selection
	// Drop down member list 
	ChangeListener<Choice> choiceListener = new ChangeListener<Choice>() {
	 	   
    	@Override 
	    	public void changed(ObservableValue<? extends Choice> observableValue, Choice oldChoice, Choice newChoice) {
	        if (newChoice.id == null) {
	      	       // If NO choice from list then new one...
	      	       member = new Member();

		           /*
		            * TODO: FX data binding...
		            */
	        	// Clear text...
		            firstNameField.setText("");
		            lastNameField.setText("");
		            idField.setText("");	            
		            memberNumberField.setText("");
		            ageField.setText("");
		            titleField.setText("");
		            stateField.setText("");
		            zipCodeField.setText("");

	        	actiontarget.setText("No Selection - that means add a NEW one!");
	        } else {
	           
	        	Long id =memberChoice.getValue().getId();	          
	            member = memberService.findOne(id);

	            // KLUDGE to handle BAD BATCH data
	            if (member.getAddress() == null) member.setAddress(new Address());
	           /*
	            * TODO: FX data binding...
	            */
	            // Convert from Domain Object to FX View
	            firstNameField.setText(member.getFirstName());
	            lastNameField.setText(member.getLastName());
	            idField.setText(member.getId().toString());	            
	            memberNumberField.setText(member.getMemberNumber().toString());
	            ageField.setText(member.getAge().toString());
	            titleField.setText(member.getTitle());
	            stateField.setText(member.getAddress().getState());
	            zipCodeField.setText(member.getAddress().getZipCode());
                 
            actiontarget.setText("");

	        }
	      }


	    };	
	    
	    private void addMemberToList() {
	    	//Disable[Remove] listener
	    	memberChoice.getSelectionModel().selectedItemProperty().removeListener(choiceListener);
    		// re-establish list
	    	this.setupChoiceList();    	
	    	// re -establish listener
	    	memberChoice.getSelectionModel().selectedItemProperty().addListener(choiceListener);
	    	
 	    }


}
