<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="adm" uri="admissionTags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="ProjectURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

<script src="${ProjectURL}/jsFiles/PersonalDetails.js"></script>
<script src="${ProjectURL}/jsFiles/Properties.js"></script>

<script>
function applicationPdfGenerationErrorAlert()
{
	$.post("${ProjectURL}/common/generateApplicationPdf.htm",function(msg)
	{
		alert( "Error " + msg );
	});
}



function applicationPdfGenerationErrorAlert1(){
$.ajax({
    type : "POST",
    url : getPrintablePDF,
    dataType : "json",
    contentType : 'application/json; charset=utf-8',
    data : JSON.stringify(params),
    success : function(data) {
        var myResponse = eval(data);
        $("<iframe />") // create an iframe
          // add the source
          .attr('src', 'data:application/pdf;base64,' + myResponse.base64EncodedResponse)
          .appendTo('.modal-body'); // append to modal body or wherever you want
    }
});    

}

function handleEwsVisibility()
{
	var catValue = document.getElementById("category").value;
	if(catValue=="GN")
	{
		document.getElementById("ewsLbl").style.display="block";
		document.getElementById("ews").style.display="block";
	}
	else
	{
		document.getElementById("ewsLbl").style.display="none";
		document.getElementById("ews").style.display="none";
	}
}
	
</script>

<div width="100%" align="center">
<div class="panelContainer" style="margin-bottom: 0px;padding-bottom:5px;">
<div class="panelHeader"><div class="panelTitle">Personal Information</div></div>
<div class="panelContent" align="center" style="padding-left: 0px; padding-right: 0px;">
<center style="font-family:verdana;color:red;font-size:12px">${requestScope.errors}</center>
		<div style="text-align: right;padding-right:30px;padding-bottom:0px;padding-top:0px;">
  				<ul>
  				<c:if test="${not empty sessionScope.applicant.applicationStatus}">
  				<li><a href="<%=response.encodeURL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/common/generateApplicationPdf.htm")%>" class="downloadLink">DOWNLOAD APPLICATION PDF</a></li>
  				</c:if>
  				</ul>
  		</div>
  	


<iframe id="downloadFrame" style="display:none"></iframe>

<form method="post" action="${ProjectURL}/application/savePersonalDetails.htm" onsubmit="return validateDetails();" autocomplete="off">
<table cellspacing="5px" cellpadding="2px" width="100%" style="text-align: right;" class="tableStyle" border="0">
<tr>
<th colspan="6" align="left">Personal Details<br><hr/></th>
</tr>
<!-- If applicant has not applied for any program then Personal Details Updation is allowed else should not be allowed -->
<c:choose>
<c:when test="${not empty sessionScope.applicant.applicationStatus}">
<tr>
<td>Applicant Name<label class="mandatory">*</label></td><td align="left"><input type="text" name="applicantName" autocomplete="off" id="applicantName" onkeyup="changeStyle(this)" class="cwFields" value="<c:out value='${sessionScope.applicant.applicantName}'/>" disabled="disabled"/></td><td>Father's Name<label class="mandatory">*</label></td><td align="left"><input type="text" name="fatherName" autocomplete="off" id="fatherName" onkeyup="changeStyle(this)" class="cwFields" value="<c:out value='${sessionScope.applicant.fatherName}'/>" disabled="disabled"/></td><td>Mother's Name<label class="mandatory">*</label></td><td align="left"><input type="text" name="motherName" id="motherName" autocomplete="off" onkeyup="changeStyle(this)" class="cwFields" value="<c:out value='${sessionScope.applicant.motherName}'/>" disabled="disabled"/></td>
</tr>
<!-- 
<tr>
<td>Applicant Name in Hindi</td><td align="left"><input type="text" name="applicantNameHi" autocomplete="off" id="applicantNameHi" onkeyup="changeStyle(this)" class="cwFields" value="<c:out value='${sessionScope.applicant.applicantNameHi}'/>" disabled="disabled"/></td><td>Father's Name in Hindi</td><td align="left"><input type="text" name="fatherNameHi" autocomplete="off" id="fatherNameHi" onkeyup="changeStyle(this)" class="cwFields" value="<c:out value='${sessionScope.applicant.fatherNameHi}'/>" disabled="disabled"/></td><td>Mother's Name in Hindi</td><td align="left"><input type="text" name="motherNameHi" id="motherNameHi" autocomplete="off" onkeyup="changeStyle(this)" class="cwFields" value="<c:out value='${sessionScope.applicant.motherNameHi}'/>" disabled="disabled"/></td>
</tr>
 -->
