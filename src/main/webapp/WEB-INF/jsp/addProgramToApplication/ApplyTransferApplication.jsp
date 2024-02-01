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
<script type="text/javascript">
  	function getStudyCenters(element)
  	{
  		var str = document.getElementById(element).value;
  		$.post("${ProjectURL}/application/getStudyCenters.htm",{ programID: element, educationMode: str })
  		//$.post("http://localhost:8080/NEW_ADMISSION/application/getStudyCenters.htm",{ programID: element, educationMode: str })
			.done(function( data ) {
			document.getElementById(element+"_div").innerHTML=data;
		});
  	}
  	function enableSecond()
  	{
  	var x=document.getElementById("transferProgram2");
    x.disabled=false;
  	}
</script>
</head>
  
<body onload="getCountries();getStates();">
<div class="bodyDiv">
<div class="headerDiv"><%@ include file="/templates/Header.jsp" %></div>
<div class="contentDiv" align="center">
<div class="panelContainer"  style="width:800px;">
<div class="panelHeader"><div class="panelTitle">Add Program</div></div>
<div class="panelContent" align="center" style="padding-left: 20px;padding-right: 20px;">
	<label style="font-family:verdana;font-size:12px;color:red;">${requestScope.error}</label>
		<table style="text-align: right;width:80%;" cellspacing="8">
			<tr>
			<td>
		<!-- 	<form method="post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/transfer/getAppliedPrograms.htm")%>">
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
    		</form>-->
			</td>
			</tr>
			
    		<tr>
    		<td align="center">
    		<c:choose>
			<c:when test="${showProgramsOption == 'yes'}">
			<table cellspacing="5px">
    		<form method="post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/testing/showBranches.htm")%>">
    		<tr>
    		<td width="50%">Already Applied Program</td><td align="left" width="50%"  colspan="3">
    		     <ul>
            <c:forEach items="${appliedPrograms}"  var="appliedPrograms">
                <li>${appliedPrograms.description}</li>
            </c:forEach>
        </ul>

    		
    		
    		</td>
    		</tr>
    		<c:if test="${optionCount ==2}">    		
    		<tr>
    		<td>Additional Program Options</td>
    		<!-- add if statement here -->
    		<td align="left"><adm:select class="cwFields" name="transferProgram1" id="transferProgram1" optionList="${transferPrograms}" defaultText="-Select Program preference 1-" onchange="changeStyle(this);enableSecond()" required="true"/></td>
    		<tr>
    		<td> 
    		</td>
    		<td align="left"><adm:select class="cwFields" name="transferProgram2" id="transferProgram2" optionList="${transferPrograms}" defaultText="-Select Program preference 2-" onchange="changeStyle(this);" disabled="disabled"/></td>
    		</tr>
    		</tr>
    		<tr>
    		<td  align="center" colspan="2"><input type="submit" value="Add Program" class="customButton"/><input type="hidden" name="appNumber" value="${requestScope.applicationNumber}"/></td>
    		</tr>
    		</c:if>
    		<c:if test="${optionCount ==1}">    		
    		<tr>
    		<td>Additional Program Option</td>
    		<!-- add if statement here -->
    		<td align="left"><adm:select class="cwFields" name="transferProgram2" id="transferProgram2" optionList="${transferPrograms}" defaultText="-Select Program preference 2-" onchange="changeStyle(this);" required="true"/></td>
    		</tr>
    		<tr>
    		<td  align="center" colspan="2"><input type="submit" value="Add Program" class="customButton"/><input type="hidden" name="appNumber" value="${requestScope.applicationNumber}"/></td>
    		</tr>
    		</c:if>
    		</form>
    		</table>
			</c:when>
			<c:otherwise>
			
			</c:otherwise>
			</c:choose>
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
