<!DOCTYPE html>
<html lang="en">
<head>
  <title>Upload Documents</title>
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
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<div class="container" style="margin-top:40px">

<div align="right" style="margin-bottom:5px;">
<a href="../report/logout.htm" style="font-weight: bold;">Logout <i class="fa fa-power-off" style="font-size:36px;color:green"></i></a>
</div>

  <form method="post" action="../report/submitPreference.htm"  enctype="multipart/form-data"> 
  <div class="card">
    <div class="card-header bg-info" style="font-weight: bold;color:white;">Certificate Program Preference</div>
    <div class="card-body">
    
    <c:if test="${message != ''}">
    	<div class="alert alert-success alert-dismissible">
  			<button type="button" class="close" data-dismiss="alert">&times;</button>
  			<strong> <c:out value='${message}'/></strong>
		</div>
    </c:if>
    
    
   	
	<table cellpadding="5" cellspacing="5" width="80%">
	<tr>
	<td style="text-align:right">Application Number</td>
	<td align="left"><b><c:out value='${applicant.applicationNumber}'/></b></td>
	</tr>
	<tr>
	<td style="text-align:right">Name</td>
	<td align="left"><b><c:out value='${applicant.applicantName}'/></b></td>
	</tr>
	<tr>
	<td style="text-align:right">Father's Name</td>
	<td align="left"><b><c:out value='${applicant.fatherName}'/></b></td>
	</tr>
	
	<tr>
	<td style="text-align:right">Preference 1</td>
	<td align="left">
	<b><c:out value='${applicant.pref1}'/>
	</td>
	</tr>
	<tr>
	<td style="text-align:right">Preference 2</td>
	<td align="left">
	<b><c:out value='${applicant.pref2}'/>
	</td>
	</tr>
	<tr>
	<td style="text-align:right">Preference 3</td>
	<td align="left">
	<b><c:out value='${applicant.pref3}'/>
	</td>
	</tr>
	
	<tr>
	<td colspan="2"><a href="https://admission.dei.ac.in:8445/admissionform/report/getCertificatePreferenceLogin.htm">Go to Home</a></td>
	</tr>
	</table>
	</div> 
    
   
  </div>
  </form>
  
       
</div>

<script>

$(".custom-file-input").on("change", function() {
  var fileName = $(this).val().split("\\").pop();
  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});
</script>

</body>
</html>
