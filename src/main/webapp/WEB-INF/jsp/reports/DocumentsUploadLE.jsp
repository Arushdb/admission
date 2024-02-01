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
<a href="../report/logoutLE.htm" style="font-weight: bold;">Logout <i class="fa fa-power-off" style="font-size:36px;color:green"></i></a>
</div>

  <form method="post" action="../report/uploadDocumentsLE.htm"  enctype="multipart/form-data"> 
  <div class="card">
    <div class="card-header bg-info" style="font-weight: bold;color:white;">Upload Documents</div>
    <div class="card-body">
    
    <c:if test="${message != ''}">
    	<div class="alert alert-success alert-dismissible">
  			<button type="button" class="close" data-dismiss="alert">&times;</button>
  			<strong> <c:out value='${message}'/></strong>
		</div>
    </c:if>
    
    
    <input type="hidden" name="applicationNumber" value = "<c:out value='${sessionScope.applicant.applicationNumber}'/>"/>
    <input type="hidden" name="password" value = "<c:out value='${sessionScope.applicant.password}'/>"/>
	
	<table cellpadding="5" cellspacing="5" width="80%">
	<tr>
	<td style="text-align:right">Application Number</td>
	<td align="left"><b><c:out value='${sessionScope.applicant.applicationNumber}'/></b></td>
	</tr>
	<tr>
	<td style="text-align:right">Name</td>
	<td align="left"><b><c:out value='${sessionScope.applicant.applicantName}'/></b></td>
	</tr>
	<tr>
	<td style="text-align:right">Father's Name</td>
	<td align="left"><b><c:out value='${sessionScope.applicant.fatherName}'/></b></td>
	</tr>
	<tr>
	<td colspan="2" valign="middle"><h6 class="text-center text-danger">Please submit picture(jpeg)/pdf of the most significant certificate (the highest level certificate) in the following categories.</h6></td>
	</tr>
	<tr>
	<td colspan="2" valign="middle"><img src="../resources/HI.jpg" style="width:1000px"></img></td>
	</tr>
	<tr>
	<td colspan="2"><h6 class="text-center text-danger">Maximum allowed size is 250KB for each file.</h6></td>
	</tr>
	<tr>
	<td style="text-align:right">Co-curricular Activity (Games, Culture Activities ...)</td>
	<td align="left">
		<c:choose>
		<c:when test="${sessionScope.applicant.ccaUploaded == 'Y'}">
			<i class="fa fa-check-square-o" style="font-size:36px;color:green"></i>
		</c:when>
		<c:otherwise>
		<div class="custom-file">
    		<input type="file" class="custom-file-input" id="cca" name="cca">
    		<label class="custom-file-label" for="cca">Choose file</label>
  		</div>
		</c:otherwise>
		</c:choose>
		
  	</td>
	</tr>
	<tr>
	<td style="text-align:right">NCC</td>
	<td align="left">
		<c:choose>
		<c:when test="${sessionScope.applicant.nccUploaded == 'Y'}">
			<i class="fa fa-check-square-o" style="font-size:36px;color:green"></i>
		</c:when>
		<c:otherwise>
		<div class="custom-file">
    		<input type="file" class="custom-file-input" id="ncc" name="ncc">
    		<label class="custom-file-label" for="ccc">Choose file</label>
  		</div>
		</c:otherwise>
		</c:choose>
		
  	</td>
	</tr>
	<tr>
	<td style="text-align:right">NSS/Scouting/Guiding</td>
	<td align="left">
		<c:choose>
		<c:when test="${sessionScope.applicant.nssUploaded == 'Y'}">
			<i class="fa fa-check-square-o" style="font-size:36px;color:green"></i>
		</c:when>
		<c:otherwise>
		<div class="custom-file">
    		<input type="file" class="custom-file-input" id="nss" name="nss">
    		<label class="custom-file-label" for="nss">Choose file</label>
  		</div>
		</c:otherwise>
		</c:choose>
		
  	</td>
	</tr>
	<tr>
	<td style="text-align:right">Social Service/Community Service/Participation in Pioneer Value Oriented Institutions</td>
	<td align="left">
		<c:choose>
		<c:when test="${sessionScope.applicant.socialUploaded == 'Y'}">
			<i class="fa fa-check-square-o" style="font-size:36px;color:green"></i>
		</c:when>
		<c:otherwise>
		<div class="custom-file">
    		<input type="file" class="custom-file-input" id="social" name="social">
    		<label class="custom-file-label" for="social">Choose file</label>
  		</div>
		</c:otherwise>
		</c:choose>
		
  	</td>
	</tr>
	<tr>
	<td colspan="2" style="text-align:center;">
	<c:choose>
		<c:when test="${sessionScope.applicant.nssUploaded == 'Y' && sessionScope.applicant.nccUploaded == 'Y' && sessionScope.applicant.ccaUploaded == 'Y' && sessionScope.applicant.socialUploaded == 'Y'}">
			
		</c:when>
		<c:otherwise>
		<div class="custom-file">
    		<input type="submit" value="Upload" class="btn btn-info"/></td>
  		</div>
		</c:otherwise>
		</c:choose>
	
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
