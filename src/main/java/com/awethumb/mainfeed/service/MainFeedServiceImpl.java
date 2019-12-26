package com.awethumb.mainfeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.MainFeedDAO;
import com.awethumb.repository.vo.BoardFile;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedPage;
import com.awethumb.repository.vo.Hashtag;
import com.awethumb.repository.vo.MainFeed;
import com.awethumb.repository.vo.Search;

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
//		dao.insertHashtag(comment);
		dao.insertComment(comment);
	}
	public void updateComment(Comment comment) {
		dao.updateComment(comment);
	}
	public void delectComment(int cmtNo) {
		dao.deleteComment(cmtNo);
	}
	
	// 해시태그 ----------------------------------------------
	public void insertHashtag(List<Hashtag> hashtag) {
		dao.insertHashtag(hashtag);
	}
//	public void updateHashtag(List<Hashtag> hashtag) {
//		dao.updateHashtag(hashtag);
//	}
	public void deleteHashtag(Hashtag hashtag) {
		dao.deleteHashtag(hashtag);
	}
	
	// 검색 -------------------------------------------------
	public List<MainFeed> search(Search serchWord) {
		return dao.search(serchWord);
	}

	public List<BoardFile> imageListDown(int postNo) {
		return dao.imageListDown(postNo);
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















