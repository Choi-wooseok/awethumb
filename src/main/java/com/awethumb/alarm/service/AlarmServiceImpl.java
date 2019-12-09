package com.awethumb.alarm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.AlarmDAO;
import com.awethumb.repository.vo.Alarm;

@Service
public class AlarmServiceImpl implements AlarmService{
	@Autowired
	private AlarmDAO dao;

	public void insertAlarm(Alarm alarm) {
		dao.insertAlarm(alarm);
	}

	@Override
	public int selectAlarmCnt(int userNo) {
		return dao.selectAlarmCnt(userNo);
	}
}
