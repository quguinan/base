package com.cms.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.sys.SysRole;
import com.cms.model.sys.SysUser;
import com.cms.util.PageFactory;

import my.dao.pool.DBManager;
import my.util.MD5Util;
import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms")
public class SysUserController extends BaseController{
	
	@RequestMapping("user/list")
	public String desktop(Model m) {
		return "cms/user/list";
	}
	
	@ResponseBody
	@RequestMapping("user/gridData")
	public GridDataModel<SysUser> gridData() {
		String realname = param("realname", "");
		String roleid = param("roleid", "");

		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and userid<>'-1'");
		if (realname.length() > 0) {
			filter.append(" and realname like ? ");
			params.add(realname + "%");
		}
		if (roleid.length() > 0) {
			filter.append(" and roleid = ? ");
			params.add(roleid);
		}
		GridDataModel<SysUser> gridDataModel=PageFactory.newPage(SysUser.class, filter.toString()," order by userid ",
				params.toArray());
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("user/gridSave")
	public JSONObject gridSave(final @RequestParam("json") String json) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
		List<SysUser> insert = model.inserts(SysUser.class);
		List<SysUser> delete = model.deletes(SysUser.class);
		List<SysUser> update = model.updates(SysUser.class);
		try {
			for (SysUser comp : delete) {
				comp.delete();
			}
			for (SysUser comp : update) {
				comp.setPassword((MD5Util.string2MD5(comp.getPassword())));
				comp.update();
			}
			for (SysUser comp : insert) {
				comp.setPassword((MD5Util.string2MD5(comp.getPassword())));
				comp.setUserid(comp.newId()+"");//oracle可以自动生成id 这里不能这么写
				comp.insert();
			}
			DBManager.commitAll();
			result.put("msg", "保存成功");
			result.put("success", true);
			
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "失败!:"+e.getMessage());
		}finally {
			
		}

		return JSONObject.fromObject(result);	
		
	}
	
	@ResponseBody
	@RequestMapping("user/getRoleList")
	public JSONArray getRoleList(HttpSession session) {
		SysUser user=(SysUser)session.getAttribute("user");
		int roleId=user.getRoleid();
		List<SysRole> list=SysRole.INSTANCE.query("");
		JSONArray jsonData=JSONArray.fromObject(list);
		return jsonData;
	}
}
