<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Attendance Sheet Download</title>
   <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

  </head>
  
  <body>
  <div class="container" style = "width:100%;">
    <c:choose>
 	<c:when test="${not empty programs}">
 	<table class = "table">
 	<thead>
      <tr>
        <th>Select</th>
        <th>Program</th>
        <th>Shift Date & Time</th>
        <th>Vanue</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
 				<c:forEach var="program" items="${requestScope.programs}" varStatus="counter">
 				<form method = "post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/report/getAttSheetGenPage.htm")%>"">
 				<tr>
 				<td align="center"><c:out value='${counter.count}'/>. <input type="hidden" name="program" id="<c:out value='${counter.count}'/>" value="<c:out value='${program.code}'/>" /></td>
 				<td align="left"><c:out value="${program.description}"/></td>
 				<td align="left"><c:out value="${program.otherProperty1}"/><input type="hidden" name="shift" value="<c:out value='${program.otherProperty1}'/>"/></td>
 				<td align="left"><c:out value="${program.description2}"/><input type="hidden" name="vanue" value="<c:out value='${program.code2}'/>"/><input type="hidden" name="vanueName" value="<c:out value='${program.description2}'/>"/></td>
 				<td><input type = "submit" name="btn" value = "M" class="btn btn-primary"/>&nbsp&nbsp<input type = "submit" name="btn" value = "F" class="btn btn-primary"/>&nbsp&nbsp<input type = "submit" name="btn" value = "%" class="btn btn-primary"/></td>
 				</tr>
 				</form>
 				</c:forEach>
 	</tbody>
 				</table>
 				</c:when>
 				</c:choose>
 </div>			
</body>
</html>
