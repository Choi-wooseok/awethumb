package com.awethumb.alarm.service;

import com.awethumb.repository.vo.Alarm;

public interface AlarmService {

	void insertAlarm(Alarm alarm);

	int selectAlarmCnt(int userNo);
	
}
