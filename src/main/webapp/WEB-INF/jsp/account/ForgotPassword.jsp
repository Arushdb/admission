<!doctype html>
<html lang="en">
<head>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

<title>Account Creation</title>
<link href="${ProjectURL}/JQ/jquery-ui.css" rel="stylesheet">
<script src="${ProjectURL}/JQ/jquery-1.10.2.js"></script>
<script src="${ProjectURL}/JQ/jquery-ui.js"></script>
<script src="${ProjectURL}/jsFiles/Properties.js"></script>
<link rel="stylesheet" type="text/css" href="${ProjectURL}/css/style.css">
<script src="${ProjectURL}/jsFiles/ForgotPassword.js"></script>
</head>
  
<body>
<div class="bodyDiv">
<div class="headerDiv"><%@ include file="/templates/Header.jsp" %></div>
<div class="contentDiv" align="center">
<div class="panelContainer"  style="width:800px;">
<div class="panelHeader"><div class="panelTitle">Forgot Password</div></div>
<div class="panelContent" align="center" style="padding-left: 20px;padding-right: 20px;">
	<label style="font-family:verdana;font-size:12px;color:red;">${requestScope.message}</label>
	<form method="post" action="${ProjectURL}/account/resendPassword.htm" onsubmit="return valid();" id="accountForm">
	<table style="text-align: right;width:80%;" cellspacing="8">
			<tr>
    		<td width="50%">Application Number</td><td align="left" width="50%"><input type="text" name="applicationNumber" id="applicationNumber" onkeyup="changeStyle(this);" value="${requestScope.user.applicationNumber}" maxlength="6" autocomplete="off"/></td>
    		</tr>
    		<tr>
    		<td>Date of Birth</td><td align="left"><adm:date class="dateField" name="dd" id="dd" size="1" defaultText="-Date-" startIndex="1" endIndex="31" onchange="changeStyle(this);" selectedValue="${user.dd}"/>-<adm:date class="dateField" name="mm" id="mm" size="1" defaultText="-Month-" startIndex="1" endIndex="12"  onchange="changeStyle(this);" selectedValue="${user.mm}"/>-<adm:date class="dateField" name="yyyy" id="yyyy" size="1" defaultText="-Year-" startIndex="1950" endIndex="2015"  onchange="changeStyle(this);" selectedValue="${user.yyyy}"/></td>
    		</tr>
    		<tr>
    		<td valign="top">Contact Number</td><td align="left" valign="top"><input type="text" name="phoneNo" maxlength="12"  id="phoneNo" onkeyup="changeStyle(this)" value="${requestScope.user.contactNumber}" autocomplete="off"/></td>
    		</tr>
    		<tr>
    		<td  align="right"><input type="submit" value="Send Password" class="customButton"/></td><td align="left"><input type="button" value="Back to Home" class="customButton" onclick="getHomePage();"/></td>
    		</tr>
    		</table>
    	</form>
     </div>
     </div>
 </div>
 <div class="footerDiv"><%@ include file="/templates/Footer.html" %></div>
</div>
</body>
</html>
