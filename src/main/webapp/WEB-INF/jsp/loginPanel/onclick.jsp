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
	
	
	
	 function onlyNos(e, t) {
            try {
                if (window.event) 
                {
                    var charCode = window.event.keyCode;
                }
                else if (e) 
                {
                    var charCode = e.which;
                }
                else
                { 
                return true; 
                }
              //alert("charCode-"+charCode);
              /** Commented by Arjun for Manish Sir
	 if (charCode > 31 && (charCode < 48 || charCode > 57)) 
                {
                alert("Only Numbers are allowed");
                document.getElementById("app_no").value="";
                return false;
                }
                else
                {
              
                } Commented by Arjun for Manish Sir **/
             
               
            }
            catch (err) {
                alert(err.Description);
            }
        }
	
  </script>
  
  

  
  <script>
  
  var USERval;
  
   function appcheck()
        {
        
          if (document.getElementById("app_no").value.length==6)
          {
         // alert(document.getElementById("app_no").value.length);
           var flag1;
           var flagCheck= document.getElementById("checkFlagId").value;
          
            if(flagCheck=="cca")
                    {
                   // alert(flagCheck);
                    falg1="CCA";
                    getAppData(falg1);
                    }
           else if (flagCheck=="int")
                    {
                   //alert(flagCheck);
                    falg1="INT";
                    getAppData(falg1);
                    }
           else if (flagCheck=="both")
                    {
                   // alert(flagCheck);
                    falg1="BOTH";
                    getAppData(falg1);
                    }
          else if (flagCheck=="adm")
                    {
                   //alert(flagCheck);
                    }
             }
           
        return true;
        }
  
  
  var XMLHttprequestobject =false;
        
        if(window.XMLHttpRequest)
        {
        XMLHttprequestobject=new XMLHttpRequest();
        }
        else if(window.ActiveXObject)
        {
        XMLHttprequestobject= new ActiveXObject("Microsoft.XMLHTTP");
        }
  
    function getAppData(flag)
        {
        var aap = document.getElementById("app_no").value;
        var USER=document.getElementById("UserId").value;
        USERval=USER;
        document.getElementById("userVari").value=USERval;
        	//alert(USERval);
        	//alert(USER);
        if (XMLHttprequestobject)
        {
        	XMLHttprequestobject.open("POST", props.projectURL+"cca_int/combodata.htm");
        	XMLHttprequestobject.onreadystatechange=function()
        	{
        		if (XMLHttprequestobject.readyState==4 && XMLHttprequestobject.status==200)
        		{
        			//document.write("Hello don");
        			document.getElementById("CandidateInfo").innerHTML=XMLHttprequestobject.responseText;
        			//document.getElementById("CCA").focus();
        		}
        	}
        	XMLHttprequestobject.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	XMLHttprequestobject.send("AppNumber="+aap+"&FLAG="+flag+"&USER="+USER);
        	//XMLHttprequestobject.send(null);
        }
        
        }
  
  
  
  function onlyNosCCA(e, t)
{
 try {
                if (window.event) 
                {
                    var charCode = window.event.keyCode;
                }
                else if (e) 
                {
                    var charCode = e.which;
                }
                else
                { 
                return true; 
                }
                
/**Commented by Arjun for Manish Sir
                if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode!=190) 
                {
                alert("Only Numbers are allowed");
                document.getElementById("CCA").value="0";
                return false;
                } Commented by Arjun for Manish Sir **/

                if (charCode==13 && document.getElementById("CCA").value.length!="")
                {
              var option1=  confirm("Would you like to save marks for application number "+document.getElementById("app_no").value)
               
                if(option1==false)
				{
					valid = false;
				}
				else
				{
					// alert("CCA Marks are saved");
					var marks =document.getElementById("CCA").value;
					var app1=document.getElementById("app_no").value;
					var  flag1="CCA_Marks";
					//alert("ABCC1");
					if(marks <=12)
					{
					marksUpdate(marks,app1,flag1);
					
					 document.getElementById("CCA").value="";
					 document.getElementById("app_no").value="";
					 document.getElementById("app_no").focus();
					 document.getElementById("tab1").style.display="none";
					}
					else
					{
					alert("Enter CCA marks < = 12");
					}
					
					//alert("ABCC2");
					 
				}
                }
                return true;  
   }
            catch (err) {
                alert(err.Description+"sssss");
            }
            
           
}
  
  
  
  function onlyNosINT(e, t)
{
 try {
                if (window.event) 
                {
                    var charCode = window.event.keyCode;
                }
                else if (e) 
                {
                    var charCode = e.which;
                }
                else
                { 
                return true; 
                }
                
                if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode!=190) 
                {
                alert("Only Numbers are allowed");
                document.getElementById("INT").value="0";
                return false;
                }
                if (charCode==13 && document.getElementById("INT").value.length!="")
                {
              var option1=  confirm("Would you like to save marks for application number "+document.getElementById("app_no").value)
               
                if(option1==false)
				{
					valid = false;
				}
				else
				{
					 //alert("INT Marks are saved");
					var marks =document.getElementById("INT").value;
					var app1=document.getElementById("app_no").value;
					var  flag1="INT_Marks";
					if(marks <=8)
					{
					marksUpdate(marks,app1,flag1);
					
					 document.getElementById("INT").value="";
					 document.getElementById("app_no").value="";
					 document.getElementById("app_no").focus();
					 document.getElementById("tabINT").style.display="none";
					}
					else
					{
					alert("Enter Interview marks <=8");
					}
					
					
				}
                }
                return true;  
   }
            catch (err) {
                alert(err.Description);
            }
}
  
  
  
  function onlyNosBOTHmarks(e, t)
{
 try {
                if (window.event) 
                {
                    var charCode = window.event.keyCode;
                }
                else if (e) 
                {
                    var charCode = e.which;
                }
                else
                { 
                return true; 
                }
                
                if (charCode > 31 && (charCode < 48 || charCode > 57)) 
                {
                alert("Only Numbers are allowed");
                document.getElementById("CCAboth").value="";
                document.getElementById("INTboth").value="";
                return false;
                }
               // alert("cca"+document.getElementById("CCAboth").value);
              //alert("int"+charCode);
                if (charCode==13 && (document.getElementById("CCAboth").value.length!="" || document.getElementById("INTboth").value.length!=""))
                {
              var option1=  confirm("Would you like to save marks for application number "+document.getElementById("app_no").value)
               
                if(option1==false)
				{
					valid = false;
				}
				else
				{
					// alert("BOTH Marks are saved");
					
					
					
					var marksC =document.getElementById("CCAboth").value;
					var marksI =document.getElementById("INTboth").value;
					var app1=document.getElementById("app_no").value;
					var  flag1="BOTH_Marks";
					if (document.getElementById("CCAboth").value.length!="" && document.getElementById("INTboth").value.length!="")
					{
					     flag2="IC";
					}
					else if (document.getElementById("CCAboth").value.length!="" && document.getElementById("INTboth").value.length=="")
					{
					     flag2="C";
					}
					else if (document.getElementById("CCAboth").value.length=="" && document.getElementById("INTboth").value.length!="")
					{
					     flag2="I";
					}
					if (marksC <= 12 && marksI <=8)
					{
					marksUpdate1(marksC,marksI,app1,flag1,flag2);
					
						 document.getElementById("CCAboth").value="";
					 document.getElementById("INTboth").value="";
					 document.getElementById("app_no").value="";
					  document.getElementById("app_no").focus();
					  
					 document.getElementById("tabBOTH").style.display="none";
					}
					else
					{
					alert("Enter CCA marks <=12 and Interview marks <=8");
					}
					
					
				
				}
                }
                return true;  
   }
            catch (err) {
                alert(err.Description);
            }
}
  
  
  
  
  
     function marksUpdate1(marksC,marksI,app,flag,flag2)
{
var USER=document.getElementById("UserId").value;
	   //alert("here-"+marksC+"-"+marksI+"-"+app+"-"+flag+"-"+flag2+"-"+USER);
        if (XMLHttprequestobject)
        {
        	XMLHttprequestobject.open("POST",props.projectURL+"cca_int/InsertMarks.htm");
        	XMLHttprequestobject.onreadystatechange=function()
        	{
        		if (XMLHttprequestobject.readyState==4 && XMLHttprequestobject.status==200)
        		{
        			//document.write("Hello don");
        			//document.getElementById("CandidateInfo1").innerHTML=XMLHttprequestobject.responseText;
        			//document.write(XMLHttprequestobject.responseText);
        			newMethod(XMLHttprequestobject);
        		}
        	}
        	XMLHttprequestobject.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	XMLHttprequestobject.send("AppNumber="+app+"&FLAG="+flag+"&MARKS1="+marksC+"&MARKS2="+marksI+"&FLAG2="+flag2+"&USER="+USER);
        	//XMLHttprequestobject.send(null);
        }
        
} 
  
  
 // function temporary()
 // {
