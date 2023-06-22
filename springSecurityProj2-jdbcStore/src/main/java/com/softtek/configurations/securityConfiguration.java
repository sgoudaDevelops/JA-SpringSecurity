package com.softtek.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class securityConfiguration extends WebSecurityConfigurerAdapter {
@Autowired
private DataSource ds;

	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(ds).passwordEncoder(new BCryptPasswordEncoder())
		.usersByUsernameQuery("select uname, pwd, status from users where uname=?") // for authentication 
		.authoritiesByUsernameQuery("select uname, role from user_roles where uname=?"); // for authorisation
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()//.anyRequest().authenticated().and().httpBasic() ;// no authentication no authorisation
		.antMatchers("/offers").authenticated() //only authentication
			.antMatchers("/balance").hasAnyAuthority("customer","manager")
			.antMatchers("/loanApprove").hasAnyAuthority("customer","manager")
			.anyRequest().authenticated().and().exceptionHandling().accessDeniedPage("/denied").and().formLogin().and().rememberMe().and().logout()
		   .logoutRequestMatcher(new AntPathRequestMatcher("/signout")).and().sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// Authorize Requests
//		http.authorizeRequests().antMatchers("/").permitAll() // no Authentication and no
//				// authorization
//				.antMatchers("/offers").authenticated() // only authentication
//				.antMatchers("/balance").hasAnyAuthority("CUSTOMER", "MANAGER") // Authentication + Authorization
//																				// Customer,// Manager role
//
//				.antMatchers("/loanApprove").hasAuthority("MANAGER").anyRequest().authenticated() // remaining all
//
//				// specify authentication mode
//				// .and().httpBasic();
//
//				.and().formLogin().and().rememberMe().and().logout()
//				.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
//
//				// exception/error handling(for 403 error)
//				.and().exceptionHandling().accessDeniedPage("/denied")
//
//				// add sessionMaxConcurrency Count
//				.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true);
//	}


}
