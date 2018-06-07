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
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.mapping.MappingInfo;
import my.dao.mapping.MappingInfoHolder;
import my.base.BasePO;



@Table("SYS_ROLE_MENU")
@View("SYS_ROLE_MENU_V")
@PK({ "MENUID","ROLEID" })
public class SysRoleMenu extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SysRoleMenu INSTANCE = new SysRoleMenu();
	
	@Column(value = "roleid", type = ColumnType.NUMBER)
	private Integer roleid ;
	
	@Column(value = "menuid", type = ColumnType.STRING)
	private String menuid ;
	
	@Column(value = "enabled", type = ColumnType.NUMBER)
	private Integer enabled ;
	
	@ReadOnly
	@Column(value = "pid", type = ColumnType.STRING)
	private String pid ;
	
	@ReadOnly
	@Column(value = "text", type = ColumnType.STRING)
	private String text ;
	
	@ReadOnly
	@Column(value = "url", type = ColumnType.STRING)
	private String url ;
	
	@ReadOnly
	@Column(value = "iconcls", type = ColumnType.STRING)
	private String iconcls ;

	public Integer getRoleid (){
		return roleid;
	}
		 
	public void setRoleid (Integer roleid){
		this.roleid=roleid;
	}

	public String getMenuid (){
		return menuid;
	}
		 
	public void setMenuid (String menuid){
		this.menuid=menuid;
	}

	public Integer getEnabled (){
		return enabled;
	}
		 
	public void setEnabled (Integer enabled){
		this.enabled=enabled;
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
