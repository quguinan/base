<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"/>
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"/>
<META HTTP-EQUIV="Expires" CONTENT="0"/>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<script>
var WEB_ROOT="${pageContext.request.contextPath}";
var LANG="${pageContext.request.locale}";
</script>


<!--JQuey库 (必须)-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script> 

<!-- easyui -->
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/jquery.easyui-1.5.1.min.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/easyui-lang-zh_CN.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/datagrid-filter.js"></script> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/themes/gray/easyui.css"> 
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/themes/default/easyui.css">  --%> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/themes/icon.css"> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/themes/color.css">  
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/jquery.edatagrid.js"> 

<!-- easyui扩展 -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/jquery-easyui-datagridview/datagrid-detailview.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/jquery-easyui-datagridview/datagrid-groupview.js"></script> 



<!-- 其他  -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.serialize-object.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/crypto/core-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/crypto/enc-base64-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pinyin.js" charset="GBK"></script>

<!-- 二维码 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.qrcode.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/qrcode.min.js"></script> 

<!-- 条形码 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/JsBarcode.all.min.js"></script> 


<!-- 二维码字符串压缩 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pako/pako.js"></script>

<!-- 打印控件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>



