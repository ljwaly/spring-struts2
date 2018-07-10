package com.ljw.job;


/**
 * 配置文件配置方法每5s执行一次，
 * 方便内部10s执行完毕，
 * 因为设置concurrent属性：false
 * 			等上一次方法执行完毕，下一次开始执行
 * 
 * @author PC
 *
 */
public class LjwJob {
	
	private String nameKey;

	public void executeMyJob(){
		long ms = System.currentTimeMillis();
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(nameKey+":"+ms);
		
	}

	public String getNameKey() {
		return nameKey;
	}

	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}
	
}
