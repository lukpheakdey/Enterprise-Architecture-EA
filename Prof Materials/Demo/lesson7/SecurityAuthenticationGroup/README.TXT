USING SECUIRTY GROUPS

ROOT Application security...
NO Web support...Just using CORE Security.

CUSTOM Authentication Manager

Create group {ADMIN, USER} assign ROLE_ADMIN & Role_USER to GROUPS

Create Sean -- assign to group USER
Create Bill -- assign to group ADMIN
Create Pete -- assign to group USER

Member FindAll has Authorization ONLY for ROLE_ADMIN which is GROUP ADMIN which is Bill
 
code FIRST tries to access WITHOUT logging in -- ANONYMOUS User - Access Denied...

Login ... with a user [Sean,Bill or Pete] ONLY Bill has access to "list" of all Users
As the ADMIN group has ROLE_ADMIN  


NOTE: There is also SAMPLE code at the end of main.java that could be used
      to verify a user can be in multiple groups
      Also, SAMPLE code to dynamically change group...
