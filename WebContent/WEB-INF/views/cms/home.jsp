<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>后台内容管理系统</title>
       <style type="text/css">
	    
		#strengthname,#strengt-span{
			float:left;
		}
		#strengt-span {
			width:120px;
			height:10px;
			margin-top:0px;
		}
		#weak,#middle,#strength{
			float:left; 
			width:30%;
			height: 34%;
		    line-height: 17px;
		    text-align: center;
		    color: #000;
		}
		
		#table-psw {font-size: 10px;}
		
		table tr td {border:0px red solid;}
		
		
		}
    </style>
	<jsp:include page="../inc/injs.jsp"/>
	
	<!-- panel收起后显示标题 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/myself.js"></script> 
	
	<script type="text/javascript">
		$(function(){
			/* 总体布局 */
			$('body').layout({
				fit : true
			});
			/* 一级菜单 */
			$.ajax({  
	            type : 'POST',  
	            dataType : "json",  
	            url : '${pageContext.request.contextPath}/cms/home/level1',  
	            success : function(data) {  
	                $.each(data, function(i, n) {//加载父类节点即一级菜单  
	                     if (i == 0) {//显示第一个一级菜单下的二级菜单  
	                        $('#west_accordion').accordion('add', {  
	                            title : n.text,  
	                            iconCls : n.iconcls,  
	                            selected : true, 
	                            content : '<div style="padding:5px"><ul name="'+n.text+'"></ul></div>',  
	                        });  
	                    } else {  
	                        $('#west_accordion').accordion('add', {  
	                            title : n.text,  
	                            iconCls : n.iconcls,  
	                            selected : false, 
	                            content : '<div style="padding:5px"><ul name="'+n.text+'"></ul></div>',  
	                        });  
	                    }   
	                });  
	            }  
	        });  
			/*  异步加载子节点，即二级菜单  */
	        $('#west_accordion').accordion({  
	        	
	            onSelect : function(title, index) {  
	                $("ul[name='" + title + "']").tree({  
	                    url : '${pageContext.request.contextPath}/cms/home/level2',  
	                    queryParams : {  
	                        text : title  //父菜单名称
	                    },  
	                    animate : true,  
	                    border : false,
	                    fit : true,
	                    iconCls : 'icon-save',
	                    lines : true,//显示虚线效果    
	                    onClick: function(node){// 在用户点击一个子节点即二级菜单时触发addTab()方法,用于添加tabs  
	                            addTab(node.text,node.url,node.iconCls);  
	                    }  
	                    /* 改变字体大小 */
	                    /* ,
	                    	 formatter:function(node){
	                    	var s ='<font size="3" face="NSimSun">'+node.text+'</font>';
	                    	if (node.children){
	                    		s += '&nbsp;<span style=\'color:blue\'>(' + node.children.length + ')</span>';
	                    	} 
	                    	return s;
	                    }     */
	                });  
	            }  
	        });  
			
			/* 右上角菜单及其事件 */
			/*1*/
		    var ddlMenu1=$('#mb1').menubutton({    
			    iconCls: 'icon-help',    
			    menu: '#mm1'  
			});
			$(ddlMenu1.menubutton('options').menu).menu({
	            onClick: function (item) {  menuHandler(item);  }
			})
			/*2*/
			var ddlMenu2=$('#mb2').menubutton({    
			    iconCls: 'icon-tip',    
			    menu: '#mm2'  
			});    
			$(ddlMenu2.menubutton('options').menu).menu({
	            onClick: function (item) { menuHandler(item); }
			})
			
			/*修改密码框*/
			$("#changePsw").dialog({
				title: '修改密码',    
			    width: 300,    
			    height: 200,    
			    closed: true,    
			    cache: false,     
			    modal: true   ,
				toolbar :[{
					text : "保存",
					onClick:function() {
						$('#myform').form("submit",{
							url: "${pageContext.request.contextPath}/cms/home/modifypassword",
							onSubmit: function(){
								if(!checkPsw()){
									$("#info").html("建议密码采用子母和数字组合，并且长度大于6！");
									return false;
								}
								var isValid = $(this).form('validate');
								if (!isValid){
									$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
								}
								return isValid;	// 返回false终止表单提交
							},
							success: function(result){
								var obj=eval('(' + result + ')')
								if (!obj.success) {//失败
									$("#info").html(obj.msg);
									//$.messager.show({title:"提示", msg : obj.msg });
								} else {//成功
									$.messager.show({title:"提示", msg : obj.msg });
									$("#changePsw").dialog("close");//关闭dialog
								}
							}
						});
						return false; //阻止form的默认提交动作
					}
				},{
					text: "取消",
					onClick: function() {
						$("#changePsw").dialog("close");//关闭dialog
					}
				}] 
			});
			/* 登录人密码 如果是123初始密码,提示需要改密码 */
			$.post('${pageContext.request.contextPath}/cms/home/validpassword', 
					' ', 
					function(result){
						if(result.success){
							$("#info").html("请修改初始密码!");
							//弹出修改密码框
							$("#changePsw").dialog("open");
						}
					}, 'json');
			/* 给tab绑定双击关闭事件*/
			$('#tt').tabs('bindDblclick', function(index, title){
				if(index>0){$('#tt').tabs('close',index);}
		    });
		});
		
		/* 创建Tab */
		function addTab(text,url ,iconCls){    
		    if ($('#tt').tabs('exists', text)){    
		        $('#tt').tabs('select', text);    
		    } else {    
		        var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';    
		        $('#tt').tabs('add',{    
		            title:text,    
		            iconCls : iconCls,
		            content:content,    
		            closable:true 
		        });    
		    }    
		}   
		
		/* 处理各个菜单事件 */
		function menuHandler(item){
			if (item.text=='退出系统'){
				
			}else if(item.text=='修改密码'){
				//初始化表单
				$("#myform").form("reset");
				$('#weak').css({backgroundColor:''});
		        $('#middle').css({backgroundColor:''});
		        $('#strength').css({backgroundColor:''});
		        $('#weak').html('');
		        $('#middle').html('');
		        $('#strength').html('');
		      	
				$("#changePsw").dialog("open");
			}
		}
		
		
		function checkPsw(){
        	var middle=$("#middle").text();
        	var strength=$("#strength").text();
        	
        	if(middle=='' && strength==''){
        		return false;
        	}else{
        		return true;
        	}
        }
		function AuthPasswd() {
			var string=$("#newpassword").val();
			var modes = 0;
		    //正则表达式验证符合要求的
		    if (string.length >= 6) { 
		    	if (/\d/.test(string)) {modes++}; //数字
			    if (/[a-z]/.test(string)) {modes++}; //小写
			    if (/[A-Z]/.test(string)) {modes++}; //大写  
			    if (/\W/.test(string)) {modes++}; //特殊字符
		    }
		    
		    //强度级别
		    if(modes == 3) {
		        $('#weak').css({backgroundColor:'#dd0000'});
		        $('#middle').css({backgroundColor:'#ffcc33'});
		        $('#strength').css({backgroundColor:'#009900'});
		        $('#weak').html('');
		        $('#middle').html('');
		        $('#strength').html('强');
		    }else if(modes == 2){
		        $('#weak').css({backgroundColor:'#dd0000'});
		        $('#middle').css({backgroundColor:'#ffcc33'});
		        $('#strength').css({backgroundColor:''});
		        $('#weak').html('');
		        $('#middle').html('中');
		        $('#strength').html('');
		    }else if(modes ==1) {
		        $('#weak').css({backgroundColor:'#dd0000'});
		        $('#middle').css({backgroundColor:''});
		        $('#strength').css({backgroundColor:''});
		        $('#weak').html('弱');
		        $('#middle').html('');
		        $('#strength').html('');
		    }else{
		        $('#weak').css({backgroundColor:''});
		        $('#middle').css({backgroundColor:''});
		        $('#strength').css({backgroundColor:''});
		        $('#weak').html('太短');
		        $('#middle').html('');
		        $('#strength').html('');
		    }
		}
		/**
	     * 绑定双击事件
	     * @param {Object} jq
	     * @param {Object} caller 绑定的事件处理程序
	     */
		$.extend($.fn.tabs.methods, {
		    bindDblclick: function(jq, caller){
		        return jq.each(function(){
		            var that = this;
		            $(this).children("div.tabs-header").find("ul.tabs").undelegate('li', 'dblclick.tabs').delegate('li', 'dblclick.tabs', function(e){
		                if (caller && typeof(caller) == 'function') {
		                    var title = $(this).text();
		                    var index = $(that).tabs('getTabIndex', $(that).tabs('getTab', title));
		                    caller(index, title);
		                }
		            });
		        });
		    },
		    /**
		     * 解除绑定双击事件
		     * @param {Object} jq
		     */
		    unbindDblclick: function(jq){
		        return jq.each(function(){
		            $(this).children("div.tabs-header").find("ul.tabs").undelegate('li', 'dblclick.tabs');
		        });
		    }
		});
		
	</script>
  </head>
  <body>
 	  	<!-- 头部信息 -->
		<div data-options="region:'north',border:false" style="height:30px;background:#00BBEE;padding:2px;color:#FFF">
			<div style="float: left;font-size: 16"><span id="title">内容管理系统(CMS)</span></div>
			<div style="float: right;">
				<a id="mb1" href="javascript:void(0)" style="color:#FFF">个人中心</a>   
				<div id="mm1" style="width:150px"> 
					<div iconCls="icon-man">个人信息</div> 
					<div iconCls="icon-edit">修改密码</div> 
				</div> 
				<a id="mb2" href="javascript:void(0)" style="color:#FFF">系统操作</a>   
				<div id="mm2" style="width:150px"> 
					<div iconCls="icon_door_out">
						<a href="${pageContext.request.contextPath}/cms/logout" style="text-decoration:none">退出系统</a>
					</div> 
				</div> 
			</div>
		</div>
		
		<!-- ***左边菜单导航*** -->
		<div id="west" data-options="region:'west',split:true,title:'导航菜单',iconCls:'icon_computer'" style="width:150px;">
			<div class="easyui-accordion" id="west_accordion" data-options="fit:true,border:false">  
		         
	    	</div>   
		</div>  
		
		
		<!-- ***右边索引查询*** -->
		 <div id="east" data-options="region:'east',split:true,collapsed:true,title:'个人中心'" style="width:200px;padding:10px;">
			<div class="easyui-panel"  data-options="fit:true,border:false">
		        <ul class="easyui-tree" 
		        	data-options="url:'${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/tree_data2.json',method:'get',animate:true,lines:true">
		        </ul> 
		    </div> 
		</div>   
	
		<!-- 中间内容 -->
		<div id="Center" data-options="region:'center'"><!-- ,title:'当前位置:系统设置->用户列表' -->
			<!-- ***TabPage*** -->
		    <div id="tt" class="easyui-tabs" style="width:700px;height:250px;"  data-options="fit:true,border:false,narrow:true,plain:true">
		        <div title="首页" style="padding:10px" data-options="iconCls:'icon_house'" >
		           <iframe scrolling="auto" frameborder="0"  src="cms/desktop" style="width:100%;height:100%;"></iframe>
		        </div>
		    </div> 
		</div>
		
		<!-- ***页尾*** -->
		<div id="south" data-options="region:'south',border:false" style="height:18px;background:#00BBEE;padding:0px;text-align: right;color:#FFF">
			版本v1.0
		</div>  
		
		
		<div id="changePsw">
		<form id="myform">
			<table id="table-psw" >
				<tr>
					<td>旧密码：</td>
					<td><input type="password"  id="oldpassword" name="oldpassword"  /> </td>
					<td></td>
				</tr>
				<tr> <td></td> <td> </td>  <td> </td> </tr>
				<tr> <td></td> <td> </td>  <td> </td> </tr>
				<tr> <td></td> <td> </td>  <td> </td> </tr>
				<tr>
					<td>新密码：</td>
					<td><input type="password" id="newpassword" name="newpassword"  onKeyUp="javascript:AuthPasswd();"/></td> 
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td><div id="strengt-span"> <div id="weak" ></div> <div id="middle"></div> <div id="strength"></div> </div></td> 
					<td></td>
				</tr>
				<tr>
					<td>密码确认：</td>
					<td colspan=2><input type="password"  id="newpassword2" name="newpassword2" /></td>
				</tr>
			</table> 
		</form>
		<div id="info" style="color:#F00"></div>
	</div>
  </body>
</html>
