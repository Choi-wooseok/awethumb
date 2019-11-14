package com.awethumb.mainfeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.MainFeedDAO;
import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.Comment;

@Service
public class MainFeedServiceImpl implements MainFeedService {
	
	@Autowired
	private MainFeedDAO dao;
	
	public List<Board> listMainFeed() {
		return dao.selectMainFeed();
	}
	
	public List<Comment> listComment(int postNo) {
		return dao.selectComment(postNo);
	}
	
	public int commentCount(int postNo) {
		return dao.commentCount(postNo);
	}
	
//	public Board detailBoard(int no) {
//		dao.updateViewCnt(no);
//		return dao.selectOneBoard(no);
//	}
//
//	public Board updateFormBoard(int no) {
//		return dao.selectOneBoard(no);
//	}
//	
//	public void deleteBoard(int no) {
//		dao.deleteBoard(no); 
//	}
//
//	public void insertBoard(Board board) {
//		dao.insertBoard(board);
//	}
//	
//	public void updateBoard(Board board) {
//		dao.updateBoard(board);
//	}
//	
//	public List<Comment> commentDelete(Comment comment) {
//		dao.deleteComment(comment.getCommentNo());
//		return dao.selectComment(comment.getNo());
//	}
//	
//	public List<Comment> commentRegist(Comment comment) {
//		dao.insertComment(comment);
//		return dao.selectComment(comment.getNo());
//	}
//	
//	public List<Comment> commentUpdate(Comment comment) {
//		dao.updateComment(comment);
//		return dao.selectComment(comment.getNo());
//	}

}















