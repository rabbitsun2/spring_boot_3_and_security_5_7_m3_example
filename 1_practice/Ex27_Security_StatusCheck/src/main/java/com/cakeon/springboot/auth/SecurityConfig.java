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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Spring Security 권장 방법
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	// 추가
	@Autowired
	public AuthenticationFailureHandler authenticationFailureHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
				
		http.authorizeHttpRequests()
			.requestMatchers("/").permitAll()
			.requestMatchers("/loginForm").permitAll()
			.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
        	.requestMatchers("/guest/**").authenticated()
        	.requestMatchers("/member/**").hasAnyAuthority("USER", "ADMIN")
        	.requestMatchers("/admin/**").hasAuthority("ADMIN")
        	//.anyRequest().authenticated()	// 모든 요청은 인증된 사용자만 가능함
        	.anyRequest().permitAll()			// 로그인하지 않고 모두 권한을 가짐
    	.and()
    		.formLogin()
    			.loginPage("/loginForm")
    			.loginProcessingUrl("/j_spring_security_check")
    			.failureUrl("/loginForm?error")
    			.failureHandler(authenticationFailureHandler)
    			.usernameParameter("j_username")
    			.passwordParameter("j_password")
    			.permitAll()
    	.and()
    		.logout()
	    		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃
	            .logoutSuccessUrl("/loginForm") // 로그아웃에 성공하면 페이지 Redirect
	            .invalidateHttpSession(true) // Session 초기화
	            .deleteCookies("JSESSIONID");
		
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
