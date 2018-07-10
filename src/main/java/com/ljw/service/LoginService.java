package com.ljw.service;

import java.util.HashMap;
import java.util.Map;




public class LoginService {
	
	public static final Map<String, String> userMap = new HashMap<String, String>();
	
	
	static {
		userMap.put("1", "admin");
		userMap.put("2", "user");

	}
	
	public String getAccontLevel(String key){
		return userMap.get(key);
		
	}
	
}
