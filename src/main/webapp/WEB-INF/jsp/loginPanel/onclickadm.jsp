<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

<!doctype html>
<html>
  <head>
    <title>User Form</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<script src="${ProjectURL}/JQ/jquery-1.10.2.js"></script>
	<script src="${ProjectURL}/jsFiles/Properties.js"></script>
	<link rel="stylesheet" type="text/css" href="${ProjectURL}/css/style.css">
	<script>
		function preventBack()
		{
			window.history.forward();
		}
  		setTimeout("preventBack()", 0);
  		window.onunload=function(){null};
	</script>
	
	
	
  </head>
  
  <body>
  <script>
  	function getHomePage()
	{
		window.location.replace(props.projectURL);
	}
	
	
  </script>
  
  

  
  <script>
  var globalEditing;
  var XMLHttprequestobject =false;
        
        if(window.XMLHttpRequest)
        {
        XMLHttprequestobject=new XMLHttpRequest();
        }
        else if(window.ActiveXObject)
        {
        XMLHttprequestobject= new ActiveXObject("Microsoft.XMLHTTP");
        }
  

  function ccaEdit()
  {
  var e1= document.getElementsByName("Edit");
  
  for (i=0; i<e1.length;i++ )
  {
   if (e1[i].checked)
                {
                if (e1[i].value=="intEdit")
                   {
                   globalEditing="intE";
                  // alert(e1[i].value);
                   }
                else if (e1[i].value=="ccaEdit")
                   {
                   globalEditing="ccaE";
                   }
                else if (e1[i].value=="bothEdit")
                   {
                 globalEditing="bothE";
                   } 
                }
                
  }
  editMarks(globalEditing);
  
  } 
 
  function editMarks(v)
   {
   if (v=="intE")
   {
  // alert("enter application number for Interview marks update");
   document.getElementById("editAppNo").style.display="block";
   }
   else if (v=="ccaE")
   {
  // alert("enter application number for CCA marks update");
   document.getElementById("editAppNo").style.display="block";
   }
   else if (v=="bothE")
   {
  // alert("enter application number for Both marks update");
   document.getElementById("editAppNo").style.display="block";
   }
   else
   {
   alert("Kindly select only one option");
   }
   //alert(v);
   } 
 
 
     function  onlyNosEdit2()
       {
       
                   if (document.getElementById("app_no_edit").value.length==6)
                     {
                     //document.getElementById("editAppNo").style.display="block";
                    // alert("Now User Can Edit Marks");
                      var option2=  confirm("Would you like to Enable marks editing for this application number")
                       if(option2==false)
				        {
				        
					valid = false;
					
				        }
				       else
				       {
				       alert("Now User Can Edit Marks for Applicatati number"+document.getElementById("app_no_edit").value);
				        document.getElementById("editAppNo").style.display="none";
				        getAppDataForEditing(globalEditing);
                        document.getElementById("app_no_edit").value="";
                        document.getElementById("ccaBox").selected=false;
                        document.getElementById("intBox").selected=false;
                        document.getElementById("bothBox").selected=false;
                        
                        
				       }
                     
                     }
                    else if(document.getElementById("app_no_edit").value.length!=6)
                     {
                    //document.getElementById("editAppNo").style.display="none";
                     }
                     return true;
       }
       
             function getAppDataForEditing(flag)
        {
        var aap = document.getElementById("app_no_edit").value;
        var USER=document.getElementById("UserId").value;
        	//alert(aap);
        if (XMLHttprequestobject)
        {
        	XMLHttprequestobject.open("POST", props.projectURL+"cca_int/EditDataEnable.htm");
        	XMLHttprequestobject.onreadystatechange=function()
        	{
        		if (XMLHttprequestobject.readyState==4 && XMLHttprequestobject.status==200)
        		{
        			//document.write("Hello don");
        			document.getElementById("CandidateInfo").innerHTML=XMLHttprequestobject.responseText;
        		}
        	}
        	XMLHttprequestobject.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	XMLHttprequestobject.send("AppNumber="+aap+"&FLAG="+globalEditing+"&USER="+USER);
        	//XMLHttprequestobject.send(null);
        }
        
        } 
  </script>
  

  
  
  
  <div class="bodyDiv">
  <div class="headerDiv">  <%@ include file="/templates/Header.jsp" %></div>
  <div class="contentDiv"   id="bodyContent" align="center">
	<div id ="menuDiv"> <%@ include file="/UserAuthority.jsp" %></div>
	
	<!-- container start below menu bar -->
	<br/>
	<br/>
	<br/>
	<br/>
	
		<div class="panelContainer"  style="width:80%;">
<div class="panelHeader"><div class="panelTitle">Enable Marks Editing</div></div>
<div class="panelContent" align="center" style="padding-left: 20px;padding-right: 20px;overflow:auto;height:250px;">
		<div >
	
<table border="1" id="admUSER" style="display:block;"  cellpadding="5" cellspacing="10">
<tr>
<th> CCA Marks Editing</th> <th> INT Marks Editing</th>  <th>BOTH Marks Editing </th>  

</tr>
<tr>
<td> <input type="radio" id ="ccaBox" value ="ccaEdit" name ="Edit" onclick="ccaEdit()"/> </td> <td> <input type="radio" id ="intBox" value ="intEdit" name ="Edit" onclick="ccaEdit()"/></td> <td><input type="radio" id ="bothBox" value ="bothEdit" name ="Edit" onclick="ccaEdit()"/>
<input type="hidden" id ="UserId" value ="<c:out value='${sessionScope.userId}'/>"/>
</td> 

</tr>
</table>
</br>
</br>
<table id="editAppNo" style="display:none;" >
<tr>
<td> Enter Application Number for Marks Editing</td> <td><input type="text" id ="app_no_edit" maxlength="6"  onkeyup="return onlyNosEdit2();" /></td>
</tr>

</table>	
<!-- container end -->
  </div>
	
	</div>
	</div>
	

  <div class="footerDiv"> <%@ include file="/templates/Footer.html" %></div>
  </div>
  </div>
  
  </body>
</html>