//  alert("DON0");
  
 //    if (XMLHttprequestobject)
   //     {
   //     alert("DON1");
   //     	XMLHttprequestobject.open("GET", "https://kaizala.cloudapp.net/getApplicationToken");
  //      	XMLHttprequestobject.onreadystatechange=function()
   //     	{
   //     		if (XMLHttprequestobject.readyState==4 && XMLHttprequestobject.status==200)
   //     		{
        			//document.write("Hello don");
        //			alert("DON");
        			//document.getElementById("temp").innerHTML=XMLHttprequestobject.responseText;
        			//document.write(XMLHttprequestobject.responseText);
        			//newMethod(XMLHttprequestobject);
        //		}
        //	}
        //	XMLHttprequestobject.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        //	XMLHttprequestobject.send("applicationId="+d556dc34-d859-441a-8661-3c67df7d0909+"&applicationSecret="+5RLSFGSVLI+"&accessToken="+eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cm46bWljcm9zb2Z0OmNyZWRlbnRpYWxzIjoie1wicGhvbmVOdW1iZXJcIjpcIis5MTk2NTIwMDEyNDhcIixcImNJZFwiOlwiXCIsXCJhcHBsaWNhdGlvbklkXCI6XCJkNTU2ZGMzNC1kODU5LTQ0MWEtODY2MS0zYzY3ZGY3ZDA5MDlcIixcInBlcm1pc3Npb25zXCI6XCI4LjRcIn0iLCJ1aWQiOiJNb2JpbGVBcHBzU2VydmljZTo0ZGVmZmEwOC1hMjk2LTRiODctOGMyZi1kOTQ0NTY1ZjVmMzEiLCJ2ZXIiOiIyIiwibmJmIjoxNDc4NjA4NDQyLCJleHAiOjE1MTAxNDQ0NDIsImlhdCI6MTQ3ODYwODQ0MiwiaXNzIjoidXJuOm1pY3Jvc29mdDp3aW5kb3dzLWF6dXJlOnp1bW8iLCJhdWQiOiJ1cm46bWljcm9zb2Z0OndpbmRvd3MtYXp1cmU6enVtbyJ9.T_ZvT8r2xOTMCmkJBmI3MDcrW_Fp8i9xhppjln2jGYY);
        	//XMLHttprequestobject.send(null);
        	
   //     }
   //     return null;
  //}
  
  
  
  
  function marksUpdate(marks,app,flag)
{
        var USER=document.getElementById("UserId").value;

	     
        if (XMLHttprequestobject)
        {
        	XMLHttprequestobject.open("POST", props.projectURL+"cca_int/InsertMarks.htm");
        	XMLHttprequestobject.onreadystatechange=function()
        	{
        		if (XMLHttprequestobject.readyState==4 && XMLHttprequestobject.status==200)
        		{
        			//document.write("Hello don");
        			//document.getElementById("CandidateInfo1").innerHTML=XMLHttprequestobject.responseText;
        			//alert("Marks Updated");
        			newMethod(XMLHttprequestobject);
        		}
        	}
        	XMLHttprequestobject.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	XMLHttprequestobject.send("AppNumber="+app+"&FLAG="+flag+"&MARKS="+marks+"&USER="+USER);
        	//XMLHttprequestobject.send(null);
        }
        
}
  
 
     function newMethod(xml)
        {
        var xmlDoc = xml.responseXML;
        document.getElementById("newTableDis").style.display="block";
        //var table = "<tr> <th> ApplicationNumber</th> <th> Name</th>  <th>Father's Name </th> <th>Gender</th> <th>Category Code </th><th> CCA_Marks</th> <th> INT_Marks</th></tr>";
        var x =xmlDoc.getElementsByTagName("item");
        var table = document.getElementById("newTableDis");
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var cell1 = row.insertCell(0);
         var cell2 = row.insertCell(1);
          var cell3 = row.insertCell(2);
           var cell4 = row.insertCell(3);
            var cell5 = row.insertCell(4);
             var cell6 = row.insertCell(5);
		 var cell7 = row.insertCell(6);
		 cell1.innerHTML = x[0].getElementsByTagName("app")[0].childNodes[0].nodeValue;
		 cell2.innerHTML = x[0].getElementsByTagName("name")[0].childNodes[0].nodeValue;
		 cell3.innerHTML = x[0].getElementsByTagName("father")[0].childNodes[0].nodeValue;
		 cell4.innerHTML = x[0].getElementsByTagName("gender")[0].childNodes[0].nodeValue;
		 cell5.innerHTML = x[0].getElementsByTagName("category")[0].childNodes[0].nodeValue;
		 cell6.innerHTML = x[0].getElementsByTagName("cca")[0].childNodes[0].nodeValue;
		 cell7.innerHTML = x[0].getElementsByTagName("interview")[0].childNodes[0].nodeValue;
      
        } 
  
  
  
  
  
  
  </script>
  

  
  
  
  <div class="bodyDiv">
  <div class="headerDiv">  <%@ include file="/templates/Header.jsp" %></div>
  <div class="contentDiv"   id="bodyContent" align="center">
	<div id ="menuDiv"> <%@ include file="/UserAuthority.jsp" %></div>
	<br/>
	<br/>
	<br/>
	<div>
	<div class="panelContainer"  style="width:80%;">
