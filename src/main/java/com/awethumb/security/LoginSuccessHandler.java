package com.awethumb.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.awethumb.repository.vo.SecurityUser;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
					throws IOException, ServletException {
		Collection<? extends GrantedAuthority> list = authentication.getAuthorities();
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		System.out.println(user.getUser());
		for (GrantedAuthority auth : list) {
			System.out.println(auth.getAuthority());
			if ("ROLE_A".equals(auth.getAuthority())) {
				response.sendRedirect(request.getContextPath() + "/admin/adminMain.do");
				return;
			}
		}
		
		if (!("Y".equals(user.getUser().getUserEmailKey()))) {
			response.sendRedirect(request.getContextPath() + "/user/logout.do");
			return;
		}

		// /user/logout.do
		response.sendRedirect(request.getContextPath() + "/feed/feed.do");
	}

}
