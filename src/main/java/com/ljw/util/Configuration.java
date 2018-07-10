package com.ljw.util;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * 严格意义上讲：在服务启动的时候，进行初始化，比较方便，减轻服务的压力
 * @author ljw
 *
 */
public class Configuration {
	
	public static Logger log=Logger.getLogger(Configuration.class);
	public static final String path = "/config.properties";
	
	private Properties properties;
	
	
	
	/**
	 * 获取配置文件的信息key对应的值
	 * @param key
	 * @return
	 */
	public String getProperties(String key) {
		return (String)properties.get(key);
	}
	
	private Configuration(String configFile){
		
		InputStream is = this.getClass().getResourceAsStream(configFile);
		properties = new Properties();
		try {
			properties.load(is);
			is.close();
			log.info("file: "+configFile+" -->"+"install successed!");
		} catch (Exception e) {
			log.info("no file: "+configFile+" -->"+e.getMessage());
			log.error("no file: "+configFile+" -->"+e.getMessage());
		}
		
	}
	
	
	public static Configuration c = null;
	public static Configuration getInstance(){
		if (c==null) {
			c= new Configuration(path);
		}
		return c;
		
	}
	
}
