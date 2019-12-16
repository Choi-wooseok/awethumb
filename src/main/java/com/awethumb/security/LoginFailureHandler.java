package com.awethumb.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if("비밀번호가 맞지 않습니다.".equals(exception.getMessage())) {
			response.sendRedirect(request.getContextPath() + "/user/login_fail.do?errCode=1");
			return;
		} else if("이메일 인증 후에 로그인 가능합니다.".equals(exception.getMessage())) {
			response.sendRedirect(request.getContextPath() + "/user/login_fail.do?errCode=2");
			return;
		} else if("정지된 회원입니다.".equals(exception.getMessage())) {
			response.sendRedirect(request.getContextPath() + "/user/login_fail.do?errCode=7&blockCode=block");
			return;
		} 
		response.sendRedirect(request.getContextPath() + "/user/login_fail.do?errCode=3");
	}

}
