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
<%@ include file="/templates/HorizontalNavigation.jsp" %>
<div class="contentDiv" style="padding-left:20%;padding-right:20%;margin-top:50px;">
Dear <b>${sessionScope.applicant.applicantName}</b>,<br/><br/>
Your Academic Details have been saved successfully.<br/>
You can view or change your Academic Details by clicking the <b>'My Academic Details'</b> menu.
</div>
<div class="footerDiv"><%@ include file="/templates/Footer.html" %></div>
</div>


<script src="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/JQ/jquery-1.10.2.js" %>"></script>
</body>
</html>
