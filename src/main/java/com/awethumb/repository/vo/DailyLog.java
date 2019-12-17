package com.awethumb.repository.vo;

import lombok.Data;

@Data
public class DailyLog {
	private String logDt;
	private int dailyVisitCnt;
	private int dailyJoinCnt;
	private int dailyViewCnt;
	private int dailyPostCnt;
}
