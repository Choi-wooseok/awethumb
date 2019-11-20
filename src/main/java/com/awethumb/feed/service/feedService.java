package com.awethumb.feed.service;

import java.util.List;

import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.feedBoard;

public interface feedService {
	List<feedBoard> selectFeedBoard();
	List<Comment> selectFeedBoardComment(int postNo);
	List<Comment> insertBoardComment(Comment comment);
	List<Comment> deleteBoardComment(Comment comment);
	List<Comment> updateBoardComment(Comment comment);
}
