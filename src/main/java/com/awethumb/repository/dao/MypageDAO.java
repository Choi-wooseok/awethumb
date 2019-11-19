package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.Category;
import com.awethumb.repository.vo.UserVO;

public interface MypageDAO {

	public List<Category> selectCategories();

	public UserVO selectOneUser(int userNo);

	public void updateUser(UserVO user);

}
