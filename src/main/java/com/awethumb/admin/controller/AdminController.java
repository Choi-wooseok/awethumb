package com.awethumb.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awethumb.admin.service.AdminService;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.Report;
@Controller("com.awethumb.admin.controller.AdminController")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@RequestMapping("/adminMain.do")
	public void adminMain() {};
	
	@RequestMapping("/reportList.do")
	public void reportList() {};
	
	@RequestMapping("/reportListAjax.do")
	@ResponseBody
	public List<Report> reportListAjax() {
		List<Report> rList = service.getReport();
		/* 스크립트 단에서 편리한 출력을 위해 리스트에 데이터를 가공해서 주입. */
		for(Report r : rList) {
			r.setUserId(service.selectUserId(r.getUserNo()));
			r.setReportReason(service.selectReportReason(r.getBlockCode()));
			if(r.getBlockCode() == 5) {
				r.setReportReason(r.getReportContent());
			}
			String reportTitle = service.selectPostContent(r.getPostNo());
			reportTitle = (reportTitle.length() > 20) ? reportTitle.substring(0, 19) : reportTitle;
			r.setReportTitle(reportTitle);
		}
		return rList;
	}
	
	@RequestMapping("/originPostAjax.do")
	@ResponseBody
	public Report originPostAjax(String reportNo) {
		//유저, 보드, 코멘트를 포함한 리포트를 넘겨버리기.
		Report report = service.selectOneReport(Integer.parseInt(reportNo));
		System.out.println("커멘트넘버받아오는지 찍어봄" + report.getCmtNo());
		if(report.getCmtNo() != 0) {
			Comment comm = service.selectOneComment(report);
			System.out.println("리포트로 커멘트셀렉 찍어봄" + comm);
			report.setComment(comm);
			report.setCommentUser(service.selectOneUserByComment(comm));
		}
		report.setBoard(service.selectOneBoard(report));
		report.setUserVO(service.selectOneUser(report));
		
//		String originContent = service.selectPostContent(report.getPostNo());
//		System.out.println(originContent);
		return report;
	}
	
	
	
}
