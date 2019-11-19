package com.awethumb.profile.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.awethumb.profile.service.ProfileService;
import com.awethumb.repository.vo.Subscribe;
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
		
		try {
			String sessionId = p.getName();
			// 세션 등록정보와 들어가려는 페이지의 주인이 일치할 경우 마이페이지로 이동
			if (sessionId.equals(user.getUserId())) {
				mav.addObject("categories", service.getCategories());
				mav.setViewName("profile/mypage");
			} else {
				// 아닐 경우 남의 페이지로 이동
				mav.setViewName("profile/yourpage");
			}
		} catch (Exception e){
			// 세션에 유저 정보가 없을 시에도 이동
			mav.setViewName("profile/yourpage");
		}
		return mav;
	}
	
	@PostMapping("/update.do")
	public String update(UserVO user) {
		service.updateUser(user);
		return "redirect:main.do";
	}
	
	@RequestMapping("/checksub.do")
	@ResponseBody
	public int checkSub(@RequestBody Subscribe sub) {
		return service.checkSub(sub);
	}
	
	@RequestMapping("/deletesub.do")
	@ResponseBody
	public void deleteSub(@RequestBody Subscribe sub) {
		service.deleteSub(sub);
	}
	
	@RequestMapping("/insertsub.do")
	@ResponseBody
	public void insertSub(@RequestBody Subscribe sub) {
		service.insertSub(sub);
	}
}
