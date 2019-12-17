package com.awethumb.invitations.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awethumb.invitations.service.InvitationsService;
import com.awethumb.repository.vo.Invitations;
import com.awethumb.repository.vo.SharedProject;

@RequestMapping("/invitations")
@Controller
public class InvitationsController {
	
	@Autowired
	private InvitationsService service;
	
	@RequestMapping("/{invitationUrl}")
	public String main(@PathVariable String invitationUrl, Principal p, Model model) {
		// 로그인이 안된 상태라면 로그인 페이지로 넘겨준다.
		if(p == null) {
			return "user/login_main";
		}
		Invitations inv = new Invitations();
		inv.setUserId(p.getName());
		inv.setInvitationUrl(invitationUrl);
		
		SharedProject sp = service.selectSharedProject(inv);
		
		// 이미 수락 했다면 프로젝트 페이지로 넘김
		if(sp.getShareCheck() == 'Y') {
			return "redirect:/detailProject/" + sp.getProjectNo();
		}
		model.addAttribute("sp", sp);
		
		return "invitations/main";
	}
	
	// 공유 수락
	@PutMapping
	@ResponseBody
	public void updateSharedProject(@RequestBody SharedProject sp) {
		service.updateSharedProject(sp);
	}
}
