
Exercises Optimistic Locking...

Test case is with Order domain Object.
Order has Version field.

An order is saved. Version = 0 
The order is then updated resulting in a new object mergedObject. Version = 1
An attempt to update the original order object will now result in a 
   ObjectOptimisticLockingFailureException	
   
 
   Product HAS TIMESTAMP which is DATABASE Generated  -- not based on individual App [JVM]...
    Modify the MySQL Version column [columnDefinition in Product.java] :
   timestamp(6) default CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6);
   HAS subsecond {milliseconds} resolution precision = 6  -- "avoid" possible "simultaneous conflict"