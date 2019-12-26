package com.awethumb.repository.vo;

import java.util.List;

import lombok.Data;

@Data
public class Comment {
	int cmtNo; // 댓글번호
	int cmtKey; // 방금 작성된 댓글의 번호
	String cmtContent; // 댓글내용
	String cmtRegDt; // 댓글 작성일자
	int userNo; // 유저번호
	int postNo; // 글번호
	String cmtUserNickname; // 댓글 유저닉네임
	String agoRegDt; // ~분 전, ~시간 전, ~일 전 표시
	List<Hashtag> hashtag;  // 해시태그 리스트로 받기
	String uImgUrl; // 이미지 url
	
	int subUserNo; // 로그인유저 번호
	int likeCheck; // 좋아요유무
}
