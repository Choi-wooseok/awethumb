package com.awethumb.api.hashtag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awethumb.mainfeed.service.MainFeedService;
import com.awethumb.util.HashUtil;

@RequestMapping("/api/hashtag")
@RestController
public class APIHashtagController {
	@Autowired
	private MainFeedService service;
	private HashUtil hashutil;
	
	
	
	
//	function hashClickFn() {
//		$(document).on("click", ".ht", (e) => {
//			let hashSrch = $(e.target).data("ht")
//			let hashTg = hashSrch.split('#');
//			let htg = hashTg[1];
//			$.ajax({
//				url: pageContextPath + "/mainfeed/search.do",
//				method: 'POST',
//				data: htg,
//				dataType: 'JSON',
//				contentType: 'application/json; charset=UTF-8',
//				success: result => {
//					for (let i = 0; i < result.length; i++){
//						if (result[i].hashtagAndNickname == hashTg[1]){
//							MainfeedMakeAjax(hashTg[1])
//							$("#detailFeedModal").css("display", "none")
//						}
//					}
//				}
//			})
//		});
//	};
	
	
	
	
	
	
	
	
	
//	// 해당 번호의 프로젝트를 가져온다
//	@GetMapping("/{projectNo}")
//	public Project getProject(@PathVariable int projectNo) {
//		return service.selectProject(projectNo);
//	}
//	
//	// 해당 프로젝트의 썸네일을 가져온다
//	@GetMapping("/{projectNo}/thumb")
//	public String getProjectThumb(@PathVariable int projectNo)  {
//		ProjectFile uf = service.selectProjectImg(projectNo);
//		// html에서 로컬 데이터로 바로 접근을 막아놨으니 여기서 직접 파일을 생성해 넘겨주는 수 밖에
//		String path = "";
//		// 유저 파일이 없을 경우 디폴트 이미지를 설정해준다.
//		if (uf == null) path = "C:\\java\\upload\\project\\default-project-thumbnail-picture.jpg";
//		else path = uf.getProjectFilePath() + "thumbnail_" + uf.getProjectFileSysName();
//
//		String eString = "";
//		try{
//			
//			BufferedImage originalImage = ImageIO.read(new File(path));
//					
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			ImageIO.write( originalImage, "png", baos );
//			baos.flush();
//			byte[] imageInByte = baos.toByteArray();
//			Encoder encoder = Base64.getEncoder();
//			eString = encoder.encodeToString(imageInByte);
//			baos.close();
//					
//		}catch(IOException e){
//			System.out.println(e.getMessage());
//		}
//		return "data:image/png;base64," + eString;
//	}
//	
//	// progress project를 전부 가져온다
//	@GetMapping("/progress")
//	public List<Project> getProgressProjects(Project p){
//		return service.selectProgressProjects(p);
//	}
//	
//	// shared project를 전부 가져온다
//	@GetMapping("/shared")
//	public List<Project> getSharedProjects(Project p){
//		return service.selectSharedProjects(p);
//	}
//	
//	// saved project를 전부 가져온다
//	@GetMapping("/saved")
//	public List<Project> getSavedProjects(Project p){
//		return service.selectSavedProjects(p);
//	}
}
