package com.awethumb.mainfeed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awethumb.mainfeed.service.MainFeedService;
import com.awethumb.repository.vo.MainFeed;

@Controller("com.awethumb.mainfeed.controller.mainfeed")
@RequestMapping("/mainfeed")
public class MainFeedController {
	@Autowired
	private MainFeedService service;
	
	@RequestMapping("/mainfeed.do")  // http://localhost:8000/awethumb/mainfeed/mainfeed.do
	public void mainFeed() {
	}
	
	@RequestMapping("/mainfeedList.do")  // http://localhost:8000/awethumb/mainfeed/mainfeed.do
	@ResponseBody
	public List<MainFeed> mainFeedList(@RequestParam(value="pageNo", defaultValue="1") int pageNo) {
		return service.listMainFeed();
	}
	
	@GetMapping("/detailmainfeed.do")
	@ResponseBody
	public void mainFeeddetail(int postNo, Model model) {
		model.addAttribute("detailList", service.detailMainFeed(postNo));
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
