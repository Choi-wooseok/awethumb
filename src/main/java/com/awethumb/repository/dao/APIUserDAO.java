package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.TokenUser;
import com.awethumb.repository.vo.UserFile;
import com.awethumb.repository.vo.UserVO;

public interface APIUserDAO {
	UserVO selectUser(int userNo);
	UserFile selectUserThumb(int userNo);
	void insertUserThumb(UserFile uf);
	void updateUserThumb(UserFile uf);
	public List<TokenUser> selectTokenUsers(String userNickname);
}
