package com.awethumb.feed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awethumb.feed.service.feedService;
import com.awethumb.repository.vo.Comment;

@Controller("com.awethumb.feed.controller.FeedController")
@RequestMapping("/feed")
public class FeedController { // http://localhost:8000/awethumb/feed/feed.do
	@Autowired // 자동주입
	private feedService service;
	
	@RequestMapping("feed.do")
	public void feedKong(Model model) {
		model.addAttribute("boardlist", service.selectFeedBoard());
		int aa = 3; // 더미 이미지 띄우는용
		model.addAttribute("aa", aa);
		
		
	}
	
	// 댓글
	@RequestMapping("boardCommentList.do")
	@ResponseBody
	public List<Comment> selectFeedBoardComment(int postNo){
		System.out.println("게시글번호 : " + postNo);
		return service.selectFeedBoardComment(postNo);
	}
	
	@RequestMapping("boardCommentInsert.do")
	@ResponseBody
	public List<Comment> insertBoardComment(@RequestBody Comment comment){
		System.out.println("댓글" + comment.getCmtContent());
		return service.insertBoardComment(comment);
	}
	
}













