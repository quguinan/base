/**
** create by code gen .
**/
package com.cms.model.sys;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.math.BigDecimal;

import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Id;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.mapping.MappingInfo;
import my.dao.mapping.MappingInfoHolder;
import my.util.MD5Util;
import my.web.IUser;
import my.web.RequestContext;
import my.base.BasePO;



@Table("SYS_USER")
@View("SYS_USER_V")
@PK({ "USERID" })
public class SysUser extends BasePO {

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SysUser INSTANCE = new SysUser();
	
	@Name
	@Column(value = "userid", type = ColumnType.NUMBER)
	private String userid ;


	@Column(value = "realname", type = ColumnType.STRING)
	private String realname ;
	
	@Column(value = "username", type = ColumnType.STRING)
	private String username ;
	
	@Column(value = "password", type = ColumnType.STRING)
	private String password ;
	
	@Column(value = "roleid", type = ColumnType.NUMBER)
	private Integer roleid ;
	
	@ReadOnly
	@Column(value = "rolename", type = ColumnType.STRING)
	private String rolename ;
	


	public String getUserid (){
		return userid;
	}
		 
	public void setUserid (String userid){
		this.userid=userid;
	}

	public String getRealname (){
		return realname;
	}
		 
	public void setRealname (String realname){
		this.realname=realname;
	}

	public String getUsername (){
		return username;
	}
		 
	public void setUsername (String username){
		this.username=username;
	}

	public String getPassword (){
		return password;
	}
		 
	public void setPassword (String password){
		this.password=password;
	}

	public Integer getRoleid (){
		return roleid;
	}
		 
	public void setRoleid (Integer roleid){
		this.roleid=roleid;
	}

	public String getRolename (){
		return rolename;
	}
		 
	public void setRolename (String rolename){
		this.rolename=rolename;
	}


}
