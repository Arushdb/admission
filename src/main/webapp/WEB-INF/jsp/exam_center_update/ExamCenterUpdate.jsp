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

<title>Admit Card Download</title>
<link href="${ProjectURL}/JQ/jquery-ui.css" rel="stylesheet">
<script src="${ProjectURL}/JQ/jquery-1.10.2.js"></script>
<script src="${ProjectURL}/JQ/jquery-ui.js"></script>
<script src="${ProjectURL}/jsFiles/Properties.js"></script>
<link rel="stylesheet" type="text/css" href="${ProjectURL}/css/style.css">

</head>
  
<body onload="getCountries();getStates();">
<div class="bodyDiv">
<div class="headerDiv"><%@ include file="/templates/Header.jsp" %></div>
<div class="contentDiv" align="center">
<div class="panelContainer"  style="width:800px;">
<div class="panelHeader"><div class="panelTitle">Exam Center Update</div></div>
<div class="panelContent" align="center" style="padding-left: 20px;padding-right: 20px;">
<!--<div align="left"><label style="font-family:verdana;font-size:12px;color:green;"><label style="color:red;font-weight:bold;">Please Note :- </label>The applicants who have applied for RET-March 2016 (Ph.D.) but have not deposited application fee<br/>so far, are advised to bring the application fee of 270 Rupees by demand draft in favour of Dayalbagh Educational Institute payable at Agra.<br/>Applicants can submit the draft up to 20th March 2016 (Before 8:30 AM) otherwise they will not be allowed to appear in examination and application form will be rejected.</label></div>-->
	<label style="font-family:verdana;font-size:12px;color:red;">${requestScope.error}</label>
	<table style="text-align: right;width:90%;" cellspacing="8">
			<tr>
			<td>
			<form method="post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/examcenter/updateExamCenter.htm")%>">
			<table cellspacing="5px">
			<tr>
			<td align="right">Application Number</td><td align="right"><input type="text" name="applicationNumber" id="applicationNumber" onkeyup="changeStyle(this)" autocomplete="off"/></td>
    		</tr>
    		<tr>
    		<td align="right">Password</td><td align="right"><input type="password" name="password" id="password" onkeyup="changeStyle(this)"/></td>
    		</tr>
    		<tr>
    		<td align="right">Examination Center</td><td align="right"><adm:select class="cwFields" name="examCenter" id="examCenter" optionList="${examCenters}" defaultText="-Select Examination Center-" onchange="changeStyle(this);"/></td>
    		</tr>
    		<tr>
    		<td  align="center" colspan="2"><input type="submit" value="Update" class="customButton"/></td>
    		</tr>
    		</table>
    		</form>
			</td>
			</tr>
			
    		<tr>
    		<td align="center">
    		
    		<table cellspacing="5px">
    		
    		<c:forEach  var="programWiseDetail" items="${requestScope.programWiseDetails}" varStatus="counter">
    		<c:if test="${counter.count==1}">
    		<tr><th align="left">Program Name</th><th  align="center">Admit Card Status</th><th>Link to DEI-MAP Direction</th></tr>
    		</c:if>
    		<form method="post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/account/downloadAdmitCard.htm")%>">
			<tr>
			<td  align="left">${counter.count}.&nbsp&nbsp<c:out value="${programWiseDetail.programName}"/><input type="hidden" name="admitCardPath" value="<c:out value="${programWiseDetail.admitCardPath}"/>"/></td>
			<td  align="center">
			<c:choose>
			<c:when test="${programWiseDetail.admitCardGenerated=='X'}">
			<input type="submit" value="Download Admit Card" class="customButton"/>
			</c:when>
			<c:otherwise>
			<label style="font-family:verdana;font-size:12px;color:red;">NOT YET GENERATED</label>
			</c:otherwise>
			</c:choose>
			</td>
			<td>
			<a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/resources/dei_route_map.pdf")%>">Download Map of venues in D.E.I.</a>
			</td>
			</tr>
			</form>
			</c:forEach>
		
    		</table>
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
