<!-- 
Author	:	Arjun Singh Chauhan
Purpose	:	For an Applicant to create his/her Account 
Date	:	14-02-2014
 -->
<!doctype html>
<html lang="en">
<head>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@	taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
response.setHeader("Pragma","no-cache"); //HTTP 1.0 
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
%>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

<title>Account Creation</title>
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
<div class="panelHeader"><div class="panelTitle">Register To Apply</div></div>
<div class="panelContent" align="center" style="padding-left: 20px;padding-right: 20px;">
	<ul style="font-family:verdana;font-size:12px;color:red;">
	<c:forEach var="error" items="${errors}">
	<li>${error}</li>
	</c:forEach>
	</ul>
	<form method="post" action="${ProjectURL}/account/createAccount.htm" onsubmit="return valid();" id="accountForm">
	<table style="text-align: right;width:80%;" cellspacing="8">
    		<tr>
    		<td width="50%">Applicant Name</td><td align="left" width="50%"><input type="text" name="applicantName" id="applicantName" onkeyup="changeStyle(this);" value="${requestScope.applicant.applicantName}" maxlength="100" autocomplete="off"/></td>
    		</tr>
   			<tr>
    		<td>Email ID</td><td align="left"><input type="text" name="emailID" id="emailID" onkeyup="changeStyle(this)" value="${requestScope.applicant.primaryEmailID}" maxlength="100" autocomplete="off"/></td>
    		</tr>
    		<tr>
    		<td>Date of Birth</td><td align="left"><adm:date class="dateField" name="dd" id="dd" size="1" defaultText="-Date-" startIndex="1" endIndex="31" onchange="changeStyle(this);"/>-<adm:date class="dateField" name="mm" id="mm" size="1" defaultText="-Month-" startIndex="1" endIndex="12"  onchange="changeStyle(this);"/>-<adm:date class="dateField" name="yyyy" id="yyyy" size="1" defaultText="-Year-" startIndex="1950" endIndex="2015"  onchange="changeStyle(this);"/></td>
    		</tr>
    		<tr>
    		<td>Category</td>
    						<td align="left">
    						<adm:select class="cwFields" name="category" id="category" optionList="${categories}" defaultText="-Select Category-" onchange="changeStyle(this);"/>
    						</td>
    		</tr>
    		<tr>
    		<td>Gender</td><td align="left"><input type="radio" name="gender" value="M"/>Male<input type="radio" name="gender" value="F"/>Female</td>
   	 		</tr>
   			<tr>
    		<td>Country</td><td align="left"><select class="cwFields" id="country" name="country" onchange="changeStyle(this); handleStateCB();"><option value="">-Select Country-</option></select></td>
    		</tr>
     		<tr>
    		<td>Contact Number</td><td align="left"><input type="text" name="phoneNo" maxlength="12"  id="phoneNo" onkeyup="changeStyle(this)" value="${requestScope.applicant.homePhone}" autocomplete="off"/></td>
    		</tr>
   			<tr>
    		<td>Security Question</td>
    								<td align="left">
    								<select class="cwFields" name="question" id="question" onchange="changeStyle(this)"><option value="0">-Select Question-</option>
    								<c:forEach var="question" items="${sQuestions}">
    								<option value="<c:out value='${question.code}'/>"><c:out value="${question.description}"/></option>
    								</c:forEach>
    								</select>
    								</td>
    		</tr>
    		<tr>
    		<td>Answer</td><td align="left"><input type="text" name="answer" id="answer" onkeyup="changeStyle(this)" value="${requestScope.applicant.answer}" autocomplete="off"/></td>
    		</tr>
    		<tr>
    			<td colspan="2" align="center"><img id="capimg" src="${ProjectURL}/SimpleCaptcha.jpg"/><br/>
    			<input type="button" value="Change Code" onclick="changeCaptchaCode();"/>
    			</td>
    		</tr>
    		<tr>
    		<td align="right">Enter the Code given above</td><td align="left"><input type="text" name="captchaValue" id="captchaValue" onkeyup="changeStyle(this)" autocomplete="off"/></td>
    		</tr>
    		<tr>
    		<td  align="right"><input type="submit" id="createAccountBtn" value="Create Account" class="customButton"/></td><td align="left"><input type="button" value="Back to Home" class="customButton" onclick="getHomePage();"/></td>
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