<div class="panelHeader"><div class="panelTitle">Enter Application Number</div></div>
<div class="panelContent" align="center" style="padding-left: 20px;padding-right: 20px;overflow:auto;height:300px;">

	
<table id="tab2"  width="100%" style="display:block">
<tr>

<td>
Enter Application Number : 
</td>

<td>
<input type="text" id="app_no" name ="app" value ="" maxlength="6" onkeydown="return onlyNos(event,this);" onkeyup="return appcheck()"/>
<input type="hidden" id ="checkFlagId" value ="<c:out value='${sessionScope.checkFlag.flag}'/>"/>
<input type="hidden" id ="UserId" value ="<c:out value='${sessionScope.userId}'/>"/>
</td>
<td  align="right" width="30%"><form method="post" action ="../cca_int/generateReport.htm"><input id ="userVari" name="userVari" type ="hidden"><input type="submit" value="GenereateReport" id="but2" style="display:block;"/></form> </td>

<!-- 
<td colspan="2" align="center"><input type="button" value="submit" id="but1" onclick="return temporary()"/> </td> -->
</tr>

</table>
<!--  
<table border="1" id="tabBOTH" style="display:none;"  cellpadding="5" cellspacing="10">
<tr>
<th> ApplicationNumber</th> <th> Name</th>  <th>Father's Name </th> <th>Gender</th> <th>Category Code </th><th> CCA_Marks</th> <th> INT_Marks</th> 

