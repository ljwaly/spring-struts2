package com.ljw.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;



/**
 * 是一个配置类，
 * 严格来讲，不算工具类，在启动的时候必须进行初始化（创建bean）
 * @author ljw
 *
 */
public class AppContextUtil implements ApplicationContextAware{
	
	 
	
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		AppContextUtil.applicationContext=applicationContext;
		
	}
	
	
	/**
	 * 获取bean的ApplicationContext实例
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}


	

	
	
}
