<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="../css/style.css">
<script>
function preventBack()
{
	window.history.forward();
}
setTimeout("preventBack()", 0);
window.onunload=function(){null};
</script>
<style>
.smallFields
{
	width:80px;
}
</style>
<c:set var="programWiseDetails" value="${sessionScope.applicant.applicationForm.programWiseDetails}"/>

<div width="100%" align="center">
<div class="panelContainer" style="margin-bottom: 10px;padding-bottom:10px;marging-top:0px;margin-left:10px;margin-right:10px;">
<div class="panelHeader"><div class="panelTitle">Application Form</div></div>
<div class="panelContent" align="center" style="padding-left: 0px; padding-right: 0px;">

<div style="font-family: verdana;font-size:14px;">
<c:if test="${sessionScope.applicant.tabStatus<3}">
<label style="font-weight: bold;color:red;">PLEASE NOTE : </label>After completing this step (submitting your academic details), you will not be allowed to add or delete programs.<br/>
If you want to apply for more programs or delete any program then click <a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/account/openPage.htm?tabToOpen=1")%>" class="downloadLink" style="font-weight: bold;color:blue;font-family:verdana;font-size:12px;">APPLY FOR MORE PROGRAM(S) / DELETE PROGRAM(S)</a><br/>
Please enter correct marks. If any discrepancy found, your application will be rejected.
<br/>
<label style="font-weight: bold;color:red;">It is mandatory to update marks of qualifying exams (HS/IN/UG) immediately after the results declaration otherwise the application is likely to be rejected.</label>
</c:if>
</div>

<div style="text-align: right;padding-right:30px;padding-bottom:0px;padding-top:0px;">
<ul>
<li><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/common/generateApplicationPdf.htm")%>" class="downloadLink">DOWNLOAD APPLICATION PDF</a></li>
</ul>
</div>

<!--  
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
-->
<center style="font-family: verdana; color:red;font-size:12px;font-weight:bold;">${requestScope.errorsStep3}</center>

<form method="post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/application/saveAcademicDetails.htm")%>" onsubmit="return validateAcademicDetails();">
<table cellspacing="5px" cellpadding="2px" width="95%" border="0">

<!--  LOOP FOR GROUP WISE COMPONENT STARTS HERE -->
<c:forEach var="groupWiseComponent" items="${sessionScope.applicant.applicationForm.groupWiseComponents}">
<tr>
	<th colspan="6" align="left">
		<c:out value="${groupWiseComponent.description}"/><br><hr/>
	</th>
