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

<title>Add Program</title>
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
<div class="panelHeader"><div class="panelTitle">Select Branches</div></div>
<div class="panelContent" align="center" style="padding-left: 20px;padding-right: 20px;">
	<label style="font-family:verdana;font-size:12px;color:red;">${requestScope.error}</label>
		<table style="text-align: right;width:80%;" cellspacing="8">
			<tr>
    		<td align="center">
    		<form method="post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/testing/applyMorePrograms.htm")%>">
			<table cellspacing="5px">
			<c:if test="${branchInP1 == 'Y'}">
			<tr>
			<th colspan="3" align="left">Branches for ${program1Name}</th>
			</tr>
    		<tr>
    		<td>Branch 1</td><td colspan="2"><adm:select class="cwFields" name="program1branches" optionList="${program1Branches}" defaultText="-Select Branch-" onchange="changeStyle(this);"/></td>
    		</tr>
    		<tr>
    		<td>Branch 2</td><td colspan="2"><adm:select class="cwFields" name="program1branches" optionList="${program1Branches}" defaultText="-Select Branch-" onchange="changeStyle(this);"/></td>
    		</tr>
    		<tr>
    		<td>Branch 3</td><td colspan="2"><adm:select class="cwFields" name="program1branches" optionList="${program1Branches}" defaultText="-Select Branch-" onchange="changeStyle(this);"/></td>
    		</tr>
    		</c:if>
    		
    		<c:if test="${branchInP2 == 'Y'}">
    		<tr>
			<th colspan="3" align="left">Branches for ${program2Name}</th>
			</tr>
    		<tr>
    		<td>Branch 1</td><td colspan="2"><adm:select class="cwFields" name="program2branches" optionList="${program2Branches}" defaultText="-Select Branch-" onchange="changeStyle(this);"/></td>
    		</tr>
    		<tr>
    		<td>Branch 2</td><td colspan="2"><adm:select class="cwFields" name="program2branches" optionList="${program2Branches}" defaultText="-Select Branch-" onchange="changeStyle(this);"/></td>
    		</tr>
    		<tr>
    		<td>Branch 3</td><td colspan="2"><adm:select class="cwFields" name="program2branches" optionList="${program2Branches}" defaultText="-Select Branch-" onchange="changeStyle(this);"/></td>
    		</tr>
    		</c:if>
    		<tr>
    		<td colspan="3"><input type="hidden" name="appNumber" value="<c:out value="${appNumber}"/>"/></td>
    		</tr>
    		<tr>
    		<td colspan="3"><input type="hidden" name="transferProgram1" value="<c:out value="${transferProgram1}"/>"/></td>
    		</tr>
    		<tr>
    		<td colspan="3"><input type="hidden" name="transferProgram2" value="<c:out value="${transferProgram2}"/>"/></td>
    		</tr>
    		<tr>
    		<td  align="center" colspan="2"><input type="submit" value="Add Program" class="customButton"/><input type="hidden" name="appNumber" value="${requestScope.applicationNumber}"/></td>
    		</tr>
    		</table>
			</form>		
			</td>
    		
    		</tr>
    		</table>
    	<label style="font-family:verdana;font-size:12px;color:red;">Please ensure your eligibility from prospectus before choosing programs, otherwise your application for additional programs may get rejected.</label>
     </div>
     </div>
 </div>
 <div class="footerDiv"><%@ include file="/templates/Footer.html" %></div>
</div>
</body>
</html>
