package com.awethumb.util;

import javax.servlet.http.HttpServletRequest;

import com.awethumb.repository.vo.BoardFile;
import com.awethumb.repository.vo.UserFile;

public class UserFileUtil {
	
	public String fileUtil(UserFile userfile, HttpServletRequest req) throws Exception {
		String uImgUrl = "";
		if (userfile == null) {
			return req.getContextPath() + "/image/project/default-user-icon.jpg";
		} else {
			String path = userfile.getUserFilePath().split("upload/")[1];
			return req.getContextPath() + "/image/" + path + userfile.getUserFileSysName();
		}
	}
}