</tr>
<tr>
<td colspan="6">
	<!--  TABLE FOR COMPONENTS STARTS HERE -->
	<table cellspacing="5px" cellpadding="2px" width="100%">
	
	<!--  LOOP FOR REGULAR COMPONENTS STARTS HERE -->
	<c:forEach var="component" items="${groupWiseComponent.components}" varStatus = "regularExaminationCounter">
	<c:if test="${regularExaminationCounter.count==1}">
	<tr align="left">
		<th style="width: 450px;text-align:left;">Examination</th>
		<th align="left">
		<c:choose>
			<c:when test="${groupWiseComponent.code=='7'}">Marks Obtained</c:when>
			<c:otherwise>Marks/CGPA Obtained
				<c:if test="${groupWiseComponent.code=='2'}">
				<br/><label style="font-family: verdana;font-weight:bold;font-size:10px;color:green;"> ( Include All Subjects )</label>
				</c:if>
			</c:otherwise>
		</c:choose>
		</th>
		<th align="left">
		<c:choose>
			<c:when test="${groupWiseComponent.code=='7'}">Out of Marks</c:when>
			<c:otherwise>Out of Marks/Max. CGPA
				<c:if test="${groupWiseComponent.code=='2'}">
					<br/><label style="font-family: verdana;font-weight:bold;font-size:10px;color:green;"> ( Include All Subjects )</label>
				</c:if>
			</c:otherwise>
		</c:choose>
		</th>
		<!-- Added by Arjun on 10-05-2019 -->
		<th align="left">
		<c:choose>
			<c:when test="${groupWiseComponent.code=='7'}">All India Ranking</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
		</th>		
		
		<th align="left">
		<c:choose>
			<c:when test="${component.boardFlag=='Y'}">Board Name</c:when>
			<c:when test="${groupWiseComponent.universityToAsk==1}">University Name</c:when>
			<c:otherwise><!-- Nothing to Show --></c:otherwise>
		</c:choose>
		</th>
		<th>
	       Roll Number
	    </th>
		
		<!-- Added by Arjun on 10-05-2019 -->
		<th align="left">
		<c:choose>
			<c:when test="${groupWiseComponent.code=='7'}">Percentile</c:when>
			<c:otherwise>Year of Passing</c:otherwise>
		</c:choose>
		</th>
		
	</tr>
	</c:if>
	
	<tr>
	<td>
		<c:choose>
		<c:when test="${groupWiseComponent.showOptions == 0}">
			<c:out value="${component.componentName}"/><input type="hidden" name="regularCompsName" value="<c:out value="${component.componentName}"/>"/><input type="hidden" name="optionToShow" value="<c:out value='${groupWiseComponent.showOptions}'/>"/><input type="hidden" name="regularComponents" value="<c:out value='${component.componentID}'/>"/>
		</c:when>
		<c:otherwise>
			<c:choose>
			<c:when test="${component.componentID=='UG'}">
				<table>
				<tr>
				<td style="width: 300px;"><c:out value="${component.componentName}"/><input type="hidden" name="regularCompsName" value="<c:out value="${component.componentName}"/>"/><input type="hidden" name="optionToShow" value="<c:out value='${groupWiseComponent.showOptions}'/>"/><input type="hidden" name="regularComponents" value="<c:out value='${component.componentID}'/>"/></td><td><adm:select name="optionName" id="optionName" optionList="${component.optionsList}" defaultText="-Select-"  selectedValue="${component.optionCode}"/></td>
				</tr>
				<tr>
				<td>SUBJECT AT HONOURS LEVEL</td><td><adm:select name="subjectOption" id="subjectOption" optionList="${component.subjectOptions}" defaultText="-Select-" selectedValue="${component.subjectOptionCode}"/></td>
				</tr>
				</table>
			</c:when>
			<c:otherwise>
				<table>
				<tr>
				<td style="width: 300px;"><c:out value="${component.componentName}"/><input type="hidden" name="regularCompsName" value="<c:out value="${component.componentName}"/>"/><input type="hidden" name="optionToShow" value="<c:out value='${groupWiseComponent.showOptions}'/>"/><input type="hidden" name="regularComponents" value="<c:out value='${component.componentID}'/>"/></td><td><adm:select name="optionName" id="optionName" optionList="${component.optionsList}" defaultText="-Select-" onchange="changeStyle(this);" selectedValue="${component.optionCode}"/></td>
				</tr>
				</table>
			</c:otherwise>
			</c:choose>
		</c:otherwise>
		</c:choose>
	</td>
	<td><input type="text" autocomplete="off" name="obtainedMarks"  style="width:80px;" value="<c:out value='${component.obtainedMarks}'/>"/></td><td><input type="text" autocomplete="off" name="totalMarks" style="width:80px;" value="<c:out value="${component.totalMarks}"/>"/></td>
	
	<!-- Added by Arjun on 10-05-2019 -->
	<td align="left">
	<c:choose>
		<c:when test="${groupWiseComponent.code=='7'}">
		
			<input type="text" autocomplete="off" name="ranking"  style="width:80px;" value="<c:out value='${component.ranking}'/>"/>
		
		</c:when>
		<c:otherwise>
			<input type="hidden" autocomplete="off" name="ranking"  style="width:80px;" value="<c:out value='${component.ranking}'/>"/>
		</c:otherwise>
	</c:choose>
	</td>
		
	<td>
		<c:choose>
		<c:when test="${component.boardFlag=='Y' || groupWiseComponent.universityToAsk==1}">
			<input type="hidden" name="brdUnivRequired" value="Y"/>
		</c:when>
		<c:otherwise>
			<input type="hidden" name="brdUnivRequired" value="N"/>
		</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${component.boardFlag=='Y'}">
				<input type="hidden" name="universityNeeded" value="N"/>
				<adm:select defaultText="-Select Board-" name="boards" class="combobox" size="1" optionList="${sessionScope.applicant.applicationForm.boards}" selectedValue="${component.boardValue}" style="width:180px;"/>
			</c:when>
			<c:when test="${groupWiseComponent.universityToAsk==1}">
				<input type="hidden" name="universityNeeded" value="Y"/>
				<input type="text" autocomplete="off" name="boards" style="width:160px;" value="<c:out value="${component.otherBoardName}"/>"/>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="universityNeeded" value="N"/>
				<input type="hidden" name="boards" value="" style="width:160px;"/>
			</c:otherwise>
		</c:choose>
	</td>
	<td>
	 <input type="text" autocomplete="off" name="rollnumbers" style="width:80px;" value="<c:out value="${component.rollnumber}"/>"/>
	</td>
	<td>
	
	<!-- Added by Arjun on 10-05-2019 -->
	<c:choose>
		<c:when test="${groupWiseComponent.code=='7'}">
			<input type="text" autocomplete="off" name="percentile"  style="width:80px;" value="<c:out value='${component.percentile}'/>"/>
			<input type="hidden" name="passingYearRequired" value="N"/>
			<input type="hidden" name="passingYears" value=""/>
		</c:when>
		<c:otherwise>
			<input type="hidden" autocomplete="off" name="percentile"  style="width:80px;" value="<c:out value='${component.percentile}'/>"/>
			
			<c:choose>
				<c:when test="${component.passingYear=='Y'}">
					<input type="hidden" name="passingYearRequired" value="Y"/>
					<input type="text" autocomplete="off" maxlength="4" autocomplete="off" name="passingYears" style="width:80px;" value="<c:out value="${component.passingYearValue}"/>"/>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="passingYearRequired" value="N"/>
					<input type="hidden" name="passingYears" value=""/>NA
				</c:otherwise>
			</c:choose>
		
		</c:otherwise>
	</c:choose>
		
		<!-- Commented by Arjun on 10-05-2019
		<c:choose>
			<c:when test="${component.passingYear=='Y'}">
				<input type="hidden" name="passingYearRequired" value="Y"/>
				<input type="text" autocomplete="off" maxlength="4" autocomplete="off" name="passingYears" style="width:80px;" value="<c:out value="${component.passingYearValue}"/>"/>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="passingYearRequired" value="N"/>
				<input type="hidden" name="passingYears" value=""/>NA
			</c:otherwise>
		</c:choose>
		 -->
		 
	<input type = "hidden" name = "compulsoryFlags" value = "${component.compulsory}"/>
	</td>
	</tr>
	</c:forEach>
	<!--  LOOP FOR REGULAR COMPONENTS ENDS HERE -->
	
	<!--  LOOP FOR GATE COMPONENT STARTS HERE -->
	<c:forEach var="component" items="${groupWiseComponent.gateComponents}" varStatus="counter">
		<c:if test="${counter.count==1}">
			<tr style="text-align: left;">
				<th style="width : 300px;"></th>
				<th colspan="2">Gate Rank</th>
				<th>Score</th>
				<th>Total Applicants</th>
				<th>Gate Branch</th>
				<th>Gate Year</th>
			</tr>
		</c:if>
	<tr>
		<td><input type="hidden" name="gateComponents" value="<c:out value='${component.componentID}'/>"/><c:out value="${component.componentName}"/></td>
		<td colspan="2"><input type="text" autocomplete="off" name="ranking"  style="width:80px;" value="<c:out value='${component.ranking}'/>"/></td>
		<td><input type="text" autocomplete="off" name="score" style="width:80px;" value="<c:out value='${component.score}'/>"/></td>
		<td><input type="text" autocomplete="off" name="totalApplicants" style="width:80px;" value="<c:out value='${component.totalApplicants}'/>"/></td>
		<td><input type="text" autocomplete="off" name="gateBranch" style="width:80px;" value="<c:out value='${component.gateBranch}'/>"/></td>
		<td><input type="text" autocomplete="off" name="gateYear" style="width:80px;" value="<c:out value='${component.gateYear}'/>"/><input type = "hidden" name = "compulsoryGtFlags" value = "${component.compulsory}"/></td>
	</tr>
	</c:forEach>
	<!--  LOOP FOR GATE COMPONENT ENDS HERE -->
	</table>
	<!--  TABLE FOR COMPONENTS ENDS HERE -->
