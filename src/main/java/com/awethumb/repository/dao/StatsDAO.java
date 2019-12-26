package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.CategoryAndProjectCnt;
import com.awethumb.repository.vo.DailyLog;
import com.awethumb.repository.vo.Stats;
import com.awethumb.repository.vo.VisitCount;

public interface StatsDAO {
	public int insertVisitCount(VisitCount vc);
	public int totalVisitToday();
	public int totalPostToday();
	public int totalUserToday();
	public Integer totalViewCntToday();
	public int dailyVisitUpdate();
	public int dailyJoinUpdate();
	public int dailyViewUpdate();
	public int dailyPostUpdate();
	public List<DailyLog> selectDailyLogOneMonth();
	public void insertSearchLog(String keyword);
	public List<CategoryAndProjectCnt> selectCategoryProjectCount();
	
//	Stats를 이용한 랭크 관련
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
	
// 1년치 통계를 위한 DailyLog 리스트
	public List<DailyLog> selectOneYearStats();
	
}
