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
<div class="contentDiv" align="center">
Dear <b>${requestScope.userName},</b><br/>
Your account has been changed successfully. Your Application Number and Password are as follows :-.<br/>
<table cellspacing="5px" cellpadding="4px">
<tr>
<td><b>Application Number/User Name</b></td><td><b>Password</b></td>
</tr>
<c:forEach var="user" items="${requestScope.users}" >
<tr>
<td>${user.applicationNumber}</td><td>${user.password}</td>
</tr>
</c:forEach>
</table>
Please Note Down this Application Number and Password for Future References.<br/>
<a href="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()%>" class="actionLink" style="font-size:14px;">Click Here to go to Login Page.</a> 

</div>
<div class="footerDiv"><%@ include file="/templates/Footer.html" %></div>
</div>
<script src="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/JQ/jquery-1.10.2.js" %>"></script>
</body>
</html>
