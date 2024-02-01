<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

<div style="background-color:#6A8CE9;color:white;padding-top:2px;padding-bottom:2px;border-bottom:1px solid black;">
<center><h1 style="font-family:georgia;align:center;">ONLINE ADMISSION SYSTEM</h1>
<p align="center" style="font-family:verdana;font-size:14px;">An Open Source Initiative of the Ministry of Human Resource & Development<br>
   ( Developed under the National Mission on Education through Information & Communication Technology )</p></center>

<img src="${ProjectURL}/images/LOGO3.gif" height="90" width="90" style="position:absolute;left:25px;top:25px;z-index:2;"/>

</div> 
<hr width="100%"/> 

 