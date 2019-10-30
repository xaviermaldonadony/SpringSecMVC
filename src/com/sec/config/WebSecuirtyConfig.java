package com.sec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.sec")
public class WebSecuirtyConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//		
//		auth.inMemoryAuthentication()
//		.withUser("kun").password("kunkun").roles("ADMIN")
//		.and()
//		.withUser("notKun").password("notKunkun").roles("USER");

	}
	
	public void configure(WebSecurity web) {
		web
		.ignoring()
		.antMatchers("/js/**", "images/**", "/css/**", "/resources/**", "/scripts/**");
	}
	
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
			//.antMatchers("/").permitAll()
			.antMatchers("/contactus").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/subscriber/**").hasRole("USER")
			.antMatchers("/all/**").hasAnyRole("ADMIN", "USER")
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").loginProcessingUrl("/loginAction").defaultSuccessUrl("/", true).permitAll()
			.and()
			.logout().logoutSuccessUrl("/login").permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/403")
			.and()
			.csrf().disable();
	}
}
