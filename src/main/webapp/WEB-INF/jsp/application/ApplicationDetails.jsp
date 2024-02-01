<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/JQ/jquery-ui.css" %>" rel="stylesheet">
<script src="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/JQ/jquery-1.10.2.js" %>"></script>
<script src="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/JQ/jquery-ui.js" %>"></script>
      <script>
         $(function() {
            
            $( "#formAccordian" ).accordion({collapsible: true,heightStyle: "content"});
         });
         
         function handleComboVisibility(elmID, elmValue)
         {
         	alert(elmID.checked);
         	if(elmID.checked==true)
         	{
         		document.getElementById(elmID.value).disabled=false;
         	}
         	else
         	{
         	document.getElementById(elmID.value).disabled=true;
         	}
         	
         	alert(elm);
         }
           
       </script>
 <link rel="stylesheet" type="text/css" href="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/css/style.css" %>">
 <div width="100%" align="center">
 <div class="panelContainer" style="width: 70%;">
 <div class="panelHeader"><div class="panelTitle">Application Details</div></div>
 <div class="panelContent" align="center"  style="padding-bottom: 20px;padding-top:20px;">     
<div id="formAccordian" style="padding-left: 20px;padding-right: 20px;">
 	
 	<h3>Your Application Status</h3>
 	<div>
 	Your Application Status is : <c:out value='${sessionScope.applicationStatus}'/>
 	</div>
 	<h3>Your Applied Programs</h3>
 	<div>
 	You have applied for the following programs : <c:out value='${sessionScope.applicationStatus}'/>
 	</div>
 	<h3>Downloads</h3>
 	<div>
 	Download Admit Card<br/>Download Application PDF
 	</div>
 	
 	</div><!-- Accordian Div Ends here -->
      </div>
      </div>
      </div>