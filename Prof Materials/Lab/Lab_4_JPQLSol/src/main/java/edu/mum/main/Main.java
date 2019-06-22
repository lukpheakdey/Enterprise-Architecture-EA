package edu.mum.main;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {

	@Autowired
	TestItemCategory testItemCategory;
	
	@Autowired
	TestUsers testUsers;
	@Autowired
	TestItems testItems;
	
 	@Autowired
	TestFindItemsBySellOrBuy testFindItemsBySellOrBuy;
	
  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");
    applicationContext.getBean(Main.class).mainInternal(applicationContext);
  }
    private void mainInternal(ApplicationContext applicationContext) {

  
    testUsers.setupUsers();
    testItems.setupItems();
    testItemCategory.testItemCategory();
     
    testFindItemsBySellOrBuy.test();

 }
  
  
  }