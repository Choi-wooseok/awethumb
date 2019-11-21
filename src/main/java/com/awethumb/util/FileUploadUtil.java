package com.awethumb.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.web.multipart.MultipartFile;

import com.awethumb.repository.vo.BoardFile;

public class FileUploadUtil {
	public static BoardFile store(MultipartFile file) throws Exception {
		try {
			if (file.isEmpty()) {
				throw new Exception("Failed to store empty file " + file.getOriginalFilename());
			}
			
			System.out.println();

			BoardFile f = new BoardFile();
			f.setBoardFileSysName(UUID.randomUUID().toString());
			f.setBoardFileOrgName(file.getOriginalFilename());
			f.setBoardFileSize(file.getSize());
			f.setBoardFileExe(file.getContentType());
			
			file.transferTo(new File("c:/java/upload/" + file.getOriginalFilename()));
			
//			f.setPostNo(postNo);
//			f.setBoardFilePath();
			
			System.out.println();
			System.out.println(file.getSize());
			System.out.println(file.getName());
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getContentType());
			System.out.println(file.getResource());


			return f;
		} catch (IOException e) {
			throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
		}
	}
}
