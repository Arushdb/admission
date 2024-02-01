<!doctype html>
<html lang="en">
<head>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<title>Account Creation</title>
<link rel="stylesheet" type="text/css" href="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/css/style.css" %>">
<style type="text/css">
body
{
font-family:verdana;
font-size:14px;
}
</style>

<script>
		function preventBack()
		{
			window.history.forward();
		}
  		setTimeout("preventBack()", 0);
  		window.onunload=function(){null};
	</script>
</head>


   
<body>
<div class="bodyDiv">
<div class="headerDiv"><%@ include file="/templates/Header.jsp" %></div>
<div class="contentDiv" style="padding-left:20%;padding-right:20%;">

Dear <b>${requestScope.userName}</b>,<br/><br/>
New Account cannot be created with the details given by you before 1 day because you have already created accounts with these details.<br/>
Your previously created <b>Application Number(s)</b> are as follows:-<br/><br/>
<table>
<tr>
<td align="left"><b>USER NAME / APPLICATION NUMBER</b></td>
</tr>
<c:forEach var="user" items="${requestScope.users}" >
<tr>
<td>${user.applicationNumber}</td>
</tr>
</c:forEach>
</table>
<br/>
If you do not know the password(s) for the application number(s) given above then you can get the password(s) by clicking 
<a href="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/account/getForgotPasswordPage.htm"%>" class="actionLink" style="font-size:14px;font-weight:bold;">Click Here to Get the password</a>.<br/><br/>
<a href="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()%>" class="actionLink" style="font-size:14px;font-weight:bold;">Click Here</a> to go to Login Page.


</div>
<div class="footerDiv"><%@ include file="/templates/Footer.html" %></div>
</div>


<script src="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/JQ/jquery-1.10.2.js" %>"></script>
</body>
</html>