package com.demonstration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
     @Autowired
     private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		/*auth.inMemoryAuthentication()
		.withUser("admin").password("{noop}1234").roles("ADMIN","USER")
		.and()
		.withUser("user").password("{noop}1234").roles("USER");*/
		
		/*auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);*/
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username as principal , password, active as credentials from app_user where username=?")
		.authoritiesByUsernameQuery("select app_user_username as principal , roles_role_name as role from app_user_roles "
				+ "where app_user_username=?")
		.passwordEncoder(bCryptPasswordEncoder)
		.rolePrefix("ROLE_");
		
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http.formLogin();

		http.csrf().disable();
		http.authorizeRequests().antMatchers("/login/**","register/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/tasks/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/tasks/**").permitAll();

		http.authorizeRequests().anyRequest().authenticated()
		
        .and()
        .httpBasic(	);
		
	}
}
