package com.awethumb.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.awethumb.common.service.CommonService;
import com.awethumb.repository.vo.UserVO;
import com.awethumb.user.service.UserService;

@Controller("com.awethumb.user.controller.UserController")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;
	@Autowired
	CommonService commService;
	
	@RequestMapping("/login_main.do")
	public void loginMain(Model model) {
		
		model.addAttribute("categoryList", commService.selectCategoryList());
	}
	
	@RequestMapping("/chk_user.do")
	@ResponseBody
	 public int chkUser(@RequestBody Map<String, Object> map) {
		return service.chkUser(map);
	}
	
	@RequestMapping("/user_regist.do")
	@ResponseBody
	public int registUser(@RequestBody UserVO user) {
		System.out.println("user : " + user);
		return service.registUser(user);
	}
	
	@RequestMapping("/regist_finish_user.do")
	public String registFinishUser(UserVO user, Model model) {
		int result = service.registFinishUser(user);
		if (result == 1) {
			model.addAttribute("emailStatus", "회원가입이 완료되었습니다.");
		} else {
			model.addAttribute("emailStatus", "이미 이메일 인증이 완료 되었습니다. 로그인 해주세요.");
		}
		return "user/user_join_finish";
	}
	
	@RequestMapping("/login_fail.do")
	public String loginFail(String errCode, RedirectAttributes attr) {
		attr.addFlashAttribute("errCode", errCode);
		return "redirect:/user/login_main.do";
	}
	

}
