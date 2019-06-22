package edu.mum.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;


 

@Component
public class ProductStepListener implements StepExecutionListener {
/*
 * Listener options:
    StepExecutionListener
    ItemReadListener
    ItemProcessListener
    ItemWriteListener
    ChunkListener
    SkipListener
 */
		
    private static final Logger logger = LoggerFactory.getLogger(ProductStepListener.class);
 
	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
 		List<Throwable> exceptions = stepExecution.getFailureExceptions();
 		if (exceptions.size() > 0) 
         logger.error("Exception occurred while in Step Name: " + stepExecution.getStepName() + "\n  Exception: " + exceptions.get(0));
 
		return null;
	}
}