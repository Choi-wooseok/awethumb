package com.awethumb.feed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awethumb.feed.service.FeedService;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedBoard;
import com.awethumb.repository.vo.FeedPage;

@Controller("com.awethumb.feed.controller.FeedController")
@RequestMapping("/feed")
public class FeedController { // http://localhost:8000/awethumb/feed/feed.do
	
	@Autowired // 자동주입
	private FeedService service;
	
	@RequestMapping("/feed.do")
	public void feed(Model model) {
		model.addAttribute("postNoList", service.postNoList());
		int aa = 1; // 더미 이미지 띄우는용
		model.addAttribute("aa", aa);
		System.out.println("feed.do들어옴");
	}
	
	@RequestMapping("/feedlist.do")
	@ResponseBody
	public List<FeedBoard> feedKong(FeedPage pageCount) {
		System.out.println("feedList.do 들어옴");
		return service.selectFeedBoardPage(pageCount);
	}
	// 댓글
	@RequestMapping("/boardCommentList.do")
	@ResponseBody
	public List<Comment> selectComment(int postNo){
		return service.selectFeedBoardComment(postNo);
	}
	@RequestMapping("/boardCommentInsert.do")
	@ResponseBody
	public List<Comment> commentInsert(@RequestBody Comment comment){
		System.out.println("댓글등록 : " + comment.getCmtContent());
		return service.insertBoardComment(comment);
	}
	@RequestMapping("/boardCommentDelete.do")
	@ResponseBody
	public List<Comment> commentDelete(Comment comment) {
		System.out.println("번호 : " + comment.getCmtNo());
		System.out.println("댓삭제");
		return  service.deleteBoardComment(comment);
	}
	@RequestMapping("/boardCommentUpdate.do")
	@ResponseBody
	public List<Comment> commentUpdate(Comment comment){
		System.out.println("댓수정");
		return service.updateBoardComment(comment);
	}
	
	

	
}













