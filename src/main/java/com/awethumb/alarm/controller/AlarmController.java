package com.awethumb.alarm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awethumb.alarm.service.AlarmService;
import com.awethumb.repository.vo.Alarm;
import com.awethumb.repository.vo.UserVO;

@RequestMapping("/alarm")
@Controller
public class AlarmController {
	
	@Autowired
	private AlarmService service;
	
	@GetMapping("/alarmcount.do")
	@ResponseBody
	public int AlarmCount(int userNo) {
		return service.selectAlarmCnt(userNo);
	}
	@GetMapping("/getalarmlist.do")
	@ResponseBody
	public List<Alarm> getAlarmList(Alarm alarm) {
		return service.selectAlarmList(alarm);
	}

	@PutMapping("/readalarm")
	@ResponseBody
	public void readAlarm(@RequestBody UserVO user) {
		service.updateAlarm(user.getUserNo());
	}
	@DeleteMapping("/deletealarm/{alarmNo}")
	@ResponseBody
	public void deleteAlarm(@PathVariable int alarmNo) {
		service.deleteAlarm(alarmNo);
	}
	@GetMapping("/getlatestalarm.do")
	@ResponseBody
	public Alarm getLatestAlarm() {
		return service.selectLatestAlarm();
	}
	
	@GetMapping("/viewall")
	public void viewAll() {}
}
