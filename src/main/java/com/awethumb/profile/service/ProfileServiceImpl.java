package com.awethumb.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.ProfileDAO;
import com.awethumb.repository.vo.Category;
import com.awethumb.repository.vo.Follow;
import com.awethumb.repository.vo.Subscribe;
import com.awethumb.repository.vo.UserFile;
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

	@Override
	public List<UserVO> getFollowingList(Follow fol) {
		return dao.selectFollowingList(fol);
	}

	@Override
	public List<UserVO> getFollowerList(Follow fol) {
		return dao.selectFollowerList(fol);
	}

	@Override
	public List<UserVO> getSearchFollowerList(Follow fol) {
		return dao.selectSearchFollowerList(fol);
	}

	@Override
	public List<UserVO> getSearchFollowingList(Follow fol) {
		return dao.selectSearchFollowingList(fol);
	}

	@Override
	public void updateUserFile(UserFile uf) {
		if(dao.selectUserFile(uf.getUserNo()) == 0) dao.insertUserFile(uf);
		else dao.updateUserFile(uf);
		
	}
}
