package com.awethumb.detailBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		System.out.println("글목록 갯수 : " + bList.size());
		
		for (int i = 0; i < bList.size(); i++) {
			List<BoardFile> fList = service.selectImgList(bList.get(i).getPostNo());
			System.out.println(bList.get(i).getPostNo() + "의 이미지 갯수 :" + fList.size());
			bList.get(i).setListFile(fList);
		}
		model.addAttribute("list", bList);
	}
	
	@RequestMapping("write.do")
	public String insertBoard(Board board) {
		service.insertBoard(board);
		return "redirect:detailBoardList.do";
	}
}
