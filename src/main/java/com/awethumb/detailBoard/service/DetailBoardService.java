package com.awethumb.detailBoard.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.BoardFile;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.Hashtag;
import com.awethumb.repository.vo.Project;
import com.awethumb.repository.vo.ProjectFile;
import com.awethumb.repository.vo.ProjectSubscribe;
import com.awethumb.repository.vo.UserFile;
import com.awethumb.repository.vo.UserVO;

public interface DetailBoardService {
	List<Board> selectBoardList(int projectNo);
	void insertBoard(Board board);
	void deleteBoard(int postNo);
	void updateBoard(Board board);
	void updateList(int postNo, int x_coord, int y_coord, int width, int hight);
	Board selectOneBoard(int postNo);
	List<BoardFile> selectImgList(int postNo);
	BoardFile selectOneBoardSys(String sysname);
	void insertImage(BoardFile boardFile);
	int postNoSelect();
	Project selectProjectName(int pjtNo);
	void updateProjectName(Project project);
	UserVO selectWriter(int postNo);
	ProjectFile selectProjectImg(int projectNo);
	Project selectProject(int projectNo);
	List<Integer> selectProjectShared(int projectNo);
	List<Comment> commentList(int postNo);
	void insertComment(Comment comment);
	void deleteComment(int cmtNo);
	void updateComment(Comment comment);
	String selectUser(int userNo);
	List<BoardFile> selectImages(int postNo);
	void viewCount(int postNo);
	int selectSavedProject(ProjectSubscribe pSub);
	void insertSavedProject(ProjectSubscribe pSub);
	void deleteSavedProject(ProjectSubscribe pSub);
	int selectLastCommentNo();
	UserFile selectUserImg(int userNo);
	void insertHashTag(Hashtag hashtag);
}
