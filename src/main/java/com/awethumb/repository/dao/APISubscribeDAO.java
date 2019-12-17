package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.Follow;
import com.awethumb.repository.vo.Subscribe;
import com.awethumb.repository.vo.UserVO;

public interface APISubscribeDAO {

	int selectSubscribe(Subscribe sub);

	void deleteSubscribe(Subscribe sub);

	void insertSubscribe(Subscribe sub);

	List<UserVO> selectFollowingList(Follow fol);

	List<UserVO> selectFollowerList(Follow fol);

	List<UserVO> selectSearchFollowingList(Follow fol);

	List<UserVO> selectSearchFollowerList(Follow fol);

	int selectFollowerCount(int userNo);

}
