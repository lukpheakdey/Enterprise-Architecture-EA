NOTE: the "regular" version is "disabled" if you want to
run it from Main.java you can "manually" run it by 
uncommenting productBatch.startjob()...

OTHERWISE this is a UNIT/Integration test to be run from src/test/java

 Test of Spring batch error handling
 
 Use "custom" PrdouctItemWriter as STUB in tests
 
Retry Tests
    uses jobLauncherTestUtils.launchStep instead of running entire job
    Uses "custom" PrdouctItemWriter as STUB in tests
    modified user-job to reflect retry-limit & exception...
Skip Tests
    uses jobLauncherTestUtils.launchStep instead of running entire job
    Uses "custom" PrdouctItemWriter as STUB in tests
    modified user-job to reflect skip-limit & exception...
Restart test 
     Uses "custom" PrdouctItemWriteRestart as STUB in restart tests
 	Added extra product to "activate" second chunk - so restart commences at failure point -    second chunk 
 
 
 NOTE TODOs: Could "cleanup" tests
        ...use H2 in memory DB
        ...leave DB is "neutral" state
 
 
 SPRING BATCH SKIP ALGORITHM
 The ItemWriter write method receives a list of items. Without going through them one at a time, 
 there is no way to determine which item in the list threw the exception in the writer. 
 All the framework knows is that something in that list caused something to break.
The way this is done is the transaction is rolled back, the commit interval is changed to 1, 
and each item is then reprocessed and the write is attempted again. 
This allows only the item with the error to be skipped instead of needing to skip the entire chunk. 


 