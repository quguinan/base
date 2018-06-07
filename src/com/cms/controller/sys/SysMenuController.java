package com.cms.controller.sys;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.sys.SysMenu;
import com.cms.model.sys.SysRoleMenu;
import com.cms.util.CutSubString;
import com.cms.util.PageFactory;

import my.dao.pool.DBManager;
import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms")
public class SysMenuController extends BaseController{
	
	@RequestMapping("sysmenu/list")
	public String desktop(Model m) {
		return "cms/sysmenu/list";
	}
	
	@ResponseBody
	@RequestMapping("sysmenu/gridData")
	public GridDataModel<SysMenu> gridData() {
		List<Object> params = new ArrayList<Object>();
		return PageFactory.newPage(SysMenu.class, ""," order by id ",params.toArray() );
	}
	@ResponseBody
	@RequestMapping("sysmenu/gridIconPath")
	public JSONArray gridIconPath(HttpServletRequest request) {
		JSONArray ja=new JSONArray();
		List<String> list= ReadCssFile(request);
		for (String string : list) {
			String icon=CutSubString.subString(string, ".", "{");
			JSONObject json=new JSONObject();
			json.put("id", icon);
			json.put("text", icon);
			json.put("iconCls", icon);
			ja.add(json);
		}
		return ja;
	}
	
	@Test
	  public static List<String> ReadCssFile(HttpServletRequest request) { 
		
		List<String> first_list = new LinkedList<>(); 
	    final String filename = request.getSession().getServletContext().getRealPath("/")+"css\\icon.css"; 
	    String str = null; 
	    int i = 0; 
	    try { 
	      LineNumberReader reader = null; 
	      reader = new LineNumberReader(new FileReader(filename)); 
	      while ((str = reader.readLine()) != null) { 
	        if (!str.isEmpty()) { 
	          String values[] = str.split("  "); 
	          first_list.add(values[0]); 
	        } 
	      } 
	    } catch (IOException e) { 
	      e.printStackTrace(); 
	    } 
	    
	    return first_list;
	  } 
	@ResponseBody
	@RequestMapping("sysmenu/gridDelete")
	public JSONObject gridDelete() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String id = param("id", "");
		try {
			//删除sys_menu
			SysMenu sysMenu=SysMenu.INSTANCE.queryOne("id=?", id);
			sysMenu.delete();
			//同时删除已分配的权限sys_role_menu
			List<SysRoleMenu> sysRoleMenus=SysRoleMenu.INSTANCE.query("menuid=?", id);
			for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
				sysRoleMenu.delete();
			}
			//commit
			DBManager.commitAll();
			result.put("success", true);
		} catch (Exception e) {
			DBManager.rollbackAll();
			result.put("success", false);
			e.printStackTrace();
		}
		return JSONObject.fromObject(result);
	}
	
	
	@ResponseBody
	@RequestMapping("sysmenu/gridSave")
	public JSONObject gridSave(final @RequestParam("json") String json) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		GridSaveModel model = JSON.parseObject(json,GridSaveModel.class);
		List<SysMenu> inserts = model.inserts(SysMenu.class);
		List<SysMenu> deletes = model.deletes(SysMenu.class);
		List<SysMenu> updates = model.updates(SysMenu.class);
		try {
			for (SysMenu comp : deletes) {
				comp.delete();
				//同时删除已分配的权限sys_role_menu
				List<SysRoleMenu> sysRoleMenus=SysRoleMenu.INSTANCE.query("menuid=?", comp.getId());
				for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
					sysRoleMenu.delete();
				}
			}
			for (SysMenu comp : updates) {
				comp.update();
			}
			for (SysMenu comp : inserts) {
				/////comp.setUserid(comp.newId()+"");//oracle可以自动生成id 这里不能这么写
				comp.insert();
			}
			DBManager.commitAll();
			result.put("success", true);
			result.put("msg", "保存成功!");
		} catch (Exception e) {
			DBManager.rollbackAll();
			result.put("success", false);
			result.put("msg", "失败!:"+e.getMessage());
		}
		
		return JSONObject.fromObject(result);	
		
	}
	
	
}
