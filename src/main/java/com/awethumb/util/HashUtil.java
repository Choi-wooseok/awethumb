package com.awethumb.util;

import java.util.ArrayList;
import java.util.List;

public class HashUtil {
//	public static void main(String[] args) {
//		String cmtAndPost = "#시발 이게 왜 aa#안나오냐 얘도 #나와야된다고";
////		String cmtAndPost = "이거슨 샵이 없는 일반 글이닷";
//		renderHashtag(cmtAndPost);
//		hashSplit(cmtAndPost);
//	}

	// 댓글 혹은 게시글 중 #이 존재할 시 해시태그 링크가 포함된 글로 변경
	public static String renderHashtag(String cmtAndPost) {
		String newContent = "";

		if (cmtAndPost.contains("#")) {
			String[] Content = cmtAndPost.split(" "); // 받은 문자열을 띄어쓰기로 나눠 배열에 입력
			for (int i = 0; i < Content.length; i++) {
//				System.out.print(Content[i] + " ");  // #시발 이게 왜 aa#안나오냐 얘도 #나와야된다고 
				if (Content[i].contains("#")) { // 문자열중 #이 중간에 추가된 것을 찾음 / aa#안나오냐
					String[] preHashContent = {}; // = hash
					preHashContent = Content[i].split("#"); // #으로 나눠서 / aa 안나오냐
					String hash = "";
					for (int j = 0; j < preHashContent.length; j++) {
						hash = preHashContent[1]; // 배열의 1번째를 찾기 / 안나오냐
					}
//					System.out.print(hash + " ");  // 시발 안나오냐 나와야된다고 
					StringBuffer sb = new StringBuffer();
					sb.append(preHashContent[0]); // 0번째도 글에 추가되야하니까 여기서 추가 / aa
					sb.append("<span class='content' data-content='");
					sb.append("#" + hash); // 클릭이벤트로 검색을 위한 데이터 삽입
					sb.append("' color='#6dd5bc'>");
					sb.append("#" + hash); // 보여질 해시태그 문자
					sb.append("</span>");
					Content[i] = sb.toString();
				}
				newContent += Content[i] + " "; // 배열을 반복돌려서 문자열로 변경
			}
//			System.out.println(newContent);
			// <span class='content' data-content='#시발' color='#6dd5bc'>#시발</span> 이게 왜
			// aa<span class='content' data-content='#안나오냐' color='#6dd5bc'>#안나오냐</span> 얘도
			// <span class='content' data-content='#나와야된다고' color='#6dd5bc'>#나와야된다고</span>
			return newContent;
		} else {
//			System.out.println(cmtAndPost);
			return cmtAndPost;
		}
	}

	// 해시태그 테이블에 저장할 해시태그 스플릿하기
	
	public static List<String> hashSplit(String cmtAndPost) {
		List<String> inputHashtag = new ArrayList<>();
		if (cmtAndPost.contains("#")) {
//			String tag = "";
			String[] hash = cmtAndPost.split(" ");
			for (int i = 0; i < hash.length; i++) {
				if (hash[i].contains("#")) { // 문자열중 #이 중간에 추가된 것을 찾음
//					System.out.println(hash[i]);
					String[] preHashContent = {};
					preHashContent = hash[i].split("#"); // #으로 나눠서
					inputHashtag.add('#' + preHashContent[1]); // 배열의 1번째를 찾기
				}
			}
		}
		System.out.println(inputHashtag);  // [#시발, #안나오냐, #나와야된다고]
		return inputHashtag; 
	}
}
