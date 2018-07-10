package com.ljw.action;

import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

public class StreamAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8205661567650096468L;
	
	private String tempList;
	
	
	public InputStream streamIO(){
		
		System.out.println("111111");
		
		return null;
		
	}
	
	
	
	public String getTempList() {
		return tempList;
	}


	public void setTempList(String tempList) {
		this.tempList = tempList;
	}


	
	

}
