package edu.mum.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.ExhaustedRetryException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.mum.domain.Product;
import edu.mum.service.ProductService;
import edu.mum.test.batch.ProductItemWriterRestart;

@ContextConfiguration(value = {"classpath:context/applicationContext-test.xml",
								"classpath:context/batch-config-test.xml","classpath:context/user-job-restart.xml"} )

@RunWith(SpringJUnit4ClassRunner.class)
public class BatchJobRestart {

	/*
	 *  Uses "custom" PrdouctItemWriteRestart as STUB in restart tests
 	 *	Added extra product to "activate" second chunk - 
 	 *  so restart commences at failure point -    second chunk 
	 */
	   @Autowired
	    private JobLauncherTestUtils jobLauncherTestUtils;

 	   @Autowired
	    private ProductItemWriterRestart productItemWriter;
	 
 	   @Autowired
	    private ProductService productService;
	 
 	   @Autowired
	    private JobOperator jobOperator;
	 
 	   @Autowired
	    private JobExplorer jobExplorer;
	 
 /*	   @Autowired
	    private Job saveProducts;
	
 	    @Autowired
 	    JobFactory jobFactory;
 	  
*/
 		@Test
 	   	public void testBatchRetryFail() {
 	 
 		    //Since Skip retry is 3 in user-job, this will  fail as there are 4 products...	
 			productItemWriter.setExceptionCounter(3);
 		    	 
 	         JobExecution jobExecution = null;
			try {
				jobExecution = jobLauncherTestUtils.launchJob();
			} catch (Exception e) {
				System.out.println("BATCH JOB EXCEPTION " );
				e.printStackTrace();
			}
 	         List<Product> products = productService.findAll();

 	 		 System.out.println("FAILED job Explorer Status: " + jobExecution.getStatus());
 	 		 System.out.println("FAILED job Product count: " + products.size());

 	        Assert.assertEquals(BatchStatus.FAILED, jobExecution.getStatus());
 	        Assert.assertEquals(5,products.size());

 	        // OK let's restart...FAILED Job
 	
			productItemWriter.setExceptionCounter(0);
			 
 		    JobInstance jobInstance = jobExecution.getJobInstance();
 		    System.out.println("job instance Id: " + jobInstance.getId());

   	    Long restartId = null;
		try {
			restartId = jobOperator.restart(jobInstance.getId());
		} catch (JobInstanceAlreadyCompleteException | NoSuchJobExecutionException | NoSuchJobException
				| JobRestartException | JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		  final JobExecution restartExecution = jobExplorer.getJobExecution(restartId);
 		 System.out.println("job Explorer Status: " + restartExecution.getStatus());

	          products = productService.findAll();

	 		 System.out.println("Successful job Product count: " + products.size());

	        Assert.assertEquals(BatchStatus.COMPLETED, restartExecution.getStatus());
	        Assert.assertEquals(9,products.size());

	
 		}


 }
