package com.awethumb.api.like.service;

import com.awethumb.repository.vo.Like;

public interface APILikeService {
	void insertLike(Like like);
	void deleteLike(Like like);
	int likeCheck(Like like);
	int likeCount(Like like);
	
}
