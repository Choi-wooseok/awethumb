package com.awethumb.feed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.FeedDAO;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedBoard;
@Service
public class FeedServiceImpl implements FeedService {
	@Autowired
	private FeedDAO dao; 
	
	public List<FeedBoard> selectFeedBoard() {
		return dao.selectFeedBoard();
	}
	public List<Comment> selectFeedBoardComment(int postNo){
		return dao.selectFeedBoardComment(postNo);
	}
	public List<Comment> insertBoardComment(Comment comment){
		dao.insertBoardComment(comment);
		return dao.selectFeedBoardComment(comment.getPostNo());
	}
	public List<Comment> deleteBoardComment(Comment comment){
		dao.deleteBoardComment(comment.getCmtNo());
		System.out.println("postNo : " + comment.getPostNo());
		return dao.selectFeedBoardComment(comment.getPostNo());
	}
	public List<Comment> updateBoardComment(Comment comment) {
		dao.updateBoardComment(comment);
		return dao.selectFeedBoardComment(comment.getPostNo());
	}
	public List<Integer> selectCmtNo(){
		return dao.selectCmtNo();
	}
	
}
