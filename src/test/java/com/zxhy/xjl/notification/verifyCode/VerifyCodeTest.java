package com.zxhy.xjl.notification.verifyCode;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ApplicationContext-verifyCode.xml"})
public class VerifyCodeTest {
	@Autowired
	VerifyCode verifyCode;
	@Test
	public void generate() {
		String code = this.verifyCode.generate("138", 1);
		Assert.assertTrue(Integer.parseInt(code) >= 1000);
		Assert.assertTrue(Integer.parseInt(code) < 10000);
	}
	@Test
	public void checkCode() {
		String code = this.verifyCode.generate("138", 1);
		Assert.assertTrue(this.verifyCode.check("138", code));
		Assert.assertFalse(this.verifyCode.check("138", code));
	}
}
