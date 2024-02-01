<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

<script src="${ProjectURL}/jsFiles/Properties.js"></script>

<ul>
<li><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/application/getPersonalInformationScreen.htm")%>?errors=${errors}&message=${message}">Step-1 Personal Information</a></li>
<li><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/application/getAcademicPage.htm")%>?error=${error}&message=${message}">Step-2 Program Selection</a></li>
<li><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/application/getProgramComponentsPage.htm")%>?errorsStep3=${errorsStep3}&message=${message}">Step-3 Academic Details</a></li>
<li><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/upload/getUploadDocsPanel.htm")%>?message=${message}">Step-4 Upload Documents</a></li>
<li><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/application/getPaymentPanel.htm")%>?message=${message}">Step-5 Payment</a></li>
<div align="right" width="100%" style="vertical-align: middle;padding-right:5px;">
		<table>
		<tr valign="middle" align="center">
		<!-- <td><a href="${ProjectURL}/application/getApplicationDetails.htm">Application Details</a></td>
		<td>|</td> -->
		<td><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/application/logout.htm")%>">Log Out</a></td>
		</tr>
		</table></div>
</ul>