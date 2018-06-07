/**
** create by code gen .
**/
package com.cms.model.sys;

import java.util.Date;
import java.util.List;


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
import my.util.SysParam;
import my.base.BasePO;



@Table("SYS_DICT")
@View("SYS_DICT")
@PK({ "ID" })
public class SysDict extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final SysDict INSTANCE = new SysDict();
	
	@Column(value = "id", type = ColumnType.NUMBER)
	private Integer id ;
	
	@Column(value = "pid", type = ColumnType.NUMBER)
	private Integer pid ;
	
	@Column(value = "text", type = ColumnType.STRING)
	private String text ;
	
	@Column(value = "texten", type = ColumnType.STRING)
	private String texten ;
	
	@Column(value = "value", type = ColumnType.STRING)
	private String value ;
	
	@Column(value = "stopflag", type = ColumnType.NUMBER)
	private Integer stopflag ;
	


	public Integer getId (){
		return id;
	}
		 
	public void setId (Integer id){
		this.id=id;
	}

	public Integer getPid (){
		return pid;
	}
		 
	public void setPid (Integer pid){
		this.pid=pid;
	}

	public String getText (){
		return text;
	}
		 
	public void setText (String text){
		this.text=text;
	}

	public String getTexten (){
		return texten;
	}
		 
	public void setTexten (String texten){
		this.texten=texten;
	}

	public String getValue (){
		return value;
	}
		 
	public void setValue (String value){
		this.value=value;
	}

	public Integer getStopflag (){
		return stopflag;
	}
		 
	public void setStopflag (Integer stopflag){
		this.stopflag=stopflag;
	}
	
	@Override
	public Long newId(){
		SysDict dict=SysDict.INSTANCE.queryOne(" id in (select max(id) from sys_dict)");
		return Long.valueOf(dict.getId()+1);
	}
	
	@Override
	public boolean cachedByName() {
		if(SysParam.isDevMode()){
			return false;
		}
		return true;
	}
	

	/**
	 * 查询下级字典
	 * 
	 * @param value
	 * @return
	 */
	public List<SysDict> querySub(String value) {
		SysDict dict = super.queryOne("value=?", value.toUpperCase());
		if (dict == null) {
			return null;
		}
		List<SysDict> list=viewHelper()
				.query(SysDict.class,
						"select * from SYS_DICT where pid = ?",
						dict.getId());
			return list;
	}
}
