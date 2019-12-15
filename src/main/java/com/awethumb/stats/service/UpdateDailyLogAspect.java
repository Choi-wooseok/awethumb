package com.awethumb.stats.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	public int updateDailyLog(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("에프터 진입테스트");
		int result = -1;
		
		Object proceed = joinPoint.proceed();
		System.out.println(proceed);
		String targetMethod = joinPoint.getSignature().getName();
		System.out.println("타겟메서드 : " + targetMethod);
		if(targetMethod.equals("insertVisitCount")) {
			System.out.println("타겟메서드가 인설트 비짓 카운트라는거 확인 완료");
			if(result != -1) {
				System.out.println("일일 방문자수 로그 업데이트 성공");
			}
			System.out.println("일일 방문자수 로그 업데이트 성공");
		}
		switch(targetMethod) {
			case "insertVisitCount" :
				System.out.println(proceed);
				result = service.dailyVisitUpdate();
				if(result != -1) {
					System.out.println("일일 방문자수 로그 업데이트 성공");
				}
				break;
			case "insertBoard" :
				System.out.println("인설트 보드 진입성공");
				result = service.dailyPostUpdate();
				if(result != -1) {
					System.out.println("일일 글작성수 로그 업데이트 성공");
				}
				break;
		}
		return result;
	}
}
