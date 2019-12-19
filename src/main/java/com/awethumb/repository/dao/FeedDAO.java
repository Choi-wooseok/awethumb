package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.BoardFile;
import com.awethumb.repository.vo.CategoryList;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedBoard;
import com.awethumb.repository.vo.FeedPage;
import com.awethumb.repository.vo.FollowMeUser;
import com.awethumb.repository.vo.Like;

public interface FeedDAO {
	List<Integer> postNoList(); // 게시판번호받기
	List<FeedBoard> selectFeedBoardPage(FeedPage pageCount); // 게시판전체
	List<Comment> selectFeedBoardComment(int postNo); // 게시판번호에 따른 댓글
	
	List<Integer> selectCmtNo(int postNo); // 댓글상세번호받기
	int commentTime(int cmtNo); // 댓글 시간
	Comment selectOneComment(int cmtNo); // 댓글번호로 댓글 가져오기 
	void insertBoardComment(Comment comment); // 댓글 입력
	void deleteBoardComment(int cmtNo); // 댓글삭제
	void updateBoardComment(Comment comment); // 댓글수정
	
	List<CategoryList> selectUserCategoryList(String userId); // 유저가 구독한 사람이 구독한 사람들 뽑기
	CategoryList selectLoginUserCategory(String userId); // 로그인한유저의 카테고리
	String selectCategoryTitle(int categoryNo); // 카테고리번호로 카테고리title뽑기
	List<FollowMeUser> selectFollowMe(FollowMeUser followmeuser); // 나를 팔로우하는 사람중에 맞팔이아닌 userNo랑 usernickname
	int selectFollowMeCount(String userId); // 팔로우 전체수
	
	void insertLike(Like like); // 좋아요 클릭
	void deleteLike(Like like); // 좋아요 해제
	int likeCheck(Like like); // 좋아요 체크 
	int likeCount(Like like); // 좋아요 개수

	List<BoardFile> boardFile(int postNo); // 파일경로가져오기
	int boardFileCheck(int postNo); // 파일 체크 
	
	int lastCmtNo(); // 마지막댓글번호 확인
}

