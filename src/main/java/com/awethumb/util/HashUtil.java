package com.awethumb.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class HashUtil {
	public static HashMap<String, Object> renderHashtag(String content) {
		HashMap<String, Object> map = new HashMap<>();
		List<String> hList = new ArrayList<>();
		if (content.contains("&lt;/p&gt;&lt;p&gt;")) {
			content = content.replaceAll("&lt;/p&gt;&lt;p&gt;", "&lt;/p&gt; &lt;p&gt;");
		}
		if (content.contains("#")) {
			content = content.replaceAll("#", " #");
		}
		String[] contents = content.split(" ");
		String returnContent = "";
		for (int i = 0; i < contents.length; i++) {
			StringBuffer sb = new StringBuffer();
			// 해시태그인 경우
			if(contents[i].contains("#")) {
				String hash = contents[i];
				// <p>#hash</p>일 경우
				if (hash.startsWith("&lt;p&gt;") && hash.endsWith("&lt;/p&gt;")) {
					// 양쪽 p태그 삭제 후 순수 해시태그만 가져오는 작업
					hash = contents[i].split("\\&lt;p&gt;")[1].split("\\&lt;/p&gt;")[0];
					hList.add(hash); // 해시태그 추가 해줌
					// p태그로 다시 감싸면서 재 생성
					sb.append("<p><span class='ht' data-ht='");
					sb.append(hash + "'>");
					sb.append(hash);
					sb.append("</span></p>");
					// <p>#hash 일 경우					
				} else if (hash.startsWith("&lt;p&gt;") && hash.endsWith("&lt;/p&gt;")==false) {
					// 앞에 p태그 삭제 후 순수 해시태그만 가져옴
					hash = contents[i].split("\\&lt;p&gt;")[1];
					hList.add(hash); // 해시태그 추가 해줌
					// 앞에 p태그 붙여주면서 재 생성
					sb.append("<p><span class='ht' data-ht='");
					sb.append(hash + "'>");
					sb.append(hash);
					sb.append("</span>");
				// #hash<p> 일 경우
				} else if (hash.startsWith("#") && hash.endsWith("&lt;/p&gt;")) {
					// 뒤에 p태그 삭제 후 순수 해시태그만 가져옴
					hash = contents[i].split("\\&lt;/p&gt;")[0];
					hList.add(hash); // 해시태그 추가 해줌
					// 뒤에 p 태그 붙여주면서 재 생성
					sb.append("<span class='ht' data-ht='");
					sb.append(hash + "'>");
					sb.append(hash);
					sb.append("</span></p>");
				// #hash 일 경우
				} else if (hash.startsWith("#") && hash.endsWith("&lt;/p&gt;")==false) {
					hash = contents[i];
					hList.add(hash);
					sb.append("<span class='ht' data-ht='");
					sb.append(hash + "'>");
					sb.append(hash);
					sb.append("</span>");
				}
			} else {
				// 해시태그가 아닌 상태에서 뒤에 p 태그가 붙을 경우 그 뒤는 전부 다 잘라버리고 텍스트만 남김.
				// 이유 : 어차피 p 태그로 전체 감싸면서 들어오므로 </p>라는 String 글자가 붙어버림
				String text = contents[i];
				System.out.println("Text  = " + text);
				// <p><br></p>일 경우 삭제
				if (text.contains("&lt;p&gt;&lt;br&gt;&lt;/p&gt;")) {
					text = text.replaceAll("&lt;p&gt;&lt;br&gt;&lt;/p&gt;", "");
				}
				// 앞에 p태그가 붙을 경우
				if (text.startsWith("&lt;p&gt;") && text.endsWith("&lt;/p&gt;")) {
					text = text.split("\\&lt;p&gt;")[1].split("\\&lt;/p&gt;")[0];
					sb.append("<p>");
					sb.append(text);
					sb.append("</p>");
				} else if (text.startsWith("&lt;p&gt;") && text.endsWith("&lt;/p&gt;")==false) {
					sb.append("<p>");
					
					String[] str = text.split("\\&lt;p&gt;");
					for (int k = 0; k < str.length; k++) {
						System.out.println("k = " + str[k]);
					}
					
					sb.append(str[1]);
					
				} else if (text.startsWith("&lt;p&gt;")==false && text.endsWith("&lt;/p&gt;")) {
					sb.append(text.split("\\&lt;/p&gt;")[0]);
					sb.append("</p>");
				} else if (text.startsWith("&lt;p&gt;")==false && text.endsWith("&lt;/p&gt;")==false) {
					sb.append(text);
				}
			}
			// 만든 해시태그를 toString 해서 재생성 시킴
			contents[i] = sb.toString();
			// 띄어쓰기로 스플릿 했던거 다시 만들면서 생성
			returnContent += " " + contents[i];
			
		}
		for(int i = 0; i < hList.size(); i++) {
			System.out.println(hList.get(i));
		}
		map.put("content", returnContent);
		map.put("hashList", hList);
		return map;
	}
}
