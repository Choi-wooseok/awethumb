package com.awethumb.alarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awethumb.alarm.service.AlarmService;

@RequestMapping("/alarm")
@Controller
public class AlarmController {
	
	@Autowired
	private AlarmService service;
	
	@RequestMapping("/alarmcount.do")
	@ResponseBody
	public int AlarmCount(int userNo) {
		System.out.println("userNo :" + userNo);
		System.out.println("cnt : " + service.selectAlarmCnt(userNo));
		return service.selectAlarmCnt(userNo);
	}
}
