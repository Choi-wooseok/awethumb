package com.awethumb.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public void loginMain(Model model, HttpServletRequest req) {
		System.out.println("222222222222222222222222");
		System.out.println("errMsg : " + req.getParameter("errMsg"));
		model.addAttribute("categoryList", commService.selectCategoryList());
		model.addAttribute("errMsg", req.getParameter("errMsg"));
	}
	
	@RequestMapping("/chk_user.do")
	@ResponseBody
	 public int chkUser(@RequestBody Map<String, Object> map) {
		return service.chkUser(map);
	}
	
	@RequestMapping("/user_regist.do")
	@ResponseBody
	public void registUser(UserVO user) {
		service.registUser(user);
	}
	
	@RequestMapping("/regist_finish_user.do")
	public String registFinishUser(UserVO user) {
		service.registFinishUser(user);
		return "user/user_join_finish";
	}
	

}
