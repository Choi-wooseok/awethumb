package com.awethumb.stats.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.awethumb.stats.service.StatsService;

@Component
@Aspect
public class insertSearchLogAspect {
	
	@Autowired
	private StatsService service;
	
	@After("@annotation(insertSearchLog) && args(searchWord)")
	public void insertSearchLog(JoinPoint joinPoint, String searchWord) throws Throwable {
		System.out.println("서치의 에프터로 진입");
		String targetMethod = joinPoint.getSignature().getName();
		service.insertSearchLog(searchWord);
	}
}
