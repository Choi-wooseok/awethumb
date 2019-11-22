package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedBoard;

public interface FeedDAO {
	List<FeedBoard> selectFeedBoard(); // 게시판전체
	List<Integer> selectCmtNo(int postNo); // 댓글번호받기
	int commentTime(int cmtNo); // 댓글 시간
	Comment selectOneComment(int cmtNo); // 댓글번호로 댓글 가져오기 
	

	List<Comment> selectFeedBoardComment(int postNo); // 게시판번호에 따른 댓글
	void insertBoardComment(Comment comment); // 댓글 입력
	void deleteBoardComment(int cmtNo); // 댓글삭제
	void updateBoardComment(Comment comment); // 댓글수정
}
