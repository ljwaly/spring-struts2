package com.ljw.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.objectweb.asm.commons.GeneratorAdapter;

import com.opensymphony.xwork2.ActionSupport;

public class LijinwuAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6796541699418154748L;

	public String doAction() {
		System.out.println("doMyAction");
		String strResponse = "";
		try {

			ServletInputStream inputStream = getRequest().getInputStream();
			String strMessage = "";
			
			BufferedReader reader;
			reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			while ((strMessage = reader.readLine()) != null) {
				strResponse += strMessage;
			}
			reader.close();
			inputStream.close();
			System.out.println("111111111111111111"+strResponse);
		} catch (Exception e) {
			System.out.println("222222222222222222"+e.getMessage());
		}

		

		return SUCCESS;

	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
}
