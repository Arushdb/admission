<html>
<!-- <link rel="stylesheet" type="text/css" href="css/mystyle.css"> -->
<script type="text/javascript">
var globalEditing;
var USER;
var Autho;
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
                { Commented by Arjun for Manish Sir**/
                /**
                   if (document.getElementById("app_no").value.length==5)
                     {
                     
                   //  document.getElementById("CCA").focus();
                   //alert(document.getElementById("app_no").value.length);
                    if (document.getElementById("userName").value=="CCA_USER")
                    {
                    getAppData();
                    //alert(document.getElementById("userName").value);
                   // document.getElementById("tab1").style.display="block";
                    }
                    else if (document.getElementById("userName").value=="INT_USER")
                    {
                    document.getElementById("tabINT").style.display="block";
                    }
                    else if (document.getElementById("userName").value=="BOTH_USER")
                    {
                    document.getElementById("tabBOTH").style.display="block";
                    }
                    else
                    {
                    alert("error")
                    }
                    }
                    else if(document.getElementById("app_no").value.length!=5)
                    {
                    document.getElementById("tab1").style.display="none";
                    document.getElementById("tabINT").style.display="none";
                    document.getElementById("tabBOTH").style.display="none";
                    }
                     return true;
                     */
               // }Commented by Arjun for Manish Sir
             
               
            }
            catch (err) {
                alert(err.Description);
            }
        }
        
        function appcheck()
        {
        if (document.getElementById("app_no").value.length==6)
                     {
                     var flag1;
                   //  document.getElementById("CCA").focus();
                   //alert(document.getElementById("app_no").value.length);
                    if (Autho=="cca")
                    {
                    falg1="CCA";
                    getAppData(falg1);
               
                    
                    //alert(document.getElementById("userName").value);
                    //document.getElementById("tab1").style.display="block";
                    
                    }
                    else if (Autho=="interview")
                    {
                     falg1="INT";
                     getAppData(falg1);
                    //document.getElementById("tabINT").style.display="block";
                    }
                    else if (Autho=="both")
                    {
                    
                     falg1="BOTH";
                     getAppData(falg1);
                    //document.getElementById("tabBOTH").style.display="block";
                    }
                    else
                    {
                    alert("error")
                    }
                    }
                    else if(document.getElementById("app_no").value.length!=6)
                    {
                    document.getElementById("tab1").style.display="none";
                    document.getElementById("tabINT").style.display="none";
                    document.getElementById("tabBOTH").style.display="none";
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
        	//alert(aap);
        if (XMLHttprequestobject)
        {
        	XMLHttprequestobject.open("POST", "http://localhost:8080/ADMISSION_SYSTEM/cca_int/combodata.htm");
        	XMLHttprequestobject.onreadystatechange=function()
        	{
        		if (XMLHttprequestobject.readyState==4 && XMLHttprequestobject.status==200)
        		{
        			//document.write("Hello don");
        			document.getElementById("CandidateInfo").innerHTML=XMLHttprequestobject.responseText;
        			document.getElementById("CCA").focus();
        		}
        	}
        	XMLHttprequestobject.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	XMLHttprequestobject.send("AppNumber="+aap+"&FLAG="+flag+"&USER="+USER);
        	//XMLHttprequestobject.send(null);
        }
        
        }
        
        
        
        
        
         function getAppDataForEditing(flag)
        {
        var aap = document.getElementById("app_no_edit").value;
        	//alert(aap);
        if (XMLHttprequestobject)
        {
        	XMLHttprequestobject.open("POST", "http://localhost:8080/ADMISSION_SYSTEM/cca_int/EditDataEnable.htm");
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
        
        
function marksUpdate(marks,app,flag)
{
	     
        if (XMLHttprequestobject)
        {
        	XMLHttprequestobject.open("POST", "http://localhost:8080/ADMISSION_SYSTEM/cca_int/InsertMarks.htm");
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
        
        
   function marksUpdate1(marksC,marksI,app,flag,flag2)
{
	     
        if (XMLHttprequestobject)
        {
        	XMLHttprequestobject.open("POST", "http://localhost:8080/ADMISSION_SYSTEM/cca_int/InsertMarks.htm");
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
        
        
        
        
        
     function onlyNosEdit(e, t) {
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
                if (charCode > 31 && (charCode < 48 || charCode > 57)) 
                {
                alert("Only Numbers are allowed");
                document.getElementById("app_no_edit").value="";
                return false;
                }
                else
                {
                /**
                   if (document.getElementById("app_no_edit").value.length==5)
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
                        document.getElementById("app_no_edit").value="";
                        document.getElementById("ccaBox").selected=false;
                        document.getElementById("intBox").selected=false;
                        document.getElementById("bothBox").selected=false;
                        ccaBox
				       }
                     
                     }
                    else if(document.getElementById("app_no_edit").value.length!=5)
                     {
                    //document.getElementById("editAppNo").style.display="none";
                     }
                     */
                     return true;
                     
                }
             
               
            }
            catch (err) 
            {
                alert(err.Description);
            }
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
                if (charCode > 31 && (charCode < 48 || charCode > 57)) 
                {
                alert("Only Numbers are allowed");
                document.getElementById("CCA").value="0";
                return false;
                }Commented by Arjun for Manish Sir **/
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
					
					marksUpdate(marks,app1,flag1);
					
					 document.getElementById("CCA").value="";
					 document.getElementById("app_no").value="";
					 document.getElementById("app_no").focus();
					  
					 document.getElementById("tab1").style.display="none";
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
                
                if (charCode > 31 && (charCode < 48 || charCode > 57)) 
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
					
					marksUpdate(marks,app1,flag1);
					 document.getElementById("INT").value="";
					 document.getElementById("app_no").value="";
					  document.getElementById("app_no").focus();
					  
					 document.getElementById("tabINT").style.display="none";
				}
                }
                return true;  
   }
            catch (err) {
                alert(err.Description);
            }
}



function onlyNosBOTH(e, t)
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
                if (charCode==13 && document.getElementById("CCAboth").value.length!="" || document.getElementById("INTboth").value.length!="")
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
					marksUpdate1(marksC,marksI,app1,flag1,flag2);
					
					 document.getElementById("CCAboth").value="";
					 document.getElementById("INTboth").value="";
					 document.getElementById("app_no").value="";
					  document.getElementById("app_no").focus();
					  
					 document.getElementById("tabBOTH").style.display="none";
				}
                }
                return true;  
   }
            catch (err) {
                alert(err.Description);
            }
}

