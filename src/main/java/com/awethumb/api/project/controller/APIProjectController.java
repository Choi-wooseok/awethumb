package com.awethumb.api.project.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Base64.Encoder;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.awethumb.api.project.service.APIProjectService;
import com.awethumb.repository.vo.Project;
import com.awethumb.repository.vo.ProjectFile;

@RequestMapping("/api/project")
@RestController
public class APIProjectController {
	@Autowired
	private APIProjectService service;
	
	// 해당 번호의 프로젝트를 가져온다
	@GetMapping("/{projectNo}")
	public Project getProject(@PathVariable int projectNo) {
		return service.selectProject(projectNo);
	}
	
	// 해당 프로젝트의 썸네일을 가져온다
	@GetMapping("/{projectNo}/thumb")
	public String getProjectThumb(@PathVariable int projectNo)  {
		ProjectFile uf = service.selectProjectImg(projectNo);
		// html에서 로컬 데이터로 바로 접근을 막아놨으니 여기서 직접 파일을 생성해 넘겨주는 수 밖에
		String path = "";
		// 유저 파일이 없을 경우 디폴트 이미지를 설정해준다.
		if (uf == null) path = "C:\\java\\upload\\project\\default-project-thumbnail-picture.jpg";
		else path = uf.getProjectFilePath() + "thumbnail_" + uf.getProjectFileSysName();

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
	
	// progress project를 전부 가져온다
	@GetMapping("/progress")
	public List<Project> getProgressProjects(Project p){
		return service.selectProgressProjects(p);
	}
	
	// shared project를 전부 가져온다
	@GetMapping("/shared")
	public List<Project> getSharedProjects(Project p){
		return service.selectSharedProjects(p);
	}
	
	// saved project를 전부 가져온다
	@GetMapping("/saved")
	public List<Project> getSavedProjects(Project p){
		return service.selectSavedProjects(p);
	}
}
