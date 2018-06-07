package com.cms.controller.biz;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.model.sys.SysUser;

import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms")
public class BootstrapController extends BaseController{
	@RequestMapping("bootstrap/list")
	public String list(Model m) {
		return "cms/biz/bootstrap/list";
	}
	
	@ResponseBody
	@RequestMapping("bootstrap/dataGrid")
	public JSONObject dataGrid(Model m) {
		JSONObject json=new JSONObject();
		List<SysUser> list=SysUser.INSTANCE.query("");
		json.put("total", list.size());
		JSONArray ja= JSONArray.fromObject(list);
		json.put("rows", ja);
		return json;
	}
	
//	{
//	   "total":20,
//	   "rows":[
//	        {
//	          "id":1,
//	          "name":"张三",
//	          "age":22
//	        },
//	       ...
//	    ]
//	}
}
