package com.awethumb.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awethumb.admin.service.AdminService;
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
			String rt = service.selectPostContent(r.getPostNo());
			rt = (rt.length() > 20) ? rt.substring(0, 19) : rt;
			r.setReportTitle(rt);
		}
		return rList;
	}
	
	
	
}
