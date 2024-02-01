$(function() 
{
	
	var xmlDoc = loadXMLDoc("countries.xml");
	var x=xmlDoc.getElementsByTagName("country");
	var con = document.getElementById("cor_country");
	var selectedCountry = document.getElementById("selectedCountry").value;
	if(selectedCountry!=="IN")
	{
		document.getElementById("cor_state").value="";
		document.getElementById("cor_state").style.display="none";
		document.getElementById("stateLbl").style.display="none";
		document.getElementById("cor_city").style.display="none";
		document.getElementById("cityLbl").style.display="none";
		document.getElementById("cor_district").style.display="none";
		document.getElementById("districtLbl").style.display="none";
		document.getElementById("cor_pincode").style.display="none";
		document.getElementById("pincodeLbl").style.display="none";
	}
	else
	{
		document.getElementById("cor_state").style.display="block";
		document.getElementById("stateLbl").style.display="block";
		document.getElementById("cor_city").style.display="block";
		document.getElementById("cityLbl").style.display="block";
		document.getElementById("cor_district").style.display="block";
		document.getElementById("districtLbl").style.display="block";
		document.getElementById("cor_pincode").style.display="block";
		document.getElementById("pincodeLbl").style.display="block";
	}
	
	/* Commented By Arjun on 14-03-2015
	for (i=0;i<x.length;i++)
  	{
  		var country = new Option(x[i].childNodes[0].nodeValue, x[i].childNodes[0].nodeValue, false, false);
  		var y = document.createElement("OPTION");
  		var countryCode = x[i].getAttribute("code");
  		y.setAttribute("value", countryCode);
  		
  		if(countryCode==selectedCountry)
  		{
  			y.setAttribute("selected", "selected");
  		}
  		
    	
    	var t = document.createTextNode(x[i].childNodes[0].nodeValue);
    	y.appendChild(t);
   		document.getElementById("cor_country").appendChild(y);
	} Commented By Arjun on 14-03-2015 */
	
	//Added By Arjun on 14-03-2015
	if(x.length > 0)
	{
		for (i=0;i<x.length;i++)
  	{
  		var country = new Option(x[i].childNodes[0].nodeValue, x[i].childNodes[0].nodeValue, false, false);
  		var y = document.createElement("OPTION");
  		var countryCode = x[i].getAttribute("code");
  		y.setAttribute("value", countryCode);
  		
  		if(countryCode==selectedCountry)
  		{
  			y.setAttribute("selected", "selected");
  		}
  		
    	
    	var t = document.createTextNode(x[i].childNodes[0].nodeValue);
    	y.appendChild(t);
   		document.getElementById("cor_country").appendChild(y);
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
	
});

/**
 * Method to load an xml file for States
 */
$(function() 
{
	var selectedState = document.getElementById("selectedState").value;
	var xmlDoc = loadXMLDoc("stateCity.xml");
	var x=xmlDoc.getElementsByTagName("state");
	var con = document.getElementById("cor_state");
	for (i=0;i<x.length;i++)
	{
		var y = document.createElement("OPTION");
		y.setAttribute("value", x.item(i).attributes[0].value);
		var stateCode = x.item(i).attributes[0].value
		if(stateCode==selectedState)
  		{
  			y.setAttribute("selected", "selected");
  		}
		var t = document.createTextNode(x.item(i).attributes[0].value);
		y.appendChild(t);
		document.getElementById("cor_state").appendChild(y);
}});



$(document).ready(function() 
{
    $("#phoneNumber").keydown(function (e) {
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

$(document).ready(function() 
{
    $("#cor_pincode").keydown(function (e) {
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

$(function() 
{
	
	var xmlDoc = loadXMLDoc("resources/xmlFiles/Nationalities.xml");
	var x = xmlDoc.getElementsByTagName("nationality");
	var con = document.getElementById("nationality");
	var selectedCountry = document.getElementById("selectedNationality").value;
		
	//Added By Arjun on 14-03-2015
	if(x.length > 0)
	{
		for (i=0;i<x.length;i++)
  	{
  		var country = new Option(x[i].childNodes[0].nodeValue, x[i].childNodes[0].nodeValue, false, false);
  		var y = document.createElement("OPTION");
  		var countryCode = x[i].childNodes[0].nodeValue;
  		y.setAttribute("value", countryCode);
  		
  		if(countryCode==selectedCountry)
  		{
  			y.setAttribute("selected", "selected");
  		}
  		
    	
    	var t = document.createTextNode(x[i].childNodes[0].nodeValue);
    	y.appendChild(t);
   		document.getElementById("nationality").appendChild(y);
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
	
});

$(function() 
{
	
	var xmlDoc = loadXMLDoc("resources/xmlFiles/Religions.xml");
	var x = xmlDoc.getElementsByTagName("religion");
	var con = document.getElementById("religion");
	var selectedCountry = document.getElementById("selectedReligion").value;
		
	//Added By Arjun on 14-03-2015
	if(x.length > 0)
	{
		for (i=0;i<x.length;i++)
  	{
  		var country = new Option(x[i].childNodes[0].nodeValue, x[i].childNodes[0].nodeValue, false, false);
  		var y = document.createElement("OPTION");
  		var countryCode = x[i].childNodes[0].nodeValue;
  		y.setAttribute("value", countryCode);
  		
  		if(countryCode==selectedCountry)
  		{
  			y.setAttribute("selected", "selected");
  		}
  		
    	
    	var t = document.createTextNode(x[i].childNodes[0].nodeValue);
    	y.appendChild(t);
   		document.getElementById("religion").appendChild(y);
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
	
});





/**
 * File Name	:	PersonalDetails.js
 * Author		:	Arjun Singh Chauhan
 * Purpose		:	To handle Client Side Scripting for Personal Details
 */

/**
 * Method to load an xml file
 */
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
	xhttp.open("GET","../"+filename,false);
	
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



/**
 * Method to validate information related to Personal Details
 * Fields with missing or incorrect values are marked RED here in this method
 * If it returns TRUE then the details are submitted otherwise not submitted
 */
function validateDetails()
{
	
	var valid = true;
	var applicantName = document.getElementById("applicantName").value;
	var fatherName = document.getElementById("fatherName").value;
	var motherName = document.getElementById("motherName").value;
	var dd = document.getElementById("dd").value;
	var mm = document.getElementById("mm").value;
	var yyyy = document.getElementById("yyyy").value;
	var category = document.getElementById("category").value;
	var gender = document.getElementById("gender").value;
	var nationality = document.getElementById("nationality").value;
	var religion = document.getElementById("religion").value;
	var physicalDisability = document.getElementById("physicalDisability").value;
	var emailID = document.getElementById("emailID").value;
	var phoneNumber = document.getElementById("phoneNumber").value;
	var minority = document.getElementById("minority").value;
	var cor_country = document.getElementById("cor_country").value;
	var per_country = document.getElementById("per_country").value;
	
	var cor_line1 = document.getElementById("cor_line1").value;
	var per_line1 = document.getElementById("per_line1").value;
	
	if(applicantName=="" || applicantName==null)
	{
		$("#applicantName").css("border","1px solid red");
		valid=false;
	}
	if(fatherName == "" || fatherName == null)
	{
		$("#fatherName").css("border","1px solid red");
		valid=false;
	}
	if(motherName == "" || motherName == null)
	{
		$("#motherName").css("border","1px solid red");
		valid=false;
	}
	
	if(dd=="" || dd == null)
	{
		$("#dd").css("border","1px solid red");
		valid=false;
	}
	if(mm=="" || mm == null)
	{
		$("#mm").css("border","1px solid red");
		valid=false;
	}
	if(yyyy=="" || yyyy == null)
	{
		$("#yyyy").css("border","1px solid red");
		valid=false;
	}
	if(category=="" || category == null)
	{
		$("#category").css("border","1px solid red");
		valid=false;
	}
	if(gender=="" || gender == null)
	{
		$("#gender").css("border","1px solid red");
		valid=false;
	}
	/**
	if(nationality=="" || nationality == null)
	{
		$("#nationality").css("border","1px solid red");
		valid=false;
	}**/
	if(physicalDisability=="" || physicalDisability == null)
	{
		$("#physicalDisability").css("border","1px solid red");
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
		
		

	if(phoneNumber=="" || phoneNumber == null)
	{
		$("#phoneNumber").css("border","1px solid red");
		valid=false;
	}
	
	if(religion=="" || religion == null)
	{
		$("#religion").css("border","1px solid red");
		valid=false;
	}
	
	if(minority=="" || minority == null)
	{
		$("#minority").css("border","1px solid red");
		valid=false;
	}
	
	if(cor_country=="" || cor_country == null)
	{
		$("#cor_country").css("border","1px solid red");
		valid=false;
	}
	else
	{
		if(cor_country=="IN")
		{
			var cor_state = document.getElementById("cor_state").value;
			var cor_city = document.getElementById("cor_city").value;
			var cor_district = document.getElementById("cor_district").value;
			var cor_pincode = document.getElementById("cor_pincode").value;
		
			if(cor_state=="" || cor_state == null)
			{
				$("#cor_state").css("border","1px solid red");
				valid=false;
			}
		
			if(cor_city=="" || cor_city == null)
			{
				$("#cor_city").css("border","1px solid red");
				valid=false;
			}
			
			if(cor_district=="" || cor_district == null)
			{
				$("#cor_district").css("border","1px solid red");
				valid=false;
			}
			
			if(cor_pincode=="" || cor_pincode == null)
			{
				$("#cor_pincode").css("border","1px solid red");
				valid=false;
			}
	
		}
		
	}
	/**
	if(cor_state=="" || cor_state == null)
	{
		$("#cor_state").css("border","1px solid red");
		valid=false;
	}**/
	if(cor_line1=="" || cor_line1 == null)
	{
		$("#cor_line1").css("border","1px solid red");
		valid=false;
	}
	if(per_line1=="" || per_line1 == null)
	{
		$("#per_line1").css("border","1px solid red");
		valid=false;
	}
	
	if(applicantName=="" || applicantName==null)
	{
		$("#applicantName").css("border","1px solid red");
		valid=false;
	}
	
	/**
	 Permanent Address Validation 
	 */
	if(per_country=="" || per_country == null)
	{
		$("#per_country").css("border","1px solid red");
		valid=false;
	}
	else
	{
		if(per_country=="IN")
		{
			var per_state = document.getElementById("per_state").value;
			var per_city = document.getElementById("per_city").value;
			var per_district = document.getElementById("per_district").value;
			var per_pincode = document.getElementById("per_pincode").value;
		
			if(per_state=="" || per_state == null)
			{
				$("#per_state").css("border","1px solid red");
				valid=false;
			}
		
			if(per_city=="" || per_city == null)
			{
				$("#per_city").css("border","1px solid red");
				valid=false;
			}
			
			if(per_district=="" || per_district == null)
			{
				$("#per_district").css("border","1px solid red");
				valid=false;
			}
			
			if(per_pincode=="" || per_pincode == null)
			{
				$("#per_pincode").css("border","1px solid red");
				valid=false;
			}
	
		}
		
	}
	
	if(!valid)
	{
		alert("PLEASE CHECK THE FIELDS IN RED.")
	}
	else
	{
		var userChoice = confirm("DO YOU WANT TO SAVE THE DETAILS ?");
		if(userChoice==false)
		{
			valid = false;
		}
		
	}
	return valid;

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

/**
 * Method to change the styles of a field
 * The field which are RED changed to default style by calling this method
 * on a particular event
 */
function changeStyle(fieldID)
{
	$(fieldID).css("border","1px solid #B8B8B8");
}

function handleStateCB()
{
	var countryCode = document.getElementById("cor_country").value;
	if(countryCode!="IN")
	{
		document.getElementById("cor_state").value="";
		document.getElementById("cor_state").style.display="none";
		document.getElementById("stateLbl").style.display="none";
		document.getElementById("cor_city").style.display="none";
		document.getElementById("cityLbl").style.display="none";
		document.getElementById("cor_district").style.display="none";
		document.getElementById("districtLbl").style.display="none";
		document.getElementById("cor_pincode").style.display="none";
		document.getElementById("pincodeLbl").style.display="none";
	}
	else
	{
		document.getElementById("cor_state").style.display="block";
		document.getElementById("stateLbl").style.display="block";
		document.getElementById("cor_city").style.display="block";
		document.getElementById("cityLbl").style.display="block";
		document.getElementById("cor_district").style.display="block";
		document.getElementById("districtLbl").style.display="block";
		document.getElementById("cor_pincode").style.display="block";
		document.getElementById("pincodeLbl").style.display="block";
	}
}


$(function() 
{
	
	var xmlDoc = loadXMLDoc("countries.xml");
	var x=xmlDoc.getElementsByTagName("country");
	var con = document.getElementById("per_country");
	var selectedCountry = document.getElementById("selectedPerCountry").value;
	if(selectedCountry!=="IN")
	{
		document.getElementById("per_state").value="";
		document.getElementById("per_state").style.display="none";
		document.getElementById("perStateLbl").style.display="none";
		document.getElementById("per_city").style.display="none";
		document.getElementById("perCityLbl").style.display="none";
		document.getElementById("per_district").style.display="none";
		document.getElementById("perDistrictLbl").style.display="none";
		document.getElementById("per_pincode").style.display="none";
		document.getElementById("perPincodeLbl").style.display="none";
	}
	else
	{
		document.getElementById("per_state").style.display="block";
		document.getElementById("perStateLbl").style.display="block";
		document.getElementById("per_city").style.display="block";
		document.getElementById("perCityLbl").style.display="block";
		document.getElementById("per_district").style.display="block";
		document.getElementById("perDistrictLbl").style.display="block";
		document.getElementById("per_pincode").style.display="block";
		document.getElementById("perPincodeLbl").style.display="block";
	}
	
	
	if(x.length > 0)
	{
		for (i=0;i<x.length;i++)
  	{
  		var country = new Option(x[i].childNodes[0].nodeValue, x[i].childNodes[0].nodeValue, false, false);
  		var y = document.createElement("OPTION");
  		var countryCode = x[i].getAttribute("code");
  		y.setAttribute("value", countryCode);
  		
  		if(countryCode==selectedCountry)
  		{
  			y.setAttribute("selected", "selected");
  		}
  		
    	
    	var t = document.createTextNode(x[i].childNodes[0].nodeValue);
    	y.appendChild(t);
   		document.getElementById("per_country").appendChild(y);
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
});



function copyCorAddress()
{
	var toCopyAddress = document.getElementById("copyAddress").checked;
	var corCountryCombo = document.getElementById("cor_country");
	var corStateCombo = document.getElementById("cor_state");
	
	if(toCopyAddress)
	{
		if(document.getElementById("cor_country").value=="IN")
		{
			document.getElementById("per_state").style.display="block";
			document.getElementById("perStateLbl").style.display="block";
			document.getElementById("per_city").style.display="block";
			document.getElementById("perCityLbl").style.display="block";
			document.getElementById("per_district").style.display="block";
			document.getElementById("perDistrictLbl").style.display="block";
			document.getElementById("per_pincode").style.display="block";
			document.getElementById("perPincodeLbl").style.display="block";
		}
		else
		{
			document.getElementById("per_state").style.display="none";
			document.getElementById("perStateLbl").style.display="none";
			document.getElementById("per_city").style.display="none";
			document.getElementById("perCityLbl").style.display="none";
			document.getElementById("per_district").style.display="none";
			document.getElementById("perDistrictLbl").style.display="none";
			document.getElementById("per_pincode").style.display="none";
			document.getElementById("perPincodeLbl").style.display="none";
		}
		document.getElementById("per_country").value = corCountryCombo.options[corCountryCombo.selectedIndex].value;
		document.getElementById("per_state").value = corStateCombo.options[corStateCombo.selectedIndex].value;
		/**
		document.getElementById("per_country").text = corCountryCombo.options[corCountryCombo.selectedIndex].text;
		document.getElementById("per_state").text = corStateCombo.options[corStateCombo.selectedIndex].text;
		**/
		document.getElementById("per_city").value = document.getElementById("cor_city").value;
		document.getElementById("per_district").value = document.getElementById("cor_district").value;
		document.getElementById("per_pincode").value = document.getElementById("cor_pincode").value;
		document.getElementById("per_line1").value = document.getElementById("cor_line1").value;
		document.getElementById("per_line2").value = document.getElementById("cor_line2").value;
	}
}


function handlePermanentStateCB()
{
	var countryCode = document.getElementById("per_country").value;
	if(countryCode!="IN")
	{
		document.getElementById("per_state").value="";
		document.getElementById("per_state").style.display="none";
		document.getElementById("perStateLbl").style.display="none";
		document.getElementById("per_city").style.display="none";
		document.getElementById("perCityLbl").style.display="none";
		document.getElementById("per_district").style.display="none";
		document.getElementById("perDistrictLbl").style.display="none";
		document.getElementById("per_pincode").style.display="none";
		document.getElementById("perPincodeLbl").style.display="none";
	}
	else
	{
		document.getElementById("per_state").style.display="block";
		document.getElementById("perStateLbl").style.display="block";
		document.getElementById("per_city").style.display="block";
		document.getElementById("perCityLbl").style.display="block";
		document.getElementById("per_district").style.display="block";
		document.getElementById("perDistrictLbl").style.display="block";
		document.getElementById("per_pincode").style.display="block";
		document.getElementById("perPincodeLbl").style.display="block";
	}
}

/**
 * Method to load an xml file for Permanent States
 */
$(function() 
{
	var selectedPerState = document.getElementById("selectedPerState").value;
	var xmlDoc = loadXMLDoc("stateCity.xml");
	var x=xmlDoc.getElementsByTagName("state");
	var con = document.getElementById("per_state");
	for (i=0;i<x.length;i++)
	{
		var y = document.createElement("OPTION");
		y.setAttribute("value", x.item(i).attributes[0].value);
		var stateCode = x.item(i).attributes[0].value
		if(stateCode==selectedPerState)
  		{
  			y.setAttribute("selected", "selected");
  		}
		var t = document.createTextNode(x.item(i).attributes[0].value);
		y.appendChild(t);
		document.getElementById("per_state").appendChild(y);
}});



