package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class BlockReason {
	 // 정지 코드 
    private int blockCode;

    // 정지 사유 
    private String blockReason;
    
    // 블록
    private Block block;
}
