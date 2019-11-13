package com.awethumb.user.service;

import com.awethumb.repository.vo.UserVO;

public interface UserService {
	UserVO getUser(String userId);
}
