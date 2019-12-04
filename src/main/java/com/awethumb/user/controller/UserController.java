package com.awethumb.user.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.awethumb.security.CustomAuthenticationProvider;
import com.awethumb.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller("com.awethumb.user.controller.UserController")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	@Autowired
	private CommonService commService;
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	@Inject
	private SnsValue googleSns;
	@Inject
	private SnsValue naverSns;
	@Inject
	private SnsValue kakaoSns;
	@Inject
	private GoogleConnectionFactory googleConnectionFactory;
	@Inject
	private OAuth2Parameters googleOAuth2Parameters;
	
	@RequestMapping("/login_main.do")
	public void loginMain(Model model, @ModelAttribute(value="user") UserVO user) throws JsonProcessingException {
		model.addAttribute("categoryList", commService.selectCategoryList());
		
		/* naver URL 생성 */
		SnsLogin naverSnsLogin = new SnsLogin(naverSns);
		SnsLogin kakaoSnsLogin = new SnsLogin(kakaoSns);
		
		// 네이버 필수사항 체크 안했을 시 재 체크 유도
		if ("4".equals(model.getAttribute("errCode"))) {
			String naverURL = naverSnsLogin.getAuthURL().replace("scope=profile", "") + "auth_type=reprompt"; 
			model.addAttribute("naver_url", naverURL);
		} else {
			model.addAttribute("naver_url", naverSnsLogin.getAuthURL());
		}
		
		model.addAttribute("kakao_url", kakaoSnsLogin.getAuthURL());
		
		/* 구글code 발행 위한 URL 생성 */
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		model.addAttribute("google_url", url);
		System.out.println();
		// oauth 로그인 시
		model.addAttribute("user", new ObjectMapper().writeValueAsString(user));
	}
	
	
	
	// oauth 로그인 - 네이버, 카카오
	@RequestMapping(value = "/{service}/callback.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String snsLoginCallback(@RequestParam String code, @PathVariable String service, HttpSession session, RedirectAttributes attr) {
		SnsValue sns = null;
		String methodType = null;
		String redirectURL = "redirect:/user/login_main.do";
		if ("google".equals(service)) {
			sns = googleSns;
			methodType = "google";
		}
		else if ("naver".equals(service)) {
			sns = naverSns;
			methodType = "naver";
		}
		else if ("kakao".equals(service)) {
			sns = kakaoSns;
			methodType = "kakao";
		} 
		
		SnsLogin snsLogin = new SnsLogin(sns);
		UserVO profile = null;
		try {
			profile = snsLogin.getUserProfile(code, methodType);
			if (profile == null) {
				if (sns.isNaver()) {
					attr.addFlashAttribute("errCode", "4");
				} else if (sns.isKakao()) {
					attr.addFlashAttribute("errCode", "5");
				}
				return redirectURL;	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(profile.getUserId(), profile.getUserPass());
		try {
			Authentication auth = customAuthenticationProvider.authenticate(authRequest);
			// 아이디, 비밀번호가 있다면 강제 로그인
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(auth);
			session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
			redirectURL = "redirect:/feed/feed.do";
		} catch (Exception e) {
			// 유저 정보가 없다면
			if ("UserNotFound".equals(e.getMessage())) {
				attr.addFlashAttribute("user", profile);
			}
			// 고유 id값 변경 시 - 처리??
			else if ("UserPasswordChanged".equals(e.getMessage())) {
				attr.addFlashAttribute("errCode", "6");				
			}
		}
		return redirectURL;
	}
	
	
	@RequestMapping("/chk_user.do")
	@ResponseBody
	 public int chkUser(@RequestBody Map<String, Object> map) {
		return service.chkUser(map);
	}
	
	@RequestMapping("/user_regist.do")
	@ResponseBody
	public int registUser(@RequestBody UserVO user) {
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
