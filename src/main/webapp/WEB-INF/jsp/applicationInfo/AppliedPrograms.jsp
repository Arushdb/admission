<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	response.setContentType("text/html");
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ApplicantHome.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath+"style.css" %>">
	
  </head>
  
  <body>
  <%@ include file="/templates/Header.html" %>
  <%@ page isELIgnored="false" %>
  <table width="100%">
  <tr>
  <td valign="top">
  	
  </td>
  <td valign="top" align="center">
  <div class="panelContainer">
  <div class="panelHeader"><div class="panelTitle">Applications Status</div></div>
  <div class="panelContent" align="center">
  <div>
  
 <table>
 <tr align="left">
 <th>S. No.</th><th>Program Name</th>
 </tr>
 <c:forEach var="program" items="${programs}" varStatus="counter">
 <tr>
 <td align="right"><c:out value="${counter.count}"/></td><td><c:out value="${program.description}"/></td>
 </tr>
 </c:forEach>
 <tr>
 <td colspan="2" align="center"><input type="button" value="Cancel"/></td>
 </tr>
 </table>
 
  	
  	</div>
  </div>
  </div>
  	
  </td>
  </tr>
  
  </table>
  
  <%@ include file="/templates/Footer.html" %>   
  </body>
</html>
