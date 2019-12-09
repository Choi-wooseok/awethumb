package com.awethumb.feed.controller;

import java.security.Principal;
import java.util.ArrayList;
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
		
		List<CategoryList> category = service.selectUserCategoryList(userId);
		for (CategoryList a : category) { // 테스트용 지워야댐 
			System.out.println(
					"유저번호 : " + a.getUserNo() +
					"이름 : " + a.getUserNickName() +
					"카테고리 : " + a.getCategoryList());
		}
		CategoryList loginUserCategory = service.selectLoginUserCategory(userId);
		System.out.println(
				"로그인유저카테고리 : " + loginUserCategory.getCategoryList());
		for(int i = 0; i < category.size(); i++) {
			String aa = category.get(i).getCategoryList();
			String bb = loginUserCategory.getCategoryList();
			String[] s1 = aa.split(":");
			String[] s2 = bb.split(":");
			List<Integer> user1 = new ArrayList<>();
			List<Integer> user2 = new ArrayList<>();
			
			for(String a : s1) {
				int b = Integer.parseInt(a);
				user1.add(b);
			}
			for (String c : s2) {
				int d = Integer.parseInt(c);
				user2.add(d);
			}
			
			int num = 0;
			int[] arr = new int[user2.size()];
			for(int ii = 0; ii < user1.size(); ii++) {
				for(int jj = 0; jj < user2.size(); jj++) {
					if (user1.get(ii) == user2.get(jj)) {
						num += 1;
						arr[jj] = user1.get(ii);
					}
				}
			}
			if(num >= 2) {
				System.out.println(
						"사람 : " + category.get(i).getUserNickName() + "  " + 
								num + "번 중복됨  " +
						"카테고리 : " + category.get(i).getCategoryList());
				System.out.println("비슷한사람 : " + category.get(i).getUserNickName());
			}
			
		}
		
	} // feed.do 
	
	@RequestMapping("/feedlist.do")
	@ResponseBody
	public List<FeedBoard> feedKong(FeedPage pageCount) {
		return service.selectFeedBoardPage(pageCount);
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













