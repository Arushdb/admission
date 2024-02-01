<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
function handleComboVisibility(elmID, elmValue)
         {
         	
         	if(elmID.checked==true)
         	{
         		document.getElementById(elmID.value).disabled=false;
         		document.getElementById(elmID.value+"_PPR").disabled=false;
         	}
         	else
         	{
         		document.getElementById(elmID.value).disabled=true;
         		document.getElementById(elmID.value+"_PPR").disabled=true;
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
 	<form method="post" id="programs" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/application/getPaperOptionsPageForReapply.htm")%>">
 	<div class="panelContainer" style="border:1px solid #6A8CE9;">
 	<div class="panelHeader" style="background-color:#6A8CE9;border:1px solid #6A8CE9;"><div class="panelTitle">Paper Options</div></div>
 	<div class="panelContent" align="left" style="padding:5px 5px 5px 5px;height:200px;overflow-y:scroll;">
 	
 	<center><label style="font-family:verdana;font-size:12px;color:red;">
	${error}
	</label></center>
 	
 	<table id="programFormTbl">
 	<tr>
 				<th>Select</th><th align="left">Program Name</th><th align="left">Paper Options</th>
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
									-- <input type="hidden" name="paperOptions"  disabled="disabled" value="<c:out value='${program.theOnlyPprCode}'/>" id="<c:out value='${program.programID}'/>"/>
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
								<input type="hidden" name="paperOptions" id="<c:out value='${program.programID}'/>" value="" disabled="disabled"/>--
								</c:otherwise>
								</c:choose>
 								</td>
 				</tr>
 				</c:forEach>
 				</table>
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
 	 	