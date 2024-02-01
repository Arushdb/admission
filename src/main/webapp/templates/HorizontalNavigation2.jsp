<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

<script src="${ProjectURL}/jsFiles/Properties.js"></script>
<style type="text/css">
#menu {

     
     width: auto;
     background-color: #6A5ACD;
     font-size: 12px;
     font-family: Tahoma, Geneva, sans-serif;
     font-weight: bold;
     text-align: left;
     padding: 2px;
     border-radius: 0px;
     -webkit-border-radius: 0px;
     -moz-border-radius: 0px;
     -o-border-radius: 0px;
     margin-top:0px;
     margin-left:0px;
     margin-right:0px;
     margin-bottom:5px;
}

#menu ul {
     margin: 0;
     padding: 8px 0;
     list-style: none;
     height: auto;
     text-align: right;
}

#menu li {
     display: inline;
     padding: 8px;
}

#menu a {
     color: #FFF;
     padding: 6px;
     text-decoration: none;
}

#menu a:hover {
     background-color: #4A3F90;
     color: #FFF;
     border-radius: 4px;
     -webkit-border-radius: 4px;
     -moz-border-radius: 4px;
     -o-border-radius: 4px;
}

#menu li .active {
     background-color: #4A3F90;
     color: #FFF;
     border-radius: 4px;
     -webkit-border-radius: 4px;
     -moz-border-radius: 4px;
     -o-border-radius: 4px;
}
</style>

<div id="menu">
    <ul>
    <li><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/manualmanage/editApplication.htm")%>">Log Out</a></li>
    </ul>
   </div>
