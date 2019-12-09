package com.awethumb.repository.dao;

import com.awethumb.repository.vo.Alarm;

public interface AlarmDAO {

	void insertAlarm(Alarm alarm);

	int selectAlarmCnt(int userNo);

}
