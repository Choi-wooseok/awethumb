package com.awethumb.stats.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.awethumb.stats.service.StatsService;

@Component
@Aspect
public class UpdateDailyLogAspect{
	
	@Autowired
	private StatsService service;
	
//	@Pointcut("execution(* com.awethumb..*Impl.*(..))")
//    public void allPointcut() {}
//	
//	@Before("allPointcut()")
//    public void printLogging() {
//        System.out.println("[로그 - 올포인트컷 실행전 모션]");
//    }
//	@Pointcut("execution(* com.awethumb..*Impl.*(..) || com.awethumb..*DAO.*(..))")
//    public void logPointcut() {}
	
//	/awethumb/src/main/java/com/awethumb/stats/service/UpdateDailyLog.java
//	@annotation(org.springframework.transaction.annotation.Transactional)
//	com.awethumb.stats.service.UpdateDailyLog
	
//	@annotation(com.awethumb.stats.service.UpdateDailyLog)
//	execution(* com.awethumb..*Impl.*(..) || com.awethumb..*DAO.*(..))
//	@Around("@annotation(UpdateDailyLog)")
	@After("@annotation(UpdateDailyLog)")
	public int updateDailyLog(JoinPoint joinPoint) throws Throwable {
		int result = 0;
		String targetMethod = joinPoint.getSignature().getName();
		
		switch(targetMethod) {
			case "insertBoard" :
				result = service.dailyPostUpdate();
				if(result != 0) {
					System.out.println("일일 글작성수 로그 업데이트 성공");
				}
				break;
			case "registUser" :
				result = service.dailyJoinUpdate();
				if(result != 0) {
					System.out.println("일일 회원가입수 로그 업데이트 성공");
				}
				break;
			case "viewCount" :
				result = service.dailyViewUpdate();
				if(result != 0) {
					System.out.println("일일 조회수 로그 업데이트 성공");
				}
				break;
		}
		return result;
	}
}
