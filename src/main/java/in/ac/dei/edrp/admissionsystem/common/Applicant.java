package in.ac.dei.edrp.admissionsystem.common;



import java.io.Serializable;
import java.util.*;
public class Applicant implements Serializable
{
private String name;
private String fatherName;
private String motherName;
private String emailID;
private String secondaryEmailID;
private String dob;
private String category;
private String nationality;
private String gender;
private String religion;
private String guardian;
private String minority;
private String maritalStatus;
private String addressLine1;
private String addressLine2;
private String city;
private String state;
private String pincode;
private String applicationDocumentPath;

private String phoneNo;
private List<ApplicantProgram> applicantPrograms;
private List<ProgramComponent> programComponents;
private String totalFee;
private String staff_ward;
private String university;
private String session;
private String universityNickName;

private String fatherIncome;
private String motherIncome;
private String guardianIncome;
private String locality;
private String hostelRequired;
private String everExpelled;
private String physicallyHandicapped;
private String staffCode;
private String cocurricularActivites;
private String applicationType;


public Applicant() 
{

}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getFatherName() {
	return fatherName;
}

public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
}

public String getMotherName() {
	return motherName;
}

public void setMotherName(String motherName) {
	this.motherName = motherName;
}

public String getEmailID() {
	return emailID;
}

public void setEmailID(String emailID) {
	this.emailID = emailID;
}

public String getSecondaryEmailID() {
	return secondaryEmailID;
}

public void setSecondaryEmailID(String secondaryEmailID) {
	this.secondaryEmailID = secondaryEmailID;
}

public String getDob() {
	return dob;
}

public void setDob(String dob) {
	this.dob = dob;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

public String getNationality() {
	return nationality;
}

public void setNationality(String nationality) {
	this.nationality = nationality;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getReligion() {
	return religion;
}

public void setReligion(String religion) {
	this.religion = religion;
}

public String getGuardian() {
	return guardian;
}

public void setGuardian(String guardian) {
	this.guardian = guardian;
}

public String getMinority() {
	return minority;
}

public void setMinority(String minority) {
	this.minority = minority;
}

public String getMaritalStatus() {
	return maritalStatus;
}

public void setMaritalStatus(String maritalStatus) {
	this.maritalStatus = maritalStatus;
}


public String getAddressLine1() {
	return addressLine1;
}

public void setAddressLine1(String addressLine1) {
	this.addressLine1 = addressLine1;
}

public String getAddressLine2() {
	return addressLine2;
}

public void setAddressLine2(String addressLine2) {
	this.addressLine2 = addressLine2;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getPincode() {
	return pincode;
}

public void setPincode(String pincode) {
	this.pincode = pincode;
}

public String getPhoneNo() {
	return phoneNo;
}

public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
}

public List<ProgramComponent> getProgramComponents() {
	return programComponents;
}

public void setProgramComponents(List<ProgramComponent> programComponents) {
	this.programComponents = programComponents;
}

public List<ApplicantProgram> getApplicantPrograms() {
	return applicantPrograms;
}

public void setApplicantPrograms(List<ApplicantProgram> applicantPrograms) {
	this.applicantPrograms = applicantPrograms;
}

public String getApplicationDocumentPath() {
	return applicationDocumentPath;
}

public void setApplicationDocumentPath(String applicationDocumentPath) {
	this.applicationDocumentPath = applicationDocumentPath;
}

public String getTotalFee() {
	return totalFee;
}

public void setTotalFee(String totalFee) {
	this.totalFee = totalFee;
}

public String getStaff_ward() {
	return staff_ward;
}

public void setStaff_ward(String staff_ward) {
	this.staff_ward = staff_ward;
}

public String getUniversity() {
	return university;
}

public void setUniversity(String university) {
	this.university = university;
}

public String getSession() {
	return session;
}

public void setSession(String session) {
	this.session = session;
}

public String getUniversityNickName() {
	return universityNickName;
}

public void setUniversityNickName(String universityNickName) {
	this.universityNickName = universityNickName;
}

public String getFatherIncome() {
	return fatherIncome;
}

public void setFatherIncome(String fatherIncome) {
	this.fatherIncome = fatherIncome;
}

public String getMotherIncome() {
	return motherIncome;
}

public void setMotherIncome(String motherIncome) {
	this.motherIncome = motherIncome;
}

public String getGuardianIncome() {
	return guardianIncome;
}

public void setGuardianIncome(String guardianIncome) {
	this.guardianIncome = guardianIncome;
}

public String getLocality() {
	return locality;
}

public void setLocality(String locality) {
	this.locality = locality;
}

public String getHostelRequired() {
	return hostelRequired;
}

public void setHostelRequired(String hostelRequired) {
	this.hostelRequired = hostelRequired;
}

public String getEverExpelled() {
	return everExpelled;
}

public void setEverExpelled(String everExpelled) {
	this.everExpelled = everExpelled;
}

public String getPhysicallyHandicapped() {
	return physicallyHandicapped;
}

public void setPhysicallyHandicapped(String physicallyHandicapped) {
	this.physicallyHandicapped = physicallyHandicapped;
}

public String getStaffCode() {
	return staffCode;
}

public void setStaffCode(String staffCode) {
	this.staffCode = staffCode;
}

public String getCocurricularActivites() {
	return cocurricularActivites;
}

public void setCocurricularActivites(String cocurricularActivites) {
	this.cocurricularActivites = cocurricularActivites;
}

public String getApplicationType() {
	return applicationType;
}

public void setApplicationType(String applicationType) {
	this.applicationType = applicationType;
}





}
