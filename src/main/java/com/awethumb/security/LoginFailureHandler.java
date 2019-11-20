package com.awethumb.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		System.out.println("1111111111111111111111111111111111111111111");
//		exception.printStackTrace();
		System.out.println("throw exception : " + exception.getMessage());
		response.sendRedirect(request.getContextPath() + "/user/login_main.do?errMsg=해당되는 회원 정보를 찾을 수 없습니다. 다시 로그인 해주세요.");
		
	}

	

}
