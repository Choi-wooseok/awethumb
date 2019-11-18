package com.awethumb.admin.service;

import java.util.List;

import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.Report;
import com.awethumb.repository.vo.UserVO;

public interface AdminService {

	public List<Report> getReport();
	public String selectUserId(int userNo);
	public String selectReportReason(int blockCode);
	public String selectPostContent(int postNo);
	public Report selectOneReport(int reportNo);
	public Comment selectOneComment(Report report);
	public UserVO selectOneUser(Report report);
	public Board selectOneBoard(Report report);
	public UserVO selectOneUserByComment(Comment comment);
}