<tr>
<td>Date of Birth<label class="mandatory">*</label></td>
<td valign="top" align="left"><adm:date class="dateField" name="dd" id="dd" size="1" defaultText="-Date-" startIndex="1" endIndex="31" selectedValue="${sessionScope.applicant.dd}" onchange="changeStyle(this)" disabled="disabled"/>-<adm:date class="dateField" name="mm" id="mm" size="1" defaultText="-Month-" startIndex="1" endIndex="12" selectedValue="${sessionScope.applicant.mm}" onchange="changeStyle(this)" disabled="disabled"/>-<adm:date class="dateField" name="yyyy" id="yyyy" size="1" defaultText="-Year-" startIndex="1950" endIndex="2015" selectedValue="${sessionScope.applicant.yyyy}" onchange="changeStyle(this)" disabled="disabled"/></td>
<td>Category<label class="mandatory">*</label></td>
<td align="left"><adm:select name="category" id="category" class="cwFields" optionList="${applicant.allCategories}" defaultText="-Select Category-" selectedValue="${applicant.category}" onchange="changeStyle(this);handleEwsVisibility()" disabled="disabled"/></td>
<td>Gender<label class="mandatory">*</label></td>
<td align="left">
<adm:select class="cwFields" name="gender" id="gender" optionList="${sessionScope.applicant.allGenders}" defaultText="-Select Gender-" selectedValue="${sessionScope.applicant.gender}" onchange="changeStyle(this);" disabled="disabled"/>
</td>
</tr>
<tr>
<td>Nationality</td>
<td align="left"><select class="cwFields" id="nationality" name="nationality" onchange="changeStyle(this);" disabled="disabled"><option value="">-Select Nationality-</option></select><input type=hidden id="selectedNationality" value="<c:out value='${sessionScope.applicant.nationality}'/>"/></td>
<td>Religion<label class="mandatory">*</label></td>
<td align="left"><select class="cwFields" id="religion" name="religion" onchange="changeStyle(this);" disabled="disabled"><option value="">-Select Religion-</option></select><input type=hidden id="selectedReligion" value="<c:out value='${sessionScope.applicant.religion}'/>"/></td>
<td>Physically Handicapped<label class="mandatory">*</label></td>
<td align="left">
<adm:selectYN name="physicalDisability" class="cwFields" id="physicalDisability" defaultText="-Select Option-" selectedValue="${applicant.pwd}" onchange="changeStyle(this);" disabled="disabled"/>
</td>
</tr>
<tr>
<td>Primary Email ID<label class="mandatory">*</label></td><td align="left"><input type="text" autocomplete="off" class="cwFields" name="emailID" id="emailID" value="<c:out value='${sessionScope.applicant.primaryEmailID}'/>" onkeyup="changeStyle(this)"/></td><td>Phone Number<label class="mandatory">*</label></td><td align="left"><input autocomplete="off" type="text" class="cwFields" id="phoneNumber" maxlength="12" name="phoneNumber" value="<c:out value='${sessionScope.applicant.homePhone}'/>" onkeyup="changeStyle(this)"/></td><td>Minority<label class="mandatory">*</label></td>
<td align="left">
<adm:selectYN name="minority" class="cwFields" id="minority" defaultText="-Select Option-" selectedValue="${applicant.minority}" onchange="changeStyle(this);" disabled="disabled"/>
</td>
</tr>
<tr>
<td>Aadhar Number</td><td align="left"><input type="text" autocomplete="off" class="cwFields" name="aadharNumber" id="aadharNumber" value="<c:out value='${sessionScope.applicant.aadharNumber}'/>" onkeyup="changeStyle(this)" disabled="disabled"/></td><td>Blood Group</td>
<td align="left">
<adm:select name="bloodGroup" class="cwFields" id="bloodGroup" optionList="${applicant.allBloodGroups}" defaultText="-Select Option-" selectedValue="${applicant.bloodGroup}" onchange="changeStyle(this);" disabled="disabled"/>
</td>
<td>Kashmiri Pandit</td>
<td align="left">
<adm:selectYN name="kashmiriPandit" class="cwFields" id="kashmiriPandit" defaultText="-Select Option-" selectedValue="${applicant.kashmiriPandit}" onchange="changeStyle(this);" disabled="disabled"/>
</td>
</tr>

