<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div width="100%" align="center">
<div class="panelContainer" style="width: 70%;">
<div class="panelHeader"><div class="panelTitle">Program Groups</div></div>
<div class="panelContent" align="center"  style="padding-bottom: 20px;padding-top:20px;">
<label style="font-family:verdana;font-size:12px;color:red;">
${error}
</label>
<form method="post" action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/application/getFormPrograms.htm")%>">
<table>
<c:forEach var="form" items="${forms}" varStatus="counter">
<tr>
<td>${counter.count}</td><td align="right"><input type="radio" name="formNumber" value="${form.code}"/></td><td>${form.description}</td>
</tr>
</c:forEach>
<tr>
<td colspan="3" align="center"><input type="submit" value="NEXT >>" class="customButton"/></td>
</tr>
</table>
<input type="hidden" name="formSelected" value="Y"/>
</form>
</div>
</div>

