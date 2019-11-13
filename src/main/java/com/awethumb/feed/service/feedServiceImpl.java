package com.awethumb.feed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.feedDAO;
import com.awethumb.repository.vo.Board;
@Service
public class feedServiceImpl implements feedService {
	@Autowired
	private feedDAO dao; 
	
	public List<Board> selectFeedBoard() {
		return dao.selectFeedBoard();
	}
}
