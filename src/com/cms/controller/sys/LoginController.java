package com.cms.controller.sys;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.annotation.LicenseAnnotation;
import com.cms.model.sys.SysUser;

import my.util.MD5Util;
import my.web.BaseController;


@Controller
public class LoginController  extends BaseController{
	@RequestMapping("/login")
	public String login(Model m){
		String msg = param("msg", "");
		m.addAttribute("msg", msg);
		return "cms/login";
	}
	
	@RequestMapping("/dologin")
	public String dologin(final Model map,HttpSession session) {
		return run(new StrCallBack() {
			@Override
			public String call() throws Exception {
				String username = param("username", "");
//				String password = param("password", "");
				String password = MD5Util.string2MD5(param("password", ""));

				SysUser user = SysUser.INSTANCE.queryOne("username=?", username);
				if (user == null) {
//					map.addAttribute("msg", i18n("LOGIN_USER_NOT_EXIST"));
					map.addAttribute("msg", i18n("用户名不正确!"));
					return "cms/login";
				} else if (!password.equals(user.getPassword())) {
//					map.addAttribute("msg", i18n("LOGIN_PASSWORD_ERROR"));
					map.addAttribute("msg", i18n("密码输入错误!"));
					return "cms/login";
				} else {
					//将登陆成功的用户名存入Cookie
					/*saveUserInCookie(user, true);*/
					
					//将登陆成功的用户名存入session
					session.setAttribute("user", user);
					//重定向到后台主页面,不是转发
					return "redirect:cms/home";
				}
			}
		}, map);
		
	}
	/*
	 * 退出登陆
	 */
	@RequestMapping("/cms/logout")
	public String logout(Model m,HttpSession session) {
		session.removeAttribute("user");
		return "cms/login";
	}
	
}
