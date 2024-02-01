<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
function handleComboVisibility(elmID, elmValue)
{
	if(elmID.checked==true)
	{
		document.getElementById(elmID.value).disabled=false;
		document.getElementById(elmID.value+"_PPR").disabled=false;
		
		//Code for Age and Gender Eligibility Added on 04-04-2016 Starts
		document.getElementById(elmID.value+"_validGender").disabled=false;
		document.getElementById(elmID.value+"_programName").disabled=false;
		document.getElementById(elmID.value+"_ageLimit").disabled=false;
		//Code for Age and Gender Eligibility Added on 04-04-2016 Ends
	}
	else
	{
		document.getElementById(elmID.value).disabled=true;
		document.getElementById(elmID.value+"_PPR").disabled=true;
		
		//Code for Age and Gender Eligibility Added on 04-04-2016 Starts
		document.getElementById(elmID.value+"_validGender").disabled=true;
		document.getElementById(elmID.value+"_programName").disabled=true;
		document.getElementById(elmID.value+"_ageLimit").disabled=true;
		//Code for Age and Gender Eligibility Added on 04-04-2016 Ends
	}
}

function loadPreviousPage()
{
	window.location.replace(props.projectURL+"/account/getUserPage.htm");
}      
</script>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />
<center>
<div style="width:80%;">
<div style="text-align: left;">
	<label style="font-family:verdana;font-size:12px;color:red;font-weight: bold;">Please Note :</label>
	<ol style="font-family:verdana;font-size:12px;color:green;"><li>You can apply for maximum 5 programs with a single application number
	before uploading Photo and Signature. For more than 5 programs, you need to create another account.</li>
	<!-- <li>Application fee for foreign candidates for <b style="color:red;">P.G. DIPLOMA (THEOLOGY)</b> is <b style="color:red;">1000 US Dollars</b>.</li> -->
	</ol>
	</div>	
 	<form method="post" id="programs" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/application/getPaperOptionsPage.htm")%>">
 	<div class="panelContainer" style="border:1px solid #6A8CE9;">
 	<div class="panelHeader" style="background-color:#6A8CE9;border:1px solid #6A8CE9;"><div class="panelTitle">Program Selection</div></div>
 	<div class="panelContent" align="left" style="padding:5px 5px 5px 5px;height:200px;overflow-y:scroll;">
 	
 	<center>
 	<label style="font-family:verdana;font-size:12px;color:red;">
	${error}
	</label></center>
 	
 	
 	<c:choose>
 	<c:when test="${not empty sessionScope.programs}">
 	<table id="programFormTbl">
 				<tr>
 				<th>SELECT</th><th align="left">PROGRAM NAME</th><th align="left">GROUP</th><th>Program Fee</th>
 				</tr>
 				<c:forEach var="program" items="${sessionScope.programs}" varStatus="counter">
 				
 				<tr>
 				<td align="center"><c:out value='${counter.count}'/>. <input type="checkbox" name="programs" id="<c:out value='${counter.count}'/>" value="<c:out value='${program.programID}'/>" onclick="handleComboVisibility(this, this.value);"/></td><td align="left"><c:out value="${program.programName}"/><input type="hidden" name="paperOptionRequired" id="<c:out value='${program.programID}_PPR'/>" value="<c:out value='${program.paperOptAvailable}'/>" disabled="disabled"/></td>
 								<td>
 								<c:choose>
								<c:when test="${program.paperOptAvailable=='Y'}">
								<!--<adm:select defaultText="-Select Paper-" name="paperOptions" size="1" optionList="${program.paperOptions}" disabled="disabled" id="<c:out value='${program.programID}'/>" />-->
								<c:choose>
								<c:when test="${program.noOfPprOptions==1}">
									NONE <input type="hidden" name="paperOptions"  disabled="disabled" value="<c:out value='${program.theOnlyPprCode}'/>" id="<c:out value='${program.programID}'/>"/>
								</c:when>
								<c:otherwise>
									<select name="paperOptions" disabled="disabled" id="<c:out value='${program.programID}'/>"><option value=''>-Select Paper Option-</option>
 									<c:forEach var="paperOption" items="${program.paperOptions}">
 									<option value="<c:out value='${paperOption.code}'/>"><c:out value='${paperOption.description}'/></option>
 									</c:forEach>
 									</select>
								</c:otherwise>
								</c:choose>
								
								</c:when>
								<c:otherwise>
								<input type="hidden" name="paperOptions" id="<c:out value='${program.programID}'/>" value="" disabled="disabled"/>NONE
								</c:otherwise>
								</c:choose>
								<!-- Code for Age and Gender Eligibility Added on 04-04-2016 Starts -->
								<input type="hidden" name="validGenders" id="<c:out value='${program.programID}_validGender'/>" value="<c:out value='${program.validGender}'/>" disabled="disabled"/>
								<input type="hidden" name="programNames" id="<c:out value='${program.programID}_programName'/>" value="<c:out value='${program.programName}'/>" disabled="disabled"/>
								<input type="hidden" name="ageLimits" id="<c:out value='${program.programID}_ageLimit'/>" value="<c:out value='${program.ageLimit}'/>" disabled="disabled"/>
								<!-- Code for Age and Gender Eligibility Added on 04-04-2016 Ends -->
 								</td>
 								<td>
 								<c:out value='${program.programFee}'/>
 								</td>
 								</tr>
 				</c:forEach>
 				</table>
 				</c:when>
 				<c:otherwise>
 				<div align="center">THERE IS NO MORE PROGRAM LEFT IN THIS GROUP.<br/>
 				YOU CAN APPLY FOR ANOTHER PROGRAM IN DIFFERENT GROUP WITH A NEW ACCOUNT.
 				</div>
 				</c:otherwise>
 				</c:choose>
 				
 				</div>
 				</div><!--  Panel Ends -->
 				<div style="padding-top:8px;padding-bottom:8px;text-align:center;">
 				<table width="100%">
 				<tr>
 				<td align="right" width="50%"><input type="submit" name="submit" value="<< BACK" class="customButton" onclick="loadPreviousPage()"/></td>
 				<td align="left" width="50%"><input type="submit" name="submit" value="NEXT >>" class="customButton"/></td>
 				</tr>
 				</table>
 				</div>
 				
 		</form>
 	</div>
 	<center>
 	 	
