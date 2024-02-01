<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@page import="in.ac.dei.edrp.admissionsystem.common.beans.Applicant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />
<!-- 
<link rel="stylesheet" href="${ProjectURL}/resources/bootstrap4/css/bootstrap.min.css">
<script src="${ProjectURL}/resources/bootstrap4/js/bootstrap.min.js"></script> -->

    <script>
      function setPaymentModeVisibility(param)
{
if(param=="DD")
{
document.getElementById("ddDiv").style.display="block";
document.getElementById("challanDiv").style.display="none";
document.getElementById("onlinePaymentDiv").style.display="none";
//document.getElementById("cashDiv").style.display="none";
}
else if(param=="OP")
{  
document.getElementById("ddDiv").style.display="none";
document.getElementById("challanDiv").style.display="none";
document.getElementById("onlinePaymentDiv").style.display="block";
//document.getElementById("cashDiv").style.display="none";

}
/*else if(param=="CH")
{
document.getElementById("ddDiv").style.display="none";
document.getElementById("cashDiv").style.display="block";
document.getElementById("onlinePaymentDiv").style.display="none";
document.getElementById("challanDiv").style.display="none";

}*/
else if(param=="VC")
{
document.getElementById("ddDiv").style.display="none";
document.getElementById("challanDiv").style.display="block";
document.getElementById("onlinePaymentDiv").style.display="none";
//document.getElementById("cashDiv").style.display="none";

}
}
</script>
<style>
.year_sem_combo {
  width: 100%;
  height:35px;
  padding-left:5px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
  font-size:10px;
}

.university_input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

.digit_input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

</style>
<div width="100%" align="center">
<div class="panelContainer" style="width:80%;">
	<div class="panelHeader">
		<div class="panelTitle">Payment
		</div>
	</div>
<div class="panelContent" align="center" style="padding-bottom: 20px;padding-top:20px;">
<div><!-- 
<div class="container">
<div class="card">
  <div style="color:white;font-weight:bold;" class="card-header bg-primary">Payment and Year/Semester wise marks</div>
  <div class="card-body"> -->
  
<ul style="font-family: verdana; color:red;font-size:12px;font-weight:bold;list-style-type: none;">
  <li>${message}</li>
</ul>

<div style="text-align: center;padding-right:30px;padding-bottom:10px;padding-top:0px;font-weight:bold;color:green;">
<c:set var='verificationStatus' value="REC"/>

<c:choose>
<c:when test="${sessionScope.applicant.applicationForm.formNumber == '0001'}">
<c:set var='examinationName' value="Graduation (UG)"/>
</c:when>
<c:otherwise>
<c:set var='examinationName' value="Intermediate"/>
</c:otherwise>
</c:choose>

<form method = "post" action = "<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/application/saveYearSemWiseMarks.htm")%>">
<c:choose>
<c:when test="${marksToAsk == 'Y'}">

<table class="table" style="width: 100%">

<thead>
<!-- Uncommented on 8/5/2021 -->
<tr>
<th style="text-align: left;" colspan="5">Enter year wise or semester wise marks/sgpa of your <label style="color:red;">${examinationName}</label>.</th>
</tr>
<tr>
<th colspan="5"><hr/></th>
</tr>
<tr style="text-align: left">
<th>Year/Semester</th><th>Obtained <br/>Marks/CGPA</th><th>Out of <br/>Marks/CGPA</th><th>University/Board</th><th>Passing Year</th>
</tr><!--Uncommented on 8/5/2021 -->
</thead>

<tbody>
<!-- Uncommented on 8/5/2021 -->
<c:forEach var="yearSemWiseMarks" items="${yearSemWiseMarksList}" varStatus="counter">
<tr style="text-align: left">
<td><adm:select class="year_sem_combo" name="yearSem" optionList="${embkups}" selectedValue="${yearSemWiseMarks.yearSemester}" defaultText="-Select Year/Semester-" onchange="changeStyle(this);"/>
<input type="hidden" name="examination" value="<c:out value='${yearSemWiseMarks.examination}'/>"/>
<input type="hidden" name="appNumber" value="<c:out value='${yearSemWiseMarks.applicationNumber}'/>"/></td>
<td><input class="digit_input"  autocomplete="off" name="obtainedMarks"  style="width:100px;" value="<c:out value='${yearSemWiseMarks.obtainedMarks}'/>"/></td>
<td><input class="digit_input"  autocomplete="off" name="totalMarks" style="width:100px;" value="<c:out value='${yearSemWiseMarks.totalMarks}'/>"/></td>
<td><input class="university_input" autocomplete="off" name="university" maxlength="100" style="width:160px;" value="<c:out value='${yearSemWiseMarks.university}'/>"/></td>
<td><input class="digit_input" type="number"  min="1900" max="2020" autocomplete="off" name="passingYear" maxlength="4" style="width:160px;" value="<c:out value='${yearSemWiseMarks.passingYear}'/>"/></td>
</tr>

