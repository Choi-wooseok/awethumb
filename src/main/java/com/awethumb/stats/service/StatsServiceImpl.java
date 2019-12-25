package com.awethumb.stats.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.StatsDAO;
import com.awethumb.repository.vo.CategoryAndProjectCnt;
import com.awethumb.repository.vo.DailyLog;
import com.awethumb.repository.vo.Stats;
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
	public int totalViewCntToday() {
		Integer result = dao.totalViewCntToday();
		return (result == null) ? 0 : result;
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

	@Override
	public void insertSearchLog(String keyword) {
		dao.insertSearchLog(keyword);
	}

	@Override
	public Map<String, List<?>> selectCategoryProjectCount() {
		//여기서 원하는게 뭐야? 18개짜리 제목과, 18개짜리 숫자를 각각 넘겨줘서 뽑기 편하게 해주는거지.
		// 그럼 여기서 리턴타입이 달라진다. Map<String, List<?>>로
		Map<String, List<?>> cnpMap = new HashMap<String, List<?>>();
		List<CategoryAndProjectCnt> list = dao.selectCategoryProjectCount();
		List<String> cnpTitleList = new ArrayList<String>();
		List<Integer> cnpCountList = new ArrayList<Integer>();
		for(CategoryAndProjectCnt cnp : list) {
			cnpTitleList.add("'" + cnp.getCategoryTitle() + "'");
			cnpCountList.add(cnp.getProjectCnt());
		}
		cnpMap.put("cnpTitleList", cnpTitleList);
		cnpMap.put("cnpCountList", cnpCountList);
		return cnpMap;
		
	}
//	랭킹 통계관련 10개의 메소드 시작
	
	@Override
	public List<Stats> selectPostDailyRankByViewCnt() {
		return dao.selectPostDailyRankByViewCnt();
	}

	@Override
	public List<Stats> selectPostDailyRankByLikeCnt() {
		return dao.selectPostDailyRankByLikeCnt();
	}

	@Override
	public List<Stats> selectPostMonthlyRankByViewCnt() {
		return dao.selectPostMonthlyRankByViewCnt();
	}

	@Override
	public List<Stats> selectPostMonthlyRankByLikeCnt() {
		return dao.selectPostMonthlyRankByLikeCnt();
	}

	@Override
	public List<Stats> selectPostTotalRankByViewCnt() {
		return dao.selectPostTotalRankByViewCnt();
	}

	@Override
	public List<Stats> selectPostTotalRankByLikeCnt() {
		return dao.selectPostTotalRankByLikeCnt();
	}

	@Override
	public List<Stats> selectUserRankBySubsribeCnt() {
		return dao.selectUserRankBySubsribeCnt();
	}

	@Override
	public List<Stats> selectUserRankByTotalViewCnt() {
		return dao.selectUserRankByTotalViewCnt();
	}

	@Override
	public List<Stats> selectUserRankByTotalLikeCnt() {
		return dao.selectUserRankByTotalLikeCnt();
	}

	@Override
	public List<Stats> selectUserRankByTotalPostCnt() {
		return dao.selectUserRankByTotalPostCnt();
	}
	
	
}
