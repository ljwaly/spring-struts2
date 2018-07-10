package com.ljw.action.sdk;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.ljw.service.LoginService;
import com.ljw.util.AppContextUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{

	
	
	public static final Logger log = Logger.getLogger(BaseAction.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8086988988452759380L;

	
	public Map<String, Object> resultMap = new HashMap<String, Object>();
	
	
	
	@SuppressWarnings("unchecked")
	public String postAndJson(){
		
		log.info("baseTest start ...");
		try {
			
			StringBuilder strResponse = new StringBuilder("");
			ServletInputStream inputStream = getRequest().getInputStream();
			String strMessage = "";
			
			//使用io流读取请求的json参数体jsonBody
			BufferedReader reader;
			reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			while ((strMessage = reader.readLine()) != null) {
				strResponse.append(strMessage);
			}
			reader.close();
			inputStream.close();
			
			
			log.info("strResponse:"+strResponse.toString());
			
			
			
			//JSON解析成map
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap = JSON.parseObject(strResponse.toString(), Map.class);
			
			//TODO:可以做一些对参数的处理或者接口的调用
			LoginService loginService = (LoginService) AppContextUtil.getApplicationContext().getBean("loginService");
			String name = (String) paramMap.get("name");
			String accontLevel = loginService.getAccontLevel(name);
			if (accontLevel==null || "".equals(accontLevel)) {
				throw new Exception("无登录客户，登录失败");
			}
			resultMap.put("result", "欢迎"+accontLevel+"登录");
			resultMap.putAll(paramMap);
			resultMap.put("resultCode", "success");
			resultMap.put("resultDesc", "成功");
			
		} catch (Exception e) {
			resultMap.put("Exception:", e.getMessage());
			resultMap.put("resultCode", "failure");
			resultMap.put("resultDesc", "失败");
		}
		
		log.info("baseTest over!");
		return SUCCESS;
		
	}
	
	
	
	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
}
