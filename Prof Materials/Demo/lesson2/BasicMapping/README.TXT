Use in Lesson 2 for

Basic Mapping Example
   &
  JPA Example
  
  Has hibernate 5 - so demonstrates change to GenerationType.AUTO for MySQL  
  SEQUENCE is considered the best identifier generator choice when using JPA and Hibernate. 
  This is because, unlike IDENTITY generator, you can take advantage of JDBC batch updates.
  UNFORTUNATELY MYSQL doesn't support SEQUENCE ..uses TABLE - which has performance issues...
  [See slides]
  
  Also [in the POM file] shows "variations" of log4j and/or logback
  Using commons-logging with log4j is considered "old school" as commons-logging is runtime discovery
  
  Logback is an "upgrade" to log4j, "picking up where log4j leaves off"