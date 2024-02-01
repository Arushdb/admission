
      <link href="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/JQ/jquery-ui.css" %>" rel="stylesheet">
      <script src="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/JQ/jquery-1.10.2.js" %>"></script>
      <script src="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/JQ/jquery-ui.js" %>"></script>
      <link rel="stylesheet" type="text/css" href="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/css/style.css" %>">
      
      <%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
	  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	  <%@ taglib prefix="adm" uri="admissionTags" %>
      
     


<script>
         $(function() 
         {
            $( "#dob" ).datepicker({
	 								changeMonth: true,
      								changeYear: true
    								});
 			$( "#button1, #button2").button();
            
            $( "#dob" ).datepicker("show");
 $( ".button" ).button();

         });

      </script>
<script>
function validateForm()
{
var applicantName = document.getElementById("applicantName").value;


if(applicantName=="")
{
alert("Please Enter Applicant Name" );
return;
}

}

function setPaymentModeVisibility(param)
{
if(param=="draft")
{
document.getElementById("draftFeeDiv").style.display="block";
document.getElementById("cashFeeDiv").style.display="none";
}
else if(param=="cash")
{
document.getElementById("draftFeeDiv").style.display="none";
document.getElementById("cashFeeDiv").style.display="block";
}
}
</script>
<div width="100%" align="center">
  <div class="panelContainer" style="margin-bottom: 40px;padding-bottom:10px;marging-top:0px;;">
  <div class="panelHeader"><div class="panelTitle">Application Form</div></div>
  <div class="panelContent" align="center" style="padding-left: 0px; padding-right: 0px;">
<form method="post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/application/saveAcademicDetails.htm")%>">
<table cellspacing="5px" cellpadding="2px" width="95%" border="0">
<tr>
<th colspan="6" align="left">You are applying for the following courses:</th>
</tr>
<c:forEach var="programForm1" items="${applicationForm.programForms}" varStatus="counter">
<tr>
<th align="right"><c:out value="${counter.count}"/></th><th colspan="5" align="left"><c:out value="${programForm1.programName}"/></th>
</tr>
</c:forEach>
<tr>
<th colspan="6" align="left">Academic Performance<br><hr/></th>
</tr>
<tr>
<td colspan="6">
	<table cellspacing="5px" cellpadding="2px" width="100%">
	<tr align="left">
	<th>Examination</th><th>Obtained Marks</th><th>Total Marks</th><th>Board Name</th><th>Have you passed from DEI ?</th><th>Result Awaited</th><th>Year of Passing</th>
	</tr>
	<c:forEach var="component" items="${sessionScope.applicant.applicationForm.components}">
	<tr>
	<td><c:out value="${component.componentName}"/><input type="hidden" name="regularComponents" value="<c:out value='${component.componentID}'/>"/></td><td><input type="text"  name="obtainedMarks"  class="marks" value="<c:out value='${component.obtainedMarks}'/>"/></td><td><input type="text" name="totalMarks" class="marks" value="<c:out value="${component.totalMarks}"/>"/></td>
					<td>
					<c:choose>
					<c:when test="${component.boardFlag=='Y'}">
						<adm:select defaultText="-Select Board-" name="boards" class="combobox" size="1" optionList="${sessionScope.applicant.applicationForm.boards}" selectedValue="${component.boardValue}"/>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="boards" value=""/>NA
					</c:otherwise>
					</c:choose>
					</td>
					<td>
					<c:choose>
					<c:when test="${component.spclWeigtageGiven=='Y'}">
					<adm:selectYN defaultText="-Select Option-" name="existingStudents" class="combobox" selectedValue="${component.existingStudentValue}"/>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="existingStudents" value=""/>NA
					</c:otherwise>	
					</c:choose>
					</td>
					<td>
					<c:choose>
					<c:when test="${component.resultAwaitedFlag=='Y'}">
						<adm:selectYN defaultText="-Select Option-" name="resultAwaiteds" class="combobox" selectedValue="${component.resultAwaitedValue}"/>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="resultAwaiteds" value=""/>NA
					</c:otherwise>
					</c:choose>
					</td>
					<td>
					<c:choose>
					<c:when test="${component.passingYear=='Y'}">
						<input type="text" name="passingYears" class="marks" value="<c:out value="${component.passingYearValue}"/>"/>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="passingYears" value=""/>NA
					</c:otherwise>
					</c:choose>
					</td>
	</tr>
	</c:forEach>
	<c:forEach var="component" items="${sessionScope.applicant.applicationForm.gateComponents}" varStatus="counter">
		<c:if test="${counter.count==1}">
		<tr style="text-align: left;">
		<th></th><th colspan="2">Ranking</th><th>Score</th><th>Total Applicants</th><th>Gate Branch</th><th>Gate Year</th>
		</tr>
		</c:if>
	<tr>
	<td><c:out value="${component.componentName}"/></td><td colspan="2"><input type="text"  name="ranking"  class="marks"/></td><td><input type="text" name="score"/></td><td><input type="text" name="totalApplicants"/></td><td><input type="text" name="gateBranch"/></td><td><input type="text" name="gateYear"/></td>
	</tr>
	
	</c:forEach>
	
	</table>
