package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.Category;
import com.awethumb.repository.vo.Follow;
import com.awethumb.repository.vo.Subscribe;
import com.awethumb.repository.vo.UserVO;

public interface ProfileDAO {

	public List<Category> selectCategories();

	public UserVO selectOneUser(String userNickname);

	public void updateUser(UserVO user);

	public int selectSubscribe(Subscribe sub);

	public void deleteSubscribe(Subscribe sub);

	public void insertSubscribe(Subscribe sub);

	public List<UserVO> selectFollowingList(Follow fol);

	public List<UserVO> selectFollowerList(Follow fol);

}
