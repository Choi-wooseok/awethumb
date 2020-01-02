package com.awethumb.repository.dao;

import java.util.Map;

import com.awethumb.repository.vo.Auth;
import com.awethumb.repository.vo.UserVO;

public interface UserDAO {
	UserVO selectUser(String userId);
	int chkUser(Map<String, Object> map);
	int registUser(UserVO user);
	int registAuth(Auth auth);
	int registFinishUser(UserVO user);
	int selectEmailAuth(String userId);
	String chkUserNickname(int userNo);
}
