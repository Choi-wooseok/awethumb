package com.awethumb.feed.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.awethumb.repository.vo.FollowMeUser;
import com.awethumb.repository.vo.Like;

@Controller("com.awethumb.feed.controller.FeedController")
@RequestMapping("/feed")
public class FeedController { // http://localhost:8000/awethumb/feed/feed.do
	
	@Autowired // 자동주입
	private FeedService service;
	
	@RequestMapping("/feed.do")
	public void feed(Model model, Principal p) { 
		model.addAttribute("postNoList", service.postNoList());
		int imageState = 0; // 더미 이미지 띄우는용 -> 0 이미지X , 1이미지O
		model.addAttribute("imageState", imageState);
		
		String userId = p.getName(); // 로그인 한 userId
		// 내가 팔로우한 사람을 팔로우한 사람들중에 2개이상 카테고리가 중복된 카테고리인 사람들과 카테고리 뽑기  
		model.addAttribute("categorylist", service.selectUserCategoryList(userId));
		model.addAttribute("meCategory", service.selectLoginUserCategory(userId)); // 내카테고리띄우기
		
		model.addAttribute("userFollowMeCount", service.selectFollowMeCount(userId));
	} // feed.do 
	
	@RequestMapping("/feedboardNo.do")
	public List<Integer> feedboard() {
		return service.postNoList();
	}
	
	
	
	// 게시글
	@RequestMapping("/feedlist.do")
	@ResponseBody
	public List<FeedBoard> feedList(FeedPage feedpage) {
		return service.selectFeedBoardPage(feedpage);
	}
	
	// 사이드바 나를팔로우하는사람들
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
		// return 
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
	
	@RequestMapping("/boardLikeInsert.do")
	@ResponseBody
	public void boardLike(@RequestBody Like like){ 
		service.insertLike(like);
	}
	@RequestMapping("/boardLikeDelete.do")
	@ResponseBody
	public void boardLikeDelete(@RequestBody Like like){ 
		System.out.println("delete");
		service.deleteLike(like);
	}
	@RequestMapping("/likeCheck.do")
	@ResponseBody
	public int boardLikeCheck(@RequestBody Like like){
		return service.likeCheck(like); // 0 -> likeoff, 1 -> likeon
	}
	@RequestMapping("/boardLikeCount.do")
	@ResponseBody
	public int boardLikeCount(@RequestBody Like like){
		return service.likeCount(like);
	}
	@RequestMapping("/commentLikeInsert.do")
	@ResponseBody
	public void commentLikeInsert(@RequestBody Like like){ 
		service.insertLike(like);
	}
	@RequestMapping("/commentLikeDelete.do")
	@ResponseBody
	public void commentLikeDelete(@RequestBody Like like){ 
		service.deleteLike(like);
	}

	@RequestMapping("/boardFileRead.do")
	@ResponseBody
	public List<String> boardFileRead(int postNo, HttpServletRequest req){
		
		List<String> name = new ArrayList<>();
		List<String> bbb = service.boardFile(postNo);
		
		for (String a : bbb) {
			String fileReadName= req.getContextPath() + "/image/";// awethumb/image/
			fileReadName = fileReadName + a;
			name.add(fileReadName);
			System.out.println("fileread : " + fileReadName);
			System.out.println("name :" + name);
		}
		return name;
	}
	
	@RequestMapping("/boardFileCheck.do")
	@ResponseBody
	public int boardFileCheck(int postNo) {
		System.out.println("zz : " + service.boardFileCheck(postNo));
		return service.boardFileCheck(postNo);
	}
	
	
}













