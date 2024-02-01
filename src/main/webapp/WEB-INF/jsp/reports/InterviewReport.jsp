<!doctype html>
<html lang="en">
<head>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

<title>Download Interview List</title>
<link href="${ProjectURL}/JQ/jquery-ui.css" rel="stylesheet">
<script src="${ProjectURL}/JQ/jquery-1.10.2.js"></script>
<script src="${ProjectURL}/JQ/jquery-ui.js"></script>
<script src="${ProjectURL}/jsFiles/Properties.js"></script>
<link rel="stylesheet" type="text/css" href="${ProjectURL}/css/style.css">
<script src="${ProjectURL}/jsFiles/ForgotPassword.js"></script>
</head>
  
<body>
<script>
function setProgramId()
{
var programValue = document.getElementById("program").value;
document.getElementById("prog2").value = programValue;

}
</script>
<div class="bodyDiv">
<div class="headerDiv"><%@ include file="/templates/Header.jsp" %></div>
<div class="contentDiv" align="center">
<div class="panelContainer"  style="width:800px;">
<div class="panelHeader"><div class="panelTitle">Download Interview List</div></div>
<div class="panelContent" style="padding-left: 20px;padding-right: 20px;">
	
   <form action="generateInterviewReport.htm" method="post">
   <table cellspacing="4" cellpadding="4">
    <tr>
    <td><label for="programs">Programs</label></td> 
    <td>
    <select name="program" id="program" onchange="setProgramId();">
    <option value="X">-Select Program-</option>
    <c:forEach var="program" items="${programList}">
    <option value="<c:out value="${program.programId}"/>"><c:out value="${program.programName}"/></option>
    </c:forEach>
  	</select>
    </td>
    </tr><!--
    
<!--    <tr>-->
<!--    <td>Download Excel report for interview panel</td>-->
<!--    <td><input type="radio" name="reportType" value="exl"/></td>-->
<!--    </tr>-->
<!--    <tr>-->
<!--    <td>Download pdf report for website</td>-->
<!--    <td><input type="radio" name="reportType" value="pdf"/></td>-->
<!--    </tr>-->

    <tr>
    <td colspan="2" align="center"><input type="submit" value="Submit"></td>
    </tr>
     
    </table>
    <!-- 
    <c:out value="${beforeCount}"/> AND  <c:out value="${afterCount}"/>
      -->
      <c:if test="${not empty beforeCount}">
    <c:choose>
    <c:when test="${beforeCount==afterCount}">
    <label style="font-family:verdana;font-size:10px;font-weight:bold;color:green;">Zip generated successfully</label>
    
    </c:when>
    <c:otherwise>
    <label style="font-family:verdana;font-size:10px;font-weight:bold;color:red;">Zip file not generated as excel and pdf have not been generated</label>
    </c:otherwise>
    </c:choose>
   </c:if>
   
   
  </form> 
  
  	 <form action="forDownload.htm" method="post">
   <table cellspacing="4" cellpadding="4">
    <input type="hidden" name="path" value="<c:out value="${zipAddress}"/>"/>
    <input type="hidden" name="programId" value="<c:out value="${progID}"/>"/> 
    <input type="hidden" name="session" value="<c:out value="${session}"/>"/>
    <input type="hidden" name="programId2" id="prog2"/>
    <tr>
    <td align="center"><input type="submit" value="Download"></td>
    </tr>
     
    </table>
    </form>
    <c:if test="${not empty exception}">
    
    <label style="font-family:verdana;font-size:10px;font-weight:bold;color:green;">
<c:out value="${exception}"/>
  </c:if>
<c:if test="${not empty exceptionDnld}">
    
    <label style="font-family:verdana;font-size:10px;font-weight:bold;color:green;">
<c:out value="${exceptionDnld}"/>


</label>
    
    
   </c:if>
   
</div>
</div>
</div>
<div class="footerDiv"><%@ include file="/templates/Footer.html" %></div>
</div>
</body>
</html>
