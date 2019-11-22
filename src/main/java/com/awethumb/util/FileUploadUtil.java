package com.awethumb.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.web.multipart.MultipartFile;

import com.awethumb.repository.vo.BoardFile;

public class FileUploadUtil {
	public static BoardFile store(MultipartFile file, int postNo) throws Exception {
		try {
			if (file.isEmpty()) {
				throw new Exception("Failed to store empty file " + file.getOriginalFilename());
			}
			BoardFile f = new BoardFile();
			
			String sysName = UUID.randomUUID().toString();

			f.setBoardFileSysName(sysName);
			f.setBoardFileOrgName(file.getOriginalFilename());
			f.setBoardFileSize(file.getSize());
			f.setBoardFileExe(file.getContentType());
			f.setBoardFilePath("/awethumb/image/");
			f.setPostNo(postNo);
			
			String type = f.getBoardFileOrgName().split("\\.")[1];
			file.transferTo(new File("c:/java/upload/" + sysName + "." + type));
			
			System.out.println(f.getPostNo());
			System.out.println(f.getBoardFileSize());
			System.out.println(f.getBoardFileSysName());
			System.out.println(f.getBoardFileOrgName());
			System.out.println(f.getBoardFileRegDt());
			System.out.println(f.getBoardFilePath());


			return f;
		} catch (IOException e) {
			throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
		}
	}
}
