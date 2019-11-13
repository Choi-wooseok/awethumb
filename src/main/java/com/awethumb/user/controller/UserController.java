package com.awethumb.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.awethumb.repository.vo.UserVO;
import com.awethumb.user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@RequestMapping("/user.do")
	public void user(Model model, UserVO user) {
		model.addAttribute("user", service.getUser(user.getUserId()));
	}
	
}
