package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class SavedBoard {
	 // 글 번호 
    private int postNo;

    // 프로젝트 번호 
    private int projectNo;

    // x좌표 
    private int xCoord;

    // y좌표 
    private int yCoord;

    // 가로 
    private int width;

    // 세로 
    private int hight;
}
