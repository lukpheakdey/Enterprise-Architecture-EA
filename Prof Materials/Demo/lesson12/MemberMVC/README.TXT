Presentation layer of Lesson 11 MemberSpringMVC
Rest services are remotely accessed for Service & Persistence Layer  


NOTE: The "appropriate" classes are Annotated with @JsonIdentityInfo
to reflect the REST application [ see slides]

ProductServiceImpl now has 2 different options for accessing  Product through REST services...

@Qualifier("MVC")
@Qualifier("Jersey")
	private ProductRestService productRestService;

They correspond to the relevant REST service server [ see edu.mum.rest.service.impl]

Jersey == used with  JerseyRestSecurity
MVC == ued with MemberRest

NOTICE: the interfaces are SIMPLE findOne, findAll AND save