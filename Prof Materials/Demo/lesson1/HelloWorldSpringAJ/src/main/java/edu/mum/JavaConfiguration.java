package edu.mum;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("edu.mum") // search the edu.mum.component package for @Component classes
public class JavaConfiguration {
	
}
