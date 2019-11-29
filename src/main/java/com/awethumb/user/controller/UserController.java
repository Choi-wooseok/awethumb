package com.awethumb.user.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.awethumb.auth.SnsLogin;
import com.awethumb.auth.SnsValue;
import com.awethumb.common.service.CommonService;
import com.awethumb.repository.vo.UserVO;
import com.awethumb.user.service.UserService;

@Controller("com.awethumb.user.controller.UserController")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	@Autowired
	private CommonService commService;
	@Inject
	private SnsValue googleSns;
	@Inject
	private SnsValue naverSns;
	@Inject
	private GoogleConnectionFactory googleConnectionFactory;
	@Inject
	private OAuth2Parameters googleOAuth2Parameters;
	
	@RequestMapping("/login_main.do")
	public void loginMain(Model model) {
		/* naver URL 생성 */
		model.addAttribute("categoryList", commService.selectCategoryList());
		SnsLogin snsLogin = new SnsLogin(naverSns);
		model.addAttribute("naver_url", snsLogin.getNaverAuthURL());
		
		System.out.println("naverUrl : " + snsLogin.getNaverAuthURL());
		
		
		/* 구글code 발행 위한 URL 생성 */
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		System.out.println("googleUrl : " + url);
		model.addAttribute("google_url", url);
	}
	
	@RequestMapping(value = "/google/callback.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String snsLoginCallback(Model model, @RequestParam String code) throws Exception {
		SnsLogin snsLogin = new SnsLogin(googleSns);
		String profile = snsLogin.getUserProfile(code);
		System.out.println("profile : " + profile);
		model.addAttribute("profile", profile);
		return "redirect:/user/login_main.do";
	}
	
//	@RequestMapping("/loginSns.do") 
//	public void loginSns(Model model) throws Exception {
//		/* 구글code 발행 위한 URL 생성 */
//		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
//		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
//		model.addAttribute("google_url", url);
//		
//	}
	
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
