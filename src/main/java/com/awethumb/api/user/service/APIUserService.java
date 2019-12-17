package com.awethumb.api.user.service;

import java.util.List;

import com.awethumb.repository.vo.TokenUser;
import com.awethumb.repository.vo.UserFile;
import com.awethumb.repository.vo.UserVO;

public interface APIUserService {

	UserVO selectUser(int userNo);
	UserFile selectUserThumb(int userNo);
	public void updateUserThumb(UserFile uf);
	public List<TokenUser> selectTokenUsers(String userNickname);
}
