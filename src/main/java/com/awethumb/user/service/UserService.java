package com.awethumb.user.service;

import java.util.Map;

import com.awethumb.repository.vo.UserVO;

public interface UserService {
	UserVO getUser(String userId);
	int chkUser(Map<String, Object> map);
	int registUser(UserVO user);
	int registFinishUser(UserVO user);
}
