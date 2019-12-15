package com.awethumb.repository.dao;

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
	
}
