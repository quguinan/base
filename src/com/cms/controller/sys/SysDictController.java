package com.cms.controller.sys;



import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.sys.SysDict;
import com.cms.util.PageFactory;

import my.web.AjaxMsg;
import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms")
public class SysDictController extends BaseController{
	
	
	@RequestMapping("sysdict/dict")
	public String list(Model m) {
		return "cms/sysdict/dict";
	}
	
	@ResponseBody
	@RequestMapping("sysdict/dict/getDictValue")
	public JSONArray getDictValue() {
		String value=param("value", "");
		SysDict sysDict=SysDict.INSTANCE.queryOne("value=?", value);
		List<SysDict> list = SysDict.INSTANCE.query("pid=?", sysDict.getId());
		return JSONArray.fromObject(list);
	}
	
	@ResponseBody
	@RequestMapping("sysdict/dict/treeData")
	public JSONArray treeData() {
		JSONArray jsonArray=new JSONArray();
		List<SysDict> list=SysDict.INSTANCE.query(" pid=0");
		JSONObject jo=new JSONObject();
		for (SysDict sysDict : list) {
			//一级菜单
			jo.put("id", sysDict.getId());
			jo.put("text", sysDict.getText());
			List<SysDict> list1=SysDict.INSTANCE.query(" pid=? order by id ",sysDict.getId());
			//二级菜单
			JSONArray ja=new JSONArray();
			for (SysDict sysDict2 : list1) {
				JSONObject jo2=new JSONObject();
				jo2.put("id", sysDict2.getId());
				jo2.put("text", sysDict2.getText());
				ja.add(jo2);
			}
			if(ja.size()>0){ jo.put("children", ja);}
			//总json
			jsonArray.add(jo);
		}
//		String s="[{'id':1,'text':'My Documents','children':[{'id':11,'text':'Photos','state':'closed','children':[{'id':111,'text':'Friend'},{'id':112,'text':'Wife'},{'id':113,'text':'Company'}]},{'id':13,'text':'index.html'},{'id':14,'text':'about.html'},{'id':15,'text':'welcome.html'}]}]";
//		json=JSONArray.fromObject(s);
		return jsonArray;
	}
	
	
	@ResponseBody
	@RequestMapping("sysdict/dict/gridData")
	public GridDataModel<SysDict> gridData() {
		String pid = param("pid", "0");
		String filter = "pid = ? ";
		
		return PageFactory.newPage(SysDict.class, filter," order by value", pid);
	}

	
	@ResponseBody
	@RequestMapping("sysdict/dict/saveGrid")
	public AjaxMsg gridSave(final @RequestParam("json") String json) {
		 
		return run(new CallBack() {
			
			@Override
			public AjaxMsg call() throws Exception {
				GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
				
				List<SysDict> insert = model.inserts(SysDict.class);
				
				List<SysDict> delete = model.deletes(SysDict.class);
				
				List<SysDict> update=model.updates(SysDict.class);
				
				for(SysDict dict:delete){
					dict.delete();
				}
				
				for(SysDict dict:update){
					dict.update();
				}
				
				for(SysDict dict:insert){
					dict.setId(Integer.valueOf(dict.newId().toString()));
					dict.insert();
				}
				return AjaxMsg.ok();
			}
		});
	
	}
}



















