package com.zxhy.xjl.notification.verifyCode;

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
			};
		};
		thread.start();
	}

}
