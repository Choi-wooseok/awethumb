package com.awethumb.feed.service;

import java.util.List;

import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.feedBoard;

public interface feedService {
	List<feedBoard> selectFeedBoard();
	List<Comment> selectFeedBoardComment(int postNo);
	void insertBoardComment(Comment comment);
	int boardSize();
}
