package com.awethumb.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.MypageDAO;
import com.awethumb.repository.vo.Category;
import com.awethumb.repository.vo.UserVO;

@Service
public class MypageServiceImpl implements MypageService{
	@Autowired
	private MypageDAO dao;

	@Override
	public List<Category> getCategories() {
		return dao.selectCategories();
	}

	@Override
	public UserVO selectOneUser(int userNo) {
		return dao.selectOneUser(userNo);
	}

	@Override
	public void updateUser(UserVO user) {
		dao.updateUser(user);
	}
}
