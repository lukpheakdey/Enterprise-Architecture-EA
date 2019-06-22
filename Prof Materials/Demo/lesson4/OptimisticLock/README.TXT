
Exercises Optimistic Locking...

Test case is with Order domain Object.
Order has Version field.

An order is saved. Version = 0 
The order is then updated resulting in a new object mergedObject. Version = 1
An attempt to update the original order object will now result in a 
   ObjectOptimisticLockingFailureException	