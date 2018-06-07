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
    <title>系统菜单维护</title>
	<jsp:include page="../../inc/injs.jsp"/> 
	<script type="text/javascript"> 
		$(function(){
            /* grid初始化 */
			$('#grid').datagrid({
						url : WEB_ROOT+'/cms/sysmenu/gridData',
						//title : '系统菜单设置',
						method : 'post',
						singleSelect : true,
					    border:false,
					    fit:true,  
					    rownumbers:true,
						columns : [[
									{title : '菜单ID', field : 'id', width : 100, align : 'center', editor : "textbox"},
									{title : '父菜单ID', field : 'pid', width : 100, align : 'center', editor : "textbox"},
									{title : '菜单名', field : 'text', width : 200, align : 'center', editor : "textbox"},
									{title : 'URL', field : 'url', width : 300, align : 'center', editor : "textbox"},
									{title : '图标', field : 'iconcls', width : 300, align : 'center', 
										editor : {
											type: "combotree",
											options: {
												url: WEB_ROOT+ '/cms/sysmenu/gridIconPath',
												method:'get',
												editable: false,
							                    valueField: 'id',
							                    textField: 'text',
							                    panelHeight: 200
							                }
											
										}
									}
									]],
							toolbar : 
								[{
									text : '新增',
									id : "button-new",
									iconCls: 'icon-add',
									onClick : function() {
										endEdit();
									    // 获取选中行的Index的值  
									    var selectedIndexed=$('#grid').datagrid('getRowIndex',$('#grid').datagrid('getSelected'));  /* 当前行号 */
										$('#grid').datagrid('insertRow',{index : selectedIndexed +1 , row:{id:'',pid:'',text:'',url:''}});
										$("#grid").datagrid('beginEdit', selectedIndexed +1 );
									}
								},{
									text : '删除',
									id : "button-remove",
									disabled : false,
									iconCls: 'icon-remove',
									onClick : function() {
										/* 确认删除 */
										if (!confirm("确认删除?")) { return; }
										endEdit();
										var row = $("#grid").datagrid('getSelected');
										if (row) {
											var rowIndex = $("#grid").datagrid('getRowIndex', row);
											$("#grid").datagrid('deleteRow', rowIndex);
										}	
										
									}
								},
								{
									text : '保存',
									id : "button-save",
									disabled : false,
									iconCls: 'icon-save',
									onClick : function() {
										endEdit();
										if ($("#grid").datagrid('getChanges').length) {
											var changedRows = new Object();
											changedRows["inserted"] = $("#grid").datagrid('getChanges', "inserted");
											changedRows["deleted"] = $("#grid").datagrid('getChanges', "deleted");
											changedRows["updated"] = $("#grid").datagrid('getChanges', "updated");
											//alert(JSON.stringify(changedRows));
											$.post(WEB_ROOT+ '/cms/sysmenu/gridSave', { json : JSON.stringify(changedRows)}, 
													function(result) {
														if(result.success){ 
															$('#grid').datagrid('reload');
														}
														$.messager.show({title: '提示',msg: result.msg});
													}, "JSON");
										}
									}
								},
								{
									text : '查询',
									id : "button-search",
									disabled : false,
									iconCls: 'icon-search',
									onClick : function() {	
										$('#grid').datagrid('reload');
									}
								}

							],
						    onClickRow : function(index, row){
						    	endEdit();
						    },
						    onDblClickRow : function(index, row){
						    	endEdit();
						    	edit();
						    }
						});
			
			
		});
		//结束编辑
		function endEdit(){
			var rows = $("#grid").datagrid('getRows');
			for ( var i = 0; i < rows.length; i++) {
				$("#grid").datagrid('endEdit', i);
			}
		};//编辑
		function edit(){
			endEdit();
			var row = $("#grid").datagrid('getSelected');
			if (row) {
				var rowIndex = $("#grid").datagrid('getRowIndex', row);
				$("#grid").datagrid('beginEdit', rowIndex);
			}
		}
	</script>
  </head>
  
  <body>
	<table id="grid"></table>
  </body>
</html>
