package com.awethumb.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("/adminMain.do")
	public void adminMain() {};
}