</td>
</tr>
</c:forEach>
<!--  LOOP FOR GROUP WISE COMPONENT ENDS HERE -->


<!--  IF TO CHECK WHETHER BRANCH EXISTS STARTS -->
<c:if test="${sessionScope.applicant.applicationForm.branchAvailability=='Y'}">
<tr>
	<th colspan="6" align="left"><br/>Preferred Branches Options<br><hr/></th>
</tr>
<tr>
	<td colspan="6"> 
	<!-- LOOP TO LOOK FOR THE BRANCHES IN APPLIED PROGRAM STARTS HERE -->
	<c:forEach var="programWiseDetail" items="${sessionScope.applicant.applicationForm.programWiseDetails}">
		<!-- LOOP TO LOOK FOR THE BRANCHES IN A PROGRAM STARTS HERE -->
		
		<c:forEach var="branch" items="${programWiseDetail.programBranches}" varStatus="branchCounter">
			<c:choose>
				<c:when test="${branchCounter.count == 1}">
					<c:set var = "selectedBranch" value="${programWiseDetail.branch1}"/>
				</c:when>
				<c:when test="${branchCounter.count == 2}">
					<c:set var = "selectedBranch" value="${programWiseDetail.branch2}"/>
				</c:when>
				<c:when test="${branchCounter.count == 3}">
					<c:set var = "selectedBranch" value="${programWiseDetail.branch3}"/>
				</c:when>
				<c:when test="${branchCounter.count == 4}">
					<c:set var = "selectedBranch" value="${programWiseDetail.branch4}"/>
				</c:when>
				<c:otherwise>
					<c:set var = "selectedBranch" value="${programWiseDetail.branch5}"/>
				</c:otherwise>
				
				
			</c:choose>

