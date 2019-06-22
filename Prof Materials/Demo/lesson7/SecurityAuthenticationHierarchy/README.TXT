
USING ROLE HIERARCHY

ROOT Application security...
NO Web support...Just using CORE Security.

CUSTOM Authentication Manager

Create Sean -- ROLE_USER
Create Bill -- ROLE_ADMIN
Create Pete -- Role_USER

Member FindAll has Authorization ONLY ROLE_USER which is Sean & Pete
 
The code FIRST tries to access WITHOUT logging in -- ANONYMOUS User - Access Denied...

Login ... with a user [Sean,Bill or Pete] - 
ALL have access even though Bill is ADMIN --- since We are using role Hierarchy...
