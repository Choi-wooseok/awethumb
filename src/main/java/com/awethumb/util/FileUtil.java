package com.awethumb.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.awethumb.repository.vo.BoardFile;

public class FileUtil {
	
	public BoardFile UploadImage(MultipartFile file, HttpServletResponse res) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		String path = "profile" + sdf.format(new Date());
		   
		BoardFile bfile = new BoardFile();
		
	    long size = file.getSize();						// 파일크기
	    String orgName = file.getOriginalFilename();	// 파일이름
	    String ext = FilenameUtils.getExtension(orgName); // 확장자
	    String sysName = (UUID.randomUUID().toString() + "." + ext); // 파일고유이름

 	    bfile.setBoardFileSize(size);
 	    bfile.setBoardFileOrgName(orgName);
 	    bfile.setBoardFileExe(ext);
 	    bfile.setBoardFileSysName(sysName);
	    bfile.setBoardFilePath(path);
	    
	    String createPath = "/var/java/upload/profile" + sdf.format(new Date());
	    File readFile = new File(createPath + sysName);
	    if(readFile.exists() == false) readFile.mkdirs();
	    try {
		    file.transferTo(readFile);
	    } catch (Exception e) {

	    }
	    return bfile;
	}
	
	public void DownLoadFile(BoardFile boardfile, HttpServletResponse res) {
	      try {
	         File f = new File(boardfile.getUrl()+boardfile.getBoardFilePath(),boardfile.getBoardFileSysName());
	         String orgName = boardfile.getBoardFileOrgName();
	         if (orgName == null) {
	            res.setHeader("Content-type", "image/jpg");
	         } else {
	            res.setHeader("Content-type", "application/octet-stream");
	            orgName = new String(orgName.getBytes("utf-8"), "8859_1");
	            res.setHeader("Content-Disposition", "attchment;filename=" + orgName);
	         }

	      FileInputStream fis = new FileInputStream(f);
	      BufferedInputStream bis = new BufferedInputStream(fis);
	      OutputStream out = res.getOutputStream();
	      BufferedOutputStream bos = new BufferedOutputStream(out);
	      while (true) {
	         int ch = bis.read();
	         if (ch == -1)
	            break;
	         bos.write(ch);
	      }
	      bis.close();
	      fis.close();
	      bos.close();
	      out.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
}
