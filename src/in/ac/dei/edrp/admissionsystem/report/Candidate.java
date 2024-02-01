package in.ac.dei.edrp.admissionsystem.report;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;
import java.util.*;

public class Candidate {
	private String applicationNumber, applicantName, fatherName, password, universityID, applicationDocumentPath, sessionStartDate, sessionEndDate;
	private String ccaUploaded, nccUploaded, nssUploaded, socialUploaded;
	private String classPassed;
	private List<CommonBean> programs = new ArrayList<CommonBean>();
	private String pref1, pref2, pref3;
	private String fromProgram;
	private String gender, category, programName, examCenter,  aadhaarNumber, contactNumber, shift;

	public void setFromProgram(String programId)
	{
	 this.fromProgram = programId;
	}
	public String getFromProgram()
	{
	 return this.fromProgram;
	}
	public void setPref1(String pref1)
	{
	 this.pref1 = pref1;
	}	

	 public void setPref2(String pref2)
        {
         this.pref2 = pref2;
        }
	 public void setPref3(String pref3)
        {
         this.pref3 = pref3;
        }
	public String getPref1()
	{
	 return pref1;
	}
	public String getPref2()
        {
         return pref2;
        }
	public String getPref3()
        {
         return pref3;
        }

	public List<CommonBean> getPrograms()
	{
		return programs;
	}
	
	public void setPrograms(List<CommonBean> programs) 
	{
		this.programs = programs;
	}
	
	public String getClassPassed() {
		return classPassed;
	}

	public void setClassPassed(String classPassed) {
		this.classPassed = classPassed;
	}
	
	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getUniversityID() {
		return universityID;
	}

	public void setUniversityID(String universityID) {
		this.universityID = universityID;
	}

	public String getApplicationDocumentPath() {
		return applicationDocumentPath;
	}

	public void setApplicationDocumentPath(String applicationDocumentPath) {
		this.applicationDocumentPath = applicationDocumentPath;
	}

	public String getSessionStartDate() {
		return sessionStartDate;
	}

	public void setSessionStartDate(String sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
	}

	public String getSessionEndDate() {
		return sessionEndDate;
	}

	public void setSessionEndDate(String sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}

	public String getCcaUploaded() {
		return ccaUploaded;
	}

	public void setCcaUploaded(String ccaUploaded) {
		this.ccaUploaded = ccaUploaded;
	}

	public String getNccUploaded() {
		return nccUploaded;
	}

	public void setNccUploaded(String nccUploaded) {
		this.nccUploaded = nccUploaded;
	}

	public String getNssUploaded() {
		return nssUploaded;
	}

	public void setNssUploaded(String nssUploaded) {
		this.nssUploaded = nssUploaded;
	}

	public String getSocialUploaded() {
		return socialUploaded;
	}

	public void setSocialUploaded(String socialUploaded) {
		this.socialUploaded = socialUploaded;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getExamCenter() {
		return examCenter;
	}
	public void setExamCenter(String examCenter) {
		this.examCenter = examCenter;
	}
	public String getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	
	
	

}
