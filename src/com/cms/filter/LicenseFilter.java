package com.cms.filter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.cms.util.PropertyUtilLisence;

import my.util.ComputerUtil;
import my.util.DesUtil;


public class LicenseFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sysdate=sdf.format(date);
		String lic="";
		lic=PropertyUtilLisence.readFile();
		//解密
        String c=DesUtil.decrypt(lic, "20110207");
        //检验信息
        List<String> info=new ArrayList<>();
        for (String s : c.split("&")) {
        	info.add(s.substring(1894, s.length()));
		}
        
        //将日期转成Date对象作比较
        int result=0;
		try {
			Date fomatDate1 = sdf.parse(sysdate);
			Date fomatDate2 = sdf.parse(info.get(3));
			//比较两个日期
	        result=fomatDate2.compareTo(fomatDate1);//result==0"两个时间相等" 小于0，参数date1就是在date2之后     大于0，参数date1就是在date2之前
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //计算机信息
        InetAddress ia = InetAddress.getLocalHost();//计算机名+ip
		String localname=ia.getHostName();  //计算机名
		String localip=ia.getHostAddress();  //id
		String localmac=ComputerUtil.getLocalMac(ia);
		
		String msg="";
		boolean tf=true;
		if(!localip.equals(info.get(0))){
			tf=false;
			msg="服务器<IP地址>没有通过认证!";
        }else if(!localmac.equals(info.get(1))){
			msg="服务器<名称>没有通过认证!";
			tf=false;
        }else if(!localname.equals(info.get(2))){
			msg="服务器<mac地址>没有通过认证!";
			tf=false;
        }else if(result<0){
			msg="使用期限已到,请购买正版!";
			tf=false;
        }
        
		if(tf){
			chain.doFilter(httpRequest, httpResponse);
		}
		else{
			//如果没有登录就访问后台,则将请求重定向到登录表单
			//获取项目在服务器上的上下文路径
			String path=httpRequest.getContextPath();
			msg=URLEncoder.encode(msg,"UTF-8");
			httpResponse.sendRedirect(path+"/login");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
