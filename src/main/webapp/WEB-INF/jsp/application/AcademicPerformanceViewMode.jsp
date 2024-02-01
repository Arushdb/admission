<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html>
  <head>
    <title>Academic Details</title>
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
  <div class="panelHeader"><div class="panelTitle">ACADEMIC DETAILS</div></div>
  <div class="panelContent" align="center" style="padding-left: 0px; padding-right: 0px;">
<label style="font-family: verdana;font-color:green;font-size:12px;">
FOLLOWINGS ARE YOUR SAVED DETAILS.
<br/>
IF YOU WANT TO CHANGE YOUR ACADEMIC DETAILS THEN CLICK <a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/account/getPhase2.htm")%>" style="font-weight: bold;">Change My Academic Details</a></label>
<table cellspacing="5px" cellpadding="2px" width="95%" border="0">



<c:forEach var="groupWiseComponent" items="${sessionScope.applicant.applicationForm.groupWiseComponents}">
<tr>
<th colspan="6" align="left"><c:out value="${groupWiseComponent.description}"/><br><hr/></th>
</tr>

<tr>
<td colspan="6">
	<table cellspacing="5px" cellpadding="2px" width="100%">
	
	<c:forEach var="component" items="${groupWiseComponent.components}" varStatus = "regularExaminationCounter">
	<c:if test="${regularExaminationCounter.count==1}">
	<tr align="left">
	<th style="width: 450px;">Examination</th>
	<th>
	<c:choose>
	<c:when test="${groupWiseComponent.code=='7'}">
	Obtained Marks
	</c:when>
	<c:otherwise>
	Obtained Marks/CGPA
	</c:otherwise>
	</c:choose>
	</th>
	<th>
	<c:choose>
	<c:when test="${groupWiseComponent.code=='7'}">
	Total Marks
	</c:when>
	<c:otherwise>
	Total Marks/Max. CGPA
	</c:otherwise>
	</c:choose>
	</th>
												<th>
													<c:choose>
													<c:when test="${component.boardFlag=='Y'}">
													Board Name
													</c:when>
													<c:when test="${groupWiseComponent.universityToAsk==1}">
													University Name
													</c:when>
													<c:otherwise>
													<!-- Nothing to Show -->
													</c:otherwise>
													</c:choose>
												</th>
												<th>Year of Passing</th>
	</tr>
	</c:if>
	<tr>
	<td>
		<c:choose>
		<c:when test="${groupWiseComponent.showOptions==0}">
			<c:out value="${component.componentName}"/><input type="hidden" name="optionToShow" value="<c:out value='${groupWiseComponent.showOptions}'/>"/><input type="hidden" name="regularComponents" value="<c:out value='${component.componentID}'/>"/>
		</c:when>
		<c:otherwise>
			<c:choose>
			<c:when test="${component.componentID=='UG'}">
				<table>
				<tr>
				<td style="width: 300px;"><c:out value="${component.componentName}"/></td><td>${component.optionName}</td>
				</tr>
				<tr>
				<td>SUBJECT AT HONOURS LEVEL</td><td>${component.subjectOptionName}</td>
				</tr>
				</table>
			</c:when>
			<c:otherwise>
				<table>
				<tr>
				<td style="width: 300px;"><c:out value="${component.componentName}"/>${component.optionName}</td>
				</tr>
				</table>
			</c:otherwise>
			</c:choose>
		</c:otherwise>
		</c:choose>
		
	</td>
						
						<td><c:out value="${component.obtainedMarks}"/></td><td><c:out value="${component.totalMarks}"/></td>
					<td>
					<c:choose>
					<c:when test="${component.boardFlag=='Y'}">
						<c:out value="${component.boardName}"/>
					</c:when>
					<c:when test="${groupWiseComponent.universityToAsk==1}">
						<c:out value="${component.otherBoardName}"/>
					</c:when>
					<c:otherwise>
						
					</c:otherwise>
					</c:choose>
					</td>
					<td>
					<c:choose>
					<c:when test="${component.passingYear=='Y'}">
						<c:out value="${component.passingYearValue}"/>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="passingYears" value=""/>NA
					</c:otherwise>
					</c:choose>
					</td>
	</tr>
	</c:forEach>
	<c:forEach var="component" items="${groupWiseComponent.gateComponents}" varStatus="counter">
		<c:if test="${counter.count==1}">
		<tr style="text-align: left;">
		<th style="width : 300px;"></th><th colspan="2">Gate Rank</th><th>Score</th><th>Total Applicants</th><th>Gate Branch</th><th>Gate Year</th>
		</tr>
		</c:if>
	<tr>
		<td><c:out value="${component.componentName}"/></td>
		<td colspan="2"><c:out value="${component.ranking}"/></td>
		<td><c:out value="${component.score}"/></td>
		<td><c:out value="${component.totalApplicants}"/></td>
		<td><c:out value="${component.gateBranch}"/></td>
		<td><c:out value="${component.gateYear}"/></td>
	</tr>
	
	</c:forEach>
	
	</table>
