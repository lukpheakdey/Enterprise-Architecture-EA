
Table Per Class

Every subclass has full contents of Super Class
MemberRole SuperClass is EMPTY   

Fetch of subclasses is done through union  

Here is the Setup:

@Entity 
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
 public  class MemberRole {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	
	NOTE:
	This strategy does not support the IDENTITY generator strategy: so we need to use TABLE
                                                 the Primary Key:id has to be shared across several DB tables.