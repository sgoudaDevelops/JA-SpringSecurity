package com.softtek.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class securityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource ds;
	@Autowired
	private UserDetailsService service;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(encoder);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// authorize request
		http.authorizeRequests().antMatchers("/bank").permitAll()// .anyRequest().authenticated().and().httpBasic() ;//
																	// no authentication no authorisation
				.antMatchers("/user/registration", "/user/showLogin").permitAll().antMatchers("/bank/offers")
				.authenticated() // only authentication
				.antMatchers("/bank/balance").hasAnyAuthority("customer", "manager").antMatchers("/bank/loanApprove")
				.hasAuthority("manager").anyRequest().authenticated().and().formLogin().defaultSuccessUrl("/bank",true)
				.loginPage("/user/showLogin")
				.loginProcessingUrl("/login") // for the post mode request to submit and process the 
				.failureUrl("/user/showLogin?error") // authentication failed url
				.and().rememberMe().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
				.logoutSuccessUrl("/user/showLogin?s")//after logout url
				.and()
				.exceptionHandling()
				.accessDeniedPage("/denied").and().sessionManagement()
				.maximumSessions(1).maxSessionsPreventsLogin(true);
	}
	

}
