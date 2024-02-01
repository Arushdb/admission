<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

<!doctype html>
<html>
<head>
   	<title>Applicant Login</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
<link href="${ProjectURL}/JQ/jquery-ui.css" rel="stylesheet">
<script src="${ProjectURL}/JQ/jquery-1.10.2.js"></script>
<script src="${ProjectURL}/JQ/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="${ProjectURL}/css/style.css">
<script src="${ProjectURL}/jsFiles/Properties.js"></script>
<script>
function preventBack()
{
   window.history.forward();
}
setTimeout("preventBack()", 0);
window.onunload=function()
{
   //$.post( props.projectURL+"/account/closeSession.htm", function() {
   //window.location(props.projectURL);
   null;
});
};
</script>
		
<script>
$(function() 
{
	var tabStatus = ${sessionScope.tabStatus}; //tabStatus is actual tab status 
	var tabToOpenSTR = document.getElementById("tabToOpen").value; //It is returned from server side, user forcefully wants to open this tab whatever the actual tab status is
	var activeTab = null;
        
	if(tabToOpenSTR == null || tabToOpenSTR == "" || parseInt(tabToOpenSTR) == "NaN")
	{
		activeTab = parseInt(tabStatus);
	}
	else
	{
		activeTab = parseInt(tabToOpenSTR);
	}
        
	if(tabStatus == "0")
	{
		$("#tabs-1").tabs({ disabled: [ 1, 2, 3, 4 ], active:activeTab});
	}
	else if(tabStatus == "1")
	{
		$("#tabs-1").tabs({ disabled: [ 2,3, 4 ], active:activeTab});
	}
	else if(tabStatus == "2")
	{
		$("#tabs-1").tabs({ disabled: [3,4], active:activeTab});
	}
	else if(tabStatus == "3")
	{
		$("#tabs-1").tabs({ disabled: [4], active:activeTab});
	}
	else if(tabStatus == "4")
	{
		$("#tabs-1").tabs({active:activeTab});
	}
    else
    {
		$("#tabs-1").tabs({ disabled: [ 0,1,2,3,4 ]});
	}
});
</script>

</head>
  
<body>
<div class="bodyDiv">

   <div class="headerDiv"><%@ include file="/templates/Header.jsp" %>
      <c:if test="${sessionScope.applicant.applicantName!=null}">
        <label style="position:absolute;right:5px;top:100px;z-index:2;font-family:verdana;font-size:12px;color:white;">Welcome : ${sessionScope.applicant.applicantName}</label>
      </c:if>
   </div>

<div class="contentDiv">
   <div id="tabs-1">
      <%@ include file="/templates/TABS.jsp" %>
   </div>
   <input type="hidden" id="tabToOpen" value="<c:out value='${tabToOpen}'/>"/>
</div>

<div class="footerDiv"> 
   <%@ include file="/templates/Footer.html" %>
</div>

</div>
</body>
</html>
