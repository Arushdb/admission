function validateForm()
{
	var applicantName = document.getElementsByName("applicantName").value;
	if(applicantName == null || applicantName=="")
		{
		alert("Please Enter User Name.");
		return false;
		}
	return true;
}