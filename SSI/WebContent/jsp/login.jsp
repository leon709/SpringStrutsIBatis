<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SSI Home</title>
</head>
<body>
<h2>Please login</h2>
<s:form action="/login" >
	<s:textfield name="userName" label="UserName"></s:textfield>
	<s:password name="passWord" label="PassWord"></s:password>
	<s:submit value="Login" ></s:submit>
</s:form>
</body>
</html>