package com.awethumb.feed.service;

import java.util.List;

import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.Comment;

public interface feedService {
	List<Board> selectFeedBoard();
	List<Comment> selectFeedBoardComment(int postNo);
}
