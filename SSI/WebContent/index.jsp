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
</head>
<body>
<center>
<table border="1" width="80%">
<tr>
	<td colspan="2" height="110">
	当前用户：<s:property value="currUser.userName" /><br>
	<h2>SSI Home</h2>
	</td>
</tr>
<tr>
	<td width="250" height="500">
	菜单：
	<ul>
	<li><a href="toStudentManagement">学生成绩查询</a></li>
	<li><a href="searchUser">用户管理</a></li>
	<li><a href="json_test.jsp">Json示例</a></li>
	<li><a href="js_html.jsp">JS HTML</a></li>
	</ul>
	</td>
	<td>
	<iframe id="main" src="toStudentManagement" width="100%" height="100%">
	
	</iframe>
	</td>
</tr>
	
	
</table>
</center>
</body>
</html>