package com.awethumb.alarm.service;

import java.util.List;

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

	@Override
	public int selectUserNoByBoardNo(int boardNo) {
		return dao.selectUserNoByBoardNo(boardNo);
	}

	@Override
	public int selectUserNoByCommentNo(int commentNo) {
		return dao.selectUserNoByCommentNo(commentNo);
	}

	@Override
	public List<Integer> selectUserNoListByProjectNo(int projectNo) {
		return dao.selectUserNoListByProjectNo(projectNo);
	}

	@Override
	public List<Alarm> selectAlarmList(Alarm alarm) {
		return dao.selectAlarmList(alarm);
	}

	@Override
	public void updateAlarm(int userNo) {
		dao.updateAlarm(userNo);
	}

	@Override
	public void deleteAlarm(int alarmNo) {
		dao.deleteAlarm(alarmNo);
	}

	@Override
	public Alarm selectLatestAlarm() {
		return dao.selectLatestAlarm();
	}
}
