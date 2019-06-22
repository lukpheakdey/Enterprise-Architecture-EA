

Demonstrates AOP.
Set up to do either Annotated Advice OR Pointcut expression
See edu.mum.aspect.LogginAspect

Here is the "AOP'ed" Method = OrderServiceImpl
	@Logging
   public void save( Order userCredentials) {  		
    	 orderDao.save(userCredentials);
 	}
 	
 	Also updateAdjunct "demos" local method call = NO PROXY
 