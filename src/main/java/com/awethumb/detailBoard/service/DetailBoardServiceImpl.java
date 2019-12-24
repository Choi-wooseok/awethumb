package com.awethumb.detailBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.DetailBoardDAO;
import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.BoardFile;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.Project;
import com.awethumb.repository.vo.ProjectFile;
import com.awethumb.repository.vo.ProjectSubscribe;
import com.awethumb.repository.vo.UserFile;
import com.awethumb.repository.vo.UserVO;
import com.awethumb.stats.aop.UpdateDailyLog;

@Service
public class DetailBoardServiceImpl implements DetailBoardService {
	
	@Autowired
	private DetailBoardDAO dao;
	
	// 전체리스트 불러오기
	public List<Board> selectBoardList(int projectno) {
		return dao.selectBoardList(projectno);
	};
	
	public List<BoardFile> selectImgList(int postNo) {
		return dao.selectImgList(postNo);
	};
	
	// 글 등록하기
	@UpdateDailyLog
	public void insertBoard(Board board) {
		dao.insertBoard(board);
	}
	
	public void deleteBoard(int postNo) {
		dao.deleteBoard(postNo);
	}
	
	public void updateBoard(Board board) {
		dao.updateBoard(board);
	}
	
	// 수정완료 시 해당 모든 위치,범위값을 저장시킴
	public void updateList(int postNo, int x_coord, int y_coord, int width, int hight) {
		Board board = dao.selectOneBoard(postNo);
		board.setxCoord(x_coord);
		board.setyCoord(y_coord);
		board.setWidth(width);
		board.setHight(hight);
		dao.updateList(board);
	}
	
	// postNo를 통해 게시판 글 VO를 1개 받아옴
	public Board selectOneBoard(int postNo) {
		return dao.selectOneBoard(postNo);
	}
	
	// pk이름을 통해 저장된 파일 정보 VO를 1개 받아옴
	public BoardFile selectOneBoardSys(String sysname) {
		return dao.selectOneBoardSys(sysname);
	}
	
	// 이미지를 DB에 저장
	public void insertImage(BoardFile boardFile) {
		dao.insertImage(boardFile);
	}

	// 가장 최근 postNo 받아옴
	public int postNoSelect() {
		return dao.postNoSelect();
	}
	
	// Project 번호로 Project VO를 받아옴
	public Project selectProjectName(int pjtNo) {
		return dao.selectProjectName(pjtNo);
	}
	
	public void updateProjectName(Project project) {
		dao.updateProjectName(project);
	}
	
	public UserVO selectWriter(int postNo) {
		return dao.selectWriter(postNo);
	}
	 
	public List<Comment> commentList(int postNo) {
		return dao.commentList(postNo);
	}
	
	public void insertComment(Comment comment) {
		dao.insertComment(comment);
	}
	
	public void deleteComment(int cmtNo) {
		dao.deleteComment(cmtNo);
	}
	
	public void updateComment(Comment comment) {
		dao.updateComment(comment);
	}
	
	public String selectUser(int userNo) {
		return dao.selectUser(userNo);
	}
	
	public List<BoardFile> selectImages(int postNo) {
		return dao.selectImages(postNo);
	}
	
	public void viewCount(int postNo) {
		dao.viewCount(postNo);
	}
	
	public ProjectFile selectProjectImg(int projectNo) {
		return dao.selectProjectImg(projectNo);
	}
	
	public Project selectProject(int projectNo) {
		return dao.selectProject(projectNo);
	}
	
	public List<Integer> selectProjectShared(int projectNo) {
		return dao.selectProjectShared(projectNo);
	}
		
	public int selectSavedProject(ProjectSubscribe projectsubscribe) {
		return dao.selectSavedProject(projectsubscribe);
	}
	
	public void insertSavedProject(ProjectSubscribe projectsubscribe) {
		dao.insertSavedProject(projectsubscribe);
	}
	
	public void deleteSavedProject(ProjectSubscribe projectsubscribe) {
		dao.deleteSavedProject(projectsubscribe);
	}
	
	public int selectLastCommentNo() {
		return dao.selectLastCommentNo();
	}
	
	public UserFile selectUserImg(int userNo) {
		return dao.selectUserImg(userNo);
	}
}
