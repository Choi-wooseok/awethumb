package com.awethumb.user.service;

import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.awethumb.repository.dao.UserDAO;
import com.awethumb.repository.vo.Auth;
import com.awethumb.repository.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO dao;
	@Autowired
	private UserMailServlce mailService;
	@Autowired
	private PasswordEncoder encoder;
	
	public UserVO getUser(String userId) {
		return dao.selectUser(userId);
	}
	
	public int chkUser(Map<String, Object> map) {
		return dao.chkUser(map);
	}
	
	
	@Transactional
	public void registUser(UserVO user) {
		try {
			String rdmKey = mailService.mailSendWithUserKey(user.getUserId(), user.getUserName());
			user.setUserEmailKey(rdmKey);
			user.setUserPass(encoder.encode(user.getUserPass()));
			dao.registUser(user);
			Auth auth = new Auth();
			auth.setUserId(user.getUserId());
			auth.setAuthType("ROLE_U");
			dao.registAuth(auth);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void registFinishUser(UserVO user) {
		dao.registFinishUser(user);
	}
	
}
