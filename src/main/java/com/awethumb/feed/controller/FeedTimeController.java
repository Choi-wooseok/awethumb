package com.awethumb.feed.controller;

import java.util.ArrayList;
import java.util.List;

import com.awethumb.feed.service.FeedService;
import com.awethumb.repository.vo.Comment;

public class FeedTimeController {
	private  FeedService service;
	
	public  List<Comment> service(int postNo) {
		List<Comment> comList = new ArrayList<>();
		List<Integer> cmtno = service.selectCmtNo(postNo);
		for(int a : cmtno) {
			Comment cm = service.selectOneComment(a);
			int cmtReg = service.commentTime(a);
			// 날짜계산
			int mi, ho, day, week ,month, year ;
		
			if(cmtReg < 60 ) {
				mi = cmtReg;
				cm.setCmtRegDt(Integer.toString(mi) + "분 전");
			}
			else if(cmtReg > 60 && cmtReg < 3600) {
				ho = cmtReg / 60;
				cm.setCmtRegDt(Integer.toString(ho) + "시간 전");
			}
			else if (cmtReg > 1440) {
				day = cmtReg / 1440;
				cm.setCmtRegDt(Integer.toString(day) + "일 전");
			}
			else if (cmtReg > 10080) {
				week = cmtReg / 10080;
				cm.setCmtRegDt(Integer.toString(week) + "주 전");
			}
			else if (cmtReg > 40320) {
				month = cmtReg / 40320;
				cm.setCmtRegDt(Integer.toString(month) + "달 전");
			}
			else if (cmtReg > 525600 ) {
				year = cmtReg / 525600;
				cm.setCmtRegDt(Integer.toString(year) + "년 전");
			}
			comList.add(cm);
		}
		return comList;
	}
}
