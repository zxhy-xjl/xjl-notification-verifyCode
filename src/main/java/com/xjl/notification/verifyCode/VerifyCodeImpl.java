package com.xjl.notification.verifyCode;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
@Service
public class VerifyCodeImpl implements VerifyCode {
	private static Map<String, Calendar> verifyCodeDuration = new HashMap<String, Calendar>();
	private static Thread thread = null;
	public VerifyCodeImpl() {
		createThread();
	}

	public String generate(String id, int duration) {
		String code =String.valueOf( (int)(Math.random()*9000+1000));
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, duration);
		verifyCodeDuration.put(id+"_" + code, calendar);
		return code;
	}

	public boolean check(String id, String verifyCode) {
		return verifyCodeDuration.remove(id + "_" + verifyCode) != null;
	}
	private static  void createThread(){
		if (thread != null){
			return;
		}
		thread = new Thread(){
			public void run() {
				//一直在后台跑
				while(true){
					if (!VerifyCodeImpl.verifyCodeDuration.isEmpty()){
						Calendar currentCalendar = Calendar.getInstance();
						Set<String> keys = VerifyCodeImpl.verifyCodeDuration.keySet();
						for (String key : keys) {
							Calendar calendar = VerifyCodeImpl.verifyCodeDuration.get(key);
							if (currentCalendar.after(calendar)){
								VerifyCodeImpl.verifyCodeDuration.remove(key);
							}
						}
					}
					try {
						//休息5秒继续检查
						sleep(1000*5);
					} catch (InterruptedException e) {
						
					}
				}
			};
		};
		thread.start();
	}

}