<tr>
<td>ID Proof</td><td align="left"><adm:select name="typeOfId" class="cwFields" id="typeOfId" optionList="${applicant.idProofs}" defaultText="-Select Option-" selectedValue="${applicant.typeOfId}" onchange="changeStyle(this);" disabled="disabled"/></td><td>ID Proof Number</td>
<td align="left">
<input type="text" autocomplete="off" class="cwFields" name="numberOfIdProof" id="numberOfIdProof" value="<c:out value='${sessionScope.applicant.idProofNumber}'/>" onkeyup="changeStyle(this)" disabled="disabled"/>
</td>
</tr>

<tr>
<c:choose>
	<c:when test="${applicant.category == 'GN'}">
		<td colspan="3"><label id="ewsLbl" style="display: block;">Economically Weaker Section (EWS)<br/><label class="mandatory">(Family income less than 8 lakhs INR annually)</label></label></td>
		<td align="left" colspan="3">
		<adm:selectYN style="display: block;" name="ews" class="cwFields" id="ews" defaultText="-Select Option-" selectedValue="${applicant.ews}" onchange="changeStyle(this);"/>
		</td>
	</c:when>
	<c:otherwise>
		<td colspan="3"><label id="ewsLbl" style="display: none;">Economically Weaker Section (EWS)<br/><label class="mandatory">(Family income less than 8 lakhs INR annually)</label></label></td>
		<td align="left" colspan="3">
		<adm:selectYN style="display: none;" name="ews" class="cwFields" id="ews" defaultText="-Select Option-" selectedValue="${applicant.ews}" onchange="changeStyle(this);"/>
		</td>
	</c:otherwise>
</c:choose>

