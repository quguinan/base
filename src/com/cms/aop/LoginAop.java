package com.cms.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoginAop {

	public LoginAop() {
		super();
	}
	@Pointcut("execution(* *.dologin())")
	public void loginPoint(){}
	
	@Before("loginPoint()")
	public void beforeLogin(){
		System.out.println("before login");
	}
	
	@After("loginPoint()")
	public void afterLogin(){
		System.out.println("after login");
	}
	
	
}
