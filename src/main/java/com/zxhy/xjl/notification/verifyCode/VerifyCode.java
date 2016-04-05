package com.zxhy.xjl.notification.verifyCode;

/**
 * 验证码接口
 *
 */
public interface VerifyCode {
	/**
	 * 生成一个验证码，超过持续时长或者验证成功将自动移除验证码
	 * @param id 验证码的唯一标示
	 * @param duration 持续时长，单位是分钟
	 * @return 验证码
	 */
    public String  generate(String id, int duration);
    /**
     * 验证验证码是否正确，如果找不到对应的id也将false
     * @param id 验证码的唯一标示
     * @param verifyCode 验证码
     * @return
     */
    public boolean check(String id, String verifyCode);
}
