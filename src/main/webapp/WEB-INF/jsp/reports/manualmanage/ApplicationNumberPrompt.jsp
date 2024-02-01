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

<script type="text/javascript" language="javascript">
function valid()
{
	var applicationNumber = document.getElementById("applicationNumber").value;
	if(applicationNumber == null)
	{
		alert("PLEASE ENTER APPLICATION NUMBER.");
		return false;
	}
	return true;
}
</script>
</head>
  
<body onload="getCountries();getStates();">
<div class="bodyDiv">
<div class="headerDiv"><%@ include file="/templates/Header.jsp" %></div>
<div class="contentDiv" align="center">
<div class="panelContainer"  style="width:800px;">
<div class="panelHeader"><div class="panelTitle">Application Number</div></div>
<div class="panelContent" align="center" style="padding-left: 20px;padding-right: 20px;">
	<form method="post" action="${ProjectURL}/manualmanage/getEditScreen.htm" onsubmit="return valid();" id="accountForm">
	
	<label style="font-family:verdana;font-size:12px;color:red;">${error}</label>
	
	<table style="width:80%;" cellspacing="8">
    		<tr>
    		<td width="50%" align="right">Application Number</td><td align="left" width="50%"><input type="text" name="applicationNumber" id="applicationNumber" onkeyup="changeStyle(this);"  maxlength="6" autocomplete="off"/></td>
    		</tr>
    		<tr>
    		<td colspan="2" align="center"><input type="submit" value="Get Details" class="customButton"/></td>
    		</tr>
    		</table>
    	</form>
    	
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