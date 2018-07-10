package com.ljw.action.sdk;

import java.util.Map;

import com.ljw.service.TestInterface;
import com.ljw.service.TestInterface2;
import com.ljw.util.AppContextUtil;



public class SleepAction extends BaseAction{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8106255787697589371L;

	
	//http://localhost:8080/spring-struts2/test/sleep
	public String testSleep(){
		
		
		TestInterface testInterface = (TestInterface) AppContextUtil.getApplicationContext().getBean("testInterface");
		
		Map<String, Object> map = testInterface.getResultMap();
		resultMap.putAll(map);
		
		
		return SUCCESS;
	}
	
	
	
	public String testSleep1(){
		
		
		TestInterface2 testInterface = (TestInterface2) AppContextUtil.getApplicationContext().getBean("testInterface2");
		
		Map<String, Object> map = testInterface.getResultMap();
		resultMap.putAll(map);
		
		
		return SUCCESS;
	}
}
