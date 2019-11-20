package com.awethumb.errpage.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("com.awethumb.errpage.controller.ErrorController")
@RequestMapping("/error")
public class ErrorController {

	
	@RequestMapping("/403.do")
	public void error403() throws Exception{
	}
	@RequestMapping("/404.do")
	public void error404() {
	}
	@RequestMapping("/405.do")
	public void error405() {
	}
	@RequestMapping("/500.do")
	public void error500() {
	}
	@RequestMapping("/default.do")
	public void errorDefault() {
	}
	
	
}
