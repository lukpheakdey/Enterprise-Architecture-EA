
 REST Service version of MemberMVC from Lesson 11...
 
 NOTE: This is a VARIATION on MemberRest for TESTING different @Json.. Annotations
 
 USES DB entiremember
 
 Uses Spring MVC Controller for REST services.
 The client side is a separate project: 
 
 MemberMVC[Lesson 12]  
           OR 
 MemberMVCCloud[Lesson 13]  
 
 NOTE: JSON circular dependencies are necessary to resolve...otherwise - [java.lang.StackOverflowError]
  
 Has commented out @JsonIgnoreProperties & JsonManagedReference/JsonBackReference examples:
             Member,Address, UserCredentials
              [see slides]
 NOTE: Especially Look at Product - Categories ManyToMany with JsonIgnoreProperties .vs. JsonIdentityInfo
       Initially set up to do JsonIndentityInfo
       
ALSO DispatcherServlet has  edu.mum.rest.HibernateAwareObjectMapper configured
It handles LAZY loading...for JSON payload

NOTE can exercise with MemberRestTest or Postman
FROM Postman call /members  to find All Members & their [hydrated] Addresses & UserCredentials
  With Postman can see JSON...
 