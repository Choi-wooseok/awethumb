package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class Comment {
	int cmtNo; // 댓글번호
	String cmtContent; // 댓글내용
	String cmtRegDt; // 댓글 작성일자
	int userNo; // 유저번호
	int postNo; // 글번호
	String cmtUserNickname; // 댓글 유저닉네임
	String agoRegDt; // ~분 전, ~시간 전, ~일 전 표시
}
