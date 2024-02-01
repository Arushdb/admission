<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

<!doctype html>
<html>
  <head>
    <title>User Form</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<script src="${ProjectURL}/JQ/jquery-1.10.2.js"></script>
	<script src="${ProjectURL}/jsFiles/Properties.js"></script>
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
  
  <body>
  <script>
  	function getHomePage()
	{
		window.location.replace(props.projectURL);
	}
  </script>
  <div class="bodyDiv">
  <div class="headerDiv">  <%@ include file="/templates/Header.jsp" %></div>
  <div class="contentDiv"   id="bodyContent" align="center">
  <!-- User Form Panel Start Here -->
 	<div class="panelContainer"  style="width:800px;">
								<div class="panelHeader"><div class="panelTitle">Download User Form</div></div>
								<div class="panelContent" align="center" style="padding-left: 20px;padding-right: 20px;">
								<center>
								<label style="color:red;font-family:verdana;">${error}</label><br/>
								<label style="color:red;font-family:verdana;font-size:14px;"><b>Important Note :-</b> For applying offline, candidate has to enable the MACROS in Excel File. ( Check the Instruction Link given below )</label>
								</center>
								<form action="${ProjectURL}/userform/combodata2.htm" method="post" onsubmit="return validateDetails();" id="offlineform"> 
    								<ul>
    								<li><a href="#" onclick="document.getElementById('offlineform').submit();" class="actionLink" style="font-size: 14px;">Click Here to Download the Offline Application Form</a></li>
    								<li><a href="#" onclick="window.open(props.projectURL+'resources/HELPS.zip');" class="actionLink" style="font-size: 14px;">Instructions for enabling Macros in User Form (Offline).</a></li>
    								</ul>
    								
    							</form>
    							
    							<form action="${ProjectURL}/userform/combodata1.htm" method="post" enctype="multipart/form-data">
    							<table cellpadding="2" border="0">
    							<tr>
    							<td>Upload the Off Line Application Form</td><td><input type="file" name="file" size="50" /></td>
    							</tr>
    							<tr>
    							<td align="right"><input type="submit" value="Upload File" class="customButton"/></td><td align="left"><input type="button" value="Back to Home" class="customButton" onclick="getHomePage();"/></td>
    							</tr>
    							</table>
    							</form>
  								<center style="font-family:verdana;font-size:14px;">${accountMessage}</center>
								</div>
								</div>
 	<!-- User Form Panel Ends Here -->  	
  </div>
  <div class="footerDiv"> <%@ include file="/templates/Footer.html" %></div>
  </div>
  
  
  </body>
</html>
