package com.awethumb.mainfeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.MainFeedDAO;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedPage;
import com.awethumb.repository.vo.MainFeed;

@Service
public class MainFeedServiceImpl implements MainFeedService {
	
	@Autowired
	private MainFeedDAO dao;
	
	public List<MainFeed> listMainFeed(FeedPage pageCount) {
		return dao.selectMainFeed(pageCount);
	}
	
	public int commentCount(int postNo) {
		return dao.commentCount(postNo);
	}
	
	public MainFeed detailMainFeed(int postNo) {
		return dao.selectOneMainFeed(postNo);
	}
	
	// 댓글 -------------------------------------------------
	
	public void insertComment(Comment comment) {
		dao.insertComment(comment);
	}
	public void updateComment(Comment comment) {
		dao.updateComment(comment);
	}
	public void delectComment(int cmtNo) {
		dao.deleteComment(cmtNo);
	}
	
	// 검색 -------------------------------------------------
	public List<MainFeed> search(String serchWord) {
		return dao.search(serchWord);
	}

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
//	
//	

}















