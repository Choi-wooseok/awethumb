package com.awethumb.detailBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.DetailBoardDAO;
import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.BoardFile;

@Service
public class DetailBoardServiceImpl implements DetailBoardService {
	
	@Autowired
	private DetailBoardDAO dao;
	
	// 전체리스트 불러오기
	public List<Board> selectBoardList() {
		return dao.selectBoardList();
	};
	
	public List<BoardFile> selectImgList(int postNo) {
		return dao.selectImgList(postNo);
	};
	
	public void insertBoard(Board board) {
		dao.insertBoard(board);
	}
}