</tr>
<tr>
<td> 121213</td> <td> Candidate 1</td>  <td>Candidate Father</td> <td>M</td> <td>GN </td> <td> <input type="text" id="CCAboth" name ="cca_marks" value ="" maxlength="6" onkeydown="return onlyNosBOTHmarks(event,this);"/></td> <td> <input type="text" id="INTboth" name ="int_marks" value ="" maxlength="6" onkeydown="return onlyNosBOTHmarks(event,this);"/></td> 
</tr>
</table>

-->

<br/>
<div id="CandidateInfo">

</div>

<div id="CandidateInfo1" style="height: 20px;">
<table id="newTableDis" border="1" style="display:none;"  cellpadding="5" cellspacing="10">
<tr>
<th> ApplicationNumber</th> <th> Name</th>  <th>Father's Name </th> <th>Gender</th> <th>Category Code </th><th> CCA_Marks</th> <th> INT_Marks</th> 
</tr>
</table>
</div>

</div>


<table border="1" id="tab1" style="display:none;"  cellpadding="5" cellspacing="10">
<tr>
<th> ApplicationNumber</th> <th> Name</th>  <th>Father's Name </th> <th>Gender</th> <th>Category Code </th><th> CCA_Marks</th> 

</tr>
<tr>
<td> 121213</td> <td> Candidate 1</td>  <td>Candidate Father</td> <td>M</td> <td>GN </td><td> <input type="text" id="CCA" name ="cca_marks" value ="0" maxlength="6" onkeydown="return onlyNosCCA(event,this);"/></td> 
</tr>
</table>



</div>

</div>
	<!-- container start below menu bar -->
	


	
<!-- container end -->
  </div>
  <div class="footerDiv"> <%@ include file="/templates/Footer.html" %></div>
  </div>
  
  
  </body>
</html>