</td>
</tr>
<!-- 
<tr>
<th colspan="6" align="left">Entrance Test Options<br><hr/></th>
</tr>
<tr>
<td>
	<table>
	<tr>
	<td></td>
	</tr>
	</table>
</td>
</tr>
 -->
<tr>
<th colspan="6" align="left">University Staff Ward/Spouse<br><hr/></th>
</tr>
<tr>
<td colspan="3" align="right">Are you an University Staff Ward/Spouse ?</td><td colspan="3" align="left">YES <input type="radio" name="staffWard" value="Y" onClick="document.getElementById('staffCode').disabled=false"/> NO <input type="radio" name="staffWard" value="N" onClick="document.getElementById('staffCode').disabled=true" checked="checked"/></td>
</tr>
<tr>
<td colspan="3" align="right">If YES then enter the Staff Code</td><td colspan="3" align="left"><input type="text" name="staffCode" id="staffCode" disabled="true"/></td>
</tr>
<c:if test="${sessionScope.applicant.applicationForm.branchAvailability=='Y'}">
<tr>
<th colspan="6" align="left">Preferred Branches Options<br><hr/></th>
</tr>
<tr>
<td colspan="6"> 
	<!-- <table cellspacing="5px" cellpadding="2px" width="100%" class="branchTable">-->
	
<c:forEach var="programWiseDetail" items="${sessionScope.applicant.applicationForm.programWiseDetails}">



			
<c:forEach var="branch" items="${programWiseDetail.programBranches}" varStatus="branchCounter">
<c:if test="${branchCounter.count==1}">
<tr>
<td valign="top" colspan="6" align="left">Preferred Branches For :- <b><c:out value="${programWiseDetail.programName}"/></b></td>
</tr>
</c:if>
<tr>
<td colspan="3" align="right">Branch <c:out value="${branchCounter.count}"/></td>
				<td colspan="3" align="left">
				<adm:select defaultText="-Select Preferred Branch-" name="branch" size="1" optionList="${programWiseDetail.programBranches}"/>
				</td>
</tr>
</c:forEach>
	
</c:forEach>
<!-- </table> --> 
</td>
</tr>
</c:if>
	
<!-- Program Paper Options starts here-->
<c:if test="${sessionScope.applicant.applicationForm.papersAvailability=='Y'}">
<tr>
<th colspan="6" align="left">Entrance Test Options<br><hr/></th>
</tr>

<c:forEach var="programWiseDetail" items="${sessionScope.applicant.applicationForm.programWiseDetails}">
		<c:if test="${programWiseDetail.programPaper.mainGroup!=''}">
		<tr>
		<td colspan="6" align="left">Paper Options for : <c:out value="${programWiseDetail.programPaper.programName}"/></td>
		</tr>
		<tr>
		
		<!--  Panel Starts -->
		<c:forEach var="groupPaper" items="${programWiseDetail.programPaper.groupPapers}" varStatus="ppGroupCounter">
		<c:choose>
		<c:when test="${ppGroupCounter.count==programWiseDetail.programPaper.groupPapersSize}">
		<td colspan="6" valign="top" align="left">
		</c:when>
		<c:otherwise>
		<td colspan="3" valign="top" align="center">
		</c:otherwise>
		</c:choose>
		
		<div class="panelContainer" style="width:400px;">
 		<div class="panelHeader"><div class="panelTitle">Paper Options</div></div>
 		<div class="panelContent" align="left" style="padding:5px 5px 5px 5px;height:100px;overflow-y:scroll;">
 		<table>
 		<c:forEach var="allPaper" items="${groupPaper.allPapers}">
 		<tr>
 		<td align="left"><input type="checkbox"/></td><td align="left"><c:out value="${allPaper.description}"/></td>
 		</tr>
 		</c:forEach>
 		</table>
 		</div>
 		</div><!--  Panel Ends -->
 		</td>
 		</c:forEach>
		
		</tr>
		
		</c:if>
		</c:forEach><!-- programWiseDetail ends -->
	
</c:if>

	
			

<!--  Program Paper Option ends here -->
<tr>
<td colspan="3" align="right"><input type="submit" value="Save and Continue >>" class="button"/></td><td  colspan="3" align="left"><input type="submit" value="<< Go to Previous Step" class="button"/></td>
</tr>
</table>
</form>
  </div>
  </div>
</div>				
