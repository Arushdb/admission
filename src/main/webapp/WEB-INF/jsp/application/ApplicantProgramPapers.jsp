<link rel="stylesheet" type="text/css" href="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/css/style.css" %>">
<script src="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/jsFiles/Properties.js" %>"></script>
      
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
  
<script>
function getPrograms()
{
	//var frmNumber = document.getElementById("formNumber").value;
	//var addMoreProgram = "Y";
	//$.post(props.projectURL+"/application/getFormPrograms.htm", {formNumber:frmNumber,addMoreProgram:addMoreProgram}).done(function(data) {   $("#morePrograms").html(data);  });
	document.getElementById("moreProgramsFrm").submit();
}

function confirmRemoval()
{
	var programs = document.getElementsByName("programs");
	//alert(programs.item(0).value);
	//alert(programs.item(0).checked);
	/**var selectedPrograms = null;
	var programSelected = "N";
	
	for(var i = 0; i < programs.length; i++)
	{
		if(programs.item(i).checked)
		{
			selectedPrograms[i]=programs.item(i).value;
			alert(selectedPrograms[i]);
			programSelected="Y";
		}
	}
	
	if(programSelected=="N")
	{
		alert("PLEASE SELECT A PROGRAM.");
	}
	
	**/
	document.getElementById("programForm").submit();
		
}
 
</script>
<div width="100%" align="center">
<div class="panelContainer" style="margin-bottom: 40px;padding-bottom:10px;marging-top:0px;">
<div class="panelHeader"><div class="panelTitle">Academic Details</div></div>
<div class="panelContent" align="center">
  	<div style="text-align: right;padding-right:30px;padding-bottom:10px;padding-top:0px;">
  				<ul>
  				<li><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/common/generateApplicationPdf.htm")%>" class="downloadLink">DOWNLOAD APPLICATION PDF</a></li>
  				<c:if test="${sessionScope.applicant.applicationStatus=='A'}">
  				<li><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/account/openPage.htm?tabToOpen=2")%>" class="downloadLink">GO TO NEXT STEP (STEP-3 ACADEMIC DETAILS).</a></li>
  				</c:if>
  				</ul>
  		</div>
<center>${error}</center>
<table cellspacing="5px" cellpadding="2px" width="95%" border="0">
<tr>
<th colspan="2" align="right" width="50%">You have applied for the following courses:</th><th colspan="4" align="left"></th>
</tr>
<tr>
	<td colspan="6" align="center" valign="middle">
	
		<table cellspacing="5" cellpadding="5" border="0" id="programFormTbl" style="width:80%;font-size:14px;">
		<c:if test="${sessionScope.applicant.tabStatus<3}">
		<tr>
		<td colspan="4" align="right" valign="middle" style="border-left:1px solid white;border-right: 1px solid white; border-top:1px solid white; ">
					<table>
					<tr>
					<td  style="border:1px solid white;"><form method="post" id="moreProgramsFrm" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/application/getFormPrograms.htm")%>"><input type="hidden" name="formNumber" value="<c:out value='${sessionScope.applicant.applicationForm.formNumber}'/>"/><a href="#" onclick="getPrograms();" class="actionLink">ADD MORE PROGRAMS</a></form></td><td   style="border:1px solid white;"><a href="#" class="actionLink" onclick="confirmRemoval();">DELETE PROGRAMS</a></td>
					</tr>
					</table>
		</td>
		</tr>
		</c:if>
		<tr>
		<td colspan="4">
		<form method ="post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/application/removeProgram.htm")%>" id="programForm">
		<table class="programTbl">
		<tr>
		<th colspan="2" align="center" width="5%">S.NO.</th><th>PROGRAM NAME</th><th width="20%">GROUP</th>
		</tr>
		<c:forEach  var="programWiseDetail" items="${sessionScope.applicant.applicationForm.programWiseDetails}" varStatus="counter">
		
		<tr>
		<td align="right" style="border-right: 0px solid white;">${counter.count}.</td><td style="border-left: 0px solid white;"><c:if test="${sessionScope.applicant.applicationStatus=='A'}"><input type="checkbox" name="programs" value="<c:out value="${programWiseDetail.programID}"/>"/></c:if></td><td>${programWiseDetail.programName}</td><td>${programWiseDetail.paperMainGroupDesc}</td>
		</tr>
		</c:forEach>
		</table>
		</form>
		
		</td>
		</tr>
		
				
</table>

	</td>
</tr>

