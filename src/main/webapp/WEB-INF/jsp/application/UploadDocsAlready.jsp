<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>

</script>
	
	
	
<div width="100%" align="center">
 	<div class="panelContainer" style="width:100%;">
  	<div class="panelHeader"><div class="panelTitle">Upload Photo and Signature</div></div>
  	<div class="panelContent" align="center" style="padding-bottom:20px;padding-top:20px;">
  	  	<h5 align="right" color="blue"><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/common/generateApplicationPdf.htm")%>">Download Application PDF</a></h5>
<!-- 
<c:choose>
<c:when test="${sessionScope.applicant.applicationForm.formNumber == '0002'}">
</c:when>
<c:otherwise>  	
  	<div align="center">
<table style=" font-family: Arial, Helvetica, sans-serif;  border-collapse: collapse;  width: 80%;"  cellpadding="4" cellspacing="4">
<tr>
<th colspan="5" style="text-align:left;">Your exam details are as below:-<br/><hr/></th>
</tr>
<tr>
<th>Application Number</th><th>Program</th><th>Passkey</th><th>Exam Date and Time</th><th>Exam Link</th>
</tr>
<c:forEach var="programWiseDetail" items="${sessionScope.applicant.applicationForm.programWiseDetails}">
<tr style="text-align:center;color:green;font-weight:bold">
<td>${programWiseDetail.applicationNumber}</td><td>${programWiseDetail.programName}</td><td>${programWiseDetail.passkey}</td><td>${programWiseDetail.examDate}</td><td><a href="http://cocubes.in/dei2021">http://cocubes.in/dei2021</a></td>
</tr>
</c:forEach>
<tr>
<td colspan = "5"><hr/></td>
</tr>
</table>
</div>
</c:otherwise></c:choose> -->
  	<form method="post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/upload/uploadDocument.htm")%>" enctype="multipart/form-data">	
 	<table>
 	<tr>
 	<td colspan="2" align="center">You have already uploaded photo and signature</td>
 	</tr> 
 	</table>
 	</form>
 	<div style="font-family:verdana;color:red;font-weight:bold;">
  							${message}
  							</div>	
	</div>
	</div>
  	</div>  
