package com.awethumb.api.user.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.awethumb.api.user.service.APIUserService;
import com.awethumb.repository.vo.TokenUser;
import com.awethumb.repository.vo.UserFile;
import com.awethumb.repository.vo.UserVO;

@RestController
@RequestMapping("/api/user")
public class APIUserController {
	@Autowired
	private APIUserService service;
	
	
	// 한명의 유저 정보를 가져온다
	@GetMapping("/{userNo}")
	public UserVO getUser(@PathVariable int userNo){
		return service.selectUser(userNo);
	}
	
	// 유저의 프로필 이미지를 가져온다
	@RequestMapping(value="/{userNo}/thumb", method=RequestMethod.GET)
	public String getUserThumb(@PathVariable int userNo)  {
		UserFile uf = service.selectUserThumb(userNo);
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
	
	// 프로필 이미지 업로드
	@RequestMapping(value="/{userNo}/thumb", method=RequestMethod.POST)
	public void updateUserThumb(UserFile uf) throws Exception {
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
		
		service.updateUserThumb(uf);
		
		// 파일 저장
		mf.transferTo(new File(path + sysName));
	}	
	
	// 토큰 유저 불러오기
	@GetMapping("/token")
	@ResponseBody
	public List<TokenUser> getTokenUsers (@RequestParam(value="userNickname" ) String userNickname) {
		return service.selectTokenUsers(userNickname);
	}
	
}
