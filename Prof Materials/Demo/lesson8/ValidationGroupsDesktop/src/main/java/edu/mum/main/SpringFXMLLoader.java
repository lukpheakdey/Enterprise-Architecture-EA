package edu.mum.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.fxml.FXMLLoader;

public class SpringFXMLLoader {

    private ApplicationContext context;
    
    public SpringFXMLLoader(ApplicationContext context)
    {
        this.context = context;
    }

     // Spring needs to manage FX controller in order to do DI
    public Object load(URL url) {   //, String resources
        FXMLLoader loader = new FXMLLoader(url);
        loader.setControllerFactory(clazz -> context.getBean(clazz));
//        loader.setLocation(getClass().getResource(url));
 
        //       loader.setResources(ResourceBundle.getBundle(resources));
        try {
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}