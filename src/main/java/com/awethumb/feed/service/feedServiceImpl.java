package com.awethumb.feed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.feedDAO;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.feedBoard;
@Service
public class feedServiceImpl implements feedService {
	@Autowired
	private feedDAO dao; 
	
	public List<feedBoard> selectFeedBoard() {
		return dao.selectFeedBoard();
	}
	public List<Comment> selectFeedBoardComment(int postNo){
		return dao.selectFeedBoardComment(postNo);
	}
	public void insertBoardComment(Comment comment){
		dao.insertBoardComment(comment);
	}
	public int boardSize() {
		return dao.boardSize();
	}
}
