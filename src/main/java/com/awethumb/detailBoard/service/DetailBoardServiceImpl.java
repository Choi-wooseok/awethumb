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
	
	public void deleteBoard(int postNo) {
		dao.deleteBoard(postNo);
	}
	
	public void updateBoard(Board board) {
		dao.updateBoard(board);
	}
	
	public void updateList(int postNo, int x_coord, int y_coord, int width, int hight) {
		Board board = dao.selectOneBoard(postNo);
		board.setxCoord(x_coord);
		board.setyCoord(y_coord);
		board.setWidth(width);
		board.setHight(hight);
		dao.updateList(board);
	}
	
	public Board selectOneBoard(int postNo) {
		return dao.selectOneBoard(postNo);
	}

}
