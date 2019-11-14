package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.feedBoard;

public interface feedDAO {
	List<feedBoard> selectFeedBoard();

	List<Comment> selectFeedBoardComment(int postNo);
}
