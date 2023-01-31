package com.test.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class TestBatch {
	
	//https://96-brain.tistory.com/82
	@Scheduled(cron="* * * * * *")	//초(0-60) 분(0-60) 시(0-24) 일 월(1-12) 요일(0-7)
	public void schedulingFunc1()
	{
//		System.out.println("[Batch-Start]======================");
//		System.out.println("[Batch]TEST======================");
//		System.out.println("[Batch-End]======================");
	}
}
