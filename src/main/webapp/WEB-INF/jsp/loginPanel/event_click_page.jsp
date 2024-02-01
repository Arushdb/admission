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
	 <div id ="menuDiv" > <%@ include file="/UserAuthority.jsp" %></div> 
  </div>
  <div class="footerDiv"> <%@ include file="/templates/Footer.html" %></div>
  </div>
  
  
  </body>
</html>
