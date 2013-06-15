<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
 
<html>
<head>
<title>Test</title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8" />
<style type="text/css">
    table {
    	
    }
    #student_list {
    	border-collapse:collapse;
    	border:1px solid;
        width :600px;
    }
    .blind{display:none;}
    .hand{cursor:pointer;width:20px;}
    .mouseon:hover{
        background-color:#FFBB66;
    }
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/js/student-mng.js"></script>
</head>
<body>
<center>
    Student Management<hr />
    <s:form action="toStudentManagement" theme="simple">
    <table border="0" width="600">
        <tr>
	        <td>Student Name:</td><td><s:textfield name="studentName" /></td>
	        <td>Student ID:</td><td><s:textfield name="studentId" /></td>
        </tr>
        <tr>
	        <td colspan="2"><s:checkbox name="enableFuzzy" />Enable Fuzzy Search For Name</td>
	        <td>&nbsp;</td><td><s:submit value="Search" /></td>
        </tr>
    </table>
    </s:form>
     
    <table id="student_list" >
    <tr bgcolor="#00AA55">
        <th width="25" >&nbsp;</th>
        <th width="50" >ID</th>
        <th>StudentName</th>
        <th width="50" >Age</th>
        <th>Gender</th>
    </tr>
    <s:iterator value="studentList" var="stdList" status="st" >
        <s:if test="#st.Even">
            <tr bgcolor="#BBFFEE" class="mouseon">
        </s:if>
        <s:else>
            <tr class="mouseon">
        </s:else>
            <td class ="hand" onclick="javascript:expend(this);">+</td>
            <td><s:property value="studentId" /></td>
            <td><s:property value="studentName" /></td>
            <td><s:property value="age" /></td>
            <td><s:property value="gender" /></td>
        </tr>
        <tr class="blind">
            <td colspan="5">loading...</td>
        </tr>
    </s:iterator>
    </table>
</center>
</body>
</html>