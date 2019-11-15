package com.awethumb.admin.service;

import java.util.List;

import com.awethumb.repository.vo.Report;

public interface AdminService {

	public List<Report> getReport();
	public String selectUserId(int userNo);
	public String selectReportReason(int blockCode);
	public String selectPostContent(int postNo);
}
