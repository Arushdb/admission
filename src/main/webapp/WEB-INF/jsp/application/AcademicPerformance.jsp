<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html>
  <head>
    <title>ACADEMIC PERFORMANCE</title>
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
	
	<style>
		.smallFields
		{
			width:80px;
		}
	</style>
	
	
  </head>
  
  <body>
  <c:set var="programWiseDetails" value="${sessionScope.applicant.applicationForm.programWiseDetails}"/>
  <div class="bodyDiv">
  <div class="headerDiv">  <%@ include file="/templates/Header.jsp" %>
  </div>
  <%@ include file="/templates/HorizontalNavigation.jsp" %>
  <div class="contentDiv"   id="bodyContent">
  <div width="100%" align="center">
  <div class="panelContainer" style="margin-bottom: 10px;padding-bottom:10px;marging-top:0px;margin-left:10px;margin-right:10px;">
  <div class="panelHeader"><div class="panelTitle">Application Form</div></div>
  <div class="panelContent" align="center" style="padding-left: 0px; padding-right: 0px;">
  
  <ul style="font-family: verdana; color:red;font-size:12px;font-weight:bold;">
  <c:forEach var="error" items="${requestScope.errors}">
  <li>${error}</li>
  </c:forEach>
  
  </ul>

<form method="post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/application/saveAcademicDetails.htm")%>" onsubmit="return validateDetails();">
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
		<th style="width: 450px;">Examination</th>
		<th>
		<c:choose>
			<c:when test="${groupWiseComponent.code=='7'}">Obtained Marks</c:when>
			<c:otherwise>Obtained Marks/CGPA
				<c:if test="${groupWiseComponent.code=='2'}">
				<br/><label style="font-family: verdana;font-weight:bold;font-size:10px;color:green;"> ( Include All Subjects )</label>
				</c:if>
			</c:otherwise>
		</c:choose>
		</th>
		<th>
		<c:choose>
			<c:when test="${groupWiseComponent.code=='7'}">Out of Marks</c:when>
			<c:otherwise>Out of Marks/Max. CGPA
				<c:if test="${groupWiseComponent.code=='2'}">
					<br/><label style="font-family: verdana;font-weight:bold;font-size:10px;color:green;"> ( Include All Subjects )</label>
				</c:if>
			</c:otherwise>
		</c:choose>
		</th>
		<th>
		<c:choose>
			<c:when test="${component.boardFlag=='Y'}">Board Name</c:when>
			<c:when test="${groupWiseComponent.universityToAsk==1}">University Name</c:when>
			<c:otherwise><!-- Nothing to Show --></c:otherwise>
		</c:choose>
		</th>
		<th>Year of Passing</th>
	</tr>
	</c:if>
	<tr>
	<td>
		<c:choose>
		<c:when test="${groupWiseComponent.showOptions==0}">
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
		<td><input type="text" autocomplete="off" name="gateYear" style="width:80px;" value="<c:out value='${component.gateYear}'/>"/></td>
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
				<c:otherwise>
					<c:set var = "selectedBranch" value="${programWiseDetail.branch3}"/>
				</c:otherwise>
			</c:choose>

<c:if test="${branchCounter.count==1}">
<tr>
<td valign="top" colspan="6" align="left">Preferred Branches For :- <b><c:out value="${programWiseDetail.programName}"/><input type="hidden" name="programIDWithBranches" value="<c:out value="${programWiseDetail.programID}"/>"/><input type="hidden" name="programNameWithBranches" value="<c:out value="${programWiseDetail.programName}"/>"/></b></td>
</tr>
</c:if>
<c:if test="${branchCounter.count <= 3}">
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


<tr>
<th colspan="6" align="left"><br/>Study Mode Details<br><hr/></th>
</tr>
<tr>
<td colspan="6" align="center">


<div style="text-align: left;">
<label style="font-family:verdana;font-size:12px;color:red;font-weight: bold;">Important note for P.G. DIPLOMA (THEOLOGY) :</label>
<ul style="font-family:verdana;font-size:12px;color:green;">
<li>If a candidate is in a foreign country and does not have any study center in his/her city then he/she can apply in <b style="color:red;">Online Mode.</b></li>
</ul>
</div>

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



<tr>
<th colspan="6" align="left"><br/>Other Details<br><hr/></th>
</tr>
<tr>
<td colspan="6" align="center">
	<table cellspacing="5px" cellpadding="2px">
	<tr>
	<td  align="right">Father Annual Income</td><td><input type="text" autocomplete="off" name="fatherIncome" id="fatherIncome" maxlength="8" value="<c:out value="${programWiseDetails[0].fatherIncome}"/>"/></td><td align="right">Hostel Required ?</td><td><adm:selectYN defaultText="-Select-" name="hostelRequired" id="hostelRequired" selectedValue="${programWiseDetails[0].hostelRequired}"/></td>
	</tr>
	<tr>
	<td  align="right">Mother Annual Income</td><td><input type="text" autocomplete="off" name="motherIncome" id="motherIncome" maxlength="8" value="<c:out value="${programWiseDetails[0].motherIncome}"/>"/></td><td align="right">Ever Expelled ?</td><td><adm:selectYN defaultText="-Select-" name="everExpelled" id="everExpelled" selectedValue="${programWiseDetails[0].everExpelled}"/></td>
	</tr>
	<tr>
	<td  align="right">Guardian Annual Income</td><td colspan="3"><input type="text" autocomplete="off" name="guardianIncome" maxlength="8" id="guardianIncome" value="<c:out value="${programWiseDetails[0].guardianIncome}"/>"/></td>
	</tr>
	</table>
</td>
</tr>



<tr>
<td colspan="6" align="center" style="width: 100%;"><input type="submit" value="SUBMIT" class="customButton"/></td>
</tr>
</table>
</form>

</div>
</div>
</div>				

</div>
<div class="footerDiv"> <%@ include file="/templates/Footer.html" %></div>
</div>
  
<script src="../JQ/jquery-1.10.2.js"></script>
<script src="${ProjectURL}/jsFiles/Properties.js"></script>
  
<script type="text/javascript">
  	
  	function getStudyCenters(element)
  	{
  		var str = document.getElementById(element).value;
  		$.post("${ProjectURL}/application/getStudyCenters.htm",{ programID: element, educationMode: str })
			.done(function( data ) {
			document.getElementById(element+"_div").innerHTML=data;
		});
  		
  	}
</script>

<script type="text/javascript">
	function validateDetails()
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
			educationMode = document.getElementById(programID).value;
			studyCenter = document.getElementById(programID+"_studyCenter").value;
			
			if(educationMode == null || educationMode.trim()=="")
			{
				alert("PLEASE SELECT EDUCATION MODE FOR "+programName);
				return false;
			}
			else
			{
				if(studyCenter == null || studyCenter.trim()=="")
				{
					alert("PLEASE SELECT STUDY CENTER FOR "+programName);
					return false;
				}
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
</script>

</body>
</html>