<c:if test="${branchCounter.count==1}">
<tr>
<td valign="top" colspan="6" align="left">Preferred Branches For :- <b><c:out value="${programWiseDetail.programName}"/><input type="hidden" name="programIDWithBranches" value="<c:out value="${programWiseDetail.programID}"/>"/><input type="hidden" name="programNameWithBranches" value="<c:out value="${programWiseDetail.programName}"/>"/></b></td>
</tr>
</c:if>
<c:if test="${branchCounter.count <= 5}">

<tr>
<td colspan="3" align="right">Branch <c:out value="${branchCounter.count}"/></td>
				<td colspan="3" align="left">
				<adm:select defaultText="-Select Preferred Branch-" name="${programWiseDetail.programID}_BRANCH" size="1" optionList="${programWiseDetail.programBranches}" selectedValue="${selectedBranch}"/>
				</td>
</tr>
</c:if>
</c:forEach>
<!-- LOOP TO LOOK FOR THE BRANCHES IN A PROGRAM ENDS HERE -->
</c:forEach>
<!-- LOOP TO LOOK FOR THE BRANCHES IN APPLIED PROGRAM ENDS HERE -->
</td>
</tr>
</c:if>
<!--  IF TO CHECK WHETHER BRANCH EXISTS ENDS -->

<!-- commented below code by Jyoti on 22-Apr-2021
<tr>
<th colspan="6" align="left"><br/>Study Mode Details<br><hr/></th>
</tr>
<tr>
<td colspan="6" align="center">
<table cellpadding="5" cellspacing="0" width="60%">
<tr>
<th align="left">Program Name</th><th align="left">Select Education Mode</th><th align="left">Study Center</th>
</tr>
<c:forEach var="programWiseDetail" items="${sessionScope.applicant.applicationForm.programWiseDetails}" varStatus="counter">
<tr>
	<td align="left">
		<input type="hidden" name="programIDs" value="<c:out value="${programWiseDetail.programID}"/>"/><input type="hidden" name="programNames" value="<c:out value="${programWiseDetail.programName}"/>"/>
		<c:out value="${counter.count}"/>.&nbsp&nbsp<c:out value="${programWiseDetail.programName}"/>
	</td>
	<td>
		<adm:select name="${programWiseDetail.programID}_educationMode" id="${programWiseDetail.programID}" onchange="getStudyCenters(this.id)" optionList="${programWiseDetail.educationModes}" defaultText="-Select Education Mode-" selectedValue="${programWiseDetail.selectedEducationMode}"/>
	</td>
	<td>
	<div id="<c:out value="${programWiseDetail.programID}_div"/>">
		<select name="<c:out value="${programWiseDetail.programID}_studyCenter"/>" id="<c:out value="${programWiseDetail.programID}_studyCenter"/>">
			<option value="">-Select Study Center-</option>
			<c:choose>
				<c:when test="${programWiseDetail.studyCenterCode!=null}">
					<option value="<c:out value="${programWiseDetail.studyCenterCode}"/>" selected="selected"><c:out value="${programWiseDetail.studyCenterName}"/></option>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</select>
	</div>
	</td>
</tr>
</c:forEach>
</table>
</td>
</tr>

 -->
