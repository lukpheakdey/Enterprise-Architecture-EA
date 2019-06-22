package edu.mum.main;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewManager {

 
 


	public void displayView(URL fxml, Stage stage, 	ApplicationContext context) throws Exception  {
	    /*
	     * Spring  needs to create the controller so we can do Dependency Injection....
	     */
 		SpringFXMLLoader loader = new SpringFXMLLoader(context);
		Parent root = (Parent) loader.load(fxml);
 	   
	   stage.setTitle("FXML Welcome");
	   stage.setScene(new Scene(root, 700, 700));
	   
	   stage.show();

	}
}
