package com.awethumb.mainfeed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awethumb.mainfeed.service.MainFeedService;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedPage;
import com.awethumb.repository.vo.MainFeed;

@Controller("com.awethumb.mainfeed.controller.mainfeed")
@RequestMapping("/mainfeed")
public class MainFeedController {
	@Autowired
	private MainFeedService service;
	
	@RequestMapping("/mainfeed.do")
	public void mainFeed() {}
	
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
	
	@RequestMapping("/insertComment.do")
	@ResponseBody
	public MainFeed commentRegistAjax(Comment comment) {
		service.insertComment(comment);
//		System.out.println("인서트 들어옴");
		return service.detailMainFeed(comment.getPostNo());
	}
	@RequestMapping("/updateComment.do")
	@ResponseBody
	public MainFeed commentUpdateAjax(Comment comment) {
		service.updateComment(comment);
//		System.out.println("update 들어옴");
		return service.detailMainFeed(comment.getPostNo());
	}
	@RequestMapping("/deleteComment.do")
	@ResponseBody
	public MainFeed commentDelete(Comment comment) {
		service.delectComment(comment.getCmtNo());
//		System.out.println("delete 들어옴");
		return service.detailMainFeed(comment.getPostNo());
	}
	@RequestMapping(value="/search.do", method = RequestMethod.POST)
	@ResponseBody
	public List<MainFeed> search(@RequestBody String searchWord) {
//		System.out.println("search 들어옴");
//		System.out.println(searchWord);
		return service.search(searchWord);
	}
	
	
//	@RequestMapping("/comment_list.do")
//	public List<Comment> commentListAjax(int postNo) {
//		return service.listComment(postNo);
//	}
//	
//	@RequestMapping("/mainfeed.do")
//	public int commentCount(int postNo) {
//		return service.commentCount(postNo);
//	}
	
}
