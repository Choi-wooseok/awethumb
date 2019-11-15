package com.awethumb.repository.dao;

import java.util.Map;

import com.awethumb.repository.vo.UserVO;

public interface UserDAO {
	UserVO selectUser(String userId);
	int chkUser(Map<String, Object> map);
}
