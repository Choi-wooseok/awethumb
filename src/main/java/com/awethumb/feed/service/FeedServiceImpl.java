package com.awethumb.feed.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.FeedDAO;
import com.awethumb.repository.vo.BoardFile;
import com.awethumb.repository.vo.CategoryList;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedBoard;
import com.awethumb.repository.vo.FeedPage;
import com.awethumb.repository.vo.FollowMeUser;
@Service
public class FeedServiceImpl implements FeedService {
	@Autowired
	private FeedDAO dao; 
	
	public List<FeedBoard> selectFeedBoardPage(FeedPage pageCount) {
		return dao.selectFeedBoardPage(pageCount);
	}
	// 댓글
	public List<Comment> selectFeedBoardComment(int postNo){
		return CommentTime(postNo);
	}
	public int insertBoardComment(Comment comment){
		dao.insertBoardComment(comment);
		int cmtNo = dao.lastCmtNo(); // 알람에필요한 방금단댓글번호 
		return cmtNo; 
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
			else if(cmtReg > 60 && cmtReg < 1440) {
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
		return SelectAllCategoryList(userId);
	}
	// 팔로워 추천 
	public List<CategoryList> SelectAllCategoryList(String userId){
		List<CategoryList> category = dao.selectUserCategoryList(userId);
		CategoryList loginUserCategory = dao.selectLoginUserCategory(userId);
		List<CategoryList> cateList = new ArrayList<>(); // 중복된카테고리List
		Random r = new Random();
		for(int i = 0; i < category.size(); i++) {
			String aa = category.get(i).getCategoryList(); // 카테고리 리슷
			String bb = loginUserCategory.getCategoryList(); // 내꺼카테고리
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
			int count = 0; // 중복횟수
			List<String> cc = new ArrayList<>(); // 중복된 카테고리 List
			for(int ii = 0; ii < user1.size(); ii++) {
				for(int jj = 0; jj < user2.size(); jj++) { 
					if (user1.get(ii) == user2.get(jj)) {
						count += 1;
						cc.add(dao.selectCategoryTitle(user1.get(ii)));
					}
				}
			}
			String categoryTitle = "";
			if(count >= 2) { // 중복되는 카테고리가 2개이상
				int random = r.nextInt(cc.size()); // 중복된 카테고리숫자만큼 랜덤돌려서 중복 분야 뽑기 
				categoryTitle = cc.get(random);
				category.get(i).setCategoryTitle(categoryTitle);
				cateList.add(category.get(i));
			}
			
		} // for
		return cateList;
	}
	// 내 카테고리 목록 title
	public String meCategoryList(String userId){
		CategoryList loginUserCategory = dao.selectLoginUserCategory(userId);
		String categoryList = loginUserCategory.getCategoryList();
		String[] sArr = categoryList.split(":");
		List<String> cateListMe = new ArrayList<>();
		for(String a : sArr) {
			int b = Integer.parseInt(a);
			cateListMe.add(dao.selectCategoryTitle(b));
		}
		String title = "";
		for (int i = 0; i < cateListMe.size(); i++) {
			title += (cateListMe.get(i) + " ");
		}
				
		return title;
	}
	
	// 맞팔안한 나를 팔로우한사람들 정보 가져오기
	public List<FollowMeUser> selectFollowMe(FollowMeUser followmeuser) {
		return dao.selectFollowMe(followmeuser);
	}
	
	// 맞팔안한 사람들 전체수 
	public int selectFollowMeCount(String userId) {
		return dao.selectFollowMeCount(userId);
	}
	
	// 파일경로가져오기
	public List<String> boardFile(int postNo){
		List<BoardFile> bf = dao.boardFile(postNo);
		List<String> msg = new ArrayList<>();;
		for(BoardFile b : bf) {
			msg.add(b.getBoardFilePath() + b.getBoardFileSysName());
		}
		return msg; 
	}
	// 파일체크
	public int boardFileCheck(int postNo) {
		return dao.boardFileCheck(postNo);
	}
	
	// 이미지체크
	public int boardImgState(int postNo) {
		return dao.boardImgState(postNo);
	}
	
}











