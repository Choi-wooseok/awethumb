package com.awethumb.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.awethumb.repository.dao.StatsDAO;
import com.awethumb.repository.vo.VisitCount;
import com.awethumb.stats.service.UpdateDailyLog;
@Component
public class VisitCounterImpl implements HttpSessionListener{
	
	@Override
	@UpdateDailyLog
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
        //등록되어있는 빈을 사용할수 있도록 설정해준다
        ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest req = sra.getRequest();
        		
        //request를 파라미터에 넣지 않고도 사용할수 있도록 설정
        StatsDAO statsDAO = (StatsDAO)wac.getBean("statsDAO");
        VisitCount vc = new VisitCount();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
        	ip = req.getRemoteAddr();
        }
        vc.setVisitIp(ip);
        vc.setVisitAgent(req.getHeader("User-Agent"));//브라우저 정보
        vc.setVisitRefer(req.getHeader("referer"));//접속 전 사이트 정보
        
        int result = statsDAO.insertVisitCount(vc);
        System.out.println(result);
        if(result != 0) {
        	int logResult = statsDAO.dailyVisitUpdate();
        	if(logResult != 0) {
        		System.out.println("일일 방문자수 업데이트 성공");
        	}
        }
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	}
	
}


