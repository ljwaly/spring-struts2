package com.ljw.action;

import com.opensymphony.xwork2.Action;

public class HelloAction implements Action {

	private String type;

	public String execute() throws Exception {
		System.out.println("hello");
		if (type == null) {
			type = "-1";
		}
		switch (type) {
			
		case "0":
			return SUCCESS;
			
		case "1":
			return ERROR;

		default:
			break;
		}
		return "woqu";
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
