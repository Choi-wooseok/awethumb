package com.awethumb.profile.service;

import java.util.List;

import com.awethumb.repository.vo.Category;
import com.awethumb.repository.vo.UserVO;

public interface ProfileService {

	public List<Category> getCategories();
	public UserVO selectOneUser(String userNickname);
	public void updateUser(UserVO user);
}
