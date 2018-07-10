package com.ljw.service;

import java.util.HashMap;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.ljw.http.HttpOtherUtil;
import com.ljw.util.AppContextUtil;



/**
 * 
 * 
 * 如果有远程接口，可以使用这个进行解析测试
 * 
 * @author PC
 *
 */

public class TestInterface{
	
	
	
	public static void main(String[] args) {
		TestInterface t1 = new TestInterface();

		t1.getResultMap();
		
	}
	
	
	public Map<String, Object> getResultMap(){
		
		
		
//--------------------------------请求url---------------------------------------------------------------			
		
		//url
		//String url= "http://localhost:17543/sleep/test1";
		String url= "http://localhost:17543/sleep/test";
		
		
//--------------------------------请求头信息---------------------------------------------------------------			
		//header
		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("abc", "456");
		//requestHeaders.put("accept-encoding", "gzip, deflate, sdch, br");
	
		
//--------------------------------请求参数---------------------------------------------------------------			
		//参数
		Map<String, Object> paramValue = new HashMap<String, Object>();
		paramValue.put("uname", "qinbo@migu.cn");
		paramValue.put("passwd", "testtest89");
		paramValue.put("userId", "ljw11");
		
		
//		paramValue.put("clientIP", "127.0.0.1");
//		paramValue.put("userId", "aaaaaa");
//		
//		Map<String, String> reMap = new HashMap<String, String>();
//		reMap.put("realtimePlay", "1");
//		reMap.put("download", "1");
//		paramValue.put("userStatus", reMap);
//		
//		paramValue.put("netType", "1");
//		paramValue.put("ts", "1400000000");

		
		//发送Content-Type=application/json情况下的post请求
		String jsonBody = JSON.toJSONString(paramValue);
		
//---------------------------------------发送请求---------------------------------------------------------------		
		
		long t1 = System.currentTimeMillis();
		HttpOtherUtil httpOtherUtil = (HttpOtherUtil) AppContextUtil.getApplicationContext().getBean("httpOtherUtil");

		String rtnJson = httpOtherUtil.httpGet(url, null);
//		String rtnJson = httpOtherUtil.httpJsonPost(url, requestHeaders, jsonBody);
//		String rtnJson = httpOtherUtil.httpNameValuePairPost(url, requestHeaders, paramValue);
		
		long t2 = System.currentTimeMillis();
		
//---------------------------------------请求结果-------------------------------------------
//		
//		System.out.println("rtnJsonOrg:"+rtnJson);
//		System.out.println("cost:"+(t2-t1));

		
		
		Map<String, Object> resultMap = null;
		try {
			
			resultMap = JSON.parseObject(rtnJson, Map.class);
			resultMap.put("cost", (t2-t1));
			return resultMap;
			
//			System.out.println("resultMap:" + resultMap);
//			String result = resultMap.get("result").toString();
//			MiguATokenResult parseObject = JSON.parseObject(result, MiguATokenResult.class);
//			System.out.println(parseObject);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			resultMap = new HashMap<String, Object>();
			resultMap.put("cost", (t2-t1));
		}
		
		return resultMap;
	}

	
	
}
