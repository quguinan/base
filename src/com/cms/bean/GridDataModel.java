package com.cms.bean;

import java.util.ArrayList;
import java.util.List;


public class GridDataModel<T> {
	// 显示的总数
	private int total;
	// 行数据
	private List<T> rows = new ArrayList<T>();
	

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
