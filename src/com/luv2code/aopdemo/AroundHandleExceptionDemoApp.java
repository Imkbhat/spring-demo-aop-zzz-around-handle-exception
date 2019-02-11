package com.luv2code.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.service.TrafficFortuneService;

public class AroundHandleExceptionDemoApp {

	private static Logger log  = Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());
	
	public static void main(String[] args) {
		
		//Read spring configuration file
		AnnotationConfigApplicationContext  ctxt = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get bean
		TrafficFortuneService fortuneService = ctxt.getBean("trafficFortuneService", 
					TrafficFortuneService.class);
		
		boolean tripWire = true;
		String data = fortuneService.getFortune(tripWire);
		
		log.info("><<<<<<<<<<>>>>>>>>>>>><<<<<<<>>>>>>>>"+ data);
		
		//close  context-
		ctxt.close();
	}

}
