package com.awethumb.mainfeed.service;

import java.util.List;

import com.awethumb.repository.vo.MainFeed;

public interface MainFeedService {
	List<MainFeed> listMainFeed();
	
	List<MainFeed> detailMainFeed(int postNo);
	
//	댓글 ------------------------------------
//	List<Comment> listComment(int postNo);
	
	int commentCount(int postNo);
}
