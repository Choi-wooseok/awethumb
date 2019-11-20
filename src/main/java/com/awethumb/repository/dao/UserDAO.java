package com.awethumb.repository.dao;

import java.util.Map;

import com.awethumb.repository.vo.Auth;
import com.awethumb.repository.vo.UserVO;

public interface UserDAO {
	UserVO selectUser(String userId);
	int chkUser(Map<String, Object> map);
	void registUser(UserVO user);
	void registAuth(Auth auth);
	void registFinishUser(UserVO user);
}
