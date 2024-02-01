<!doctype html>
<html lang="en">
   <head>
   	<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
      <title>jQuery UI Tabs functionality</title>
      <link href="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/JQ/jquery-ui.css" %>" rel="stylesheet">
      <script src="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/JQ/jquery-1.10.2.js" %>"></script>
      <script src="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/JQ/jquery-ui.js" %>"></script>
           <link rel="stylesheet" type="text/css" href="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/css/style.css" %>">
   </head>
      
   <body style="font-family:verdana;">
      
      
<div class="bodyDiv">
<div class="headerDiv"><%@ include file="/templates/Header.jsp" %></div>
<div class="contentDiv" align="center">
   <%@ page isELIgnored="false" %>
    <div align="center" style="font-family:verdana;">
    <div style="font-weight:bold;color:red;">YOUR SESSION HAS EXPIRED.</div>
   	Please <a href="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/account/getApplicantLoginScreen.htm" %>">Click Here</a> to Login Again.
    </div>
       		
</div>
<div class="footerDiv"><%@ include file="/templates/Footer.html" %></div>
</div>
   </body>
</html>