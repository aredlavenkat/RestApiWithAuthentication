package com.websystique.springmvc.security;

import com.websystique.springmvc.service.CentrifyOAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan(basePackages = "com.websystique.springmvc")
  public class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Autowired
	CentrifyOAuthService centrifyOAuthService;
	
    @Bean
    public ApiAuthenticationFilter apiAuthenticationFilter() {
    	ApiAuthenticationFilter apiAuthenticationFilter = new ApiAuthenticationFilter(centrifyOAuthService);
    	return apiAuthenticationFilter;
    }

    protected void configure(HttpSecurity http) throws Exception {
	http.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf()
		.disable()
		/*.antMatcher("/api/")*/
		.addFilterBefore(apiAuthenticationFilter(),BasicAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers("/user","/").permitAll()
		.anyRequest().authenticated();
    }
}