<tr>
	<td colspan="6">
	<div id="morePrograms"></div>
	</td>
</tr>




<!-- Program Paper Options starts here-->
<c:if test="${sessionScope.applicant.applicationForm.papersAvailability=='Y'}">
<tr>
<th colspan="6" align="left">Entrance Test Options<br><hr/></th>
</tr>

<c:forEach var="programWiseDetail" items="${sessionScope.applicant.applicationForm.programWiseDetails}">
		<c:if test="${programWiseDetail.paperMainGroup!=''}">
		<tr>
		<th  colspan="2" align="right">Paper Options for : </th><th colspan="4" align="left"><c:out value="${programWiseDetail.programPaper.programName}"/></th>
		</tr>
		<tr>
		
		<!--  Panel Starts -->
		<c:forEach var="groupPaper" items="${programWiseDetail.programPaper.groupPapers}" varStatus="ppGroupCounter">
		<c:choose>
		<c:when test="${ppGroupCounter.count==programWiseDetail.programPaper.groupPapersSize}">
			<c:choose>
			<c:when test="${ppGroupCounter.count%2==0}"><td colspan="4" valign="top" align="left"></c:when>
			<c:otherwise><td colspan="2" valign="top" align="right"></c:otherwise>
			</c:choose>
		
		</c:when>
		<c:otherwise>
		<td colspan="2" valign="top" align="right">
		</c:otherwise>
		</c:choose>
		<!-- Commented to convert it to only Table
		<div class="panelContainer" style="width:400px;">
 		<div class="panelHeader"><div class="panelTitle">Paper Options</div></div>
 		<div class="panelContent" align="left" style="padding:5px 5px 5px 5px;height:100px;overflow-y:scroll;"> -->
 		<table><!-- th added to convert it to only Table -->
 		<tr>
 		<th colspan="2" align="left">Group <c:out value="${ppGroupCounter.count}"/></th>
 		</tr>
 		<c:forEach var="selectedPaper" items="${groupPaper.selectedPapers}" varStatus="paperCounter">
 		<tr>
 		<td align="right"><c:out value="${paperCounter.count}"/>. </td><td align="left"><c:out value="${selectedPaper.description}"/></td>
 		</tr>
 		</c:forEach>
 		</table>
 		<!-- Commented to convert it to only Table
 		</div>
 		</div>  Panel Ends -->
 		</td>
 		</c:forEach>
		
		</tr>
				</c:if>
		
		</c:forEach><!-- programWiseDetail ends -->
	
</c:if>
<!-- Examination Centers Starts -->
		<c:if test="${sessionScope.applicant.applicationForm.papersAvailability=='Y'}">
		<tr>
		<th colspan="6" align="left">Examination Centers<br><hr/></th>
		</tr>
 		<tr>
 		<td align="right" colspan="3" width="50%"><b>Preferred Examination Center for Entrance Test</b></td><td align="left" width="50%"><c:out value="${sessionScope.applicant.examCenter1Name}"/></td>
 		</tr>
 		</c:if>
 		<!-- 
 		<tr>
 		<td align="right" colspan="3" width="50%">Preferred Examination Center 2</td><td align="left" width="50%"><c:out value="${sessionScope.applicant.examCenter2}"/></td>
 		</tr>
 		<tr>
 		<td align="right" colspan="3" width="50%">Preferred Examination Center 3</td><td align="left" width="50%"><c:out value="${sessionScope.applicant.examCenter3}"/></td>
 		</tr> -->
 		<!-- Examination Centers Ends -->

	
			

<!--  Program Paper Option ends here -->
		<!-- Staff Ward Details Starts Here -->
		<!-- 
		<tr>
		<th colspan="6" align="left">Staff Ward Details<br><hr/></th>
		</tr>
		<tr>
 		<td align="right" colspan="3" width="50%">Are you staff ward/spouse ? </td><td align="left" width="50%"><c:out value="${sessionScope.applicant.staffWardFlag}"/></td>
 		</tr>
 		<c:if test="${sessionScope.applicant.staffWardFlag=='Y'}">
 		<tr>
 		<td align="right" colspan="3" width="50%">Staff Ward Code : </td><td align="left" width="50%"><c:out value="${sessionScope.applicant.staffCode}"/></td>
 		</tr>
 		</c:if>
 		 -->
 		<!-- Staff Ward Details Starts Ends -->

</table>

<div style="font-family:verdana;color:red;font-weight:bold;">
  							${message}
  							</div>	
  </div>
  </div>
</div>				
