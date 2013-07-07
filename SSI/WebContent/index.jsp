<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SSI Home</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
	function clickMenu(url){
		alert(url);
		document.getElementById("menu").src=url;
		
	}
</script>
<style type="text/css">
* {
	margin:0;
	padding:0;
}
html {
	width:100%;
	height:100%;
	overflow-x:hidden;
}
body {
	height:100%;
	margin:0;
	padding:0;
	font-size:10pt;
	background:#CCC;
	color:#333;
	overflow:auto;
	overflow-x:hidden;
}
body#index {
	border-left:200px solid #333333;
}

#globalNav {
	position:absolute;
	top:100px;
	left:0;
}
#globalNav li a{
	display:block;
	width:180px;
	padding:10px;
	background:#666;
	border-bottom:3px solid #525252;
	color:#FFF;
	font-weight:bold;
	text-decoration:none;
}
#globalNav li a:hover{
	background:#585858;
	border-right:3px solid #333;
}
#globalNav li ul li a{
	background:#585858;
}
#globalNav li ul li a:hover{
	background:#666;
}
#head{
	height:15%;
	border-left:5px solid #666;
	border-bottom:5px solid #666;
}
#main {
	border-left:5px solid #666;
	height:84%;
}
</style>
</head>
<body id="index">
	<ul id="globalNav">
	<li><a target="main" href="toStudentManagement">学生成绩查询</a></li>
	<li><a target="main" href="searchUser">用户管理</a></li>
	<li><a target="main" href="json_test.jsp">Json示例</a></li>
	<li><a target="main" href="js_html.jsp">JS HTML</a></li>
	</ul>
	
	<div id="head">
	当前用户：<s:property value="currUser.userName" /><br>
	</div>
	<iframe id="main" src="toStudentManagement" width="99%" frameborder="0"></iframe>
</body>
</html>