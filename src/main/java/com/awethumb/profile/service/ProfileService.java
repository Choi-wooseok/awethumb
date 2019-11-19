package com.awethumb.profile.service;

import java.util.List;

import com.awethumb.repository.vo.Category;
import com.awethumb.repository.vo.Subscribe;
import com.awethumb.repository.vo.UserVO;

public interface ProfileService {

	public List<Category> getCategories();
	public UserVO selectOneUser(String userNickname);
	public void updateUser(UserVO user);
	public int checkSub(Subscribe sub);
	public void deleteSub(Subscribe sub);
	public void insertSub(Subscribe sub);
}
