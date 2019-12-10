package com.awethumb.stats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.StatsDAO;
import com.awethumb.repository.vo.VisitCount;

@Service
public class StatsServiceImpl implements StatsService {
	
	@Autowired
	private StatsDAO dao;
	
	@Override
	public void insertVisitCount(VisitCount vc) {
		dao.insertVisitCount(vc);
	}
	
}
