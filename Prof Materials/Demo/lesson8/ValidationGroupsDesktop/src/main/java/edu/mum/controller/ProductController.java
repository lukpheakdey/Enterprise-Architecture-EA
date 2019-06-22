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
 *    contributors may be used to endorse or promote products derived
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
 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import edu.mum.domain.Product;
import edu.mum.domain.UserCredentials;
import edu.mum.exception.ValidationException;
import edu.mum.main.ViewManager;
import edu.mum.service.CredentialsService;
import edu.mum.service.ProductService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
@Component
@Scope("prototype")
public class ProductController  {

	@Autowired
	ApplicationContext context;

 	@Autowired
	CredentialsService userCredentialsService;

	@Autowired
	ProductService productService;

	
	Product product;
	
	@FXML private Text actiontarget;
    @FXML
    TextField nameField;
    @FXML
    TextArea descriptionField;
    @FXML
    TextArea shortDescriptionField;
    @FXML
    TextField priceField;
    @FXML
    TextField quantityField;
    @FXML
    TextField weightField;
    @FXML
    TextField productNumberField;
    @FXML
    TextField idField;
    @FXML
    TextField manufacturerField;
 
    @FXML
    ChoiceBox<Choice> productChoice;
     
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
    	
/*
 * TODO: FX data binding...
 */
		
      	product.setName(nameField.getText());
      	product.setDescription(descriptionField.getText());
       	if (!priceField.getText().trim().isEmpty())
       		product.setPrice(new BigDecimal(priceField.getText()));
       	else product.setPrice(new BigDecimal(0.0));

       	product.setShortDescription(shortDescriptionField.getText());
      	product.setProductNumber(productNumberField.getText());
       	if (!weightField.getText().trim().isEmpty())
       		product.setWeight(new BigDecimal(weightField.getText()));
       	else product.setWeight(new BigDecimal(0.0));
              	
       	if (!quantityField.getText().trim().isEmpty())
       		product.setQuantity(new Integer(quantityField.getText()));
       	else product.setQuantity(0);
        
       	product.setId(new Long(idField.getText()));
     	product.setManufacturer(manufacturerField.getText());
     
        actiontarget.setText("Success!");

     	try {
     		productService.update(product);
     		// Remove product from list
     		removeProductFromList();
     		// Clear Screen
 	 	   this.clearScreen();

      	}
     	catch (AccessDeniedException e) {
     		actiontarget.setText(e.getMessage() + " - You are not the Owner!");
     	}
     	catch (ValidationException e) {
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

        Stage stage=(Stage) productChoice.getScene().getWindow();
        ViewManager viewManager = new ViewManager();
    	try {
			viewManager.displayView(getClass().getResource("/view/login.fxml"), stage, context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
    
    /*
     * Called after constructor - has access to @FXML fields for initialization
     */
    		
	@FXML
	public void initialize(){
 
		setupChoiceList();

 	    // Listener for pick of Product from drop down list...
  	    productChoice.getSelectionModel().selectedItemProperty().addListener(choiceListener);
 	    
	}
	
	private void setupChoiceList() {
	    List<Product> products = new ArrayList<Product>();
		   
 	    // Get the member specific work items
			String userName = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
			UserCredentials  owner = userCredentialsService.findOne(userName); 		
			for (Long id : owner.getMember().getWorkQueue()) {
				products.add( productService.findOne(id));
			}
 
    		// Prepare list of work items for display...
			ObservableList<Choice> productsFX = FXCollections.observableArrayList();
			productsFX.add(new Choice(null, "Choose Product"));

	    for ( Product product : products) {
	    	productsFX.add(new Choice(product.getId(), product.getName()));
	      }

	    
	   productChoice.setItems(productsFX);
	 	// Init the choice...
		productChoice.getSelectionModel().select(0);
  
	 	   // Default to if NO choice from list then it is a new one...
	 	   product = new Product();

 
	}
	
	// Listener for Product Selection
	// Drop down product list 
	ChangeListener<Choice> choiceListener = new ChangeListener<Choice>() {
	 	   
	    	@Override 
	    	public void changed(ObservableValue<? extends Choice> observableValue, Choice oldChoice, Choice newChoice) {
	        if (newChoice.id == null) {
	        	actiontarget.setText("But you have to make a Selection!");
	        } else {
	        	// Display product up selection...
	        	Long id =productChoice.getValue().getId();	          
	            product = productService.findOne(id);

	           /*
	            * TODO: FX data binding...
	            */
	            // Convert from Domain Object to FX View
	            shortDescriptionField.setText(product.getShortDescription());
	            descriptionField.setText(product.getDescription());
	            productNumberField.setText(product.getProductNumber());
	            nameField.setText(product.getName());
	            priceField.setText(product.getPrice().toString());
	            idField.setText(product.getId().toString());	            
	            quantityField.setText(product.getQuantity().toString());
	            weightField.setText(product.getWeight().toString());
	            manufacturerField.setText(product.getManufacturer());
      
	            actiontarget.setText("");

	        }
	      }


	    };	
	    
	    private void removeProductFromList() {
	    	//Disable[Remove] listener
	    	productChoice.getSelectionModel().selectedItemProperty().removeListener(choiceListener);
    		// re-establish list
	    	this.setupChoiceList();    	
	    	// re -establish listener
	    	productChoice.getSelectionModel().selectedItemProperty().addListener(choiceListener);
	    	
 	    }
	    
	    private void clearScreen() {
	    		product= new Product();

	    		shortDescriptionField.setText(product.getShortDescription());
	            descriptionField.setText(product.getDescription());
	            productNumberField.setText(product.getProductNumber());
	            nameField.setText(product.getName());
	            manufacturerField.setText("");
	            
	            priceField.setText("");
	            idField.setText("");	            
	            quantityField.setText("");
	            weightField.setText("");
  
 
	    }

}
