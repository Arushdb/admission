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

<title>Application Status</title>


<link rel="stylesheet" type="text/css" href="${ProjectURL}/css/style.css">

<script>
		function preventBack()
		{
			window.history.forward();
		}
  		setTimeout("preventBack()", 0);
  		window.onunload=function(){null};
	</script>
</head>
  
<body onload="getCountries();getStates();">
<div class="bodyDiv">
<div class="headerDiv"><%@ include file="/templates/Header.jsp" %></div>
<div class="contentDiv" align="center">
<div class="panelContainer"  style="width:800px;">
<div class="panelHeader"><div class="panelTitle">Application Status</div></div>
<div class="panelContent" align="center" style="padding-left: 20px;padding-right: 20px;">
	<form method="post" action="${ProjectURL}/application/getApplicationStatus.htm" onsubmit="return valid();" id="accountForm">
	<ul style="font-family:verdana;font-size:12px;color:red;">
	<c:forEach var="error" items="${errors}">
	<li>${error}</li>
	</c:forEach>
	</ul>
	<label style="font-family:verdana;font-size:12px;color:green;">Please note that Payment Verification Process completes after 3 working days.</label>
	<table style="text-align: right;width:80%;" cellspacing="8">
    		<tr>
    		<td width="50%">Application Number</td><td align="left" width="50%"><input type="text" name="applicationNumber" id="applicationNumber" onkeyup="changeStyle(this);" value="${requestScope.applicationDetail.applicationNumber}" maxlength="6" autocomplete="off"/></td>
    		</tr>
    		<tr>
    		<td  align="right"><input type="submit" value="Get Application Status" class="customButton"/></td><td align="left"><input type="button" value="Back to Home" class="customButton" onclick="getHomePage();"/></td>
    		</tr>
    		</table>
    	</form>
    	<c:if test="${not empty requestScope.applicationDetail}">
    	<fieldset><legend style="font-family: verdana;font-size:10px;">Application Status</legend>
    	<table>
    	<tr>
    	<th>Applicant Name</th><td>:</td><td style="font-size: 12px;">${requestScope.applicationDetail.applicantName}</td>
    	</tr>
    	<tr>
    	<th>Application Status</th><td>:</td><td style="font-size: 12px; color: green;font-weight: bold;">${requestScope.applicationDetail.vrfStatusDesc}</td>
    	</tr>
    	<tr>
    	<th>Father Name</th><td>:</td><td style="font-size: 12px;">${requestScope.applicationDetail.fatherName}</td>
    	</tr>
    	<tr>
    	<th>Mother Name</th><td>:</td><td style="font-size: 12px;">${requestScope.applicationDetail.motherName}</td>
    	</tr>
    	</table>
    	</fieldset>
    	</c:if>
     </div>
     </div>
 </div>
 <div class="footerDiv"><%@ include file="/templates/Footer.html" %></div>
</div>

<script src="${ProjectURL}/JQ/jquery-1.10.2.js"></script>
<script src="${ProjectURL}/jsFiles/Properties.js"></script>
<script src="${ProjectURL}/jsFiles/Account.js"></script>
</body>
</html>