package com.awethumb.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component("myBatisUserDetailsService")
public class MyBatisUserDetailsService implements UserDetailsService {


	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("userName : " + userName);
//		Member vo = mapper.selectMember(userName);   
//		List<SimpleGrantedAuthority> list = new ArrayList<>();
//		for (Auth auth : vo.getAuthList()) {
//			list.add(new SimpleGrantedAuthority(auth.getAuth()));
//		}
//		return vo == null ? null : new SecurityUser(vo, list);
		return null;
	} 
}