function CheckUserAuthority(userName,password)
{
var  value1;
 if (XMLHttprequestobject)
        {
        	XMLHttprequestobject.open("POST", "http://localhost:8080/ADMISSION_SYSTEM/cca_int/checkAuthority.htm");
        	XMLHttprequestobject.onreadystatechange=function()
        	{
        		if (XMLHttprequestobject.readyState==4 && XMLHttprequestobject.status==200)
        		{
        			//document.write("Hello don");
        			//document.getElementById("CandidateInfo1").innerHTML=XMLHttprequestobject.responseText;
        			//document.write(XMLHttprequestobject.responseText);
        			//document.write(XMLHttprequestobject.responseText);
        			//value1=XMLHttprequestobject.responseText.value;
        			//newMethod(XMLHttprequestobject);
        			
        			//checkUserName(XMLHttprequestobject.responseText);
        		check11(XMLHttprequestobject);
        		}
        	}
        	XMLHttprequestobject.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	XMLHttprequestobject.send("userName="+userName+"&password="+password);
        	//XMLHttprequestobject.send(null);
        		
        }
}

function check11(xml)
{
var xmlDoc = xml.responseXML;
var x =xmlDoc.getElementsByTagName("CodeList");
var abc = x[0].getElementsByTagName("autho")[0].childNodes[0].nodeValue;
var abc1 = x[0].getElementsByTagName("modi")[0].childNodes[0].nodeValue;

checkUserName(abc,abc1);

}

function callCheckAuthority()
{
 CheckUserAuthority(document.getElementById("userName").value,document.getElementById("password").value);
}