<!-- Added below Study Mode by Jyoti on 22 Apr 2021 -->
<tr>
<th colspan="6" align="left"><br/>Study Mode Details<br><hr/></th>
</tr>
<tr>
<td colspan="6" align="center">
<table cellpadding="5" cellspacing="0" width="60%">
</br>
<tr>
<th align="left">Program Name</th><th align="left">Select Education Mode</th>
<th align="left">
<c:choose>
<c:when test="${sessionScope.applicant.applicationForm.formNumber == '0013'}">**Examination/Study Center</c:when>
<c:when test="${sessionScope.applicant.applicationForm.formNumber == '0017'}">**Examination/Study Center</c:when>
<c:when test="${sessionScope.applicant.applicationForm.formNumber == '0018'}">**Examination/Study Center</c:when>
<c:when test="${sessionScope.applicant.applicationForm.formNumber == '0019'}">**Examination/Study Center</c:when>
<c:otherwise>**Examination Center</c:otherwise>
</c:choose>
</th>
</tr>
<c:forEach var="programWiseDetail" items="${sessionScope.applicant.applicationForm.programWiseDetails}" varStatus="counter">
<tr>
	<td align="left">
		<input type="hidden" name="programIDs" value="<c:out value="${programWiseDetail.programID}"/>"/><input type="hidden" name="programNames" value="<c:out value="${programWiseDetail.programName}"/>"/>
		<c:out value="${counter.count}"/>.&nbsp&nbsp<c:out value="${programWiseDetail.programName}"/>
	</td>
	<td>
		<!--<adm:select name="${programWiseDetail.programID}_educationMode" id="${programWiseDetail.programID}" onchange="getStudyCenters(this.id)" optionList="${programWiseDetail.educationModes}" defaultText="-Select Education Mode-" selectedValue="${programWiseDetail.selectedEducationMode}"/> -->
		<div>
		 <c:forEach var="eduMode" items="${programWiseDetail.educationModes}" varStatus="counter">
		 	  <input type="hidden" name="${programWiseDetail.programID}_educationMode" id="${programWiseDetail.programID}_educationMode" value="<c:out value="${eduMode.code}"/>"/>
			  <c:if test="${eduMode.code=='REG'}">
			  		<input type="checkbox" name="${programWiseDetail.programID}_regularMode" id="${programWiseDetail.programID}_R"
			  		<c:if test="${programWiseDetail.selectedRegularMode=='Y'}">checked=checked value="Y"</c:if>
			  		<c:if test="${programWiseDetail.selectedRegularMode=='N'}">value="N"</c:if>
			  		<c:if test="${programWiseDetail.selectedRegularMode==null}">checked=checked value="Y"</c:if>
			  		<c:if test="${fn:length(programWiseDetail.educationModes) == 1}">disabled=disabled checked=checked value="Y"</c:if>
			  		 onchange="setRegularMode(this.id)"/> 
			  		<label for="${programWiseDetail.programID}_R">Regular</label>
			  </c:if>
			  <c:if test="${eduMode.code=='OLM'}"> 
			  		<input type="checkbox" name="${programWiseDetail.programID}_onlineMode" id="${programWiseDetail.programID}"
			  		<c:if test="${programWiseDetail.selectedOnlineMode=='Y'}">checked=checked value="Y" 
			  		</c:if>
			  		<c:if test="${programWiseDetail.selectedOnlineMode=='N'}">value="N"</c:if>
			  		<c:if test="${programWiseDetail.selectedOnlineMode==null}">value="N"</c:if> 
			  		onchange="getOnlineStudyCenters(this.id)"/> 
			  		<label for="${programWiseDetail.programID}">Online</label>
			  </c:if>
			  <c:if test="${eduMode.code=='DIS'}"> 
			  		<input type="checkbox" name="${programWiseDetail.programID}_distanceMode" id="${programWiseDetail.programID}_D"
			  		<c:if test="${programWiseDetail.selectedDistanceMode=='Y'}">checked=checked value="Y" 
			  		</c:if>
			  		<c:if test="${programWiseDetail.selectedDistanceMode=='N'}">value="N"</c:if>
			  		<c:if test="${programWiseDetail.selectedDistanceMode==null}">value="N"</c:if> 
			  		onchange="getdistanceStudyCenters(this.id)"/> 
			  		<label for="${programWiseDetail.programID}">Distance</label>
			  </c:if>
		 </c:forEach>
		</div>
	</td>
	<td>	
	<div id="<c:out value="${programWiseDetail.programID}_div"/>" >
		<select name="<c:out value="${programWiseDetail.programID}_studyCenter"/>" id="<c:out value="${programWiseDetail.programID}_studyCenter"/>">
			<c:choose>
				<c:when test="${programWiseDetail.studyCenterCode!=null}">
					<option value="<c:out value="${programWiseDetail.studyCenterCode}"/>" selected="selected"><c:out value="${programWiseDetail.studyCenterName}"/></option>
				</c:when>
				<c:when test="${fn:length(programWiseDetail.regStudyCenters) >= 1}">
					<c:forEach var="studyCenter" items="${programWiseDetail.regStudyCenters}">
						<option value="<c:out value="${studyCenter.code}"/>">
						<c:out value="${studyCenter.description}"/>
						</option>
					</c:forEach>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</select>
	</div>
	<input type="hidden" name="${programWiseDetail.programID}_studyCenterName" value="<c:out value="${programWiseDetail.studyCenterName}"/>"/>
	</td>
</tr>
</c:forEach>
</table>
</td>
</tr>
<tr>
<c:choose>
<c:when test="${sessionScope.applicant.applicationForm.formNumber == '0013'}">
</c:when>
<c:when test="${sessionScope.applicant.applicationForm.formNumber == '0017'}"></c:when>
<c:when test="${sessionScope.applicant.applicationForm.formNumber == '0018'}"></c:when>
<c:when test="${sessionScope.applicant.applicationForm.formNumber == '0019'}"></c:when>
<c:otherwise>
<td colspan="6" align="left"><label style="font-weight:bold;">Note :-</label>
<ul style="color:green;">
<li>ONLINE MODE Programmes offered are in conformity with the UGC Notification dated 4th September, 2020. (Reference: UGC, Distance Education Bureau letter no. F.No. 1-14/2020 (DEB-I) dated 10th March, 2021 and subsequent communication)</li>
<li>Candidate interested in ONLINE MODE (wherever applicable) must tick ONLINE MODE check box, otherwise, later they will not be considered for ONLINE MODE.</li>
<li>Candidate who ticks ONLINE MODE, will mandatorily have to choose an Examination Centre from the drop-down list in front of the programme, where candidate will give various Examinations conducted by the Institute.</li>
</ul>
</td>
</c:otherwise>
</c:choose>