</td>
</tr>
</c:forEach>




<c:if test="${sessionScope.applicant.applicationForm.branchAvailability=='Y'}">
<tr>
<th colspan="6" align="left"><br/>Preferred Branches Options<br><hr/></th>
</tr>
<tr>
<td colspan="6"> 

	
<c:forEach var="programWiseDetail" items="${sessionScope.applicant.applicationForm.programWiseDetails}">



			
<c:forEach var="branch" items="${programWiseDetail.programBranches}" varStatus="branchCounter">
<c:choose>
<c:when test="${branchCounter.count == 1}">
<c:set var = "selectedBranch" value="${programWiseDetail.branch1Name}"/>
</c:when>
<c:when test="${branchCounter.count == 2}">
<c:set var = "selectedBranch" value="${programWiseDetail.branch2Name}"/>
</c:when>
<c:otherwise>
<c:set var = "selectedBranch" value="${programWiseDetail.branch3Name}"/>
</c:otherwise>
</c:choose>

<c:if test="${branchCounter.count==1}">
<tr>
<td valign="top" colspan="6" align="left">Preferred Branches For :- <b><c:out value="${programWiseDetail.programName}"/></b></td>
</tr>
</c:if>
<c:if test="${branchCounter.count <= 3}">
<tr>
<td colspan="3" align="right">Branch <c:out value="${branchCounter.count}"/></td>
				<td colspan="3" align="left">
				<c:out value="${selectedBranch}"/>
				</td>
</tr>
</c:if>
</c:forEach>
	
</c:forEach>
<!-- </table> --> 
</td>
</tr>
</c:if>

<tr>
<th colspan="6" align="left"><br/>Study Mode Details<br><hr/></th>
</tr>
<tr>
<td colspan="6" align="center">
<table cellpadding="5" cellspacing="0" width="80%">
<tr>
<th align="left">Program Name</th><th align="left">Select Education Mode</th><th align="left">Study Center</th>
</tr>
<c:forEach var="programWiseDetail" items="${sessionScope.applicant.applicationForm.programWiseDetails}" varStatus="counter">
<tr>
	<td align="left">
		<c:out value="${counter.count}"/>.&nbsp&nbsp<c:out value="${programWiseDetail.programName}"/>
	</td>
	<td>
		${programWiseDetail.selectedEducationModeName}
	</td>
	<td>
	
	${programWiseDetail.studyCenterName}
	
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
	<td  align="right">Father Income</td><td><c:out value="${programWiseDetails[0].fatherIncome}"/></td><td align="right">Hostel Required ?</td><td><c:out value="${programWiseDetails[0].hostelRequired}"/></td>
	</tr>
	<tr>
	<td  align="right">Mother Income</td><td><c:out value="${programWiseDetails[0].motherIncome}"/></td><td align="right">Ever Expelled ?</td><td><c:out value="${programWiseDetails[0].everExpelled}"/></td>
	</tr>
	<tr>
	<td  align="right">Guardian Income</td><td colspan="3"><c:out value="${programWiseDetails[0].guardianIncome}"/></td>
	</tr>
	</table>
</td>
</tr>




</table>

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
  </body>
</html>
