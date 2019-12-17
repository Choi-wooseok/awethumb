package com.awethumb.api.subscribe.service;

import java.util.List;

import com.awethumb.repository.vo.Follow;
import com.awethumb.repository.vo.Subscribe;
import com.awethumb.repository.vo.UserVO;

public interface APISubscribeService {

	int selectSubscribe(Subscribe sub);
	void deleteSub(Subscribe sub);
	void insertSub(Subscribe sub);
	List<UserVO> getFollowingList(Follow fol);
	List<UserVO> getFollowerList(Follow fol);
	int selectFollowerCount(int userNo);
}
