package com.awethumb.repository.dao;

import com.awethumb.repository.vo.VisitCount;

public interface StatsDAO {
	public void insertVisitCount(VisitCount vc);
	public int totalVisitToday();
	public int totalPostToday();
}
