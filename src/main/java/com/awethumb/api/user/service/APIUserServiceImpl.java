package com.awethumb.api.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.APIUserDAO;
import com.awethumb.repository.vo.TokenUser;
import com.awethumb.repository.vo.UserFile;
import com.awethumb.repository.vo.UserVO;

@Service
public class APIUserServiceImpl implements APIUserService{

	@Autowired
	private APIUserDAO dao;
	
	@Override
	public UserVO selectUser(int userNo) {
		return dao.selectUser(userNo);
	}

	@Override
	public UserFile selectUserThumb(int userNo) {
		return dao.selectUserThumb(userNo);
	}

	@Override
	public void updateUserThumb(UserFile uf) {
		// 사진이 없다면 insert 있다면 update
		if(dao.selectUserThumb(uf.getUserNo()) == null) {
			dao.insertUserThumb(uf);
			return;
		}
		dao.updateUserThumb(uf);
	}
	
	@Override
	public List<TokenUser> selectTokenUsers(String userNickname) {
		return dao.selectTokenUsers(userNickname);
	}
}
