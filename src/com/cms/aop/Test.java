package com.cms.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	private static ApplicationContext ctx;
	public static void main(String[] args) {
		ctx=new ClassPathXmlApplicationContext("root-context.xml");
		LicenseAop lic=(LicenseAop)ctx.getBean("LicenseAopImpl");
		lic.dologin();
	}
}