</tr>

<!-- Added till here by Jyoti on 22 Apr 2021 -->
<th colspan="6" align="left"><br/>Other Details<br><hr/></th>
</tr>
<tr>
<td colspan="6" align="center">
	<table cellspacing="5px" cellpadding="2px">
	<tr>
	<td  align="right">Father Annual Income</td><td><input type="number" autocomplete="off" name="fatherIncome" id="fatherIncome" maxlength="8" value="<c:out value="${programWiseDetails[0].fatherIncome}"/>"/></td><td align="right">Hostel Required ?</td><td><adm:selectYN defaultText="-Select-" name="hostelRequired" id="hostelRequired" selectedValue="${programWiseDetails[0].hostelRequired}"/></td>
	</tr>
	<tr>
	<td  align="right">Mother Annual Income</td><td><input type="number" autocomplete="off" name="motherIncome" id="motherIncome" maxlength="8" value="<c:out value="${programWiseDetails[0].motherIncome}"/>"/></td><td align="right">Ever Expelled from College/University?</td><td><adm:selectYN defaultText="-Select-" name="everExpelled" id="everExpelled" selectedValue="${programWiseDetails[0].everExpelled}"/></td>
	</tr>
	<tr>
	<td  align="right">Guardian Annual Income</td><td colspan="3"><input type="number" autocomplete="off" name="guardianIncome" maxlength="8" id="guardianIncome" value="<c:out value="${programWiseDetails[0].guardianIncome}"/>"/></td>
	</tr>
	<tr>
	<td  align="right">Last degree/examination passed from school/college </td><td><adm:collegeSelection defaultText="-Select-" name="deiStudent" id="deiStudent" selectedValue="${programWiseDetails[0].deiStudent}" onchange="handleSchoolVisibility()"/></td>
	
	<c:choose>
		<c:when test="${programWiseDetails[0].deiStudent == 'N'}">
			<td align="right"><label id="schoolLbl" style="display: block;">College/School/University Name</label></td><td><label id="schoolName" style="display: block;"><input type="text" autocomplete="off" name="lastExamFrom" maxlength="100" id="lastExamFrom" value="<c:out value="${programWiseDetails[0].lastExamFrom}"/>"/></label></td>
		</c:when>
		<c:otherwise>
			<td align="right"><label id="schoolLbl" style="display: none;">College/School/University Name</label></td><td><label id="schoolName" style="display: none;"><input type="text" autocomplete="off" name="lastExamFrom" maxlength="100" id="lastExamFrom" value="<c:out value="${programWiseDetails[0].lastExamFrom}"/>"/></label></td>
		</c:otherwise>
	</c:choose>
	</tr>
	<tr>
	<td  align="right">Last degree/examination studied in Co-Education ?</td><td><adm:selectYN name="co_ed" class="cwFields" id="co_ed" defaultText="-Select Option-" selectedValue="${programWiseDetails[0].co_ed}"/></td>
	</tr>
	</table>
</td>
</tr>



<tr>
<td colspan="6" align="center" style="width: 100%;"><input type="submit" value="SAVE" class="customButton"/></td>
</tr>
</table>
</form>

</div>
</div>
</div>				

</div>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />
  
<script src="../JQ/jquery-1.10.2.js"></script>
<script src="${ProjectURL}/jsFiles/Properties.js"></script>
  
