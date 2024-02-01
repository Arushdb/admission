
<link rel="stylesheet" type="text/css" href="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/css/style.css" %>">

<script src="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/jsFiles/Properties.js" %>"></script>
      
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
  
<script>
function loadPreviousPage()
{
	window.location.replace(props.projectURL+"/account/getUserPage.htm");
}

function validate()
{
	var valid = false;
	//${sessionScope.formApplicant.applicationForm.papersAvailability};
	var paperAvailability = <%=((in.ac.dei.edrp.admissionsystem.common.beans.Applicant)request.getSession().getAttribute("formApplicant")).getApplicationForm().getPapersAvailability()%>;
	alert(paperAvailability);
	/**if(paperAvailability=="Y")
	{
		var examCenter1 = document.getElementById("examCenter1").value;
		var examCenter2 = document.getElementById("examCenter2").value;
		var examCenter3 = document.getElementById("examCenter3").value;
		
		if((examCenter1=="" || examCenter2=="" || examCenter3==""))
		{
			alert("PLEASE SELECT EXAMINATION CENTERS.");
			valid = false;
		}
		else if((examCenter1==examCenter2 || examCenter1==examCenter3 || examCenter3==examCenter2))
		{
			alert("PLEASE SELECT DIFFERENT EXAMINATION CENTERS.");
			valid = false;
		}
		
	}**/
	return valid;
}
</script>
<div width="100%" align="center">
<div class="panelContainer" style="margin-bottom: 0px;padding-bottom:10px;marging-top:0px;">
<div class="panelHeader"><div class="panelTitle">Application Form</div></div>
<div class="panelContent" align="center">
<div style="font-family:verdana;font-size:12px;color:red;">${requestScope.error}</div>
<form method="post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/application/savePaperOptions.htm")%>">
<table cellspacing="5px" cellpadding="2px" width="95%" border="0">
<tr>
<th colspan="2" align="right" width="50%">You are applying for the following courses:</th><th align="right"><th colspan="3" align="left"  width="50%"></th>
</tr>
<c:forEach  var="programWiseDetail" items="${sessionScope.formApplicant.applicationForm.programWiseDetails}" varStatus="counter">
<tr>
<th colspan="2"></th><th  align="right"><c:out value="${counter.count}"/></th><th colspan="3" align="left"><c:out value="${programWiseDetail.programName}"/></th>
</tr>
</c:forEach>


<!-- Program Paper Options starts here-->
<c:set var="papAvailability" value="${sessionScope.formApplicant.applicationForm.papersAvailability}" />
<c:if test="${sessionScope.formApplicant.applicationForm.papersAvailability=='Y'}">
<tr>
<th colspan="6" align="left">Entrance Test Options<br><hr/></th>
</tr>
<c:forEach var="programWiseDetail" items="${sessionScope.formApplicant.applicationForm.programWiseDetails}">
		<c:if test="${programWiseDetail.paperMainGroup!=''}">
		<tr>
		<td colspan="6" align="center"><b>Paper Options for : <c:out value="${programWiseDetail.programPaper.programName}"/></b></td>
		</tr>
		
		
		
		<!--  Panel Starts -->
		<c:forEach var="groupPaper" items="${programWiseDetail.programPaper.groupPapers}" varStatus="ppGroupCounter">
		<c:choose>
		<c:when test="${(ppGroupCounter.count+1)%2==0}">
			<tr>
			<td colspan="3" align="center">
		</c:when>
		<c:otherwise>
		<td colspan="3"  align="center">
		</c:otherwise>
		</c:choose>
		<input type="hidden" name="<c:out value="${groupPaper.paperGroupID}_MAX"/>" value="<c:out value="${groupPaper.maxChoice}"/>"/>
		<input type="hidden" name="<c:out value="${groupPaper.paperGroupID}_MIN"/>" value="<c:out value="${groupPaper.minChoice}"/>"/>
		<div class="panelContainer" style="width:400px;">
 		<div class="panelHeader"><div class="panelTitle">
 			<font size='2'>
 			<c:choose>
 			<c:when test="${groupPaper.allPaperSize==groupPaper.maxChoice}">
 			Compulsory Paper(s)
 			</c:when>
 			<c:otherwise>
 			Select ${groupPaper.minChoice} Paper(s)
 			</c:otherwise>
 			</c:choose>
 			</font>
 		</div></div>
 		<div class="panelContent" align="left" style="padding:5px 5px 5px 5px;height:100px;overflow-y:scroll;">
 		<table>
 		<c:forEach var="allPaper" items="${groupPaper.allPapers}">
 		<tr>
 		<c:choose>
 		<c:when test="${groupPaper.allPaperSize==groupPaper.maxChoice}">
 		<td align="left" valign="top"><input type="checkbox" name="<c:out value="${groupPaper.paperGroupID}"/>" value="<c:out value="${allPaper.code}"/>" onclick="return false;" checked/></td><td align="left" valign="top"><c:out value="${allPaper.description}"/></td>
 		</c:when>
 		<c:otherwise>
 		<td align="left"><input type="checkbox" name="<c:out value="${groupPaper.paperGroupID}"/>" value="<c:out value="${allPaper.code}"/>"/></td><td align="left"><c:out value="${allPaper.description}"/></td>
 		</c:otherwise>
 		</c:choose>
 		</tr>
 		</c:forEach>
 		</table>
 		</div>
 		</div><!--  Panel Ends -->
 		<c:choose>
		<c:when test="${(ppGroupCounter.count+1)%2==0}">
			</td>
			<c:if test="${ppGroupCounter.count==programWiseDetail.programPaper.groupPapersSize}">
			</tr>
			</c:if>
		</c:when>
		<c:otherwise>
		</td></tr>
		</c:otherwise>
		</c:choose>
 		</c:forEach>
		
		
		<tr>
		<td colspan="6"><hr/></td>
		</tr>
		</c:if>
		
		</c:forEach><!-- programWiseDetail ends -->
		
