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
public class BatchStepNoSkip {

	   @Autowired
	    private JobLauncherTestUtils jobLauncherTestUtils;

 	   @Autowired
	    private ProductItemWriter productItemWriter;
	 	 
 	   @Autowired
	    private ProductService productService;

 		@Test
 	   	public void testBatchNoSkip() {
 	 
 		    //Since retry is 3 in user-job, this will obviously NOT cause a skip...	
 			// edu.mum.test.batch.ProductItemWriter "simulates errors {counter value}" / Exceptions
 			// "0" means no exception
 			productItemWriter.setCounter(0);
 		    	 
 			// Use Spring tool Start at step 2
 	         JobExecution jobExecution = jobLauncherTestUtils.launchStep("step2ETL");

 	         List<Product> products = productService.findAll();
 	         
  	        Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
 	        Assert.assertEquals(4,products.size());
 	         }

 		
  }
