package com.awethumb.stats.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.StatsDAO;
import com.awethumb.repository.vo.DailyLog;
import com.awethumb.repository.vo.VisitCount;

@Service
public class StatsServiceImpl implements StatsService {
	
	@Autowired
	private StatsDAO dao;
	
	@Override
	public void insertVisitCount(VisitCount vc) {
		dao.insertVisitCount(vc);
	}

	@Override
	public int totalVisitToday() {
		return dao.totalVisitToday();
	}

	@Override
	public int totalPostToday() {
		return dao.totalPostToday();
	}
	@Override
	public int totalUserToday() {
		return dao.totalUserToday();
	}

	@Override
	public int dailyVisitUpdate() {
		return dao.dailyVisitUpdate();
	}

	@Override
	public int dailyJoinUpdate() {
		return dao.dailyJoinUpdate();
	}

	@Override
	public int dailyViewUpdate() {
		return dao.dailyViewUpdate();
	}

	@Override
	public int dailyPostUpdate() {
		return dao.dailyPostUpdate();
	}

	@Override
	public Map<String, List<?>> selectDailyLogOneMonth() {
		//임플에서 가공을 해서 넘겨주도록 해보자.
		List<DailyLog> list = dao.selectDailyLogOneMonth();
		
		//원하는게 뭐야? 30개짜리 리스트 5개를 하나의 객체에 담아 넘기는 것.
		
		//날짜면 날짜, 방문자면 방문자 키값을 입력하면 리스트를 내뱉는 맵을 만들어서 넘긴다.
		
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		
		List<String> logDtList = new ArrayList<String>();
		List<Integer> visitCntList = new ArrayList<Integer>();
		List<Integer> joinCntList = new ArrayList<Integer>();
		List<Integer> viewCntList = new ArrayList<Integer>();
		List<Integer> postCntList = new ArrayList<Integer>();
		
		for(int i = 0; i < list.size(); i++) {
			logDtList.add("'" + list.get(i).getLogDt() + "'");
			visitCntList.add(list.get(i).getDailyVisitCnt());
			joinCntList.add(list.get(i).getDailyJoinCnt());
			viewCntList.add(list.get(i).getDailyViewCnt());
			postCntList.add(list.get(i).getDailyPostCnt());
		}
		
		map.put("logDtList", logDtList);
		map.put("visitCntList", visitCntList);
		map.put("joinCntList", joinCntList);
		map.put("viewCntList", viewCntList);
		map.put("postCntList", postCntList);
		
		return map;
	}
	
	
}
