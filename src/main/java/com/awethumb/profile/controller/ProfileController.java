package com.awethumb.profile.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.awethumb.profile.service.ProfileService;
import com.awethumb.repository.vo.UserVO;

@Controller("com.awethumb.mypage.controller.ProfileController")
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	private ProfileService service;
	
	@GetMapping("/{userNickname}")
	public ModelAndView mypage(@PathVariable String userNickname, Principal p) {
		ModelAndView mav = new ModelAndView();
		UserVO user = service.selectOneUser(userNickname);
		
		mav.addObject("u", user);

		String sessionId = p.getName();
		// 세션 등록정보와 들어가려는 페이지의 주인이 일치할 경우 마이페이지로 이동
		if (sessionId.equals(user.getUserId())) {
			mav.addObject("categories", service.getCategories());
			mav.setViewName("profile/mypage");
			return mav;
		}
		// 아닐 경우 남의 페이지로 이동
		mav.setViewName("profile/yourpage");
		return mav;
	}
	
	@PostMapping("/update.do")
	public String update(UserVO user) {
		service.updateUser(user);
		return "redirect:main.do";
	}
}
