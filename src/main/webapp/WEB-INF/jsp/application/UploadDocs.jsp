<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/css/style.css" %>">
<script>
function validateDetails()
{
	var valid = true;
	$("#photoInput").css("border","1px solid #B8B8B8");
	$("#signInput").css("border","1px solid #B8B8B8");
	
	var photoInput = document.getElementById("photoInput").value;
	var signInput = document.getElementById("signInput").value;
	if(photoInput=="" || photoInput==null)
	{
		$("#photoInput").css("border","1px solid red");
		valid=false;
	}

	if(signInput=="" || signInput==null)
	{
		$("#signInput").css("border","1px solid red");
		valid=false;
	}

	if(!valid)
	{
		alert("Please check the fields in RED. Both fields are compulsory");
	}else
		{
			
				var userChoice = confirm("Do you want to upload documents?");
				if(userChoice==false)
				{
					valid = false;
				}
						
		}
		return valid;
	
}



function changeStyle(fieldID)
{
	$(fieldID).css("border","1px solid #B8B8B8");
}

function handleSubmit()
{

	var disclaimerClicked = document.getElementById("disclaimer").checked;
	
	if(disclaimerClicked)
	{
		document.getElementById("uploadButton").style.display="block";
	}
	else
	{
		document.getElementById("uploadButton").style.display="none";
	}
}


</script>
	
	
	
<div width="100%" align="center">
 	<div class="panelContainer" style="width:80%;">
  	<div class="panelHeader"><div class="panelTitle">Upload Photo and Signature</div></div>
  	<div class="panelContent" align="center" style="padding-bottom:20px;padding-top:20px;">
  		<div style="text-align: right;padding-right:30px;padding-bottom:10px;padding-top:0px;">
  				<ul>
  				<li><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/common/generateApplicationPdf.htm")%>" class="downloadLink">DOWNLOAD APPLICATION PDF</a></li>
  				<!--<c:if test="${sessionScope.applicant.applicationStatus=='A'}">
  				<li><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/account/openPage.htm?tabToOpen=1")%>" class="downloadLink">APPLY FOR MORE PROGRAM(S).</a></li>
  				</c:if>-->
  				</ul>
  		</div>
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
  	<div style="font-family:verdana;color:blue;font-weight:bold;text-align: left;">
  	<ul>
  	<li>Photo and Signature file must be in jpg/jpeg format only. Both fields are compulsory.</li>
  	<li>Maximum size of Photo file is ${photoSize} KB.</li>
  	<li>Maximum size of Signature file is ${signSize} KB.</li>
  	</ul>
  	</div>	
  	<form method="post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/upload/uploadDocument.htm")%>" enctype="multipart/form-data">	
 	<table>
 	<tr>
 	<td colspan="2" align="center"><img src="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/images/USER1.jpg" %>"  style="border:1px solid black;" width="50px" height="50px"/></td>
 	<td align="right">Select Your Photo File</td><td><input type="file" name="photo" id="photoInput" value="photo"/></td>
 	</tr>
   	<tr>
 	<td colspan="2" align="center"><img src="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/images/SIGN.jpg" %>" style="border:1px solid black;" width="50px" height="50px"/></td>
 	<td align="right">Select Your Signature File</td><td><input type="file" name="sign" id="signInput" value="sign"/></td>
 	</tr> 
 	<tr>
 	<td align="center" colspan="4"><input  type="submit" id="uploadButton" style="display: none;" value="Upload" class="customButton" onclick="return validateDetails();"/></td>
 	</tr>
 	<tr>
 	<td colspan="4">
 	<div style="font-family:verdana;color:red;font-weight:bold;">
  		${message}
  	</div>
 	</td>
 	</tr>
 	<tr>
 	<td colspan="4"><input type="checkbox" id="disclaimer" onclick="handleSubmit()"/> I hereby declare that :- <br/>
 	<div>
 	<ol>
 	<li>
 	Whatever data/information I have provided is correct to the best of my knowledge.
 	</li>
 	<li>I am aware of the system of the punishment in case of ragging other student(s) and that in case I am found
 	to be involed in any case of ragging, in any form whatsoever, I am liable for any punishment, including, but
 	not limited to, the following:
 	<ol type="a">
 	<li>Cancellation of admission;</li>
 	<li>Suspension from attending classes;</li>
 	<li>Withholding / Withdrawing Scholarship / Fellowship and/or any other benefits;</li>
 	<li>Debarring from appearing for any test / examination and/or other evaluation process;</li>
 	<li>Withholding results of any test / examination;</li>
 	<li>Debarring from representing the Institute in any campus interview;</li>
 	<li>Debarring from representing the Institute from attending/ participating in any national or international
	<br/>meet/tournament / youth festival, etc.;</li>
 	<li>Suspension / expulsion from the hostel;</li>
 	<li>Rustication from the Institute for such period as may be decided by the concerned authorities;</li>
 	<li>Expulsion from the Institute and consequent debarring from admission to any other educational institution,
	<br/>for such period as may be decided by the concerned authorities;</li>
 	<li>Imposition of fine;</li>
 	<li>Rigorous imprisonment up to 3 (three) years (by a Court of Law);</li>
 	</ol>
 	
 	</li>
 	<li>During my stay within the campus/location of the institute, I shall not be in possession of a mobile phone.If I am found with a mobile phone, it will be confiscated and returned only after the end of the semester or
 	I will be liable for any punishment decided by the competent authority of the institute.</li>
 	<li>If I come to the institute on a motorized vehicle, I shall abide by the motor vehicle driving rules and regulations.</li>
	<li>If I come on a personal transport, I shall always park it at the designated parking places.</li>
 	<li>I am fully aware of the ban on consumption of alcohol; tobacco, panmasala, ghutka etc; smoking in the campus, drugs prohibited by the law 
	and I will not consume any of them.</li>
	<li>Wearing a mask/helmet is compulsory and maintaining social distancing is must under covid-19 protocol.</li>
	</ol>
	<br/>
	<img src="http://10.151.0.80:8085/admissionform/resources/DEC_HINDI.jpg" width="700"/>
 	</div>
 	</td>
 	</tr>
 	 
 	</table>
 	</form> 
 		
	</div>
	</div>
  	</div>  