<script type="text/javascript">
  	
  	function getStudyCenters(element)
  	{
  		var str = document.getElementById(element).value;
  		$.post("${ProjectURL}/application/getStudyCenters.htm",{ programID: element, educationMode: str})
  		//$.post("http://localhost:8080/NEW_ADMISSION/application/getStudyCenters.htm",{ programID: element, educationMode: str })
			.done(function( data ) {
			document.getElementById(element+"_div").innerHTML=data;
			});
  	}
  	
  	function getOnlineStudyCenters(element)  //added by Jyoti on 23-Apr-2021
  	{
  		var str = ""; //document.getElementById(element).value;
  		
  		if ( document.getElementById(element).checked ) { 
  		    str ="OLM"; 
  		    document.getElementById(element).value = "Y";
  		    $.post("${ProjectURL}/application/getStudyCenterList.htm",{ programID: element, educationMode: str})
			.done(function( data ) {
			document.getElementById(element+"_div").innerHTML=data;
			});
		}
		else {
		 	document.getElementById(element).value = "N";
		 	str="REG";
		 	$.post("${ProjectURL}/application/getStudyCenterList.htm",{ programID: element, educationMode: str})
			.done(function( data ) {
			document.getElementById(element+"_div").innerHTML=data;
			});
		}	
  	}
  
   function getdistanceStudyCenters(element)  //added by Jyoti on 23-Apr-2021
  	{
  		var str = ""; //document.getElementById(element).value;
  		var programId = "";
  		programId = element.toString();
  		programId = programId.substring(0,7);
  		   
  		if ( document.getElementById(element).checked ) { 
  		    str ="DIS"; 
  		    document.getElementById(element).value = "Y";
  		    $.post("${ProjectURL}/application/getStudyCenterList.htm",{ programID: programId, educationMode: str})
			.done(function( data ) {
			document.getElementById(programId+"_div").innerHTML=data;
			});
		}
		else {
		 	document.getElementById(element).value = "N";
		 	str="REG";
		 	$.post("${ProjectURL}/application/getStudyCenterList.htm",{ programID: programId, educationMode: str})
			.done(function( data ) {
			document.getElementById(programId+"_div").innerHTML=data;
			});
		}	
  	}
  	function setRegularMode(element)
  	{
  		if ( document.getElementById(element).checked ) { 
  			document.getElementById(element).value = "Y";
  		}
  		else
  		{
  		 	document.getElementById(element).value = "N";
  		}
  	}
</script>

