package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.Category;
import com.awethumb.repository.vo.UserVO;

public interface ProfileDAO {

	public List<Category> selectCategories();

	public UserVO selectOneUser(String userNickname);

	public void updateUser(UserVO user);

}
