package com.awethumb.mainfeed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awethumb.mainfeed.service.MainFeedService;
import com.awethumb.repository.vo.FeedPage;
import com.awethumb.repository.vo.MainFeed;

@Controller("com.awethumb.mainfeed.controller.mainfeed")
@RequestMapping("/mainfeed")
public class MainFeedController {
	@Autowired
	private MainFeedService service;
	
	@RequestMapping("/mainfeed.do")
	public void mainFeed() {
	}
	
	@RequestMapping("/mainfeedList.do")  // http://localhost:8000/awethumb/mainfeed/mainfeed.do
	@ResponseBody  // jsp를 호출하는게 아닌 데이터만 호출 : ajax를 호출할때 
	public List<MainFeed> mainFeedList(FeedPage pageCount) {
		return service.listMainFeed(pageCount);
	}
	
	@GetMapping("/detailmainfeed.do")
	@ResponseBody
	public MainFeed mainFeeddetail(int postNo) {
		return service.detailMainFeed(postNo);
//		System.out.println("디테일 들어옴");
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
