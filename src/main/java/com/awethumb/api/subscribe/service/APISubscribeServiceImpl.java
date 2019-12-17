package com.awethumb.api.subscribe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.APISubscribeDAO;
import com.awethumb.repository.vo.Follow;
import com.awethumb.repository.vo.Subscribe;
import com.awethumb.repository.vo.UserVO;

@Service
public class APISubscribeServiceImpl implements APISubscribeService{
	@Autowired
	private APISubscribeDAO dao;

	@Override
	public int selectSubscribe(Subscribe sub) {
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
		// 만약에 검색어가 있다면
		if (fol.getSearchName() != null) {
			return dao.selectSearchFollowingList(fol);
		}
		return dao.selectFollowingList(fol);
	}
	@Override
	public List<UserVO> getFollowerList(Follow fol) {
		// 만약에 검색어가 있다면
		if (fol.getSearchName() != null) {
			return dao.selectSearchFollowerList(fol);
		}
		return dao.selectFollowerList(fol);
	}
	
	@Override
	public int selectFollowerCount(int userNo) {
		return dao.selectFollowerCount(userNo);
	}
}
