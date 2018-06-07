package com.cms.bean;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class GridSaveModel {
	private String inserted;
	private String updated;
	private String deleted;
	
	
	
	public String getInserted() {
		return inserted;
	}

	public void setInserted(String inserted) {
		this.inserted = inserted;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public <T> List<T> updates(Class<T> cls){
		 return JSON.parseArray(updated, cls);
	}
	
	public <T> List<T> inserts(Class<T> cls){
		 return JSON.parseArray(inserted, cls);
	}
	
	public <T> List<T> deletes(Class<T> cls){
		 return JSON.parseArray(deleted, cls);
	}
	 
}
