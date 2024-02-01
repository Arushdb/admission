<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AtulJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
         <h3>Testing:</h3>
<form name="table" method ="post" action ="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath())+"/bubble/method1.htm"%>">
<input type ="text" value ="num1" name ="rowstart">
<input type ="text" value ="num2" name ="rowend">
</br> 
<input type="submit" value="submit number" name="submit">
</form>
 <h3>File Download:</h3>
<form name="table" method ="post" action ="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath())+"/bubble/method1.htm"%>">
<input type ="text" value ="Number" name ="number">
</br> 
<input type="submit" value="submit number" name="submit">
</form>
<h3>File Upload:</h3>
Select a file to upload: <br />
<form action="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath())+"/bubble/method2.htm"%>" method="post" enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<br />
<input type="submit" value="Upload File" />
</form>
  </body>
</html>
