package com.packt.webstore.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
 
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class Security extends WebSecurityConfigurerAdapter
{
     @Autowired
    private DataSource dataSource;
 
    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception
    {
         // The authentication provider below uses JDBC to retrieve your credentials
        // The data source bean configuration can be found at the bottom of this file
        // The first example uses the default Spring Security tables, see link below
        // http://docs.spring.io/spring-security/site/docs/current/reference/appendix-schema.html
//      auth
//          .jdbcAuthentication()
//          .dataSource( dataSource )
//          .passwordEncoder( new ShaPasswordEncoder() );
 
 
        // The second example shows how you can override the default queries in order
        // to use your own tables rather than Spring Security's default tables
        // The SQL is relatively simple and should be easy to figure out and map to your needs
      auth
          .jdbcAuthentication()
          .dataSource( dataSource )
          .passwordEncoder( passwordEncoder() )
          .usersByUsernameQuery( "select username,password,enabled from users where username=?" )
          .authoritiesByUsernameQuery( "select u1.username, u2.authority from users u1, authorities u2 where u1.username = u2.username and u1.username =?" );
    }
 
 
     @Override
    protected void configure( HttpSecurity http ) throws Exception
    {
        http
 
            // access-denied-page: this is the page users will be
            // redirected to when they try to access protected areas.
/*            .exceptionHandling()
                .accessDeniedPage( "/error-forbidden" )
                .and()
*/ 
            // The intercept-url configuration is where we specify what roles are allowed access to what areas.
            // We specifically force the connection to https for all the pages, although it could be sufficient
            // just on the login page. The access parameter is where the expressions are used to control which
            // roles can access specific areas. One of the most important things is the order of the intercept-urls,
            // the most catch-all type patterns should at the bottom of the list as the matches are executed
            // in the order they are configured below. So /** (anyRequest()) should always be at the bottom of the list.
            .authorizeRequests()
                .antMatchers( "/login**" ).permitAll()
                .antMatchers( "/employees/add" ).hasRole( "ADMIN" )
                .antMatchers( "/employees" ).hasAnyRole( "ADMIN","USER" )
                .anyRequest().permitAll()
             .and()
            
            // This is where we configure our login form.
            // login-page: the page that contains the login screen
            // login-processing-url: this is the URL to which the login form should be submitted
            // default-target-url: the URL to which the user will be redirected if they login successfully
            // authentication-failure-url: the URL to which the user will be redirected if they fail login
            // username-parameter: the name of the request parameter which contains the username
            // password-parameter: the name of the request parameter which contains the password
            .formLogin()
                .loginPage( "/login" )
                .loginProcessingUrl( "/postlogin" )
                .defaultSuccessUrl( "/welcome" )
                .failureUrl( "/loginfailed" )
                .permitAll()
                 .and()
 
            // This is where the logout page and process is configured. The logout-url is the URL to send
            // the user to in order to logout, the logout-success-url is where they are taken if the logout
            // is successful, and the delete-cookies and invalidate-session make sure that we clean up after logout
            .logout()
                .logoutRequestMatcher( new AntPathRequestMatcher( "/dologout" ) )
                .logoutSuccessUrl( "/logout" )
                .deleteCookies( "JSESSIONID" )
                .invalidateHttpSession( true )
                
                .and()
                // access-denied-page: this is the page users will be
                // redirected to when they try to access protected areas.
                .exceptionHandling()
                    .accessDeniedPage( "/accessDenied" );

     }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}