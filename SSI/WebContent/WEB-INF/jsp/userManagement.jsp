<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SSI Home</title>
<style type="text/css">
	#user_list{
		border-collapse: collapse; 
		width:500px;
	}
	a{
		text-decoration:none;
		color:black;
	}
	.subtr{
		display: none;
	}
	.hand{
		cursor:pointer;
		text-align:center;
	}
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
	function expend(select){
		if($(select).html()=="+"){
	        $(select).html('-');
	        //$(select).parents('tr').css('background-color','#FFBB66');
	        $(select).parents('tr').next("tr").css('background-color','#c6daf5');
	        $(select).parents('tr').next("tr").show();
	         
	        //ajaxLoadingDetail(select);
	        jsonLoadingDetail(select);
	    }else{
	        $(select).html('+');
	        $(select).parents('tr').next("tr").hide();
	    }
		
	}
	function jsonLoadingDetail(select){
		var str="<table width='100%' >"
			+"<tr><th>Name</th><th>PassWord</th></tr>"
			+"<tr><td>admin</td><td>admin</td></tr>"
			+"</table>";
		$(select).parents('tr').next("tr").find("td").html(str);
	}
</script>
</head>
<body>

<center>
<h2>用户管理</h2>
输入查询条件:
<form action="searchUser">
	User Name:<input type="text" name="userName" />
	User ID:<input type="text" name="userId" />
	Role:<s:select name="roleId" list="roleList" listValue="roleName" listKey="id" headerKey=""
		headerValue="---"></s:select>
	<input type="submit" value="Search"/>
</form>
<hr />
<table id="user_list" border="1" >
<tr bgColor="#888">
	<th width="25">&nbsp;</th>
	<th width="40">ID</th>
	<th>用户名</th>
	<th>密码</th>
	<th>角色</th>
</tr>
<s:iterator value="userList" var="u" status="st" >
	<s:if test="#st.Odd"><tr bgcolor="#eee"></s:if>
	<s:else><tr></s:else>
		<td class="hand" onclick="javascript:expend(this);" >+</td>
		<td><s:property value="userId" /></td>
		<td><s:property value="userName" /></td>
		<td><s:property value="passWord" /></td>
		<td><s:property value="roleName" /></td>
	</tr>
	<tr class="subtr" >
		<td colspan="5" width="100%">loading...</td>
	</tr>
</s:iterator>
</table>
<br>

</center>
</body>
</html>