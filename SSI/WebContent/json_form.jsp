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
<script type="text/javascript">
$(document).ready(function(){
	//向服务器发送表达数据 
	$("#regRe").click(function(){ 
		
	    //把表单的数据进行序列化 
	    var params = $("#reg_form").serialize(); 
	    //使用jQuery中的$.ajax({});Ajax方法 
	
	    $.ajax({ 
	        url:"jsontest!gainUserInfo", 
	        type:"POST", 
	        data:params, 
	        dataType:"json", 
	        success:function(data){ 
	
	        $("#message").html(""); 
	
	        //为显示层添加获取到的数据 
	        //获取对象的数据用data.userInfo.属性 
	        $("#message").append("<div><font color='red'>用户ID："+data.userInfo.userId+"</font></div>") 
	                     .append("<div><font color='red'>用户名："+data.userInfo.userName+"</font></div>") 
	                     .append("<div><font color='red'>密码："+data.userInfo.passWord+"</font></div>");
	        } 
	    }); 
	});

}); 
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
	<form id="reg_form"> 
		用户ID：<input name="userInfo.userId" type="text"/><br/> 
		用户名：<input name="userInfo.userName" type="text"/><br/> 
		密   码：<input name="userInfo.passWord" type="password"/><br> 
		<input id="regRe" type="button" value="注册"/> 
	</form> 
	
	<!-- 要显示信息的层--> 
	<div id="message"></div> 

</body>
</html>
