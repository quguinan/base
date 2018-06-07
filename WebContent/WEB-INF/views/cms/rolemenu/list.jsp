<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户权限维护</title>
    	<!-- 
    	
    	
    		由于tree控制比较方便灵活，此页面使用easyui框架
    		
    		
    	 -->
	<jsp:include page="../../inc/injs.jsp"/>
	<script type="text/javascript">
	$(function(){
		 //dlg初始化
      	  $('#dlg').dialog({    
		    width: 400,    
		    height: 150,    
		    closed: true,    
		    cache: false,
		    buttons:'#dlg-buttons',    
		    modal: true   ,
		    inline:true,
		    border: 'thin'
		});   
		
		//dg初始化
		$('#dg').datagrid({
			url:WEB_ROOT+'/cms/rolemenu/findRoles',   
		    columns:[[
		        {field:'roleid',title:'权限ID',width:100,align:'center'},    
		        {field:'rolename',title:'权限名称',width:200,align:'center'}     
		    ]],
		    singleSelect:true,
		    striped:true,
		    fit:true,
		    border:false,
		    toolbar:'#toolbar',
		    toolbar: ['-',{
						iconCls: 'icon-add',
						text:'新增',
						handler: function(){newRole()}
					 },'-',{
						iconCls: 'icon-edit',
						text:'编辑',
						handler: function(){editRole()}
					 },'-',{
						iconCls: 'icon-remove',
						text:'删除',
						handler: function(){deleteRole()}
					 },'-',{
						iconCls: 'icon-save',
						text:'保存',
						handler: function(){updateRoleMenu()}
					 },'-',{
						iconCls: 'icon-reload',
						text:'刷新',
						handler: function(){refreshRoleMenu()}
					 },'-'],
			onClickRow : function(index, row){
							refreshTree(index, row)
						},
		});
	});
	//刷新全部
	function refreshRoleMenu(){
		
	}
	//更新权限tree
	function updateRoleMenu(){
		var nodes = $('#dt').tree('getChecked');
		var menuids = '';
		var row = $('#dg').datagrid('getSelected');
		if (row){
			for(var i=0; i<nodes.length; i++){
				if (menuids != '') menuids += ',';
				menuids += nodes[i].id;
			}
			 $.post(WEB_ROOT+'/cms/rolemenu/updateRoleMenu',{roleid:row.roleid,menuids:menuids},function(result){
				if (result.success){
					$.messager.show({	// show error message
						title: '提示',
						msg: '更新成功!'
					});
				} else {
					$.messager.show({	// show error message
						title: '提示',
						msg: result.errorMsg
					});
				} 
			},'json'); 
		}

	}
	//选择dg,刷新tree
	 function refreshTree(index, row){
		/* alert(row.roleid); */
		$('#dt').tree({
	  		url : WEB_ROOT+'/cms/rolemenu/getRoleMenu?roleid='+row.roleid,
	  		checkbox : true,
	  		cascadeCheck : false, //选中子节点时,父节点是否被选中.
	  		onlyLeafCheck : true,  //是否只选叶子节点
	  		lines : true,
	  		loadFilter: function(data){     
	            return data;        
	        }      
	       // data : WEB_ROOT+'/js/jquery-easyui-1.5.1/tree_data2.json'
	    })  
	} 
	//新增
	function newRole(){
		$('#dlg').dialog('open').dialog('setTitle','新增权限');
		$('#fm').form('clear');
	};
	//编辑
	function editRole(){
		var row = $('#dg').datagrid('getSelected');
		//alert(JSON.stringify(row));
		if (row){
			 $('#dlg').dialog('open').dialog('setTitle','编辑权限');
			 $('#fm').form('load',row);
		}
	};
	//新增和编辑的保存
	function saveRole(){
		$('#fm').form('submit',{
			 url: WEB_ROOT+'/cms/rolemenu/saveRole',
			 onSubmit: function(){
			 	//表单验证信息
			 	//return $(this).form('validate');
			 },
			 success: function(result){
			 	 var result = eval('('+result+')');
				 if (result.success){
				 	 $('#dlg').dialog('close'); // close the dialog
					 $('#dg').datagrid('reload'); // reload the user data
					 $.messager.show({
						 title: 'Success',
						 msg: result.msg
					 });
				 } else {
					 $.messager.show({
						 title: 'Error',
						 msg: result.msg
					 });
				 }
			 }
		 });
	};
	//删除
	function deleteRole(){
		var row = $('#dg').datagrid('getSelected');
			if (row){
				$.messager.confirm('Confirm','是删除这个权限?',function(r){
					if (r){
						 $.post(WEB_ROOT+'/cms/rolemenu/delete',{roleid:row.roleid},function(result){
							if (result.success){
								$('#dg').datagrid('reload');	// reload the user data
								$.messager.show({	// show error message
									title: '提示',
									msg: result.msg
								});
							} else {
								$.messager.show({	// show error message
									title: '提示',
									msg: result.msg
								});
							} 
						},'json'); 
					}
				});
			}
	};
  </script>
  </head>
  
  <body >
	<div id="lo" class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',title:'权限列表',collapsible:false,split:true" style="width:320px" >
			<div id="dg"></div>
		</div>  
		<!-- tree -->
		<div data-options="region:'center',title:'菜单选择'"  >
			<form:form modelAttribute="formRoleMenu" id="fmTree" method="post">
				<div id="dt"></div>
			</form:form>  
		</div>  
		 
	</div>
	
	<!-- 表单对话框 -->
 	<div id="dlg"> 
		<form:form modelAttribute="form" id="fm" method="post">
			<form:hidden path="roleid"/> 
			<table cellpadding="5">
				<tr>
					<td>权限名称:</td>
					<td><form:input path="rolename" type="text" class="easyui-textbox" required="true" data-options="required:true"/></td>
				</tr>
			</table>
		</form:form> 
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRole()">保存</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div> 
	
  </body>
</html>
