package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.Comment;
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
}
