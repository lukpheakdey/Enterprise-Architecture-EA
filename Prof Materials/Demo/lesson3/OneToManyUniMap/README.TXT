Executes oneToMany Unidirectional Column example with MAP

Uses @KeyColumn - creates new table column to hold key ... 
key  lowercased version of the authority field  "reduced" to actual role [e.g. for ROLE_ADMIN the key = admin]
See UserCredentials builder for Map PUT [.put("admin",authority) sets key]

ALSO see query by KEY()...can also query by VALUE()
Also failure based on non existent key