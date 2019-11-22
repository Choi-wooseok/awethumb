package com.awethumb.feed.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awethumb.feed.service.FeedService;
import com.awethumb.repository.vo.Comment;

@Controller("com.awethumb.feed.controller.FeedController")
@RequestMapping("/feed")
public class FeedController { // http://localhost:8000/awethumb/feed/feed.do
	@Autowired // 자동주입
	private FeedService service;
	
	@RequestMapping("/feed.do")
	public void feedKong(Model model) {
		model.addAttribute("boardlist", service.selectFeedBoard());
		int aa = 2; // 더미 이미지 띄우는용
		model.addAttribute("aa", aa);
		
		model.addAttribute("cmtno", service.selectCommentNo() ); //댓글번호받기
	}
	
	// 댓글
	@RequestMapping("/boardCommentList.do")
	@ResponseBody
	public List<Comment> selectComment(int postNo){
		List<Comment> comList = new ArrayList<>();
		List<Integer> cmtno = service.selectCmtNo(postNo);
		for(int a : cmtno) {
			Comment cm = service.selectOneComment(a);
			int cmtReg = service.commentTime(a);
			// 날짜계산
			int mi, ho, day, week ,month, year ;
		
			if(cmtReg < 60 ) {
				mi = cmtReg;
				cm.setCmtRegDt(Integer.toString(mi) + "분 전");
			}
			else if(cmtReg > 60 && cmtReg < 3600) {
				ho = cmtReg / 60;
				cm.setCmtRegDt(Integer.toString(ho) + "시간 전");
			}
			else if (cmtReg > 1440) {
				day = cmtReg / 1440;
				cm.setCmtRegDt(Integer.toString(day) + "일 전");
			}
			else if (cmtReg > 10080) {
				week = cmtReg / 10080;
				cm.setCmtRegDt(Integer.toString(week) + "주 전");
			}
			else if (cmtReg > 40320) {
				month = cmtReg / 40320;
				cm.setCmtRegDt(Integer.toString(month) + "달 전");
			}
			else if (cmtReg > 525600 ) {
				year = cmtReg / 525600;
				cm.setCmtRegDt(Integer.toString(year) + "년 전");
			}
			comList.add(cm);
		}
		
		return comList;
	}
	@RequestMapping("/boardCommentInsert.do")
	@ResponseBody
	public List<Comment> commentInsert(@RequestBody Comment comment){
		System.out.println("댓글등록" + comment.getCmtContent());
		return service.insertBoardComment(comment);
	}
	@RequestMapping("/boardCommentDelete.do")
	@ResponseBody
	public List<Comment> commentDelete(Comment comment) {
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













