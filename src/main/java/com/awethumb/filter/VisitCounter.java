package com.awethumb.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.awethumb.repository.dao.StatsDAO;
import com.awethumb.repository.vo.VisitCount;

public class VisitCounter implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
        //등록되어있는 빈을 사용할수 있도록 설정해준다
        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        //request를 파라미터에 넣지 않고도 사용할수 있도록 설정
        StatsDAO statsDAO = (StatsDAO)wac.getBean("statsDAO");
        VisitCount vc = new VisitCount();
        vc.setVisit_ip(req.getRemoteAddr());
        vc.setVisit_agent(req.getHeader("User-Agent"));//브라우저 정보
        vc.setVisit_refer(req.getHeader("referer"));//접속 전 사이트 정보
        statsDAO.insertVisitCount(vc);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	}
	
}