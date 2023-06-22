package com.softtek.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.inMemoryAuthentication().withUser("Shiv").password("{noop}shiv").roles("MANAGER");
		auth.inMemoryAuthentication().withUser("Ram").password("{noop}Ram").roles("CUSTOMER");
		
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		//authorize request
		http.authorizeRequests().antMatchers("/").permitAll()//no authentication and no authorization
		.antMatchers("/offers").authenticated()//only authentication
		.antMatchers("/balance").hasAnyRole("CUSTOMER","MANAGER")// Authentication + for customer and manager role user
																	
		.antMatchers("/loanApprove").hasAnyRole("MANAGER").anyRequest().authenticated()//remaining
		
		//specify authentication mode
		//.and().httpBasic()
		.and().formLogin().and().rememberMe().and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
		
		//exception/error handling (for 403 error)
		.and().exceptionHandling().accessDeniedPage("/denied")
		
//		//add SessionMaxConcurrency count
	.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true);
		
		
		
	}
	
	
	
}
