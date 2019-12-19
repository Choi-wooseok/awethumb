package com.awethumb.api.project.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.awethumb.api.project.service.APIProjectService;
import com.awethumb.repository.vo.Project;
import com.awethumb.repository.vo.ProjectFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

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
		
		String path = uf.getProjectFilePath() + "thumbnail_" + uf.getProjectFileSysName();

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
		return "data:image/" + uf.getProjectFileExe() + ";base64," + eString;
	}
	
	// 해당 프로젝트 사진 원본을 가져온다
	@GetMapping("/{projectNo}/img")
	public String getProjectImg(@PathVariable int projectNo)  {
		ProjectFile uf = service.selectProjectImg(projectNo);
		
		String path = uf.getProjectFilePath() + uf.getProjectFileSysName();

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
		return "data:image/" + uf.getProjectFileExe() + ";base64," + eString;
	}
	
	// 프로젝트 사진을 업데이트한다
	@PostMapping("/{projectNo}/img")
	public void updateProjectImg (@PathVariable int projectNo, Project p) throws IllegalStateException, IOException  {
		
		MultipartFile mf = p.getProjectFile();
		
		System.out.println(projectNo); 
		System.out.println(mf);
		
		long size = mf.getSize(); // 파일 사이즈
		String orgName = mf.getOriginalFilename(); // 파일 이름
		String ext = FilenameUtils.getExtension(orgName); // 파일 확장자
		String sysName = (UUID.randomUUID().toString() + "." + ext); // 파일 시스템 이름 
		
		// 경로 설정
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/HH/");
		String path = "c:/java/upload/project" + sdf.format(new Date()); // 파일 경로
		
		ProjectFile pf = new ProjectFile();
		
		pf.setProjectFileSize(size);
		pf.setProjectFileOrgName(orgName);
		pf.setProjectFileExe(ext);
		pf.setProjectFileSysName(sysName);
		pf.setProjectFilePath(path);
		pf.setProjectNo(projectNo);
		
		service.updateProjectImg(pf);
		
		mf.transferTo(new File(path + sysName));
		Thumbnails.of(path + sysName).crop(Positions.CENTER).size(300, 300).toFile(new File(path + "thumbnail_" + sysName));
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
