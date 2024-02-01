<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html>
  <head>
    <title>PHASE SELECTIONS</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<link rel="stylesheet" type="text/css" href="../css/style.css">
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
  <div class="bodyDiv">
  <div class="headerDiv">  <%@ include file="/templates/Header.jsp" %>
  </div>
  <%@ include file="/templates/HorizontalNavigation.jsp" %>
  <div class="contentDiv"   id="bodyContent" style="margin-top:50px;">
  
   <div width="50%" align="center">
<div class="panelContainer" style="margin-bottom: 10px;padding-bottom:10px;marging-top:0px;margin-left:10px;margin-right:10px;width:800px;">
<div class="panelHeader"><div class="panelTitle">Phase Options</div></div>
<div class="panelContent" align="center" style="padding-left: 0px; padding-right: 0px;">
<label style="font-family: verdana;font-weight:bold;font-size:12px;color:red;">${requestScope.message}</label><br/>
<label style="font-family: verdana;font-weight:bold;font-size:14px;">Please Click the Phase Option : -</label>
 <ul style="font-weight:bold;color:green;">
	<li><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/account/getPhase1.htm")%>" class="downloadLink" style="font-weight:bold;color:green;font-family:verdana;">Application Details ( Admission Part -1 ).</a><label style="font-family: verdana;font-weight:bold;font-size:12px;color:red;">Last Date :- 01-03-2016</label></li>
	<li><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/account/getPhase2.htm")%>" class="downloadLink" style="font-weight:bold;color:green;font-family:verdana;">Fill the Academic Details ( Admission Part -2 ).</a><label style="font-family: verdana;font-weight:bold;font-size:12px;color:red;">Last Date :- 01-03-2016</label></li>
 </ul>
 </div>
</div>
</div>				
   
  </div>
  <div class="footerDiv"> <%@ include file="/templates/Footer.html" %></div>
  </div>
  
  <script src="../JQ/jquery-1.10.2.js"></script>
  <script src="${ProjectURL}/jsFiles/Properties.js"></script>
  </body>
</html>
