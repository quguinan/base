package com.cms.form;

import java.util.List;

import com.cms.model.sys.SysUserMenuRoleV;

import net.sf.json.JSONArray;


public class SysMenuForm {
	private String pid;
	private String ptext;
	private List<SysUserMenuRoleV> mlist;
	private JSONArray jsonArray;
	private String icon;
	
	public JSONArray getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}
	
	public SysMenuForm(String pid, String ptext, List<SysUserMenuRoleV> mlist,
			JSONArray jsonArray) {
		super();
		this.pid = pid;
		this.ptext = ptext;
		this.mlist = mlist;
		this.jsonArray = jsonArray;
	}
	
	public SysMenuForm(String pid, String ptext, List<SysUserMenuRoleV> mlist,String icon) {
		super();
		this.pid = pid;
		this.ptext = ptext;
		this.mlist = mlist;
		this.icon = icon;
	}
	public SysMenuForm(String pid, List<SysUserMenuRoleV> mlist) {
		super();
		this.pid = pid;
		this.mlist = mlist;
	}
	public SysMenuForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public List<SysUserMenuRoleV> getMlist() {
		return mlist;
	}
	public void setMlist(List<SysUserMenuRoleV> mlist) {
		this.mlist = mlist;
	}
	public String getPtext() {
		return ptext;
	}
	public void setPtext(String ptext) {
		this.ptext = ptext;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
