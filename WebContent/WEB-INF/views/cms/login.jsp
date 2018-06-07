<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<style>
/* html {
  background: url(${pageContext.request.contextPath}/img/login.jpg) no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
} */
    html{
    background-color:#00BBEE;
}
  
</style>

<HEAD>
<TITLE>登录 </TITLE>
<META content="text/html; charset=UTF-8" http-equiv=Content-Type>
<jsp:include page="../inc/injs.jsp"/>
<script type="text/javascript">
		$(function(){
			$('#w_login').window({    
			    width:400,    
			    height:300,    
			    modal:true,
			    title:"请先登录",
			    closed:false,
			    collapsible :false,
			    iconCls:'icon-ok',
			    closable: false,
			    minimizable: false,
			    maximizable: false,
			    resizable: false,
			    border: false,
			    draggable: false
			});  
			$('#username').textbox({
				prompt:'用户名',
				iconCls:'icon-man',
				iconWidth:38
			}); 
			/* $('#username').textbox('textbox').keydown(function (e) {
                if (e.keyCode == 13) {
                	if($('#username').textbox('getText')==''){return;}
                	$('#password').next('span').find('input').focus();
                }
            }); */
			$('#password').passwordbox({
				prompt:'密码',
				iconWidth:38,
				showEye: true 
			});  
			$('#password').textbox('textbox').keydown(function (e) {
				if (e.keyCode == 13) {
					dologin();
                 }
            }); 
		});
		
		function dologin(){
			$("#login").form('submit');
		};   
</script>
</HEAD>
	<BODY> 
	    <div id="w_login" style="padding:20px 70px 20px 70px;">  
	    	<div style="margin-bottom:10px;">  
	    		<h2 style="text-align:center;">CMS系统登录</h2>
	    	</div>
	    	<form id="login" method="post" action="${pageContext.request.contextPath}/dologin">
		        <div style="margin-bottom:10px">  
		            <input id="username" name="username" style="width:100%;height:30px;padding:12px" >  
		        </div>  
		        <div style="margin-bottom:20px">  
		            <input type="password" id="password" name="password" style="width:100%;height:30px;padding:12px" >  
		        </div>  
		        
				<div>  
		            <!-- <a id="btn_dologin" onclick=dologin() style="padding:5px 0px;width:100%;" >登录</a> -->
		         <INPUT id=btn_dologin onclick=this.blur() value=登录 type=submit>  
		            
		        </div>  
			</form>
	    </div> 
	</BODY>
</HTML>
