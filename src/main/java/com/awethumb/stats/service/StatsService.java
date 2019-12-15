package com.awethumb.stats.service;

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
}
