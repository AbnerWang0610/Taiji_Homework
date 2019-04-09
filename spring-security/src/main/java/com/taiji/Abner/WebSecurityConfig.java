package com.taiji.Abner;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity(debug = false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("1").password("1").roles("USER").build());
		manager.createUser(User.withUsername("2").password("2").roles("ADMIN").build());
		manager.createUser(User.withUsername("3").password("3").roles("DBA").build());
		manager.createUser(User.withUsername("4").password("4").roles("DBA","ADMIN").build());
		return manager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.antMatchers("/resources/**", "/signup", "/about").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN") 
			.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login").successForwardUrl("/home")
			.permitAll();     
		
	}
	
	
	

}
