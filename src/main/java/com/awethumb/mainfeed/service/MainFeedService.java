package com.awethumb.mainfeed.service;

import java.util.List;

import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedPage;
import com.awethumb.repository.vo.MainFeed;

public interface MainFeedService {
	List<MainFeed> listMainFeed(FeedPage pageCount);
	
	MainFeed detailMainFeed(int postNo);
	
//	댓글 ------------------------------------
	void insertComment(Comment comment);
//	List<Comment> listComment(int postNo);
	
	int commentCount(int postNo);
}
