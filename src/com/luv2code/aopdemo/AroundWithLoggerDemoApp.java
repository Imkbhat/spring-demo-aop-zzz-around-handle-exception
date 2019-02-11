package com.luv2code.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.service.TrafficFortuneService;

public class AroundWithLoggerDemoApp {

	private static Logger log  = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());
	
	public static void main(String[] args) {
		
		//Read spring configuration file
		AnnotationConfigApplicationContext  ctxt = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get bean
		TrafficFortuneService fortuneService = ctxt.getBean("trafficFortuneService", 
					TrafficFortuneService.class);
		
		String data = fortuneService.getFortune();
		
		log.info("><<<<<<<<<<>>>>>>>>>>>><<<<<<<>>>>>>>>"+ data);
		
		//close  context-
		ctxt.close();
	}

}