</tr>
</c:when>
<c:otherwise>
<tr>
<td>Applicant Name<label class="mandatory">*</label></td><td align="left"><input type="text" name="applicantName" autocomplete="off" id="applicantName" onkeyup="changeStyle(this)" class="cwFields" value="<c:out value='${sessionScope.applicant.applicantName}'/>"/></td><td>Father's Name<label class="mandatory">*</label></td><td align="left"><input type="text" name="fatherName" autocomplete="off" id="fatherName" onkeyup="changeStyle(this)" class="cwFields" value="<c:out value='${sessionScope.applicant.fatherName}'/>"/></td><td>Mother's Name<label class="mandatory">*</label></td><td align="left"><input type="text" name="motherName" id="motherName" autocomplete="off" onkeyup="changeStyle(this)" class="cwFields" value="<c:out value='${sessionScope.applicant.motherName}'/>"/></td><!-- <td>Mother's Annual Income</td><td colspan="3" align="left"><input type="text" name="motherIncome" id="motherIncome" value="<c:out value='${sessionScope.applicant.motherIncome}'/>"/></td>-->
</tr>
<tr>
<td colspan="6" align="center"><label style="color:green;">Please <a href="https://translate.google.co.in/#view=home&op=translate&sl=en&tl=hi" target="_blank" style="color:red;">Click Here</a> for google input tools. Type hindi name there and paste that here.(In case you do not know hindi typing in unicode.)</label></td>
</tr>
<tr>
<td>Applicant Name in Hindi</td><td align="left"><input type="text" name="applicantNameHi" autocomplete="off" id="applicantNameHi" onkeyup="changeStyle(this)" class="cwFields" value="<c:out value='${sessionScope.applicant.applicantNameHi}'/>"/></td><td>Father's Name in Hindi</td><td align="left"><input type="text" name="fatherNameHi" autocomplete="off" id="fatherNameHi" onkeyup="changeStyle(this)" class="cwFields" value="<c:out value='${sessionScope.applicant.fatherNameHi}'/>"/></td><td>Mother's Name in Hindi</td><td align="left"><input type="text" name="motherNameHi" id="motherNameHi" autocomplete="off" onkeyup="changeStyle(this)" class="cwFields" value="<c:out value='${sessionScope.applicant.motherNameHi}'/>"/></td>
</tr>
<tr>
<td>Date of Birth<label class="mandatory">*</label></td>
<td valign="top" align="left"><adm:date class="dateField" name="dd" id="dd" size="1" defaultText="-Date-" startIndex="1" endIndex="31" selectedValue="${sessionScope.applicant.dd}" onchange="changeStyle(this)"/>-<adm:date class="dateField" name="mm" id="mm" size="1" defaultText="-Month-" startIndex="1" endIndex="12" selectedValue="${sessionScope.applicant.mm}" onchange="changeStyle(this)"/>-<adm:date class="dateField" name="yyyy" id="yyyy" size="1" defaultText="-Year-" startIndex="1950" endIndex="2015" selectedValue="${sessionScope.applicant.yyyy}" onchange="changeStyle(this)"/></td>
<td>Category<label class="mandatory">*</label></td>
<td align="left"><adm:select name="category" id="category" class="cwFields" optionList="${applicant.allCategories}" defaultText="-Select Category-" selectedValue="${applicant.category}" onchange="changeStyle(this);handleEwsVisibility()"/></td>
<td>Gender<label class="mandatory">*</label></td>
<td align="left">
<adm:select class="cwFields" name="gender" id="gender" optionList="${sessionScope.applicant.allGenders}" defaultText="-Select Gender-" selectedValue="${sessionScope.applicant.gender}" onchange="changeStyle(this);"/>
</td>
</tr>
<tr>
<td>Nationality</td>
<!-- <td align="left"><input autocomplete="off" type="text" class="cwFields" id="nationality" name="nationality" value="<c:out value='${sessionScope.applicant.nationality}'/>" onkeyup="changeStyle(this)"/></td> -->
<td align="left"><select class="cwFields" id="nationality" name="nationality" onchange="changeStyle(this);"><option value="">-Select Nationality-</option></select><input type=hidden id="selectedNationality" value="<c:out value='${sessionScope.applicant.nationality}'/>"/></td>
<td>Religion<label class="mandatory">*</label></td>
<!-- <td align="left"><input autocomplete="off" type="text" class="cwFields" id="religion" name="religion" value="<c:out value='${sessionScope.applicant.religion}'/>" onkeyup="changeStyle(this)"/></td>-->
<td align="left"><select class="cwFields" id="religion" name="religion" onchange="changeStyle(this);"><option value="">-Select Religion-</option></select><input type=hidden id="selectedReligion" value="<c:out value='${sessionScope.applicant.religion}'/>"/></td>
<td>Physically Handicapped<label class="mandatory">*</label></td>
<td align="left">
<adm:selectYN name="physicalDisability" class="cwFields" id="physicalDisability" defaultText="-Select Option-" selectedValue="${applicant.pwd}" onchange="changeStyle(this);"/>
</td>
</tr>
<tr>
<td>Primary Email ID<label class="mandatory">*</label></td><td align="left"><input type="text" autocomplete="off" class="cwFields" name="emailID" id="emailID" value="<c:out value='${sessionScope.applicant.primaryEmailID}'/>" onkeyup="changeStyle(this)"/></td><td>Phone Number<label class="mandatory">*</label></td><td align="left"><input autocomplete="off" type="text" class="cwFields" id="phoneNumber" maxlength="12" name="phoneNumber" value="<c:out value='${sessionScope.applicant.homePhone}'/>" onkeyup="changeStyle(this)"/></td><td>Minority<label class="mandatory">*</label></td>
<td align="left">
<adm:selectYN name="minority" class="cwFields" id="minority" defaultText="-Select Option-" selectedValue="${applicant.minority}" onchange="changeStyle(this);"/>
</td>
</tr>
<tr>
<td>Aadhar Number</td><td align="left"><input type="text" autocomplete="off" class="cwFields" name="aadharNumber" id="aadharNumber" value="<c:out value='${sessionScope.applicant.aadharNumber}'/>" onkeyup="changeStyle(this)"/></td><td>Blood Group</td>
<td align="left">
<adm:select name="bloodGroup" class="cwFields" id="bloodGroup" optionList="${applicant.allBloodGroups}" defaultText="-Select Option-" selectedValue="${applicant.bloodGroup}" onchange="changeStyle(this);"/>
</td>
<td>Kashmiri Pandit</td>
<td align="left">
<adm:selectYN name="kashmiriPandit" class="cwFields" id="kashmiriPandit" defaultText="-Select Option-" selectedValue="${applicant.kashmiriPandit}" onchange="changeStyle(this);"/>
</td>
</tr>

