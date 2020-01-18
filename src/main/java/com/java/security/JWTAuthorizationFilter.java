package com.java.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	private JwtUtil jwtUtil;
	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil,UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String token = request.getHeader("Authorization");
		
		if (token != null && token.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token.substring(7));
			if (authenticationToken != null) {
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		chain.doFilter(request, response);		
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (jwtUtil.tokenIsValid(token)) {
			String username = jwtUtil.getUserName(token);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		}
		return null;
	}
	
}
