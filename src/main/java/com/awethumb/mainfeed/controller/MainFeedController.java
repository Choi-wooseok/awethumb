package com.awethumb.mainfeed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.awethumb.mainfeed.service.MainFeedService;
import com.awethumb.repository.vo.Comment;

@Controller("com.awethumb.mainfeed.controller.mainfeed")
@RequestMapping("/mainfeed")
public class MainFeedController {
	@Autowired
	private MainFeedService service;
	
	@RequestMapping("/mainfeed.do")  // http://localhost:8000/awethumb/mainfeed/mainfeed.do
	public void mainFeedList(@RequestParam(value="pageNo", defaultValue="1") int pageNo, Model model) {
		model.addAttribute("list", service.listMainFeed());
	}
	
//	@RequestMapping("/comment_list.do")
//	public List<Comment> commentListAjax(int postNo) {
//		return service.listComment(postNo);
//	}
	
//	@RequestMapping("/mainfeed.do")
//	public int commentCount(int postNo) {
//		return service.commentCount(postNo);
//	}
	
}
