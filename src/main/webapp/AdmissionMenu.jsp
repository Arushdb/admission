<%@ page session="false" %>
<html>
<link rel="stylesheet" type="text/css" href="css/mystyleMenu.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<head>
</head>

<body>
<div class="bodyDiv">
<div class="headerDiv"><%@ include file="/templates/Header.jsp" %></div>
<div class="contentDiv" style="padding-top:70px;" align="center">

<!-- <div align="center" style="padding-top:200px;"> -->
<div class="panelContainer"  style="width:500px;">
<div class="panelHeader"><div class="panelTitle">Login For Entering CCA and Interview Marks</div></div>
<div class="panelContent" align="center" style="padding-left: 20px;padding-right: 20px;">
<div id ="LoginTable">
<form method="post" action ="login/checkAuthority.htm" >
<table >

<tr>
<td>UserName</td> <td><input type="text" id="user" name ="userName" value ="" /></td>
</tr>

<tr>
<td>Password</td> <td><input type="password" id="pass" name ="password" value ="" /></td>
</tr>

<tr>
<td colspan="2"><input type="submit" value="submit" id="but1" class ="customButton"/>  </td>
</tr>
</table>
</form>
</div>


</div>
</div>
</div>
</div>
 <div class="footerDiv"><%@ include file="/templates/Footer.html" %></div>
</body>


</html>
