package com.awethumb.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.awethumb.mypage.Service.MypageService;

@Controller("com.awethumb.mypage.controller.MypageController")
@RequestMapping("/mypage")
public class MypageController {
	@Autowired
	private MypageService service;
	
	@RequestMapping("/main.do")
	public void mypage(Model model) {
		model.addAttribute("categories", service.getCategories());
	}
}
