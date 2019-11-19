package com.awethumb.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.awethumb.mypage.service.MypageService;
import com.awethumb.repository.vo.UserVO;

@Controller("com.awethumb.mypage.controller.MypageController")
@RequestMapping("/mypage")
public class MypageController {
	@Autowired
	private MypageService service;
	
	@RequestMapping("/main.do")
	public void mypage(Model model) {
		model.addAttribute("categories", service.getCategories());
	}
	
	@PostMapping("/update.do")
	public String update(UserVO user) {
		System.out.println(user.getUserNo());
		System.out.println(user.getCategoryList());
		service.updateUser(user);
		return "redirect:main.do";
	}
}
