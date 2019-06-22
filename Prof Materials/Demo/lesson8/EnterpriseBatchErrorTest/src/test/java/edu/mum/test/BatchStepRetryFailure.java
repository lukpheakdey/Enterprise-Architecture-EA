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
								"classpath:context/batch-config-test.xml","classpath:context/user-job-retry.xml"} )

@RunWith(SpringJUnit4ClassRunner.class)
public class BatchStepRetryFailure {

	   @Autowired
	    private JobLauncherTestUtils jobLauncherTestUtils;

 	   @Autowired
	    private ProductItemWriter productItemWriter;
	 
 	   @Autowired
	    private ProductService productService;
	 

 		@Test
 	   	public void testBatchRetryFail() {
 	 
 		    //Since retry is 3 in user-job, this will be fail...	
 			// edu.mum.test.batch.ProductItemWriter "simulates errors {counter value}" / Exceptions
 			// "3" means 3 exceptions
			productItemWriter.setCounter(3);
 		    
 			JobExecution jobExecution= null;
 			try { 
 	 			// Use Spring tool Start at step 2
 				jobExecution = jobLauncherTestUtils.launchStep("step2ETL");
 			} catch (Exception e) {
// 				System.out.println("Exception " + e.getMessage());
 				//e.printStackTrace();
 			}
 			
 			List<Product> products = productService.findAll();

 	        Assert.assertEquals(BatchStatus.FAILED, jobExecution.getStatus());
 	        Assert.assertEquals(products.size(), 0);

 	        
 		}


 }
