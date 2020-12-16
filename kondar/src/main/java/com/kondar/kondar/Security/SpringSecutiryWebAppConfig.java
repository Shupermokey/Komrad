package com.kondar.kondar.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration 
@EnableWebSecurity
public class SpringSecutiryWebAppConfig extends WebSecurityConfigurerAdapter {    
    
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;
	}
	
	@Override  
    protected void configure(HttpSecurity http) throws Exception {  
    	  http.authorizeRequests().and()
    	  .csrf().disable()
    	  .formLogin()
    	  .loginPage("/").permitAll()
    	  .and()
    	  .logout().invalidateHttpSession(true)
    	  .clearAuthentication(true)
    	  .logoutRequestMatcher(new AntPathRequestMatcher("/loggout"))
    	  .logoutSuccessUrl("/loggout").permitAll();
    	  
    	  
          //.antMatchers("/").permitAll()  
          //.antMatchers("/img/**").permitAll()  
          
    	
    }  
}