<tr>
<td>ID Proof</td><td align="left"><adm:select name="typeOfId" class="cwFields" id="typeOfId" optionList="${applicant.idProofs}" defaultText="-Select Option-" selectedValue="${applicant.typeOfId}" onchange="changeStyle(this);"/></td><td>ID Proof Number</td>
<td align="left">
<input type="text" autocomplete="off" class="cwFields" name="numberOfIdProof" id="numberOfIdProof" value="<c:out value='${sessionScope.applicant.idProofNumber}'/>" onkeyup="changeStyle(this)"/>
</td>
</tr>
<tr>
<c:choose>
	<c:when test="${applicant.category == 'GN'}">
		<td colspan = "3"><label id="ewsLbl" style="display: block;">Economically Weaker Section (EWS)<br/><label class="mandatory">(Family income less than 8 lakhs INR annually)</label></label></td>
		<td align="left" colspan = "3">
		<adm:selectYN style="display: block;" name="ews" class="cwFields" id="ews" defaultText="-Select Option-" selectedValue="${applicant.ews}" onchange="changeStyle(this);"/>
		</td>
	</c:when>
	<c:otherwise>
		<td colspan = "3"><label id="ewsLbl" style="display: none;">Economically Weaker Section (EWS)<br/><label class="mandatory">(Family income less than 8 lakhs INR annually)</label></label></td>
		<td align="left" colspan = "3">
		<adm:selectYN style="display: none;" name="ews" class="cwFields" id="ews" defaultText="-Select Option-" selectedValue="${applicant.ews}" onchange="changeStyle(this);"/>
		</td>
	</c:otherwise>
</c:choose>

</tr>

</c:otherwise>
</c:choose>

<tr>
<td align = "left">Are you appearing in CUET ?<br/>OR<br/>Have you appeared in CUET ?</td>
<td align="left">
<adm:selectYN name="cuetFlag" class="cwFields" id="cuetFlag" defaultText="-Select Option-" selectedValue="${applicant.cuetFlag}" onchange="changeStyle(this);"/>
</td>
<td>12 Digit CUET Application Number (IF appearing/appeared)</td>
<td align="left">
<input type="text" autocomplete="off" maxlength="12" class="cwFields" name="cuetAppNumber" id="cuetAppNumber" value="<c:out value='${sessionScope.applicant.cuetAppNumber}'/>" onkeyup="changeStyle(this)"/>
</td>
<td>CUET Roll Number (IF appearing/appeared)</td>
<td align="left">
<input type="text" autocomplete="off" maxlength="10" class="cwFields" name="cuetRollNumber" id="cuetRollNumber" value="<c:out value='${sessionScope.applicant.cuetRollNumber}'/>" onkeyup="changeStyle(this)"/>
</td>
</tr>

<tr>
<td colspan="6" align="left"><label style="color:red;font-weight:bold;">Note :-</label><br/>
<ul style="color:green;">
<li>Please bring all of your certificates/documents in original at the time of counselling.</li>
<li>All your supporting certificates/documents related to caste category must be traceble on the government website.</li>
<li>Income certificate is mandatory for EWS (Economically Weaker Section) category. </li>
</ul>
</td>
</tr>
<!-- Correspondance Address Starts Here -->
<tr>
<th colspan="6" align="left">Address Details<br><hr/></th>
</tr>
<tr>
<td colspan="6" align="center">
	<table  cellspacing="5px" cellpadding="2px">
	<tr>
	<th colspan="4" style="text-align: left;">CORRESPONDANCE ADDRESS</th>
	</tr>
	<tr>
	<td align="right">Country<label class="mandatory">*</label></td><td align="left"><select class="cwFields" id="cor_country" name="cor_country" onchange="changeStyle(this); handleStateCB();"><option value="">-Select Country-</option></select><input type=hidden id="selectedCountry" value="<c:out value='${sessionScope.applicant.corCountry}'/>"/></td><td align="right"><label id="stateLbl">State<label class="mandatory">*</label></label></td><td align="left"><select class="cwFields" name="cor_state" id="cor_state" class="combobox" onchange="changeStyle(this);"><option value="">-Select State-</option></select><input type=hidden id="selectedState" value="<c:out value='${sessionScope.applicant.corState}'/>"/></td>
	</tr>
	<tr id="cityRow">
	<td align="right"><label id="cityLbl">City<label class="mandatory">*</label></label></td><td align="left"><input autocomplete="off" type="text" name="cor_city" id="cor_city" value="<c:out value='${sessionScope.applicant.corCity}'/>" onkeyup="changeStyle(this);"/></td><td align="right"><label id="districtLbl">District<label class="mandatory">*</label></label></td><td align="left"><input autocomplete="off" type="text" name="cor_district" id="cor_district" value="<c:out value='${sessionScope.applicant.corDistrict}'/>" onkeyup="changeStyle(this);"/></td>     
	</tr>
	<tr>
	<td align="right"><label id="pincodeLbl">Pincode<label class="mandatory">*</label></label></td><td align="left"><input autocomplete="off" type="text" class="cwFields" name="cor_pincode" id="cor_pincode" maxlength="6" value="<c:out value='${sessionScope.applicant.corPincode}'/>" onkeyup="changeStyle(this);"/></td>
	</tr>
	<tr>
	<td align="right">Address Line 1<label class="mandatory">*</label></td><td align="left"><input type="text" autocomplete="off" class="cwFields" name="cor_line1" id="cor_line1" value="<c:out value='${sessionScope.applicant.corAddressL1}'/>" onkeyup="changeStyle(this);"/></td><td align="right">Address Line 2</td><td align="left"><input type="text" autocomplete="off" class="cwFields" name="cor_line2" id="cor_line2" value="<c:out value='${sessionScope.applicant.corAddressL2}'/>" onkeyup="changeStyle(this);"/></td>
	</tr>
	</table>
