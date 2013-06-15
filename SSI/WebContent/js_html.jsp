<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SSI Home</title>
<script type="text/javascript" src="js/common.js" ></script>
<style type="text/css">
	table {
		border-collapse: collapse;
	}
	.head td {
		text-align:center;
		background-color:#00BBFF;
	}
	#comment_text {
		overflow:auto;
		/* border:1px solid red; */
		width:500px;
		height:100px;
	}
	#cm {
		border-left:0px;
	}
</style>
<script type="text/javascript">
function test(){
	//alert("asdfads");
	var com = document.getElementById('comment').value;
	
	if(com.length==0){
		alert("Please enter the notes!");
	}else{
		var cmCount = parseInt(document.getElementById("cm_count").value)+1;
		
		var hd = "<input type='hidden' name='comments' value='"+com+"' />";
		document.getElementById("login_form").innerHTML+=hd;
		
		var x=document.getElementById('cm').insertRow(0);
		var c1=x.insertCell(0);
		var c2=x.insertCell(1);
		var c3=x.insertCell(2);
		c1.width=150;
		c2.width=100;
		c1.innerHTML=getDatetime();
		c2.innerHTML=document.getElementById('user').value;
		c3.innerHTML=com;
		
		document.getElementById("cm_count").value=cmCount;
		document.getElementById('comment').value="";
	}
	
}
</script>
</head>
<body>
<form action="welcome" id="login_form" method="post">
	<input type="hidden" id="cm_count" name="cmCount" value="0" />
	<input type="submit" value="Save" />
</form>

<textarea rows="3" cols="30" id="comment"></textarea><br>
<input type="text" id="user" value="Leon"/><br>
<input type="button" value="Add Note" onclick="test()" />

<br>
<br>

<div>
<table border='1' width="500">
	<tr id="title" class="head" >
		<td width="152">Date Time</td>
		<td width="100">Creator</td>
		<td>Comments</td>
	</tr>
	<tr>
	<td colspan="3">	
		<div id='comment_text'>
			<table id="cm" border="1" width="100%"></table>
		</div>
	</td>
	</tr>
</table>
</div>

	
<br>
<br>
<table id="test" style="table-layout:fixed;" border="1" width='500'>
<tr>
<td width=50>asdasd</td>
<td width="100%" style="word-wrap:break-word;word-break:break-all; overflow:hidden;">
abcdefghigklmnopqrstuvwxyz1234567890abcdefghigklmnopqrs
tuvwxyz1234567890abcdefghigklmnopqrstuvwxyz1234567890abcdefghigklmnopqrstuvwxyz1234567890abcdefghigklmnopqrstuvwxyz1234567890abcdefghigklmnopqrstuvwxyz1234567890abcdefghigklmnopqrstuvwxyz1234567890
</td>
</tr>
</table>
</body>
</html>