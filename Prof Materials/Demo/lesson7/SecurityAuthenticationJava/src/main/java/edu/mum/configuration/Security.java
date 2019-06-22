package edu.mum.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 
@Configuration
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class Security extends SecurityConfigurerAdapter
{
     @Autowired
    private DataSource dataSource;
 
     AuthenticationConfiguration authenticationConfiguration;
     
     // "imported" by @EnableGlobalAuthentication allows access to 
     // AuthenticationManager [authenticationConfiguration.getAuthenticationManager()]
    @Autowired
     public void setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration) {
          this.authenticationConfiguration = authenticationConfiguration;
     }
     
     @Autowired
     public void configureGlobal( AuthenticationManagerBuilder authentication ) throws Exception
    {
    	 authentication
          .jdbcAuthentication()
          .dataSource( dataSource )
          .passwordEncoder( passwordEncoder() )
          .usersByUsernameQuery( "select username,password,enabled from credentials where username=?" )
          .authoritiesByUsernameQuery( "select u1.username, u2.authority from credentials u1, authority u2 where u1.username = u2.username and u1.username =?" );
    }
 

    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
   @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}