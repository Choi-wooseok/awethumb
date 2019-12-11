package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class VisitCount {
	 private int visitId;
     private String visitIp;
     private String visitTime;
     private String visitRefer;
     private String visitAgent;
}
