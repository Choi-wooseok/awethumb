package com.awethumb.api.like.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.FeedDAO;
import com.awethumb.repository.vo.Like;
@Service
public class APILikeServiceImpl implements APILikeService {
	@Autowired
	private FeedDAO dao; 

	// 좋아요 클릭
	public void insertLike(Like like) {
		dao.insertLike(like);
	}
	// 좋아요 해제
	public void deleteLike(Like like) {
		dao.deleteLike(like);
	}
	// 좋아요 체크
	public int likeCheck(Like like) {
		return dao.likeCheck(like);
	}
	// 좋아요 개수
	public int likeCount(Like like) {
		return dao.likeCount(like);
	}
}
