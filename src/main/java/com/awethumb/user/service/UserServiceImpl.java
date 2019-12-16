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
import com.awethumb.stats.service.UpdateDailyLog;
import com.awethumb.util.CommUtil;

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
	
	@UpdateDailyLog
	@Transactional
	public int registUser(UserVO user) {
		int result = 0;
		try {
			String rdmKey = CommUtil.randomKeyByPassword(); 
			if (!user.isOauth()) {
				user.setUserEmailKey(rdmKey);
			} else {
				user.setUserEmailKey("Y");
			}
			user.setUserPass(encoder.encode(user.getUserPass()));
			result += dao.registUser(user);
			Auth auth = new Auth();
			auth.setUserId(user.getUserId());
			auth.setAuthType("ROLE_U");
			result += dao.registAuth(auth);
			if (!user.isOauth()) {
				mailService.mailSendWithUserKey(user.getUserId(), user.getUserName(), rdmKey);
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Transactional
	public int registFinishUser(UserVO user) {
		if (dao.selectEmailAuth(user.getUserId()) != 0) {
			return dao.registFinishUser(user);
		};
		return 0;
	}
	
}
