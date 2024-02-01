package in.ac.dei.edrp.admissionsystem.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApplicantProgram implements Serializable 
{

	private String applicationNumber;
	private String registrationNumber;
	private String programID;
	private String programName;
	private String emailID;
	private String admitCardGenerated;
	private String paymentMode;
	private String subjectName;
	private String examinationCenter;
	private List<EntranceTest> entranceTests;
	private String status;
	private String staff_ward;
	
	private int noOfBranches;
	private String prefferedChoiceAllowed;
	
	private String branch1;
	private String branch2;
	private String branch3;
	private String branch4;
	private String branch5;
	
	public ApplicantProgram() 
	{
	
	}

	

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getProgramID() {
		return programID;
	}

	public void setProgramID(String programID) {
		this.programID = programID;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getAdmitCardGenerated() {
		return admitCardGenerated;
	}

	public void setAdmitCardGenerated(String admitCardGenerated) {
		this.admitCardGenerated = admitCardGenerated;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}



	public String getExaminationCenter() {
		return examinationCenter;
	}



	public void setExaminationCenter(String examinationCenter) {
		this.examinationCenter = examinationCenter;
	}



	


	public List<EntranceTest> getEntranceTests() {
		return entranceTests;
	}



	public void setEntranceTests(List<EntranceTest> entranceTests) {
		this.entranceTests = entranceTests;
	}



	public String getStaff_ward() {
		return staff_ward;
	}



	public void setStaff_ward(String staff_ward) {
		this.staff_ward = staff_ward;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public int getNoOfBranches() {
		return noOfBranches;
	}



	public void setNoOfBranches(int noOfBranches) {
		this.noOfBranches = noOfBranches;
	}



	



	public String getPrefferedChoiceAllowed() {
		return prefferedChoiceAllowed;
	}



	public void setPrefferedChoiceAllowed(String prefferedChoiceAllowed) {
		this.prefferedChoiceAllowed = prefferedChoiceAllowed;
	}



	public String getBranch1() {
		return branch1;
	}



	public void setBranch1(String branch1) {
		this.branch1 = branch1;
	}



	public String getBranch2() {
		return branch2;
	}



	public void setBranch2(String branch2) {
		this.branch2 = branch2;
	}



	public String getBranch3() {
		return branch3;
	}



	public void setBranch3(String branch3) {
		this.branch3 = branch3;
	}



	public String getBranch4() {
		return branch4;
	}



	public void setBranch4(String branch4) {
		this.branch4 = branch4;
	}



	public String getBranch5() {
		return branch5;
	}



	public void setBranch5(String branch5) {
		this.branch5 = branch5;
	}
	
	
}
