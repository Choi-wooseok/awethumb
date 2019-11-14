/**
 * Data 의 처리 클래스
 * 
 * CRUD -> 
 * CREATE  : insert + 기능
 * READ    : select + 기능,  selectOne + 기능
 * UPDATE  : update + 기능
 * DELETE  : delete + 기능
 */
package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.Comment;

public interface MainFeedDAO {
	List<Board> selectMainFeed();
//	Board selectOneBoard(int no);
//	void updateViewCnt(int no);
//	void insertBoard(Board board);
//	void updateBoard(Board board);
//	int deleteBoard(int no);
	
	/* 댓글 파트 시작 ========================================= */
	List<Comment> selectComment(int postNo);
	int commentCount(int postNo);
//	void insertComment(Comment comment);
//	void deleteComment(int commentNo);
//	void updateComment(Comment comment);
}