<!-- Examination Centers Starts -->
		<c:if test="${sessionScope.applicant.applicationStatus!='A' || sessionScope.applicant.applicationForm.papersAvailability=='N'}">
		<tr>
		<th colspan="6" align="left">Examination Centers<br><hr/></th>
		</tr>
		<tr>
		<td colspan="6" align="center"><div align="center"><label style="color:red;font-weight:bold;">Please Note : </label>The decision of the University shall be final  in deciding the location at which the <br/>
		admission test of the applicant will be conducted, based on the number of applicants <br/>
		opting for a location and other considerations  of  logistics.</div></td>
		</tr>

 		<tr>
 		<td align="right" colspan="3" width="50%">Preferred Examination Center for Entrance Test</td><td align="left" width="50%"><adm:select class="cwFields" name="examCenter1" id="examCenter1" optionList="${sessionScope.formApplicant.examinationCenters}" defaultText="-Select Exam Center-"/></td>
 		</tr>
		</c:if>
		
 		<!-- <tr>
 		<td align="right" colspan="3" width="50%">Preffered Examination Center 2</td><td align="left" width="50%"><adm:select class="cwFields" name="examCenter2" id="examCenter2" optionList="${sessionScope.formApplicant.examinationCenters}" defaultText="-Select Exam Center-"/></td>
 		</tr>
 		<tr>
 		<td align="right" colspan="3" width="50%">Preffered Examination Center 3</td><td align="left" width="50%"><adm:select class="cwFields" name="examCenter3" id="examCenter3" optionList="${sessionScope.formApplicant.examinationCenters}" defaultText="-Select Exam Center-"/></td>
 		</tr> -->
 		
<!-- Examination Centers Ends -->		
</c:if>

<!--  Staff Ward Details Start Here -->
<!-- 
<tr>
<th colspan="6" align="left">University Staff Ward/Spouse<br><hr/></th>
</tr>
<tr>
<td colspan="3" align="right" width="50%">Are you an University Staff Ward/Spouse ?</td><td colspan="3" align="left" width="50%">YES <input type="radio" name="staffWard" value="Y" onClick="document.getElementById('staffCode').disabled=false"/> NO <input type="radio" name="staffWard" value="N" onClick="document.getElementById('staffCode').disabled=true" checked="checked"/></td>
</tr>
<tr>
<td colspan="3" align="right" width="50%">If YES then enter the Staff Code</td><td colspan="3" align="left" width="50%"><input type="text" name="staffCode" id="staffCode" disabled="true"/></td>
</tr>
<!--  Staff Ward Details End Here -->	
 

<!--  Program Paper Option ends here -->
<tr>
<td colspan="3" align="right"><input type="submit" name="submit" value="<< BACK" class="customButton"/></td><td  colspan="3" align="left"><input type="submit" name="submit" value="SUBMIT" class="customButton"  onclick="return validate();"/></td>
</tr>
</table>
</form>
  </div>
  </div>
</div>				
