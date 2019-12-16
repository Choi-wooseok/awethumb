package com.awethumb.admin.service;

import java.util.List;
import java.util.Map;

import com.awethumb.repository.vo.Block;
import com.awethumb.repository.vo.BlockReason;
import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.Criteria;
import com.awethumb.repository.vo.Report;
import com.awethumb.repository.vo.UserVO;

public interface AdminService {

	List<Report> getReport();
	String selectUserId(int userNo);
	String selectReportReason(int blockCode);
	String selectPostContent(int postNo);
	Report selectOneReport(int reportNo);
	Comment selectOneComment(Report report);
	UserVO selectOneUser(Report report);
	Board selectOneBoard(Report report);
	UserVO selectOneUserByComment(Comment comment);
	List<Report> insertBlock(Map<String, String> rmap);
	void updateReportStatus(Map<String, String> rmap);
	Block selectBlock(int userNo);
	void denyReportStatus(int reportNo);
	List<Report> deleteBlock(int userNo);
	void deleteReport(int reportNo);
	Board selectOneBoardUsingNo(int postNo);
	Comment selectOneCommentUsingNo(int commentNo);
	UserVO selectOneUserUsingPostNo(int postNo);
	UserVO selectOneUserUsingCommentNo(int commentNo);
	void insertReport(Report report);
	UserVO selectOneUserUsingUserNo(int userNo);
	void updateBlcok(Map<String, Object> rmap);
	void cancelBlockByUserNo(int userNo);
	void insertBlockByAdmin(Map<String, Object> rmap);
	
	List<Report> selectReportPaging(Criteria cri);
	int reportCount();
	List<UserVO> selectUserPaging(Criteria cri);
	int userCount();
	void deleteUser(int userNo);
	
	BlockReason selectReportReasonTwo(int userNo);
}
