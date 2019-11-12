package com.awethumb.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.UserDAO;
import com.awethumb.repository.vo.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO dao;

	@Override
	public User getUser() {
		return dao.selectUser();
	}
	
}
