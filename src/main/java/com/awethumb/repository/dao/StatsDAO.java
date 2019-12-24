package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.CategoryAndProjectCnt;
import com.awethumb.repository.vo.DailyLog;
import com.awethumb.repository.vo.VisitCount;

public interface StatsDAO {
	public int insertVisitCount(VisitCount vc);
	public int totalVisitToday();
	public int totalPostToday();
	public int totalUserToday();
	public int dailyVisitUpdate();
	public int dailyJoinUpdate();
	public int dailyViewUpdate();
	public int dailyPostUpdate();
	public List<DailyLog> selectDailyLogOneMonth();
	public void insertSearchLog(String keyword);
	public List<CategoryAndProjectCnt> selectCategoryProjectCount();
	
}
