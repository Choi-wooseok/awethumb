package com.awethumb.stats.service;

import java.util.List;
import java.util.Map;

import com.awethumb.repository.vo.CategoryAndProjectCnt;
import com.awethumb.repository.vo.VisitCount;

public interface StatsService {
	public void insertVisitCount(VisitCount vc);
	public int totalVisitToday();
	public int totalPostToday();
	public int totalUserToday();
	public int dailyVisitUpdate();
	public int dailyJoinUpdate();
	public int dailyViewUpdate();
	public int dailyPostUpdate();
	public Map<String, List<?>> selectDailyLogOneMonth();
	public void insertSearchLog(String keyword);
	public Map<String, List<?>> selectCategoryProjectCount();
}
