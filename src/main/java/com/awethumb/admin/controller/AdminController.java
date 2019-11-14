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
	public List<Report> reportListAjax(int no) {
		return service.getReport();
	}
	
	
	
}
