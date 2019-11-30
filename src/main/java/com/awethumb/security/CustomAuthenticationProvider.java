package com.awethumb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.awethumb.repository.vo.SecurityUser;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	 	@Autowired
	    private UserDetailsService userDeSer;
	 	@Autowired
	    private BCryptPasswordEncoder passwordEncoder;



	 	
	    @Override
	    public Authentication authenticate(Authentication authentication) throws AuthenticationException, BadCredentialsException  {
	        
	        String username = (String) authentication.getPrincipal();
	        String password = (String) authentication.getCredentials();
	        SecurityUser user = (SecurityUser) userDeSer.loadUserByUsername(username);
	        if (user == null) {
	        	throw new BadCredentialsException("UserNotFound");
	        }
	        if(!passwordEncoder.matches(password, user.getPassword())) {
	            throw new BadCredentialsException("UserPasswordChanged");
	        }
	        
	        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
	    }
	 
	    @Override
	    public boolean supports(Class<?> authentication) {
	        return true;
	    }
	    
	  
		


}