function generateReport()
{

    if (XMLHttprequestobject)
        {
        	XMLHttprequestobject.open("POST", "http://localhost:8080/ADMISSION_SYSTEM/cca_int/generateReport.htm");
        	XMLHttprequestobject.onreadystatechange=function()
        	{
        		if (XMLHttprequestobject.readyState==4 && XMLHttprequestobject.status==200)
        		{
        		alert("working excel genrated");
        		//check11(XMLHttprequestobject);
        		}
        	}
        	XMLHttprequestobject.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	XMLHttprequestobject.send("&USER="+USER);
        	//XMLHttprequestobject.send(null);
        		
        }
}
 function checkUserName(val,user)
            {
          
          // document.getElementById("demo").innerHTML = CheckUserAuthority(document.getElementById("userName").value,document.getElementById("password").value);
          //alert(val.trim());
           var authority;
           USER=user;
           document.getElementById("userVari").value=user;
          
           
           //alert(val);
           //alert(user);
            if (val.trim()=="PW")
            {
            Autho="interview";
            authority="interview";
            
            }
            else if (val.trim()=="CA")
            {
            authority="cca";
            Autho="cca";
            }
            else if (val.trim()=="BA")
            {
            authority="both";
            Autho="both";
            }
             else if (val.trim()=="AA")
            {
            authority="all";
            Autho="all";
            }
            else
            {
            authority="XX";
            Autho="XX";
            }
            
            //////////////////////////////////////////////////////////////////////////////
            if (authority=="cca")
            {
            document.getElementById("tab2").style.display="block";
             document.getElementById("but2").style.display="block";
            }
            else if (authority=="interview")
            {
            //alert("INT");
            document.getElementById("tab2").style.display="block";
             document.getElementById("but2").style.display="block";
            }
            else if (authority=="both")
            {
           // alert("BOTH");
            document.getElementById("tab2").style.display="block";
             document.getElementById("but2").style.display="block";
            }
            else if(authority=="all")
            {
            //alert("ADMIN");
            document.getElementById("admUSER").style.display="block";
            
            }
            else
            {
            alert("wrong password or Invalid User");
             document.getElementById("but2").style.display="none";
            }
            
            return null;
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
</script>
<body onload="">
<div id ="UserTable">
<table  cellpadding="4" cellspacing="10" border="0">
<tr>
<td>UserName </td> <td> <input type="text" id="userName" name ="user" value ="" /> </td>
</tr>
<tr>
<td>Password </td> <td> <input type="password" id="password" name ="password" value ="" />  </td>
</tr>
<tr>
<td colspan="2" align="center"><input type="button" value="submit" id="but1" onclick="return callCheckAuthority()"/> </td>
</tr>
<tr>
<td colspan="2" ><form method="post" action ="http://localhost:8080/ADMISSION_SYSTEM/cca_int/generateReport.htm"><input id ="userVari" name="userVari" type ="hidden"><input type="submit" value="GenereateReport" id="but2" style="display:none;"/></form> </td>
</tr>
</table>
</div>
<br/>
<table id="tab2" style="display:none;">
<tr>
<td>
Enter Application Number : 
</td>

<td>
<input type="text" id="app_no" name ="app" value ="" maxlength="6" onkeydown="return onlyNos(event,this);" onkeyup="return appcheck()"/>
</td>
</tr>
</table>

<div id="CandidateInfo">

</div>
<br/>
<br/>
<br/>
<br/>


<div id="CandidateInfo1" style="height: 20px;">
<table id="newTableDis" border="1" style="display:none;"  cellpadding="5" cellspacing="10">
<tr>
<th> ApplicationNumber</th> <th> Name</th>  <th>Father's Name </th> <th>Gender</th> <th>Category Code </th><th> CCA_Marks</th> <th> INT_Marks</th> 
</tr>
</table>
</div>
<table border="1" id="tab1" style="display:none;"  cellpadding="5" cellspacing="10">
<tr>
<th> ApplicationNumber</th> <th> Name</th>  <th>Father's Name </th> <th>Gender</th> <th>Category Code </th><th> CCA_Marks</th> 

</tr>
<tr>
<td> 121213</td> <td> Candidate 1</td>  <td>Candidate Father</td> <td>M</td> <td>GN </td><td> <input type="text" id="CCA" name ="cca_marks" value ="0" maxlength="6" onkeydown="return onlyNosCCA(event,this);"/></td> 
</tr>
</table>



<table border="1" id="tabINT" style="display:none;"  cellpadding="5" cellspacing="10">
<tr>
<th> ApplicationNumber</th> <th> Name</th>  <th>Father's Name </th> <th>Gender</th> <th>Category Code </th><th> INT_Marks</th> 

</tr>
<tr>
<td> 121213</td> <td> Candidate 1</td>  <td>Candidate Father</td> <td>M</td> <td>GN </td><td> <input type="text" id="INT" name ="int_marks" value ="0" maxlength="6" onkeydown="return onlyNosINT(event,this);"/></td> 
</tr>
</table>


<table border="1" id="tabBOTH" style="display:none;"  cellpadding="5" cellspacing="10">
<tr>
<th> ApplicationNumber</th> <th> Name</th>  <th>Father's Name </th> <th>Gender</th> <th>Category Code </th><th> CCA_Marks</th> <th> INT_Marks</th> 

</tr>
<tr>
<td> 121213</td> <td> Candidate 1</td>  <td>Candidate Father</td> <td>M</td> <td>GN </td> <td> <input type="text" id="CCAboth" name ="cca_marks" value ="" maxlength="6" onkeydown="return onlyNosBOTH(event,this);"/></td> <td> <input type="text" id="INTboth" name ="int_marks" value ="" maxlength="6" onkeydown="return onlyNosBOTH(event,this);"/></td> 
</tr>
</table>




<table border="1" id="admUSER" style="display:none;"  cellpadding="5" cellspacing="10">
<tr>
<th> CCA Marks Editing</th> <th> INT Marks Editing</th>  <th>BOTH Marks Editing </th>  

</tr>
<tr>
<td> <input type="radio" id ="ccaBox" value ="ccaEdit" name ="Edit" onclick="ccaEdit()"/> </td> <td> <input type="radio" id ="intBox" value ="intEdit" name ="Edit" onclick="ccaEdit()"/></td> <td><input type="radio" id ="bothBox" value ="bothEdit" name ="Edit" onclick="ccaEdit()"/></td> 
</tr>
</table>

<table id="editAppNo" style="display:none;" >
<tr>
<td> Enter Application Number for Marks Editing</td> <td><input type="text" id ="app_no_edit" maxlength="6" onkeydown="return onlyNosEdit(event,this);" onkeyup="return onlyNosEdit2();" /></td>
</tr>

</table>

</body>

</html>
