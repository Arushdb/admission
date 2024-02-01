<!DOCTYPE html>
<html lang="en">
<head>
  <title>Program Counter</title>

<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="${ProjectURL}/resources/bootstrap4/css/bootstrap.min.css">
  <script src="${ProjectURL}/resources/bootstrap4/js/bootstrap.min.js"></script>
  <script src="${ProjectURL}/resources/jquery/jquery-3.5.0.min.js"></script>
</head>
<body>

<div class="container mt-3">
  <table>
  <tr>
  <td><h4>Total Complete Applications : <label style="color:red"><c:out value='${totalApplicationsCount}'/></label>&nbsp&nbsp&nbsp</h4></td>
  <td><h4>Fee Not Verified : <label style="color:red"><c:out value='${feeNotVerifiedAppsCount}'/></label>&nbsp&nbsp&nbsp</h4></td>
  <td><h4>Fee Verified : <label style="color:red"><c:out value='${feeVerifiedAppsCount}'/></label>&nbsp&nbsp&nbsp</h4></td>
  </tr>
  </table>

	Search for a program : <input class="form-control" id="myInput" type="text" placeholder="Search.."> <br/> 
  <table class="table table-bordered">
    <thead>
      <tr>
      	<th>S.No.</th>
        <th>Program Name</th>
        <th>Total Applications</th>
        <th>Fee Not Verified</th>
        <th>Fee Verified</th>
      </tr>
    </thead>
    <tbody id="myTable">
    <c:forEach var="program" items="${programs}" varStatus="counter">
    <tr>
    	<td><c:out value='${counter.count}'/></td>
        <td><c:out value='${program.programName}'/></td>
        <td><c:out value='${program.totalApplicationsCount}'/></td>
     	<td><c:out value='${program.feeNotVerifiedAppsCount}'/></td>
        <td><c:out value='${program.feeVerifiedAppsCount}'/></td>
      </tr>
    </c:forEach>
      
    </tbody>
  </table>
</div>


<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>

</body>
</html>
