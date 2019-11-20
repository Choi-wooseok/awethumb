package com.awethumb.detailBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awethumb.detailBoard.service.DetailBoardService;
import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.BoardFile;

@Controller("com.awethumb.detailBoard.controller.DetailBoardController")
@RequestMapping("/detailProject")
public class DetailBoardController {
	
	@Autowired
	private DetailBoardService service;
	
	@RequestMapping("detailBoardList.do")
	public void DetailBoardList(Model model) {
		List<Board> bList = service.selectBoardList();
		for (int i = 0; i < bList.size(); i++) {
			List<BoardFile> fList = service.selectImgList(bList.get(i).getPostNo());
			bList.get(i).setListFile(fList);
		}
		model.addAttribute("list", bList);
	}
	
	@RequestMapping("write.do")
	public String insertBoard(Board board) {
		service.insertBoard(board);
		return "redirect:detailBoardList.do";
	}

	@RequestMapping("delete.do")
	public String deleteBoard(int postNo) {
		service.deleteBoard(postNo);
		return "redirect:updateListForm.do";
	}
	
	@RequestMapping("updateListForm.do")
	public void updateListForm(Model model) {
		List<Board> bList = service.selectBoardList();
		for (int i = 0; i < bList.size(); i++) {
			List<BoardFile> fList = service.selectImgList(bList.get(i).getPostNo());
			bList.get(i).setListFile(fList);
		}
		model.addAttribute("list", bList);
	}

	@RequestMapping("update.do")
	public String updateBoard(Board board) {
		service.updateBoard(board);
		return "redirect:updateListForm.do";
	}
	
	@RequestMapping("updateList.do")
	public void updateList(int postNo, int x_coord, int y_coord, int width, int hight) {
		service.updateList(postNo, x_coord, y_coord, width, hight);
	}
	
	@RequestMapping("selectOneBoard.do")
	@ResponseBody
	public Board selectOneBoard(int postNo) {
		return service.selectOneBoard(postNo);
	}
}
