package com.awethumb.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.awethumb.admin.service.AdminService;
import com.awethumb.repository.vo.Report;

@Controller("com.awethumb.report.controller.ReportController")
@RequestMapping("/report")
public class ReportController {
	@Autowired
	private AdminService service;
	
	@RequestMapping("/insertReportForm.do")
	public void insertReportForm() {
	};
	@RequestMapping("/insertReport.do")
	public void insertReport(@RequestParam(value="commentNo", defaultValue="0") int commentNo, int blockCode,
							 @RequestParam(value="reportReason", defaultValue="") String reportReason, int postNo, int reportUserNo, int userNo) {
		System.out.println("인설트 리포트 진입시");
		Report report = new Report();
		report.setBlockCode(blockCode);
		report.setPostNo(postNo);
		report.setReportUserNo(reportUserNo);
		report.setUserNo(userNo);
		if(commentNo != 0) {
			report.setCmtNo(commentNo);
		}
		if(reportReason != "" && reportReason != null) {
			report.setReportContent(reportReason);
		}
		System.out.println((report.toString()));
		service.insertReport(report);
		
	}
}
