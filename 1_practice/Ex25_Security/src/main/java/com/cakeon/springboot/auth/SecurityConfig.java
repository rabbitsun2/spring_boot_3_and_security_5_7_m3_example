package com.cakeon.springboot.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Spring Security 권장 방법
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.csrf().disable();
		
		http.authorizeHttpRequests()
			.requestMatchers("/").permitAll()
			.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
        	.requestMatchers("/guest/**").authenticated()
        	.requestMatchers("/member/**").hasAnyAuthority("USER", "ADMIN")
        	.requestMatchers("/admin/**").hasAuthority("ADMIN")
        	.anyRequest().authenticated()
        	.and()
        		.formLogin()
        			.permitAll()
        	.and()
        		.logout()
        			.permitAll();
		
		return http.build();
		
	}
	
	@Bean
    public InMemoryUserDetailsManager configureAuthentication() {
        
		List<UserDetails> userDetails = new ArrayList<>();
		List<GrantedAuthority> employeeRoles = new ArrayList<>();
		employeeRoles.add(new SimpleGrantedAuthority("USER"));
		
		List<GrantedAuthority> adminRoles = new ArrayList<>();
		adminRoles.add(new SimpleGrantedAuthority("USER"));
		adminRoles.add(new SimpleGrantedAuthority("ADMIN"));
		
		userDetails.add( new User("user", passwordEncoder().encode("1234"), employeeRoles) );
		userDetails.add( new User("admin", passwordEncoder().encode("1234"), adminRoles) );
		
        return new InMemoryUserDetailsManager(userDetails);
    }
	
}
