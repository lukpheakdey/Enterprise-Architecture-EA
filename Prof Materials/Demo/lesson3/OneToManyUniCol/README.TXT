Executes oneToMany Unidirectional Column example

COMMENT OUT  @OrderColumn(name = "authorties_index")  in UserCredentials to see NON indexed List 

NOTE: with @OrderColumn LIST is indexed - uses @OrderColumn to put index field in MANY table[Authority]
CHECK DB for Order number

Demonstrate orphanRemoval...
with NON indexed list -  notice that the Authorities are NOT ALL updated
Notice how ALL the Authorities are UPDATED when using INDEX

 