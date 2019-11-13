package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.Board;

public interface feedDAO {
	List<Board> selectFeedBoard();
}
