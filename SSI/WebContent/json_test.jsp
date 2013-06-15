<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Struts2+JQuery+JSON</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="<%=path%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/json-test.js"></script>
<script type="text/javascript">
function testEach(){
	$("#grid tr").each(function() {
        alert($(this).children().eq(1).text());
	});
}
</script>
<style type="text/css">
	table {
		border-collapse:collapse;
		width:500;
	}
	th {
		background-color:#99FF99;
	}
</style>

</head>

<body>

	<input id="getMessage" type="button" value="获取单个值" />
	<input id="getUserInfo" type="button" value="获取UserInfo对象" />
	<input id="getList" type="button" value="获取List对象" />
	<input id="getMap" type="button" value="获取Map对象" />

	<br>
	<br>
	<!-- 要显示信息的层-->
	<div id="message"></div>


	<table id="grid">
		<tr><td>ads1</td><td>ddd1</td></tr>
		<tr><td>dsd2</td><td>ddd2</td></tr>
		<tr><td>asf3</td><td>asd3</td></tr>
		<tr><td>scf4</td><td>adf4</td></tr>
	</table>
	<input type="button" value="遍历表格" onclick="testEach();" />
</body>
</html>
