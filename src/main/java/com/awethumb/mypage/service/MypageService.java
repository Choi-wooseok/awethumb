package com.awethumb.mypage.service;

import java.util.List;

import com.awethumb.repository.vo.Category;
import com.awethumb.repository.vo.UserVO;

public interface MypageService {

	public List<Category> getCategories();
	public UserVO selectOneUser(int userNo);
	public void updateUser(UserVO user);
	
}
