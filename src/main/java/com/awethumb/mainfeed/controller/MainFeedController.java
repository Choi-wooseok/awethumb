package com.awethumb.mainfeed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awethumb.mainfeed.service.MainFeedService;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedPage;
import com.awethumb.repository.vo.Hashtag;
import com.awethumb.repository.vo.MainFeed;
import com.awethumb.stats.aop.insertSearchLog;

@Controller("com.awethumb.mainfeed.controller.mainfeed")
@RequestMapping("/mainfeed")
public class MainFeedController {
	@Autowired
	private MainFeedService service;
	
	@RequestMapping("/mainfeed.do")
	public void mainFeed(String hashtag, Model model) {
		service.search(hashtag);
		model.addAttribute("hashtag", hashtag);
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
	@RequestMapping("/insertComment.do") 
	@ResponseBody
//	@Transactional
	public int commentRegistAjax(@RequestBody Comment comment) {
		System.out.println("인서트 들어옴");
//		System.out.println(comment);
		service.insertComment(comment);
//		hashutil.hashSplit(comment.getCmtContent());
//		System.out.println(comment.getCmtNo());
//		int cmtKey = comment.getCmtNo();
//		service.insertHashtag(comment);\
		return comment.getCmtNo();
	}
	@RequestMapping("/insertHashtag.do")
	@ResponseBody
	public void hashtagRegistAjax(@RequestBody List<Hashtag> hashtag) {
		System.out.println("hashinsert 들어옴");
		System.out.println(hashtag);
		service.insertHashtag(hashtag);
	}
	@RequestMapping("/deleteHashtag.do")
	@ResponseBody
	public void hashtagDelete(@RequestBody Hashtag hashtag) {
		System.out.println("hashdelete 들어옴");
		System.out.println(hashtag);
		service.deleteHashtag(hashtag);
	}
	
	@RequestMapping("/updateComment.do")
	@ResponseBody
	public void commentUpdateAjax(Comment comment) {
		service.updateComment(comment);
//		service.deleteHashtag(comment.getHashtag());
//		service.insertHashtag(comment);
		System.out.println("update 들어옴");
	}
	@RequestMapping("/deleteComment.do")
	@ResponseBody
	public int commentDelete(@RequestBody Comment comment) {
		service.delectComment(comment.getCmtNo());
//		System.out.println("delete 들어옴");
		return comment.getCmtNo();
	}
	@RequestMapping(value="/search.do", method = RequestMethod.POST)
	@ResponseBody
	@insertSearchLog
	public List<MainFeed> search(@RequestBody String searchWord) {
		System.out.println("search 들어옴");
		System.out.println(searchWord);
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