</c:forEach><!-- Uncommented on 8/5/2021 -->
<tr>
<td style="vertical-align: top;text-align: left;"><!-- Uncommented on 8/5/2021 --> <input type = "submit" value = "Save/Update Marks" class = "customButton"/><!-- Uncommented on 8/5/2021 --></td>
<td colspan="4" style="vertical-align: top;text-align: left;">
<c:choose>
<c:when test="${sessionScope.applicant.verificationStatus!=verificationStatus}">
	Your Application Fee is Rs. <b><label style="color:red;">${fees}</label></b><br/>
	Your Application Fee Payment Password is : <b><label style="color:red;">${applicant.feePassword}</label></b>
	<br/>
	<!-- <a href="<%=response.encodeURL("https://www.onlinesbi.com/sbicollect/icollecthome.htm")%>" target="_blank" class="downloadLink" style="font-weight:bold;color:green;"><u>PLEASE CLICK HERE TO MAKE YOUR PAYMENT.</u></a> -->
	<a href="<%=response.encodeURL("https://admission.dei.ac.in/epay80_testing/#/applicationfee/"+((Applicant)request.getAttribute("applicant")).getApplicationNumber())%>" target="_blank" class="downloadLink" style="font-weight:bold;color:green;"><u>PLEASE CLICK HERE TO MAKE YOUR PAYMENT.</u></a>
<br/><br/><div style="text-align: left;">
	<label style="font-family:verdana;font-size:12px;color:red;font-weight: bold;">Important note for P.G. DIPLOMA (THEOLOGY) :</label>
	<ul style="font-family:verdana;font-size:12px;color:green;">
	<li>Application fee for foreign candidates for <b style="color:red;">P.G. DIPLOMA (THEOLOGY)</b> is <b style="color:red;">1000 US Dollars</b>.</li>
	</ul>
	</div>
</c:when>
<c:otherwise>
	<label style="font-weight:bold;color:green;font-family:verdana;font-size:16px;">YOUR PAYMENT HAS BEEN RECEIVED SUCCESSFULLY.</label>
</c:otherwise>
</c:choose>
<br/><br/>
<a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/common/generateApplicationPdf.htm")%>" class="downloadLink" style="font-family:verdana;font-size: 12px;font-weight:bold;color:brown;"><u>Click here to download your application pdf.</u></a>
</td>
</tr>
</tbody>
</table>

</c:when>
<c:otherwise>
<c:choose>
<c:when test="${sessionScope.applicant.verificationStatus!=verificationStatus}">
	Your Application Fee is Rs. <b><label style="color:red;">${fees}</label></b><br/>
	Your Application Fee Payment Password is : <b><label style="color:red;">${applicant.feePassword}</label></b>
	<br/>
	
	
	
	<!-- <a href="<%=response.encodeURL("https://www.onlinesbi.com/prelogin/icollecthome.htm?corpID=379179")%>" target="_blank" class="downloadLink" style="font-weight:bold;color:green;"><u>PLEASE CLICK HERE TO MAKE YOUR PAYMENT.</u></a> -->
	
		
	<% String payurl="https://admission.dei.ac.in/epay80_testing/#/applicationfee/"
	+session.getAttribute("applicationNumber");
	
	//+(String) pageContext.getAttribute("appno")
	;
	out.println(payurl);
	
	%>
	
	<a href="<%=payurl %>" target="_blank"  style="font-weight:bold;color:green;">E-Payment:PLEASE CLICK HERE TO MAKE YOUR PAYMENT.</a>
</c:when>
<c:otherwise>
	<label style="font-weight:bold;color:green;font-family:verdana;font-size:16px;">YOUR PAYMENT HAS BEEN RECEIVED SUCCESSFULLY.</label>
</c:otherwise>
</c:choose>
<br/><br/>
<a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/common/generateApplicationPdf.htm")%>" class="downloadLink" style="font-family:verdana;font-size: 12px;font-weight:bold;color:brown;"><u>Click here to download your application pdf.</u></a>
</c:otherwise>
</c:choose>
</form>
</div>	

 	 
<div align="left" style="padding-left: 50px;padding-right:0px;">
<label style = "font-family:verdana;color:red;font-weight:bold;">Please note the followings : -</label>
<ul style="font-family:verdana;color:black;font-weight:bold;">
<li style="padding-bottom: 5px;">This Application will be processed only after you have deposited fee.<br/>
Please check the status of your Application periodically to ensure that it has been processed.</li>
<li style="padding-bottom: 5px;">Fee Verification Process takes 3 working days to complete from the Day you pay your Application Fee.
</li>
</ul>
</div>

<br/>
<center>
<blink><label style = "font-family:verdana;color:red;font-weight:bold;">Please upload your NSS, NCC, Social Service or Other Co-Curricular Certificates (If you have any) for CCA Marks.</label></blink>
<br/></br/>
<a href = "<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/report/admissionToUploadLink.htm")%>" style = "font-family:verdana;color:green;font-weight:bold;">Click here to upload your certificates.</a>
</center>

<!-- 
</div>
</div>	
</div> -->


</div>
</div>
</div>
</div>
  	     		
