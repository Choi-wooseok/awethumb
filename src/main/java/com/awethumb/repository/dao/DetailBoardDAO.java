package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.BoardFile;

public interface DetailBoardDAO {
	List<Board> selectBoardList();
	List<BoardFile> selectImgList(int postNo);
	void insertBoard(Board board);
}
