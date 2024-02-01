<!DOCTYPE html>
<html lang="en">
<head>
  <title>Login</title>
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

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!--<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>-->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
  $( function() {
    $( "#date_of_birth" ).datepicker({
changeYear: true, 
yearRange: "1980:2020",
dateFormat: "yy-mm-dd"
});
  } );
  </script>
</head>
<body>
<form method="post" action="getCertificatePreferenceScreen.htm">
<div class="container" style="margin-top:40px">
<center><h3 style="color:green">Certificate Program Preference</h3></center></br>
  <c:forEach var="error" items="${errors}" varStatus="counter">
    <div class="alert alert-danger">
  		<strong><c:out value='${counter.count}'/> Warning !</strong> <c:out value='${error}'/>
	</div>
  </c:forEach>
    
  <div class="card">
    <div class="card-header bg-info" style="font-weight: bold;color:white;">Login</div>
    <div class="card-body">
	<table cellpadding="5" cellspacing="5" width="80%">
	<tr>
	<td style="text-align:right">Application Number</td><td><input class="form-control" name="applicationNumber"/></td>
	</tr>
	<tr>
	<td style="text-align:right" valign="top">Date of Birth</td><td><input id="date_of_birth" class="form-control" name="password"/> YYYY-MM-DD</td>
	</tr>
	<tr>
	<td style="text-align:right">Which class have you passed ?</td>
	<td>
	<select  class="form-control" name="classPassed">
		<option value="08">8th</option>
		<option value="10">10th</option>
		<option value="12">12th</option>
	</select>
	</td>
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
