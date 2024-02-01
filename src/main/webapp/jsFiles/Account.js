function loadXMLDoc(filename)
{
	if(window.XMLHttpRequest)
	{
		xhttp = new XMLHttpRequest();
	}
	else
	{
		xhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xhttp.open("GET", "../"+filename,false);
	
	//Added on 12-03-2014 by Arjun
	xhttp.onreadystatechange = function()
          {
            if (xhttp.readyState == 4 && xhttp.status == 200) 
            {
                return xhttp.responseXML; //This should be duplicated in outer method because it returns only its' container method not the outer one.
            }
            else
            {
            	if ($.browser.msie) 
            	{
            		document.execCommand("Stop");
            	}
            	else
            	{
            		window.stop();
            	}
            }
          };
          //Added on 12-03-2014 by Arjun

	xhttp.send();
	return xhttp.responseXML; 
}

$(document).ready(function() 
{
    $("#phoneNo").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A
            (e.keyCode == 65 && e.ctrlKey === true) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
});


function valid()
{
		var valid = true;
		var applicantName = document.getElementById("applicantName").value;
		var emailID = document.getElementById("emailID").value;
		var dd = document.getElementById("dd").value;
		var mm = document.getElementById("mm").value;
		var yyyy = document.getElementById("yyyy").value;
		var country = document.getElementById("country").value;
		var category = document.getElementById("category").value;
		//var state = document.getElementById("state").value;
		//var city = document.getElementById("city").value;
		var phoneNo = document.getElementById("phoneNo").value;
		var question = document.getElementById("question").value;
		var answer = document.getElementById("answer").value;
		var captchaValue = document.getElementById("captchaValue").value;
		
		if(applicantName==null || applicantName == "")
		{
			$("#applicantName").css("border","1px solid red");
			valid=false;
		}
		
		if(emailID == null || emailID == "")
		{
			$("#emailID").css("border","1px solid red");
			valid=false;
		}
		else
		{
			if(!validEmail(emailID))
			{
				$("#emailID").css("border","1px solid red");
				valid=false;
				//alert("You have entered an invalid email address!");
			}
		}
		
		function validEmail(mail)    
		{  
			var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;   
			if(mail.match(mailformat))   
			{   
				return true;   
			}   
			else  
			{   
				return false;   
			}   		
		}  

		if((dd == null || dd == "") || (mm==null || mm=="")||(yyyy==null||yyyy==""))
		{
			
			if((dd==null||dd==""))
			{
				$("#dd").css("border","1px solid red");
				valid=false;
			}
		
			if((mm==null || mm==""))
			{
				$("#mm").css("border","1px solid red");
				valid=false;
			}
			
			if((yyyy==null||yyyy==""))
			{
				$("#yyyy").css("border","1px solid red");
				valid=false;
			}
		
		}
		else
		{
			if((dd==31 && (mm == 2 || mm ==4 || mm==6 || mm ==9 || mm == 11)) || ( dd>29 && mm==2) || (dd==29 && mm==2 && yyyy%4 != 0))
			{
				$("#dd").css("border","1px solid red");
				$("#mm").css("border","1px solid red");
				$("#yyyy").css("border","1px solid red");
				valid = false;
			}
			else
			{
				//Changes Date Fields to Default Style Starts Here
				$("#dd").css("border","1px solid #B8B8B8");
				$("#mm").css("border","1px solid #B8B8B8");
				$("#yyyy").css("border","1px solid #B8B8B8");
				//Changes Date Fields to Default Style Ends Here
					
			}

		}
		
		if(category == "")
		{
			$("#category").css("border","1px solid red");
			valid=false;
		}
		if(country == null || country == "")
		{
			$("#country").css("border","1px solid red");
			valid=false;
		}
		
		//if((state == null || state == "")&&country=="IN")
		//{
		//	$("#state").css("border","1px solid red");
		//	valid=false;
		//}
		//if(city == null || city == "")
		//{
		//	$("#city").css("border","1px solid red");
		//	valid=false;
		//}
		if((phoneNo == null || phoneNo == "") ||($("#phoneNo").val().length<10 || $("#phoneNo").val().length>12))
		{
			$("#phoneNo").css("border","1px solid red");
			valid=false;
		}
		if(question == "0")
		{
			$("#question").css("border","1px solid red");
			valid=false;
		}
		if(answer == null || answer == "")
		{
			$("#answer").css("border","1px solid red");
			valid=false;
		}
		if(captchaValue == null || captchaValue == "")
		{
			$("#captchaValue").css("border","1px solid red");
			valid=false;
		}
		if(!valid)
		{
			alert("PLEASE CHECK THE FIELDS IN RED.")
		}
		else
		{
			
				var userChoice = confirm("DO YOU WANT TO CREATE ACCOUNT WITH THESE DETAILS ?");
				if(userChoice==false)
				{
					valid = false;
				}
				else
				{
					document.getElementById("createAccountBtn").disabled = true;
				}
						
		}
		return valid;
	}


function changeStyle(fieldID)
{
	$(fieldID).css("border","1px solid #B8B8B8");
}

function getCountries()
{
	var xmlDoc = loadXMLDoc("countries.xml");
	var x=xmlDoc.getElementsByTagName("country");
	var con = document.getElementById("country");

	/* Commented By Arjun 14-03-2015
	for (i=0;i<x.length;i++)
  	{
  		var country = new Option(x[i].childNodes[0].nodeValue, x[i].childNodes[0].nodeValue, false, false);
  		var y = document.createElement("OPTION");
  		var countryCode = x[i].getAttribute("code");
  		y.setAttribute("value", countryCode);
  		if(countryCode=="IN")
  		{
  			y.setAttribute("selected", "selected");
  		}
  		
    	
    	var t = document.createTextNode(x[i].childNodes[0].nodeValue);
    	y.appendChild(t);
   		document.getElementById("country").appendChild(y);
	}  Commented By Arjun 14-03-2015 */
	
	//Added By Arjun on 14-03-2015
	if(x.length > 0)
	{
		for (i=0;i<x.length;i++)
  		{
  			var country = new Option(x[i].childNodes[0].nodeValue, x[i].childNodes[0].nodeValue, false, false);
  			var y = document.createElement("OPTION");
  			var countryCode = x[i].getAttribute("code");
  			y.setAttribute("value", countryCode);
  			if(countryCode=="IN")
  			{
  				y.setAttribute("selected", "selected");
  			}
  		
    	
    		var t = document.createTextNode(x[i].childNodes[0].nodeValue);
    		y.appendChild(t);
   			document.getElementById("country").appendChild(y);
		}

	}
	else
	{
		if ($.browser.msie) 
         {
            document.execCommand("Stop");
         }
        else
         {
            window.stop();
          }
	}
		//Added By Arjun on 14-03-2015

}

function getStates()
{
	var xmlDoc = loadXMLDoc("stateCity.xml");
	var x=xmlDoc.getElementsByTagName("state");
	var con = document.getElementById("state");
	for (i=0;i<x.length;i++)
  	{
 		var y = document.createElement("OPTION");
  		y.setAttribute("value", x.item(i).attributes[0].value);
 		var t = document.createTextNode(x.item(i).attributes[0].value);
		y.appendChild(t);
 		document.getElementById("state").appendChild(y);
 	} 
}

function getHomePage()
{
	window.location.replace(props.projectURL);
}

function handleStateCB()
{
	var countryCode = document.getElementById("country").value;
	if(countryCode!=="IN")
	{
		document.getElementById("state").value="";
		document.getElementById("state").disabled=true;
	}
	else
	{
		document.getElementById("state").disabled=false;
	}
}

function changeCaptchaCode()
{
	var randomNumber = Math.random()*10;
	document.getElementById("capimg").src=props.projectURL+"SimpleCaptcha.jpg?randomNumber"+randomNumber;
}

