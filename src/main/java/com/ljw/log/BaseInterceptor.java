package com.ljw.log;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



public class BaseInterceptor {
	
	public static Logger systemAnalysisLogger = Logger.getLogger("systemAnalysisLogger");
	public static Logger systemErrorLogger = Logger.getLogger("systemErrorLogger");
	
	/**
	 * 调用方法名
	 * @param jp
	 * @return
	 */
	protected String printInvokedMethod(JoinPoint jp){
		if (jp == null) {
			return "";
		}
		return jp.getSignature().getName();
	} 
	
	/**
	 * 接口参数列表
	 * @param jp
	 * @return
	 */
	protected String printArgs(JoinPoint jp) {
		String argsStr = "";
		if (jp != null) {
			Object[] arguments = jp.getArgs();
			if (arguments != null) {
				for (Object arg : arguments) {
					if (arg == null) {
						argsStr += "$null";
					} else {
						argsStr += "$" + arg.toString();
					}
				}
			}
		}
		return argsStr;
	}
	
	/**
	 * 额外记录参数
	 * @return
	 */
	protected String printExtArgs() {
		if(RequestContextHolder.getRequestAttributes() != null) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
			String clientId = request.getParameter("clientId");
			String filterType = request.getParameter("filterType");
		
			String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
			return "$" + clientId + "$" + filterType+ "$"  + sessionId;
		} 
		
		return "";
		
	}
	
	/**
	 * 发送方
	 * @return
	 */
	protected String printSrcSystem() {
		if(RequestContextHolder.getRequestAttributes() != null) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
			return request.getContextPath().substring(1);
		}
		return "";
	}
	
	/**
	 * 格式化日志
	 * @param jp
	 * @param begin
	 * @param useTime
	 * @param resultCode
	 * @param destSystem
	 * @return
	 */
	protected String formatLogMsg(JoinPoint jp, long begin, long useTime, String resultCode, SystemId destSystem) {
		String logContent = String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s", printSrcSystem(), destSystem, getIp(), new Date(begin),
				useTime, printInvokedMethod(jp), printArgs(jp), printExtArgs(), resultCode);
		return logContent;
	}
	
	/**
	 * 格式化异常日志
	 * @param jp
	 * @param ex
	 * @param destSystem
	 * @return
	 */
	protected String formatErrLogMsg(JoinPoint jp, Throwable ex, SystemId destSystem) {
		String logContent = String.format("%s|%s|%s|%s|%s|%s|%s|%s", printSrcSystem(), destSystem, getIp(), new Date(),
				printInvokedMethod(jp), printArgs(jp), printExtArgs(), ex);
		return logContent;
	}
	
	protected String getIp(){
		String ipStr = "";
		Enumeration allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
				.nextElement();
				Enumeration addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address ) {
						if(!"127.0.0.1".equals(ip.getHostAddress()) && !"127.0.0.2".equals(ip.getHostAddress())){
							ipStr = ip.getHostAddress();
							break;
						}
					}
				}
			}
		} catch (SocketException e) {
		}
		return ipStr;
	}
}

enum SystemId {
	MIGUCLOUD, MARKET
}
