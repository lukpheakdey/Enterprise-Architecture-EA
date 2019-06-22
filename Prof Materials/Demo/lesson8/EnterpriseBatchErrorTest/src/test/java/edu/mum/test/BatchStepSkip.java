package edu.mum.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.mum.domain.Product;
import edu.mum.service.ProductService;
import edu.mum.test.batch.ProductItemWriter;

@ContextConfiguration(value = {"classpath:context/applicationContext-test.xml",
								"classpath:context/batch-config-test.xml","classpath:context/user-job-skip.xml"} )

@RunWith(SpringJUnit4ClassRunner.class)
public class BatchStepSkip {

	   @Autowired
	    private JobLauncherTestUtils jobLauncherTestUtils;

 	   @Autowired
	    private ProductItemWriter productItemWriter;
	 	 
 	   @Autowired
	    private ProductService productService;
 		
 		@Test
 	   	public void testBatchSkip() {
 	 
 			// chunk is 5, # of rows read in - is 4, 
 		    //Since skip is 3 in user-job,  
 			// this[productItemWriter.setCounter(2)] will skip 1 ** why not 2 ? - See README.TXT on Skip algorithm...
 			// Based on algorithm - since our stub "simulates" skips by counting we'll "lose" the first skip exception
 			// Since counter is NOT reset...
 			// TODO: Fix by using "real" data - special csv file then we will see 2 skips
 			// edu.mum.test.batch.ProductItemWriter "simulates errors {counter value}" / Exceptions
 			// "2" means two exceptions
  			productItemWriter.setCounter(2);
 		    	 
             // Spring tool to start at specific step
 	         JobExecution jobExecution   = jobLauncherTestUtils.launchStep("step2ETL");

 	         List<Product> products = productService.findAll();
 	         
  	        Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
 	        Assert.assertEquals(3,products.size());
 	         }


 }
