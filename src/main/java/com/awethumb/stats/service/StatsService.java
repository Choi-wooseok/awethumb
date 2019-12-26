package com.awethumb.stats.service;

import java.util.List;
import java.util.Map;

import com.awethumb.repository.vo.DailyLog;
import com.awethumb.repository.vo.Stats;
import com.awethumb.repository.vo.VisitCount;

public interface StatsService {
	public void insertVisitCount(VisitCount vc);
	public int totalVisitToday();
	public int totalPostToday();
	public int totalUserToday();
	public int totalViewCntToday();
	public int dailyVisitUpdate();
	public int dailyJoinUpdate();
	public int dailyViewUpdate();
	public int dailyPostUpdate();
	public Map<String, List<?>> selectDailyLogOneMonth();
	public void insertSearchLog(String keyword);
	public Map<String, List<?>> selectCategoryProjectCount();
	
	public List<Stats> selectPostDailyRankByViewCnt();
	public List<Stats> selectPostDailyRankByLikeCnt();
	public List<Stats> selectPostMonthlyRankByViewCnt();
	public List<Stats> selectPostMonthlyRankByLikeCnt();
	public List<Stats> selectPostTotalRankByViewCnt();
	public List<Stats> selectPostTotalRankByLikeCnt();
	
	public List<Stats> selectUserRankBySubsribeCnt();
	public List<Stats> selectUserRankByTotalViewCnt();
	public List<Stats> selectUserRankByTotalLikeCnt();
	public List<Stats> selectUserRankByTotalPostCnt();
	
	public Map<String, List<?>> selectOneYearStats();
}
