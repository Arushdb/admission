<!DOCTYPE html>
<html lang="en">
<head>
  <title>Application Login</title>
  <meta charset="utf-8">

<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@	taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
response.setHeader("Pragma","no-cache"); //HTTP 1.0 
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
%>

<script>
		function preventBack()
		{
			window.history.forward();
		}
  		setTimeout("preventBack()", 0);
  		window.onunload=function(){null};
</script>
  
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<form method="post" action="../report/getUploadDocumentsPanel.htm">
<div class="container" style="margin-top:40px">
  
  <c:forEach var="error" items="${errors}" varStatus="counter">
    <div class="alert alert-danger">
  		<strong><c:out value='${counter.count}'/> Warning !</strong> <c:out value='${error}'/>
	</div>
  </c:forEach>
    
  <div class="card">
    <div class="card-header bg-info" style="font-weight: bold;color:white;">Login for documents upload</div>
    <div class="card-body">
	<table cellpadding="5" cellspacing="5" width="80%">
	<tr>
	<td style="text-align:right">Application Number</td><td><input class="form-control" name="applicationNumber"/></td>
	</tr>
	<tr>
	<td style="text-align:right">Password</td><td><input type="password" class="form-control" name="password"/></td>
	</tr>
	<tr>
	<td colspan="2" style="text-align:center;"><input type="submit" value="Login" class="btn btn-info"/></td>
	</tr>
	</table>
	</div> 
    
  </div>
</div>
</form>
</body>
</html>
