

function valid()
{
		var valid = true;
		
		var applicationNumber = document.getElementById("applicationNumber").value;
		var dd = document.getElementById("dd").value;
		var mm = document.getElementById("mm").value;
		var yyyy = document.getElementById("yyyy").value;
		var phoneNo = document.getElementById("phoneNo").value;
		
		if(applicationNumber == null || applicationNumber == "")
		{
			$("#applicationNumber").css("border","1px solid red");
			valid=false;
		}
		
		if(phoneNo == null || phoneNo == "")
		{
			$("#phoneNo").css("border","1px solid red");
			valid=false;
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
		
		
		
		if(!valid)
		{
			alert("PLEASE CHECK THE FIELDS IN RED.")
		}
		else
		{
			
				var userChoice = confirm("DO YOU WANT TO CONTINUE ?");
				if(userChoice==false)
				{
					valid = false;
				}
						
		}
		return valid;
	}


function changeStyle(fieldID)
{
	$(fieldID).css("border","1px solid #B8B8B8");
}


function getHomePage()
{
	window.location.replace(props.projectURL);
}
