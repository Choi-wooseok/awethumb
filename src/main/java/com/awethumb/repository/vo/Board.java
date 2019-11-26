package com.awethumb.repository.vo;

import java.util.List;

public class Board {
	 // 글 번호 
    private int postNo;

    // 프로젝트 번호 
    private int projectNo = 1;

    // 글 내용 
    private String postContent;

    // x좌표 
    private int xCoord;

    // y좌표 
    private int yCoord;

    // 가로 
    private int width;

    // 세로 
    private int hight;

    // 글 공개 여부 
    private String postPublicEnabled;

    // 해시태그 번호 
    private String hashtagNo;
    
    // 이미지 리스트 받아옴
    private List<BoardFile> listFile;
    
    // 글쓴
    private String writer;
    
    
	public List<BoardFile> getListFile() {
		return listFile;
	}

	public void setListFile(List<BoardFile> listFile) {
		this.listFile = listFile;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHight() {
		return hight;
	}

	public void setHight(int hight) {
		this.hight = hight;
	}

	public String getPostPublicEnabled() {
		return postPublicEnabled;
	}

	public void setPostPublicEnabled(String postPublicEnabled) {
		this.postPublicEnabled = postPublicEnabled;
	}

	public String getHashtagNo() {
		return hashtagNo;
	}

	public void setHashtagNo(String hashtagNo) {
		this.hashtagNo = hashtagNo;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

}
