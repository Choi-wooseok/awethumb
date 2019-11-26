package com.awethumb.detailBoard.service;

import java.util.List;

import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.BoardFile;
import com.awethumb.repository.vo.Project;

public interface DetailBoardService {
	List<Board> selectBoardList();
	void insertBoard(Board board);
	void deleteBoard(int postNo);
	void updateBoard(Board board);
	void updateList(int postNo, int x_coord, int y_coord, int width, int hight);
	Board selectOneBoard(int postNo);
	
	List<BoardFile> selectImgList(int postNo);
	
	// image
	BoardFile selectOneBoardSys(String sysname);
	void insertImage(BoardFile boardFile);
	int postNoSelect();
	
	// project
	Project selectProjectName(int pjtNo);
	void updateProjectName(Project project);
	String selectWriter(int postNo);
}
