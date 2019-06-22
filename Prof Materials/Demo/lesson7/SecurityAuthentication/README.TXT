
ROOT Application security...
NO Web support...Just using CORE Security.

CUSTOM Authentication Manager

Create Sean -- ROLE_USER
Create Bill -- ROLE_ADMIN
Create Pete -- Role_USER

Member FindAll has Authorization ONLY ROLE_USER which is Sean & Pete
 
code FIRST tries to access WITHOUT logging in -- ANONYMOUS User - Access Denied...

Login ... with a user [Sean,Bill or Pete] ONLY Sean & Pete has access to "list" of all Users

We COULD Add test in @PreAuthorize for ROLE_ADMIN to get Bill access also...

