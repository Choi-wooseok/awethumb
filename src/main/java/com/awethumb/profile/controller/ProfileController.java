package com.awethumb.profile.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.awethumb.profile.service.ProfileService;
import com.awethumb.repository.vo.Follow;
import com.awethumb.repository.vo.Project;
import com.awethumb.repository.vo.ProjectFile;
import com.awethumb.repository.vo.Subscribe;
import com.awethumb.repository.vo.UserFile;
import com.awethumb.repository.vo.UserVO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

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
		return "redirect:" + user.getUserNickname();
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
	
	@RequestMapping("/getfollowinglist.do")
	@ResponseBody
	public List<UserVO> getFollowingList(@RequestBody Follow fol) {
		return service.getFollowingList(fol);
	}
	@RequestMapping("/getfollowerlist.do")
	@ResponseBody
	public List<UserVO> getFollowerList(@RequestBody Follow fol) {
		return service.getFollowerList(fol);
	}
	@RequestMapping("/getsearchfollowerlist.do")
	@ResponseBody
	public List<UserVO> getSearchFollowerList(@RequestBody Follow fol) {
		return service.getSearchFollowerList(fol);
	}
	@RequestMapping("/getsearchfollowinglist.do")
	@ResponseBody
	public List<UserVO> getSearchFollowingList(@RequestBody Follow fol) {
		return service.getSearchFollowingList(fol);
	}
	
	// 프로필 이미지 업로드
	@RequestMapping("/updateprofileimg.do")
	@ResponseBody
	public void updateProfileImg(UserFile uf) throws Exception {
		MultipartFile mf = uf.getUserFile();
		
		long size = mf.getSize(); // 파일 사이즈
		String orgName = mf.getOriginalFilename(); // 파일 이름
		String ext = FilenameUtils.getExtension(orgName); // 파일 확장자
		String sysName = (UUID.randomUUID().toString() + "." + ext); // 파일 시스템 이름
		
		// 경로 설정
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/HH/");
		String path = "c:/java/upload/profile" + sdf.format(new Date()); // 파일 경로
		
		uf.setUserFileSize(size);
		uf.setUserFileOrgName(orgName);
		uf.setUserFileExe(ext);
		uf.setUserFileSysName(sysName);
		uf.setUserFilePath(path);
		
		service.updateUserFile(uf);
		
		// 파일 저장
		mf.transferTo(new File(path + sysName));
	}	
	
	@RequestMapping("/getprofileimg.do")
	@ResponseBody
	public String getProfileImg(int userNo)  {
		UserFile uf = service.getProfileImg(userNo);
		// html에서 로컬 데이터로 바로 접근을 막아놨으니 여기서 직접 파일을 생성해 넘겨주는 수 밖에
		String path = "";
		// 유저 파일이 없을 경우 디폴트 이미지를 설정해준다.
		if (uf == null) path = "C:\\java\\upload\\profile\\default-profile-picture.png";
		else path = uf.getUserFilePath() + uf.getUserFileSysName();

		String eString = "";
		try{
			
			BufferedImage originalImage = ImageIO.read(new File(path));
					
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( originalImage, "png", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			Encoder encoder = Base64.getEncoder();
			eString = encoder.encodeToString(imageInByte);
			baos.close();
					
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		return "data:image/png;base64," + eString;
	}
	
	@RequestMapping("/insertproj.do")
	public String insertProj(Project p) throws IllegalStateException, IOException {
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
		
		return "redirect:" + p.getUserNickname();
	}
	
	@RequestMapping("/getprojects.do")
	@ResponseBody
	public List<Project> getProjects(Project p){
		return service.selectProjects(p);
	}
	
	@RequestMapping("/getprojectthumb.do")
	@ResponseBody
	public String getProjectThumb(int projectNo)  {
		ProjectFile pf = service.getProjectThumb(projectNo);
		// html에서 로컬 데이터로 바로 접근을 막아놨으니 여기서 직접 파일을 생성해 넘겨주는 수 밖에
		String path = "";
		// 파일이 없을 경우 디폴트 이미지를 설정해준다.
		if (pf == null) path = "C:\\java\\upload\\project\\default-project-thumbnail-picture.jpg";
		else path = pf.getProjectFilePath() + "thumbnail_" + pf.getProjectFileSysName();

		String eString = "";
		try{
			
			BufferedImage originalImage = ImageIO.read(new File(path));
					
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( originalImage, "jpg", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			Encoder encoder = Base64.getEncoder();
			eString = encoder.encodeToString(imageInByte);
			baos.close();
					
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		return "data:image/jpg;base64," + eString;
	}
}
