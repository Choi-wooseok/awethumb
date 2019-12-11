package com.awethumb.feed.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awethumb.feed.service.FeedService;
import com.awethumb.repository.vo.CategoryList;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedBoard;
import com.awethumb.repository.vo.FeedPage;
import com.awethumb.repository.vo.FollowMeUser;

@Controller("com.awethumb.feed.controller.FeedController")
@RequestMapping("/feed")
public class FeedController { // http://localhost:8000/awethumb/feed/feed.do
	
	@Autowired // 자동주입
	private FeedService service;
	
	@RequestMapping("/feed.do")
	public void feed(Model model, Principal p) { 
		model.addAttribute("postNoList", service.postNoList());
		int imageState = 1; // 더미 이미지 띄우는용
		model.addAttribute("imageState", imageState);
		
		String userId = p.getName(); // 로그인 한 userId
		// 내가 팔로우한 사람을 팔로우한 사람들중에 2개이상 카테고리가 중복된 카테고리인 사람들과 카테고리 뽑기  
		model.addAttribute("categorylist", service.selectUserCategoryList(userId));
		model.addAttribute("meCategory", service.selectLoginUserCategory(userId)); // 내카테고리띄우기
		
		
	} // feed.do 
	
	// 게시글
	@RequestMapping("/feedlist.do")
	@ResponseBody
	public List<FeedBoard> feedKong(FeedPage feedpage) {
		return service.selectFeedBoardPage(feedpage);
	}
	
	@RequestMapping("/feedsidelist.do")
	@ResponseBody
	public List<FollowMeUser> feedSideFollow(FollowMeUser followmeuser) {
		return service.selectFollowMe(followmeuser);
	}
	
	// 댓글
	@RequestMapping("/boardCommentList.do")
	@ResponseBody
	public List<Comment> selectComment(int postNo){ // 글당 댓글 리스트
		return service.selectFeedBoardComment(postNo);
	}
	@RequestMapping("/boardCommentInsert.do")
	@ResponseBody
	public List<Comment> commentInsert(@RequestBody Comment comment){ // 댓글등록
		return service.insertBoardComment(comment);
	}
	@RequestMapping("/boardCommentDelete.do")
	@ResponseBody
	public List<Comment> commentDelete(Comment comment) {// 댓글 삭제
		return  service.deleteBoardComment(comment);
	}
	@RequestMapping("/boardCommentUpdate.do")
	@ResponseBody
	public List<Comment> commentUpdate(Comment comment){ // 댓글 수정
		return service.updateBoardComment(comment);
	}
	//신고
	@RequestMapping("/boardReport.do")
	public void boardReport() {
		System.out.println("신고");
	}
	
}













