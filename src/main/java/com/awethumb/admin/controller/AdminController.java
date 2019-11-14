package com.awethumb.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.awethumb.admin.service.AdminService;
@Controller("com.awethumb.admin.controller.AdminController")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@RequestMapping("/adminMain.do")
	public void adminMain() {};
	
	@RequestMapping("/reportList.do")
	public void reportList() {};
	
	
	
	
}
