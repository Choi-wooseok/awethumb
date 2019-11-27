package com.awethumb.repository.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.BoardFile;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.Project;
import com.awethumb.repository.vo.UserVO;

public interface DetailBoardDAO {
	List<Board> selectBoardList();
	void insertBoard(Board board);
	void deleteBoard(int postNo);
	void updateBoard(Board board);
	Board selectOneBoard(int postNo);
	void updateList(Board board);
	
	List<BoardFile> selectImgList(int postNo);
	BoardFile selectOneBoardSys(String sysname);
	void insertImage(BoardFile boardFile);
	int postNoSelect();
	
	Project selectProjectName(int pjtNo);
	void updateProjectName(Project project);
	UserVO selectWriter(int postNo);
	List<Comment> commentList(int postNo);
	void insertComment(Comment comment);
}
