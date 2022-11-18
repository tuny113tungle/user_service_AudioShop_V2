package com.example.Nhom2_AudioShop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.Nhom2_AudioShop.filter.CustomAuthenticationFilter;
import com.example.Nhom2_AudioShop.filter.CustomAuthorizationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomAuthenticationFilter  customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
		customAuthenticationFilter.setFilterProcessesUrl("/api/login");
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**").permitAll()
		.and().logout().logoutUrl("/api/logout/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/color/**").hasAnyAuthority("ROLE_MANAGER", "ROLE_STAFF");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/color/**").hasAnyAuthority("ROLE_MANAGER", "ROLE_STAFF");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/colors/**").permitAll();
		
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/category/**").hasAnyAuthority("ROLE_MANAGER", "ROLE_STAFF");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/category/**").hasAnyAuthority("ROLE_MANAGER", "ROLE_STAFF");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/categories/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/category/**").permitAll();
		
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/product/**").hasAnyAuthority("ROLE_MANAGER", "ROLE_STAFF");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/product/**").hasAnyAuthority("ROLE_MANAGER", "ROLE_STAFF");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/products/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/product/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/productDtos/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/productDetailDto/**").permitAll();
		
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/order/**").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_MANAGER", "ROLE_STAFF");
//		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/order/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/order/**").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_MANAGER", "ROLE_STAFF");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/order/**").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_MANAGER", "ROLE_STAFF");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/orders/**").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_MANAGER", "ROLE_STAFF");

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/orderDetail/**").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_MANAGER", "ROLE_STAFF");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/orderDetail/**").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_MANAGER", "ROLE_STAFF");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/orderDetail/**").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_MANAGER", "ROLE_STAFF");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/orderDetails/**").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_MANAGER", "ROLE_STAFF");

		
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/role/addToUser/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/myUser/**").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_MANAGER", "ROLE_STAFF");
		http.authorizeRequests().anyRequest().authenticated();

		http.addFilter(customAuthenticationFilter);
		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
}
