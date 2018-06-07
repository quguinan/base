<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>bootstrap测试</title>

<style>
/* 行高 */
#table{height:0px;padding-bottom:100%}
</style>
<!-- 引入bootstrap样式 -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<!-- 引入bootstrap-table样式 -->
<link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">
<!-- jquery -->
<script src="https://cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!-- bootstrap-table.min.js -->
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<!-- 引入中文语言包 -->
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript">
	$(function(){
			$("#table").bootstrapTable({ // 对应table标签的id
			      url: "dataGrid", // 获取表格数据的url
			      cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
			      striped: true,  //表格显示条纹，默认为false
			      pagination: true, // 在表格底部显示分页组件，默认false
			      pageList: [10, 20], // 设置页面可以显示的数据条数
			      pageSize: 10, // 页面数据条数
			      pageNumber: 1, // 首页页码
			      sidePagination: 'server', // 设置为服务器端分页
			      queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
			          return {
			              pageSize: params.limit, // 每页要显示的数据条数
			              offset: params.offset, // 每页显示数据的开始行号
			              sort: params.sort, // 要排序的字段
			              sortOrder: params.order, // 排序规则
			              dataId: $("#dataId").val() // 额外添加的参数
			          }
			      },
			      sortName: 'id', // 要排序的字段
			      sortOrder: 'desc', // 排序规则
			      columns: [
			          {field: 'userid',title: '用户ID',align: 'center', valign: 'middle' }, 
			          {field: 'realname',title: '用户名',align: 'center',valign: 'middle'}, 
			          {field: 'username',title: '登录名', align: 'center',valign: 'middle'}
			      ],
			      onLoadSuccess: function(){  //加载成功时执行
			            console.info("加载成功");
			      },
			      onLoadError: function(){  //加载失败时执行
			            console.info("加载数据失败");
			      }
			})
	});

	</script>		
</head>
<body>
 	<table id="table"></table>
</body>
</html>