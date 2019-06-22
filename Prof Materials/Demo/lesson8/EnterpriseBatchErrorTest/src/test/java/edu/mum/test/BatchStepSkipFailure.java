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
public class BatchStepSkipFailure {

	   @Autowired
	    private JobLauncherTestUtils jobLauncherTestUtils;

 	   @Autowired
	    private ProductItemWriter productItemWriter;
	 	 
 	   @Autowired
	    private ProductService productService;
 		
 		@Test
 	   	public void testBatchSkip() {
 	 
 			// chunk is 5 - # of rows read in is 4, 
 		    //Since skip is 3 in user-job,  
 			// this[counter=5] will skip all
 			//AND fail due to exceeding the limit = 3
 			// edu.mum.test.batch.ProductItemWriter "simulates errors {counter value}" / Exceptions
 			// "5" means 5 exceptions
			productItemWriter.setCounter(5);
 		    	 
 			// Use Spring tool to launch specific step
 	         JobExecution jobExecution   = jobLauncherTestUtils.launchStep("step2ETL");

 	         List<Product> products = productService.findAll();
 	         
   	        Assert.assertEquals(BatchStatus.FAILED, jobExecution.getStatus());
 	      
  	        Assert.assertEquals(0,products.size());
 	         }


 }