</td>
</tr>
<!-- Correspondance Address Ends Here -->


<tr>
<td colspan="6" align="center"><input type="checkbox" id="copyAddress" name="copyAddress" onclick="copyCorAddress();"/> Same as Above</td>
</tr>

<tr>
<td colspan="6" align="center">

	<table  cellspacing="5px" cellpadding="2px">
	<tr>
	<th colspan="4" style="text-align: left;">PERMANENT ADDRESS</th>
	</tr>
	<tr>
	<td align="right">Country<label class="mandatory">*</label></td><td align="left"><select class="cwFields" id="per_country" name="per_country" onchange="changeStyle(this); handlePermanentStateCB();"><option value="">-Select Country-</option></select><input type=hidden id="selectedPerCountry" value="<c:out value='${sessionScope.applicant.perCountry}'/>"/></td><td align="right"><label id="perStateLbl">State<label class="mandatory">*</label></label></td><td align="left"><select class="cwFields" name="per_state" id="per_state" class="combobox" onchange="changeStyle(this);"><option value="">-Select State-</option></select><input type=hidden id="selectedPerState" value="<c:out value='${sessionScope.applicant.perState}'/>"/></td>
	</tr>
	<tr id="cityRow">
	<td align="right"><label id="perCityLbl">City<label class="mandatory">*</label></label></td><td align="left"><input autocomplete="off" type="text" name="per_city" id="per_city" value="<c:out value='${sessionScope.applicant.perCity}'/>" onkeyup="changeStyle(this);"/></td><td align="right"><label id="perDistrictLbl">District<label class="mandatory">*</label></label></td><td align="left"><input autocomplete="off" type="text" name="per_district" id="per_district" value="<c:out value='${sessionScope.applicant.perDistrict}'/>" onkeyup="changeStyle(this);"/></td>     
	</tr>
	<tr>
	<td align="right"><label id="perPincodeLbl">Pincode<label class="mandatory">*</label></label></td><td align="left"><input autocomplete="off" type="text" class="cwFields" name="per_pincode" id="per_pincode" maxlength="6" value="<c:out value='${sessionScope.applicant.perPincode}'/>" onkeyup="changeStyle(this);"/></td>
	</tr>
	<tr>
	<td align="right">Address Line 1<label class="mandatory">*</label></td><td align="left"><input type="text" autocomplete="off" class="cwFields" name="per_line1" id="per_line1" value="<c:out value='${sessionScope.applicant.perAddressL1}'/>" onkeyup="changeStyle(this);"/></td><td align="right">Address Line 2</td><td align="left"><input type="text" autocomplete="off" class="cwFields" name="per_line2" id="per_line2" value="<c:out value='${sessionScope.applicant.perAddressL2}'/>" onkeyup="changeStyle(this);"/></td>
	</tr>
	</table>

</td>
</tr>



<tr>
<td colspan="6" align="center">
<label style="color:green;font-family:verdana;font-size:12px;"><b>Please verify your details before submitting/saving the personal details.</label></b><br/>
	<c:choose>
					<c:when test="${sessionScope.applicant.tabStatus=='0'}">
						<input type="submit" value="SAVE AND CONTINUE >>" class="customButton"/>
					</c:when>
					<c:otherwise>
						<input type="submit" value="UPDATE" class="customButton"/>
					</c:otherwise>
					</c:choose>
	
</td>

</tr>
</table>
</form>
<div style="font-family:verdana;color:red;font-weight:bold;">${message}</div>	
</div>
</div>
</div>


