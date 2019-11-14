package com.awethumb.detailBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("com.awethumb.detailBoard.controller.DetailBoardController")
public class DetailBoardController {
	
	@RequestMapping("/detailBoardList.do")
	public String DetailBoardList() {
		return "detailProject/DetailBoardList";
	}
}
