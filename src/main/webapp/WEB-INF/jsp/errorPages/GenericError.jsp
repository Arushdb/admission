<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

<script src="${ProjectURL}/jsFiles/Properties.js"></script>
<div width="100%" align="center">
<div class="panelContainer" style="margin-bottom: 0px;padding-bottom:5px;">
<div class="panelHeader"><div class="panelTitle">Error</div></div>
<div class="panelContent" align="center" style="padding-left: 0px; padding-right: 0px;">
<center style="font-family:verdana;color:red;font-size:12px"><c:out value="${error}"/></center>
</div>
</div>
</div>


