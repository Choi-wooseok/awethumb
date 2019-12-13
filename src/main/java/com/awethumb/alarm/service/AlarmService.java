package com.awethumb.alarm.service;

import java.util.List;

import com.awethumb.repository.vo.Alarm;

public interface AlarmService {

	void insertAlarm(Alarm alarm);

	int selectAlarmCnt(int userNo);

	int selectUserNoByBoardNo(int boardNo);

	int selectUserNoByCommentNo(int commentNo);

	List<Integer> selectUserNoListByProjectNo(int projectNo);

	List<Alarm> selectAlarmList(Alarm alarm);

	void updateAlarm(int userNo);

	void deleteAlarm(int alarmNo);

	Alarm selectLatestAlarm();

}
