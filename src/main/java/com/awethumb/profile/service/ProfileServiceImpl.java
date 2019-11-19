package com.awethumb.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.ProfileDAO;
import com.awethumb.repository.vo.Category;
import com.awethumb.repository.vo.Subscribe;
import com.awethumb.repository.vo.UserVO;

@Service
public class ProfileServiceImpl implements ProfileService{
	@Autowired
	private ProfileDAO dao;

	@Override
	public List<Category> getCategories() {
		return dao.selectCategories();
	}

	@Override
	public UserVO selectOneUser(String userNickname) {
		return dao.selectOneUser(userNickname);
	}

	@Override
	public void updateUser(UserVO user) {
		dao.updateUser(user);
	}

	@Override
	public int checkSub(Subscribe sub) {
		return dao.selectSubscribe(sub);
	}

	@Override
	public void deleteSub(Subscribe sub) {
		dao.deleteSubscribe(sub);
	}

	@Override
	public void insertSub(Subscribe sub) {
		dao.insertSubscribe(sub);
	}
}