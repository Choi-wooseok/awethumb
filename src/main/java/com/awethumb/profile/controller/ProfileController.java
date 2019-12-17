package com.awethumb.profile.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.awethumb.profile.service.ProfileService;
import com.awethumb.repository.vo.Project;
import com.awethumb.repository.vo.ProjectFile;
import com.awethumb.repository.vo.UserVO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@Controller("com.awethumb.mypage.controller.ProfileController")
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	private ProfileService service;
	
	@RequestMapping("/{userNickname}")
	public ModelAndView mypage(@PathVariable String userNickname, Principal p) {
		ModelAndView mav = new ModelAndView();
		UserVO user = service.selectOneUser(userNickname);
		// 해당 유저가 없으면 404 페이지로 이동시킨다.
		
		mav.addObject("projectCnt", service.selectProjectCount(user.getUserNo()));
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
		return "redirect:" + user.getUserNickname();
	}
	
	@RequestMapping("/insertproj.do")
	public String insertProj(Project p, RedirectAttributes rttr) throws IllegalStateException, IOException {
		ProjectFile pf = new ProjectFile();
		
		MultipartFile mf = p.getProjectFile();
		// 썸네일을 입력 받았을 경우
		if(mf.getSize() != 0) {
			long size = mf.getSize(); // 파일 사이즈
			String orgName = mf.getOriginalFilename(); // 파일 이름
			String ext = FilenameUtils.getExtension(orgName); // 파일 확장자
			String sysName = (UUID.randomUUID().toString() + "." + ext); // 파일 시스템 이름
			
			// 경로 설정
			SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/HH/");
			String path = "c:/java/upload/project" + sdf.format(new Date()); // 파일 경로
			
			pf.setProjectFileSize(size);
			pf.setProjectFileOrgName(orgName);
			pf.setProjectFileExe(ext);
			pf.setProjectFileSysName(sysName);
			pf.setProjectFilePath(path);
			
			mf.transferTo(new File(path + sysName));
			
			Thumbnails.of(path + sysName).crop(Positions.CENTER).size(300, 300).toFile(new File(path + "thumbnail_" + sysName));
		}
		
		service.insertProj(p, pf);
		
		// 알림을 보내기 위해 1회성 변수를 보내준다
		if(p.getProjectType() == 2) {
			int projectNo = service.selectCurrentSharedProjectNo(p.getUserNo());
			rttr.addFlashAttribute("test", "makeAlarm(4," + projectNo + ")");
		}
		
		return "redirect:" + p.getUserNickname();
	}
}
