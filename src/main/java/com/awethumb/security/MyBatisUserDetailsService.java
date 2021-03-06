package com.awethumb.security;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.awethumb.repository.dao.UserDAO;
import com.awethumb.repository.vo.Auth;
import com.awethumb.repository.vo.SecurityUser;
import com.awethumb.repository.vo.UserVO;


@Component("myBatisUserDetailsService")
public class MyBatisUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserDAO dao;

	@Override
	public UserDetails loadUserByUsername(String userId) {
		UserVO user = dao.selectUser(userId);
		List<SimpleGrantedAuthority> list = null;
		try {
			list = new ArrayList<>();
			for (Auth auth : user.getAuthList()) {
				list.add(new SimpleGrantedAuthority(auth.getAuthType()));
			}
		} catch (UsernameNotFoundException e) {

		} catch (Exception e) {
		}

		return user == null ? null : new SecurityUser(user, list);
	} 
}