<script type="text/javascript">
	function validateAcademicDetails()
	{
		noOfPrograms = document.getElementsByName("programIDs").length;
		ttlRegularComponents = document.getElementsByName("regularComponents").length;
		ttlGateComponents = document.getElementsByName("gateComponents").length;
		prgWithBranchCount = document.getElementsByName("programIDWithBranches").length;
		
		//REGULAR COMPONENT LOOP STARTS
		for(i = 0 ; i < ttlRegularComponents; i++)
		{
			regularComponent = document.getElementsByName("regularComponents")[i].value;
			regularCompName = document.getElementsByName("regularCompsName")[i].value;
			obtainedMarks = document.getElementsByName("obtainedMarks")[i].value;
			
			totalMarks = document.getElementsByName("totalMarks")[i].value;
			
			brdUnivRequired = document.getElementsByName("brdUnivRequired")[i].value;
			passingYearRequired = document.getElementsByName("passingYearRequired")[i].value;
			board = document.getElementsByName("boards")[i].value;
			passingYear = document.getElementsByName("passingYears")[i].value;
			rollnumber = document.getElementsByName("rollnumbers")[i].value;
			
			if(obtainedMarks.length > 0)
			{
				if(totalMarks == null || totalMarks.trim() == "")
				{
					alert("OUT OF MARKS CANNOT BE LEFT BLANK FOR "+regularCompName);
					return false;
				}
			}
			
			if(totalMarks.length > 0)
			{
				if(obtainedMarks == null || obtainedMarks.trim() == "")
				{
					alert("OBTAINED MARKS CANNOT BE LEFT BLANK FOR "+regularCompName);
					return false;
				}
			}	
			
				
			if(!((obtainedMarks == null || obtainedMarks.trim() == "") && (totalMarks == null || totalMarks.trim() == "")))
			{
				if(isNaN(obtainedMarks)||isNaN(totalMarks))
				{
					alert("PLEASE ENTER NUMBERS FOR OBTAINED MARKS AND TOTAL MARKS.");
					return false;
				}
				else
				{
					if(parseFloat(obtainedMarks+"") > parseFloat(totalMarks+""))
					{
						alert("OBTAINED MARKS ARE GREATER THAN TOTAL MARKS FOR  "+regularCompName);
						return false;
					}
				}
				
				if(!(obtainedMarks == totalMarks && totalMarks == 0))
				{
					if(brdUnivRequired.trim().toUpperCase()=="Y")
				{
					if(board.trim().toUpperCase()=="")
					{
						universityNeeded = document.getElementsByName("universityNeeded")[i].value;
						if(universityNeeded=="Y")
						{
							alert("PLEASE ENTER UNIVERSITY FOR "+regularCompName);
						}
						else
						{
							alert("PLEASE SELECT BOARD FOR "+regularCompName);
						}
						
						return false;
					}
					
					if(rollnumber.trim().toUpperCase()=="")
					{
						alert("PLEASE ENTER ROLL NUMBER FOR "+regularCompName);
						return false;
					}
				}
			
				if(passingYearRequired.trim().toUpperCase()=="Y")
				{
					if(passingYear == null || passingYear.trim().toUpperCase()=="")
					{
						alert("PLEASE ENTER YEAR OF PASSING FOR "+regularCompName);
						return false;
					}
					else if(isNaN(passingYear))
					{
						alert("PLEASE ENTER NUMBER AS YEAR OF PASSING FOR "+regularCompName);
						return false;
					}
					else
					{
						//NOTHING TO DO
					}
				}
				}
				
				
				
			}
		}//REGULAR COMPONENT ENDS
		
		//GATE COMPONENT LOOP STARTS
		for(x = 0 ; x < ttlGateComponents; x++)
		{
			ranking = document.getElementsByName("ranking")[x].value;
			score = document.getElementsByName("score")[x].value;
			totalApplicants = document.getElementsByName("totalApplicants")[x].value;
			gateBranch = document.getElementsByName("gateBranch")[x].value;
			gateYear = document.getElementsByName("gateYear")[x].value;
			
			if((!(ranking == null || ranking.trim()==""))&& isNaN(ranking))
			{
				alert("PLEASE ENTER NUMBER FOR GATE RANK.");
				return false;
			}
			
			if((!(score == null || score.trim()=="")) && isNaN(score))
			{
				alert("PLEASE ENTER NUMBER FOR GATE SCORE.");
				return false;
			}
			
			if((!(totalApplicants == null || totalApplicants.trim()=="")) && isNaN(totalApplicants))
			{
				alert("PLEASE ENTER NUMBER FOR TOTAL APPLICANTS IN GATE EXAMINATION.");
				return false;
			}
			
			
					
			if((!(gateYear == null || gateYear.trim()=="")) && isNaN(gateYear))
			{
				alert("PLEASE ENTER NUMBER FOR GATE YEAR.");
				return false;
			}
		
		}//GATE COMPONENT LOOP ENDS
		for(j = 0 ; j < noOfPrograms ; j++)
		{
			
			programID = document.getElementsByName("programIDs")[j].value;
			programName = document.getElementsByName("programNames")[j].value;
			educationMode = document.getElementById(programID + "_educationMode").value;
			studyCenter = document.getElementById(programID+"_studyCenter").value;
			
			if(studyCenter == null || studyCenter.trim()=="")
			{
				alert("PLEASE SELECT EXAMINATION CENTER FOR "+programName);
				return false;
			}
			
		}
		
		
		fatherIncome = document.getElementById("fatherIncome").value;
		motherIncome = document.getElementById("motherIncome").value;
		guardianIncome = document.getElementById("guardianIncome").value;
		hostelRequired = document.getElementById("hostelRequired").value;
		everExpelled = document.getElementById("everExpelled").value;
				
		if(!((fatherIncome == null || fatherIncome.trim()=="")||(motherIncome == null || motherIncome.trim()=="")||(guardianIncome == null || guardianIncome.trim()=="")||(hostelRequired == null || hostelRequired.trim()=="")||(everExpelled == null || everExpelled.trim()=="")))
		{
			if(isNaN(fatherIncome)||isNaN(motherIncome)||isNaN(guardianIncome))
			{
				alert("PLEASE ENTER NUMBER FOR INCOME DETAILS.");
				return false;
			}
		}
		
		for(b = 0 ; b < prgWithBranchCount; b++)
		{
			programIDWithBranch = document.getElementsByName("programIDWithBranches")[b].value;
			programNameWithBranch = document.getElementsByName("programNameWithBranches")[b].value;
			programBranchCount = document.getElementsByName(programIDWithBranch+"_BRANCH").length;
			
			for(k = 0 ; k < programBranchCount; k++)
			{
				branch = document.getElementsByName(programIDWithBranch+"_BRANCH")[k].value;
				if(branch == null || branch.trim() == "")
				{
					alert("PLEASE SELECT BRANCH "+(k+1)+" FOR "+programNameWithBranch);
					return false;
				}
				else
				{
					if(k>0)
					{
						previousBranch = document.getElementsByName(programIDWithBranch+"_BRANCH")[k-1].value;
						if(branch == previousBranch)
						{
							alert("BRANCHES MUST BE DIFFERENT FOR "+programNameWithBranch);
							return false;
						}
					}
				}
				
			}
		}
		response = confirm("DO YOU WANT TO SAVE THE DETAILS ?");
		if(response == true)
		{
			return true;
		}
		else
		{
			return false;
		}	
	}
	
function handleSchoolVisibility()
{

	var deiStdFlag = document.getElementById("deiStudent").value;
	
	if(deiStdFlag=="N")
	{
		document.getElementById("schoolLbl").style.display="block";
		document.getElementById("schoolName").style.display="block";
	}
	else
	{
		document.getElementById("schoolLbl").style.display="none";
		document.getElementById("schoolName").style.display="none";
	}
}
</script>


