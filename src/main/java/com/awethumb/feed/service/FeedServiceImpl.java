package com.awethumb.feed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.FeedDAO;
import com.awethumb.repository.vo.CategoryList;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedBoard;
import com.awethumb.repository.vo.FeedPage;
@Service
public class FeedServiceImpl implements FeedService {
	@Autowired
	private FeedDAO dao; 
	
	public List<Integer> postNoList(){
		return dao.postNoList();
	}
	
	public List<FeedBoard> selectFeedBoardPage(FeedPage pageCount) {
		return dao.selectFeedBoardPage(pageCount);
	}
	// 댓글
	public List<Comment> selectFeedBoardComment(int postNo){
		return CommentTime(postNo);
	}
	public List<Comment> insertBoardComment(Comment comment){
		dao.insertBoardComment(comment);
		return CommentTime(comment.getPostNo());
	}
	public List<Comment> deleteBoardComment(Comment comment){
		dao.deleteBoardComment(comment.getCmtNo());
		return CommentTime(comment.getPostNo());
	}
	public List<Comment> updateBoardComment(Comment comment) {
		dao.updateBoardComment(comment);
		return CommentTime(comment.getPostNo());
	}
	
	// 댓글 시간대 모듈 메소드
	public List<Comment> CommentTime(int postNo){
		List<Comment> comList = new ArrayList<>();
		List<Integer> cmtno = dao.selectCmtNo(postNo);
		for(int a : cmtno) {
			Comment cm = dao.selectOneComment(a);
			int cmtReg = dao.commentTime(a);
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
	// side바 추천
	public List<CategoryList> selectUserCategoryList(String userId) {
		return dao.selectUserCategoryList(userId);
	}
	public CategoryList selectLoginUserCategory(String userId) {
		return dao.selectLoginUserCategory(userId);
	}
	
}











