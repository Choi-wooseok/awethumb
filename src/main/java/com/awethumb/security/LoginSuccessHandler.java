package com.awethumb.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.awethumb.repository.vo.SecurityUser;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess (
			HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
					throws IOException, ServletException, AuthenticationException {
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
			System.out.println("dddddddddd");
			throw new DisabledException("이메일 인증 후에 로그인 가능합니다."); 
		}

		// /user/logout.do
		response.sendRedirect(request.getContextPath() + "/feed/feed.do");
	}

}
