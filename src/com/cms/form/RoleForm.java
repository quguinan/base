package com.cms.form;

import com.cms.model.sys.SysRole;

public class RoleForm {
	private Integer roleid;
	private String rolename;
	
	
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public SysRole getEntityUpdate(){
		SysRole role=new SysRole(roleid, rolename);
		return role;
	}
	public SysRole getEntityAdd(){
		SysRole role=new SysRole(rolename);
		return role;
	}
}
