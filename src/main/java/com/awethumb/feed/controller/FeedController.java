package com.awethumb.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.awethumb.feed.service.feedService;

@Controller("com.awethumb.feed.controller.FeedController")
@RequestMapping("/feed")
public class FeedController { // http://localhost:8000/awethumb/feed/feed.do
	@Autowired // 자동주입
	private feedService service;
	
	@RequestMapping("feed.do")
	public void feedKong(Model model) {
		model.addAttribute("list", service.selectFeedBoard());
	}
	
	@RequestMapping()
	public void feddComment() {
		
	}
	
	
}
