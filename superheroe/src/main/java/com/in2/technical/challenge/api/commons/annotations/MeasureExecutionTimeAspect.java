package com.in2.technical.challenge.api.commons.annotations;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MeasureExecutionTimeAspect {

	private static final Logger logger = LoggerFactory.getLogger(MeasureExecutionTimeAspect.class);
	
	@Around("@annotation(com.in2.technical.challenge.api.commons.annotations.MeasureExecutionTime)")
	public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object proceed = joinPoint.proceed();

		stopWatch.stop();

		logger.info("Operation [{}] was executed in  [{}] ms", joinPoint.getSignature().getName(),stopWatch.getTime());
		
		return proceed;
	}
}
