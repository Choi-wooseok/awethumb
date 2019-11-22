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
	
	
	// 댓글
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
	public List<Integer> selectCmtNo(int postNo){
		return dao.selectCmtNo(postNo);
	}
	public int commentTime(int cmtNo) {
		return dao.commentTime(cmtNo);
	}
	public Comment selectOneComment(int cmtNo) {
		return dao.selectOneComment(cmtNo);
	}
	public List<Integer> selectCommentNo(){
		return dao.selectCommentNo();
	}
}











