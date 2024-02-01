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

<title>Application Transfer</title>
<link href="${ProjectURL}/JQ/jquery-ui.css" rel="stylesheet">
<script src="${ProjectURL}/JQ/jquery-1.10.2.js"></script>
<script src="${ProjectURL}/JQ/jquery-ui.js"></script>
<script src="${ProjectURL}/jsFiles/Properties.js"></script>
<link rel="stylesheet" type="text/css" href="${ProjectURL}/css/style.css">
<script src="${ProjectURL}/jsFiles/ForgotPassword.js"></script>
</head>
  
<body onload="getCountries();getStates();">
<div class="bodyDiv">
<div class="headerDiv"><%@ include file="/templates/Header.jsp" %></div>
<div class="contentDiv" align="center">
<div class="panelContainer"  style="width:800px;">
<div class="panelHeader"><div class="panelTitle">Application Transfer</div></div>
<div class="panelContent" align="center" style="padding-left: 20px;padding-right: 20px;">
	<label style="font-family:verdana;font-size:12px;color:red;">${requestScope.error}</label>
	<br/>
	<label style="font-family:verdana;font-size:12px;color:red;font-weight:bold;">Please Note :- </label>
	<label style="font-family:verdana;font-size:12px;color:green;">There will be a new Application Number and Password for your transferred application.</label>
	<br/>
	<label style="font-family:verdana;font-size:12px;color:green;">For example, if your application number is 121632 then your new application number will be <b>521632</b> (first digit of your application number will convert to 5) and if your name is ANKIT and your date of birth is 16-07-1988 then your password will be <b>ANK1988</b> (first 3 characters of your name and your year of birth).</label>
	<table style="text-align: right;width:80%;" cellspacing="8">
			<tr>
			<td>
			<form method="post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/transfer/getAppliedPrograms.htm")%>">
			<table cellspacing="5px">
			<tr>
    		<td width="50%">Application Number</td><td align="left" width="50%"><input type="text" name="applicationNumber" id="applicationNumber" onkeyup="changeStyle(this);" value="${requestScope.applicationNumber}" maxlength="6" autocomplete="off"/></td>
    		</tr>
    		<tr>
    		<td>Date of Birth</td><td align="left"><adm:date class="dateField" name="dd" id="dd" size="1" defaultText="-Date-" startIndex="1" endIndex="31" onchange="changeStyle(this);" selectedValue="${requestScope.dd}"/>-<adm:date class="dateField" name="mm" id="mm" size="1" defaultText="-Month-" startIndex="1" endIndex="12"  onchange="changeStyle(this);" selectedValue="${requestScope.mm}"/>-<adm:date class="dateField" name="yyyy" id="yyyy" size="1" defaultText="-Year-" startIndex="1950" endIndex="2015"  onchange="changeStyle(this);" selectedValue="${requestScope.yyyy}"/></td>
    		</tr>
    		<tr>
    		<td  align="center" colspan="2"><input type="submit" value="Get My Programs" class="customButton"/></td>
    		</tr>
    		</table>
    		</form>
			</td>
			</tr>
			
    		<tr>
    		<td align="center">
    		<c:choose>
			<c:when test="${showProgramsOption == 'yes'}">
			<table cellspacing="5px">
    		<form method="post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/transfer/applyTransferApplication.htm")%>">
    		<tr>
    		<td width="50%">Applied Program</td><td align="left" width="50%"><adm:select class="cwFields" name="appliedProgram" id="appliedProgram" optionList="${appliedPrograms}" defaultText="-Select Applied Program-" onchange="changeStyle(this);"/></td>
    		</tr>
    		<tr>
    		<td>Transfer To Program</td><td align="left"><adm:select class="cwFields" name="transferProgram" id="transferProgram" optionList="${transferPrograms}" defaultText="-Select Transfer Program-" onchange="changeStyle(this);"/></td>
    		</tr>
    		<tr>
    		<td  align="center" colspan="2"><input type="submit" value="Apply for Transfer" class="customButton"/><input type="hidden" name="appNumber" value="${requestScope.applicationNumber}"/></td>
    		</tr>
    		</form>
    		</table>
			</c:when>
			<c:otherwise>
			
			</c:otherwise>
			</c:choose>
			</td>
    		
    		</tr>
    		</table>
    	
     </div>
     </div>
 </div>
 <div class="footerDiv"><%@ include file="/templates/Footer.html" %></div>
</div>
</body>
</html>
