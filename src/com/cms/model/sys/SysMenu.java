/**
** create by code gen .
**/
package com.cms.model.sys;

import java.util.Date;
import java.math.BigDecimal;

import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Id;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.mapping.MappingInfo;
import my.dao.mapping.MappingInfoHolder;
import my.base.BasePO;



@Table("SYS_MENU")
@View("SYS_MENU_V")
@PK({ "ID" })
public class SysMenu extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SysMenu INSTANCE = new SysMenu();
	
	//@Name   此注解自动找序列
	@Column(value = "id", type = ColumnType.STRING)
	private String id ;
	
	@Column(value = "pid", type = ColumnType.STRING)
	private String pid ;
	
	@Column(value = "text", type = ColumnType.STRING)
	private String text ;
	
	@Column(value = "url", type = ColumnType.STRING)
	private String url ;

	@Column(value = "iconcls", type = ColumnType.STRING)
	private String iconcls ;


	public String getId (){
		return id;
	}
		 
	public void setId (String id){
		this.id=id;
	}

	public String getPid (){
		return pid;
	}
		 
	public void setPid (String pid){
		this.pid=pid;
	}

	public String getText (){
		return text;
	}
		 
	public void setText (String text){
		this.text=text;
	}

	public String getUrl (){
		return url;
	}
		 
	public void setUrl (String url){
		this.url=url;
	}

	public String getIconcls() {
		return iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	

	
	

}
