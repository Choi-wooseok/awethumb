package com.awethumb.repository.vo;

import org.apache.commons.net.ntp.TimeStamp;

import lombok.Data;

@Data
public class VisitCount {
	 private int visitId;
     private String visitIp;
     private TimeStamp visitTime;
     private String visitRefer;
     private String visitAgent;
}
