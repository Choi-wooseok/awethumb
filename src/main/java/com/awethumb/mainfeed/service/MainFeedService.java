package com.awethumb.mainfeed.service;

import java.util.List;

import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.Comment;

public interface MainFeedService {
	List<Board> listMainFeed();
	
	List<Comment> listComment(int postNo);
	int commentCount(int postNo);
}
