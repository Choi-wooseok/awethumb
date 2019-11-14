package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.Comment;

public interface feedDAO {
	List<Board> selectFeedBoard();

	List<Comment> selectFeedBoardComment(int postNo);
}
