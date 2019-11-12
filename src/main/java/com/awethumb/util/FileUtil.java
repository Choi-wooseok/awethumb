package com.awethumb.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	// 바이트단위 처리
	public static byte[] readByteFile(String name) throws Exception {
		FileInputStream fis = new FileInputStream(name);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	// 문자단위 처리
	public static String readFile(String name) throws IOException {
		FileReader fr = new FileReader(name);
		BufferedReader br = new BufferedReader(fr);
		StringBuffer sb = new StringBuffer();
		while (true) {
			String line = br.readLine();
			if (line == null) break;
			
			sb.append(line);
		}
		br.close(); fr.close();
		return sb.toString();
	}
}
