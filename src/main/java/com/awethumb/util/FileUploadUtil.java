package com.awethumb.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.awethumb.repository.vo.BoardFile;
import com.google.gson.JsonObject;

public class FileUploadUtil {
	public static BoardFile store(MultipartFile file, int postNo, HttpServletRequest req) throws Exception {
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
			
			String url = "c:/java/upload/" + sysName + "." + type;
			file.transferTo(new File(url));
			
			f.setUrl(url);
			
			return f;
		} catch (IOException e) {
			throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
		}
	}
}
