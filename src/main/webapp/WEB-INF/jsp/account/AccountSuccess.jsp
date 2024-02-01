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

Dear <b>${requestScope.applicant.applicantName}</b>,<br/><br/>
Your account has been created successfully. Your <b>Application Number</b> and <b>Password</b> are as follows:-<br/><br/>
<table>
<tr>
<td align="left"><b>USER NAME / APPLICATION NUMBER</b></td><th>:</th><td>${requestScope.applicant.applicationNumber}</td>
</tr>
<tr>
<td align="left"><b>PASSWORD</b></td><th>:</th><td>${requestScope.applicant.password}</td>
</tr>
</table>
<br/>
Please note down this Application Number and Password for Future References.<br/>
Please use this Application Number and Password to login and apply for the course you want.<br/>
<c:if test="${requestScope.emailSent=='Y'}">
Your Application Number and Password has also been sent to your Email Address.
</c:if>
<a href="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()%>" class="actionLink" style="font-size:14px;font-weight:bold;">Click Here</a> to go to Login Page.


</div>
<div class="footerDiv"><%@ include file="/templates/Footer.html" %></div>
</div>


<script src="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/JQ/jquery-1.10.2.js" %>"></script>
</body>
</html>