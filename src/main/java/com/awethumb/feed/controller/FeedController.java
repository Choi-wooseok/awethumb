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
import com.awethumb.repository.vo.CategoryList;
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
//		int imageState = 0; // 더미 이미지 띄우는용 -> 0 이미지X , 1이미지O
//		model.addAttribute("imageState", imageState);
		
		String userId = p.getName(); // 로그인 한 userId
		model.addAttribute("userFollowMeCount", service.selectFollowMeCount(userId));
	} // feed.do 
	
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
	public int commentInsert(@RequestBody Comment comment){ // 댓글등록
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
		}
		return name;
	}
//	// 이미지 확인유무
//	@RequestMapping("/boardFileCheck.do")
//	@ResponseBody
//	public int boardFileCheck(int postNo) {
//		return service.boardFileCheck(postNo);
//	}

	@RequestMapping("/categoryListSideBar.do")
	@ResponseBody
	public List<CategoryList> categoryListSideBar(String userId) {
		return service.selectUserCategoryList(userId);
	}
	
	
	
	
}













