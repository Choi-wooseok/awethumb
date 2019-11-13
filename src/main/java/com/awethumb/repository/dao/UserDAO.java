package com.awethumb.repository.dao;

import com.awethumb.repository.vo.UserVO;

public interface UserDAO {
	UserVO selectUser(String userId);
}
