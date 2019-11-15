package com.awethumb.user.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.UserDAO;
import com.awethumb.repository.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO dao;

	@Override
	public UserVO getUser(String userId) {
		return dao.selectUser(userId);
	}
	
	@Override
	public int chkUser(Map<String, Object> map) {
		return dao.chkUser(map);
	}
}
