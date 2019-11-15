package com.awethumb.detailBoard.service;

import java.util.List;

import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.BoardFile;

public interface DetailBoardService {
	List<Board> selectBoardList();
	List<BoardFile> selectImgList(int postNo);
	void insertBoard(Board board);
}
