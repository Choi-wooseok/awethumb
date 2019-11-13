package com.awethumb.repository.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class SecurityUser extends User {
	private static final long serialVersionUID = 1L;
	private UserVO user;
	public SecurityUser(UserVO vo, Collection<? extends GrantedAuthority> list) {
		super(vo.getUserId(), vo.getUserPass(), list);
		this.user = vo;
	}
}
