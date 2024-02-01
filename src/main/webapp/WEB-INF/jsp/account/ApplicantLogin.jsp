<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html>
  <head>
    <title>Applicant Login</title>
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
<div class="headerDiv"><%@ include file="/templates/Header.jsp"%></div>
<div class="contentDiv" id="bodyContent">
<table width="100%">
<tr>
<td align="left" valign="top">
<ul>
<li><blink><a href="../examcenter/getUpdateExamCenterPage.htm" class="actionLink" style="color:blue; font-size: 16px; font-weight:bold;">Update Examination Center (B.Tech. only)</a></blink></li>
<br>
<li><label class="actionLink" style="font-size: 16px; font-weight : bold;">NOTICE:</label>
 		<ul style="list-style: none;list-style-image: url('https://admission.dei.ac.in:8445/admissionform/resources/hand.gif');margin-bottom:15px;">
 		<li><a href="../resources/InstructionsHindi.html" class="actionLink" style="font-size: 14px; color:red;font-weight:bold;">PLEASE READ THESE INSTRUCTIONS(IN HINDI) BEFORE APPLYING.  (Specially if your result is not declared.)</a></li>
	<!--<li><label style="font-size: 14px; color:red;">Last Date to Apply for Certificate/Modular Courses: 07-Aug-2021 </label></li></br>-->
<!--<li>
 			<table>
 			<tr>
 			<th colspan="2" align="left">Dates of submission of online application forms</th>
 			</tr>
 			 <tr>
                        <td colspan="2"><label style="color:red;font-weight:bold;">01-05-2020 TO 30-05-2020</label></td>
                         </tr> 



 			</table>
 		</li>--> 
<li><label style="font-size: 14px; color:red;">Kindly refer admission schedule available at DEI website (www.dei.ac.in) for more information.</label> </li> 
<li><a href="#" onclick="window.open(props.projectURL+'resources/ApplicationFeePaymentHelp.docx');"  class="actionLink" style="font-size: 16px; color:red;font-weight:bold;">Application Fee Payment Help</a></li>
 		</ul>
 	</li>
 	<!--<li><a href="../OffLineForm.jsp" class="actionLink" style="font-size: 16px; font-weight : bold;">Download/Upload User Form ( If you want to apply offline ).</a></li> -->
 	<li><a href="#" onclick="window.open(props.projectURL+'resources/VIDEOS.zip');" class="actionLink" style="font-size: 16px; font-weight : bold;">Download Help Videos for Online Applications.</a></li>
 	<li><a href="../account/getDownloadAdmitCardScreen.htm" class="actionLink" style="font-size: 16px; font-weight : bold;">Download Admit Card.</a></li>
<!-- 	<li><a href="#" onclick="window.open(props.projectURL+'resources/Instructions.zip');" class="actionLink" style="font-size: 16px; font-weight : bold;">Instructions for Written test(Pdf)</a></li>
 	<li><a href="#" onclick="window.open(props.projectURL+'resources/OMR.zip');" class="actionLink" style="font-size: 16px; font-weight : bold;">Instructions to Fill OMR Sheet(Video)</a></li>
-->
<!-- <li><a href="../testing/getAddProgramPage.htm" class="actionLink" style="font-size: 16px; font-weight : bold;">Add More Programs to your Application</a></li>-->
<!--<li>
Applicants, who want to update their email id, should make correction by 3 P.M. on 20-07-2021.<br/>
Steps for email correction:-
<ol>
<li>Login into your account.</li>
<li>Go to Step-1 Personal Details tab.</li>
<li>Modify email id and press Update button.</li>
</ol>
</li>-->
 	</ul>
 	</td>
 	<td align="right" valign="top">
 						<table>
 						<tr>
 							<td valign="top">
 								<div id="login" align="right">
  								<div style="font-family:verdana;color:red;font-weight:bold;">
  								${message}
  								</div>
  								<div class="panelContainer"  style="width:450px;">
								<div class="panelHeader"><div class="panelTitle">Login</div></div>
								<div class="panelContent" align="center" style="padding-left: 20px;padding-right: 20px;">
								<form action="../account/login.htm" method="post" onsubmit="return validateDetails();"> 
    								<table  cellpadding="2" border="0" width="400px">
    								<tr>
    								<td align="right">Application Number</td><td align="right"><input type="text" name="applicationNumber" id="applicationNumber" onkeyup="changeStyle(this)" autocomplete="off"/></td>
    								</tr>
    								<tr>
    								<td align="right">Password</td><td align="right"><input type="password" name="password" id="password" onkeyup="changeStyle(this)"/></td>
    								</tr>
    								<tr>
    								<td colspan="2" align="center"><input type="submit" value="Login" class="customButton" /></td>
    								</tr>
    								</table>
    							</form>
  
								</div>
								</div>
  								</div>
 						</td>
 						</tr>
 						<tr>
 						<td align="center">
 							<br/>
 							<div style="font-size:10pt;color:#660000;">If you are not registered ? <a href="../account/getCreateAccountPage.htm" class="actionLink">Click Here to Register.</a></div>
 						</td>
 						</tr>
 						<tr>
 						<td align="center">
 							<div style="font-size:10pt;color:#660000;">Forgot Your Password ? <a href="../account/getForgotPasswordPage.htm" class="actionLink">Click Here to Get it.</a></div>
 						</td>
 						</tr>
 						<!-- 
 						<tr>
 						<td align="center">
 							<div style="font-size:10pt;color:#660000;"><a href="../OffLineForm.jsp" class="actionLink">Download Offline Application Form.</a></div>
 						</td>
 						</tr>
 					 	-->
 						<tr>
 						<td align="center">
 							<div style="font-size:10pt;color:#660000;"><a href="../application/getAppStatusPage.htm" class="actionLink">View Your Application Status.</a></div>
 						</td>
 						</tr>
 						</table>
 
      
 	</td>
</tr>
</table>

</div>
<div class="footerDiv"> <%@ include file="/templates/Footer.html" %></div>
</div>
<script src="../JQ/jquery-1.10.2.js"></script>
<script>
function validateDetails()
{
	var valid = true;
	var applicationNumber = document.getElementById("applicationNumber").value;
	var password = document.getElementById("password").value;
	if(applicationNumber == "" || applicationNumber == null)
	{
		$("#applicationNumber").css("border","1px solid red");
		valid=false;
	}
	
	if(password == "" || password == null)
	{
		$("#password").css("border","1px solid red");
		valid=false;
	}
	
	if(!valid)
	{
		alert("PLEASE CHECK THE FIELDS IN RED.");
	}
	
	return valid;
}//validateDetails ends here
	
function changeStyle(fieldID)
{
	$(fieldID).css("border","1px solid #B8B8B8");
}
</script>
<script src="${ProjectURL}/jsFiles/Properties.js"></script>
</body>
</html>
