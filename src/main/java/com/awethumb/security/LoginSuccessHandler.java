package com.awethumb.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.awethumb.admin.service.AdminServiceImpl;
import com.awethumb.repository.vo.Block;
import com.awethumb.repository.vo.SecurityUser;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	LoginFailureHandler fail;
	@Autowired
	AdminServiceImpl adminServiceImpl;
	
	
	@SuppressWarnings("serial")
	@Override
	public void onAuthenticationSuccess (
			HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
					throws IOException, ServletException, AuthenticationException {
		Collection<? extends GrantedAuthority> list = authentication.getAuthorities();
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		for (GrantedAuthority auth : list) {
			if ("ROLE_A".equals(auth.getAuthority())) {
				response.sendRedirect(request.getContextPath() + "/admin/adminMain.do");
				return;
			}
		}
		
		// 이메일 인증이 되지 않았다면 세션을 날려버리고 exception 발생
		if (!("Y".equals(user.getUser().getUserEmailKey()))) {
			HttpSession session = request.getSession();
			session.invalidate();
			fail.onAuthenticationFailure(request, response, new AuthenticationException("이메일 인증 후에 로그인 가능합니다.") {});
			return;
		}
		
		Block block = adminServiceImpl.selectBlock(user.getUser().getUserNo());
		System.out.println("block : " + block);
		if (block != null) {
			fail.onAuthenticationFailure(request, response, new AuthenticationException("정지된 회원입니다.") {});
			return;
		}
		

		// /user/logout.do
		response.sendRedirect(request.getContextPath() + "/feed/feed.do");
	}

}
