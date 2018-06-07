package com.cms.controller.sys;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.form.RoleForm;
import com.cms.form.RoleMenuForm;
import com.cms.model.sys.SysMenu;
import com.cms.model.sys.SysRole;
import com.cms.model.sys.SysRoleMenu;
import com.cms.model.sys.SysUser;

import my.dao.pool.DBManager;
import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms")
public class SysRoleMenuController extends BaseController{
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("form")
	public RoleForm getForm(){
		return new RoleForm();
	}
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("formRoleMenu")
	public List<RoleMenuForm> getFormRoleMenu(){
		List<RoleMenuForm> list =new ArrayList<RoleMenuForm>();
		return list;
	}
	
	@RequestMapping("rolemenu/list")
	public String desktop(Model m) {
		return "cms/rolemenu/list";
	}
	
	/**
	 * 返回权限列表(全部)
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rolemenu/findRoles")
	public JSONObject findRoles(){
		List<SysRole> list=new ArrayList<SysRole>();
		list=SysRole.INSTANCE.query("order by roleid");
		JSONArray jsonData=new JSONArray();
		for (SysRole sysRole : list) {
			JSONObject jo = new JSONObject() ;
			jo.put("roleid", sysRole.getRoleid());
			jo.put("rolename", sysRole.getRolename());
			jsonData.add(jo);
		}
		JSONObject jsonobj = new JSONObject() ;
		jsonobj.put("total",list.size()) ;  
		jsonobj.put("rows",jsonData) ; 
		return jsonobj;
	}
	
	/**
	 * 根据当前roleid返回权限列表
	 * 只能获取权限弱于当前roleid的权限
	 * @return
	 */
/*	@ResponseBody
	@RequestMapping("findRolesByRoleId")
	public JSONArray findRolesByRoleId(HttpSession session){
		SysUser user=(SysUser)session.getAttribute("user");
		String roleid=user.getRoleid().toString();
		List<SysRole> list=new ArrayList<SysRole>();
		list=SysRole.INSTANCE.query("roleid=?", roleid);
		JSONArray jsonData=JSONArray.fromObject(list);
		return jsonData;
	}*/
	/**
	 * 保存权限,新增权限和更新权限公用此方法
	 * @param roleid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rolemenu/saveRole")
	public JSONObject update(@ModelAttribute("form") RoleForm form){
		HashMap<String, Object> result = new HashMap<String, Object>();
		Integer roleid=form.getRoleid(); 
		
		try {
			if (!(null==roleid||roleid.equals(""))){
				//更新
				SysRole role=form.getEntityUpdate(); 
				role.update();
			}else{
				//新增
				roleid=SysRole.INSTANCE.getNextRoleId();
				SysRole role=form.getEntityAdd();
				role.setRoleid(roleid);
				//插入sys_role（总单）
				SysRole role1=new SysRole(role.getRoleid(), role.getRolename());
				role1.insert();
				//插入sys_role_menu（明细）
				List<SysMenu> list=SysMenu.INSTANCE.query("");//插入所有菜单并初始化
				for (SysMenu sysMenu : list) {
					SysRoleMenu sysRoleMenu=new SysRoleMenu();
					sysRoleMenu.setRoleid(roleid); 
					sysRoleMenu.setMenuid(sysMenu.getId());
					sysRoleMenu.setEnabled(0);
					sysRoleMenu.insert();
				}
			}
			DBManager.commitAll();
			result.put("success", true);
			result.put("msg","保存成功!");
		} catch (NumberFormatException e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("success", false);
			result.put("msg","保存失败!");
		}
		
		return JSONObject.fromObject(result);
	}	

	/**
	 * 删除
	 * @param roleid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rolemenu/delete")
	public JSONObject delete(HttpSession session){
		String roleid=param("roleid", "");
		//当前登录用户
		SysUser user=(SysUser) session.getAttribute("user");
		
		Map<String,Object> result = new HashMap<String,Object>();
		String errorMsg="删除失败!";
		
		List<SysUser> sysUsers=SysUser.INSTANCE.query("roleid=?", roleid);
		if (sysUsers.size()>0){
			errorMsg+="权限已被使用,不能删除!";
			result.put("success", false);
			result.put("msg", errorMsg);
		}
		else if(roleid=="-1"){
			errorMsg+="管理员权限不能删除!";
			result.put("success", false);
			result.put("msg", errorMsg);
//		}else if(Integer.valueOf(roleid) <user.getRoleid()){
//			errorMsg+="你的权限低于要删除的权限,不能删除!";
		}
		else{
			try {
				//先删除sys_role_menu（明细）
				List<SysRoleMenu> list=SysRoleMenu.INSTANCE.query("roleid=?", roleid);
				for (SysRoleMenu sysRoleMenu : list) {
					sysRoleMenu.delete();
				}
				//再删除sys_role（总单）
				SysRole sysRole=SysRole.INSTANCE.queryOne("roleid=?", roleid);
				sysRole.delete();
				
				DBManager.commitAll();
				result.put("success", true);
				result.put("msg", "删除成功!");
			} catch (Exception e) {
				DBManager.rollbackAll();
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("success", false);
				result.put("msg", errorMsg);
			}
		}
		
		return JSONObject.fromObject(result);
	}
	/**
	 * 获取当前roleid的roleMenu
	 * @param roleid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rolemenu/getRoleMenu")
	public JSONArray getRoleMenu(){
		String roleid=param("roleid", "");
		
		/*
		 * 在刷新rolemenu之前,核对是否与sysMenu中的菜单一致
		 * 因为菜单如果删除,会同时删除sysRoleMenu中的数据,但是增加菜单则不会增加sysRoleMenu中的数据
		 * 所以一般情况下,不一致的情况sysRoleMenu只会比sysMenu中多,而不会比sysMenu中少
		 */
		List<SysMenu> sysMenus=SysMenu.INSTANCE.query("");
		for (SysMenu sysMenu : sysMenus) {
			SysRoleMenu sysRoleMenu=SysRoleMenu.INSTANCE.queryOne("menuid=? and roleid=?", sysMenu.getId(),roleid);
			if (sysRoleMenu==null){
				try {
					SysRoleMenu srm=new SysRoleMenu();
					srm.setRoleid(Integer.valueOf(roleid));
					srm.setMenuid(sysMenu.getId());
					srm.setEnabled(0);
					srm.insert();
					DBManager.commitAll();
				} catch (Exception e) {
					DBManager.rollbackAll();
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//刷新rolemenu数据
		List<SysRoleMenu> roleMenus=SysRoleMenu.INSTANCE.query("roleid=?", roleid);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		for (int i = 0; i < roleMenus.size(); i++) {
			if(roleMenus.get(i).getPid()==null||roleMenus.get(i).getPid().equals("")){
				Map<String,Object> map=new HashMap<String, Object>();
				List<Map<String,Object>> listChildren=new ArrayList<Map<String,Object>>();
				map.put("id", roleMenus.get(i).getMenuid());
				map.put("text", roleMenus.get(i).getText());
				map.put("iconCls", roleMenus.get(i).getIconcls()==null?"":roleMenus.get(i).getIconcls());
				map.put("children", listChildren);
				list.add(map);
			}else{
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("id",  roleMenus.get(i).getMenuid());
				map.put("text", roleMenus.get(i).getText());
				map.put("iconCls", roleMenus.get(i).getIconcls()==null?"":roleMenus.get(i).getIconcls());
				map.put("checked", roleMenus.get(i).getEnabled()==1?true:false);
				List<Map<String,Object>> listChildren=(List<Map<String,Object>>)list.get(list.size()-1).get("children");
				listChildren.add(map);
			}
		}
		JSONArray jsonArray=JSONArray.fromObject(list);
		return jsonArray;
	}
	/**
	 * 更新当前roleid的roleMenu
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rolemenu/updateRoleMenu")
	public JSONObject updateRoleMenu(HttpSession session){

		String menuids=param("menuids", "");
		String roleid=param("roleid", "");
		String errorMsg="更新权限失败！";
		Map<String,Object> result = new HashMap<String,Object>();
		int row=0;
		List<String> list = Arrays.asList(menuids.split(","));
		//当前登录用户
		 SysUser user=(SysUser) session.getAttribute("user");
//		if(user.getRoleid()>Integer.valueOf(roleid)){
//			result.put("success", false);
//			result.put("errorMsg", errorMsg+"低权限级别不能更改高级别权限!");
//			return JSONObject.fromObject(result);
//		}
		//初始化，将roleid先所有enabled设置成0
		List<SysRoleMenu> list_srm=SysRoleMenu.INSTANCE.query("roleid=?", roleid);
		for (SysRoleMenu srm : list_srm) {
			srm.setEnabled(0);
			row=srm.update();
			if(row<=0){
				result.put("success", false);
				result.put("errorMsg", errorMsg+"初始化roleMenu失败!");
				return JSONObject.fromObject(result);
			}
		}
		
		//根据选择设置enabled
		for (int i = 0; i < list.size(); i++) {
			String menuid=list.get(i);
			//如果新增菜单,在权限菜单中没有的,需要添加进来,并初始化
//			if (roleMenuService.getOneRoleMenu(menuid, Integer.valueOf(roleid))==null) {
//				roleMenuService.insertRoleMenu(menuid, Integer.valueOf(roleid));
//			}
			SysRoleMenu srm=SysRoleMenu.INSTANCE.queryOne("roleid=? and menuid=?", roleid,menuid);
			if (srm==null){
				srm.setMenuid(menuid);
				srm.setRoleid(Integer.valueOf(roleid));
				srm.setEnabled(0);
				srm.insert();
			}
			//更新标志
//			row=roleMenuService.updateRoleMenu(Integer.valueOf(roleid), menuid, 1);
			SysRoleMenu sysRoleMenu=SysRoleMenu.INSTANCE.queryOne("roleid=? and menuid=?", roleid,menuid);
			sysRoleMenu.setEnabled(1);
			row=sysRoleMenu.update();
			if(row<=0){
				result.put("success", false);
				result.put("errorMsg", errorMsg+"更新roleMenu失败!");
				return JSONObject.fromObject(result);
			}
		}
		DBManager.commitAll();
		result.put("success", true);
		return JSONObject.fromObject(result);
	}
	
}
