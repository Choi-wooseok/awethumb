package com.awethumb.repository.dao;

import java.util.List;
import java.util.Map;

import com.awethumb.repository.vo.Block;
import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.Criteria;
import com.awethumb.repository.vo.Report;
import com.awethumb.repository.vo.UserVO;

public interface AdminDAO {
	List<Report> selectReport();
	String selectUserId(int userNo);
	String selectReportReason(int blockCode);
	String selectPostContent(int postNo);
	Report selectOneReport(int reportNo);
	Comment selectOneComment(Report report);
	UserVO selectOneUser(Report report);
	Board selectOneBoard(Report report);
	UserVO selectOneUserByComment(Comment comment);
	void insertBlock(Map<String, String> rmap);
	void updateReportStatus(Map<String, String> rmap);
	Block selectBlock(int userNo);
	void denyReportStatus(int reportNo);
	void deleteBlock(int userNo);
	void deleteReport(int reportNo);
	Board selectOneBoardUsingNo(int postNo);
	Comment selectOneCommentUsingNo(int commentNo);
	UserVO selectOneUserUsingPostNo(int postNo);
	UserVO selectOneUserUsingCommentNo(int commentNo);
	void insertReport(Report report);
	
	
	
	/* 페이징 관련 */
	@SuppressWarnings("unchecked")
	List<Report> selectReportPaging(Criteria cri);
	int reportCount();
	
	@SuppressWarnings("unchecked")
	List<UserVO> selectUserPaging(Criteria cri);
	int userCount();
}
