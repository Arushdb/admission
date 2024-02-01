package in.ac.dei.edrp.admissionsystem.common.beans;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;

import java.util.ArrayList;
import java.util.List;

public class ProgramWiseDetail 
{
	private String programID;
	private String EntityId;   //added by atul Dayal
	private String CosValue;   // added By Atul Dayal
	private String ReasonCode; //added By Atul Dayal
	private String Called;  // added By Atul Dayal
	private String Status; // added By Atul Dayal
	private String programName;
	private String registrationNumber;
	private String applicationNumber;
	private String testNumber;
	private String sessionStartDate;
	private String sessionEndDate;
	private String userID;
	private String universityID;
	private String paperMainGroup;
	private String paperMainGroupDesc;
	private String paperGrouping;
	private List<GroupPaper> groupPapers = new ArrayList<GroupPaper>();
	private int groupPapersSize;
	
	private String categoryCode;
	private String genderCode;
	private String formNumber;
	private String formName;
	
	private List<StudentProgramPaper> studentProgramPapers = new ArrayList<StudentProgramPaper>();
	
	//private List<ProgramBranch> programBranches = new ArrayList<ProgramBranch>();
	private List<CommonBean> programBranches = new ArrayList<CommonBean>();
	private List<CommonBean> selectedBranches = new ArrayList<CommonBean>();
	
	private ProgramPaper programPaper = new ProgramPaper();
	private List<ProgramExamCenter> programExamCenters = new ArrayList<ProgramExamCenter>();
	private List<AcademicDetail> academicDetails = new ArrayList<AcademicDetail>();
	private List<AcademicDetail> gateAcademicDetails = new ArrayList<AcademicDetail>();
	private List<CommonBean> educationModes = new ArrayList<CommonBean>();
	private List<CommonBean> regStudyCenters = new ArrayList<CommonBean>();
	private String preferredBranchAvailable ;
	
	private String selectedEducationMode;
	private String selectedEducationModeName;
	private String studyCenterCode;
	private String studyCenterName;
	private String selectedOnlineMode; //added by Jyoti on 26 Apr 2021
	private String selectedOnlineModeName; //added by Jyoti on 26 Apr 2021
	private String selectedRegularMode; //added by Jyoti on 1 May 2021
	private String selectedDistanceMode; //added by Jyoti on 15 May 2021
	
	private String examCenter1;
	private String examCenter2;
	private String examCenter3;
	
	private String examCenter1Name;
	
	private String staffWardFlag;
	private String staffCode;
	
	private String admitCardGenerated;
	private String admitCardPath;
	
	private String branch1;
	private String branch2;
	private String branch3;
	private String branch4;
	private String branch5;
	
	private String branch1Name;
	private String branch2Name;
	private String branch3Name;
	private String branch4Name;
	private String branch5Name;
	
	private String fatherIncome;
	private String motherIncome;
	private String guardianIncome;
	
	private String ccActivities;
	private String everExpelled;
	private String hostelRequired;
	
	private String deiStudent;
	private String lastExamFrom;
	private String co_ed;
	
	private String passkey;
	private String examDate;
	
	public ProgramWiseDetail()
	{
		
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

	
	public List<CommonBean> getProgramBranches() {
		return programBranches;
	}

	public void setProgramBranches(List<CommonBean> programBranches) {
		this.programBranches = programBranches;
	}

	public List<CommonBean> getSelectedBranches() {
		return selectedBranches;
	}

	public void setSelectedBranches(List<CommonBean> selectedBranches) {
		this.selectedBranches = selectedBranches;
	}

	
	public ProgramPaper getProgramPaper() {
		return programPaper;
	}

	public void setProgramPaper(ProgramPaper programPaper) {
		this.programPaper = programPaper;
	}

	public List<ProgramExamCenter> getProgramExamCenters() {
		return programExamCenters;
	}

	public void setProgramExamCenters(List<ProgramExamCenter> programExamCenters) {
		this.programExamCenters = programExamCenters;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getTestNumber() {
		return testNumber;
	}

	public void setTestNumber(String testNumber) {
		this.testNumber = testNumber;
	}

	public List<StudentProgramPaper> getStudentProgramPapers() {
		return studentProgramPapers;
	}

	public void setStudentProgramPapers(
			List<StudentProgramPaper> studentProgramPapers) {
		this.studentProgramPapers = studentProgramPapers;
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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUniversityID() {
		return universityID;
	}

	public void setUniversityID(String universityID) {
		this.universityID = universityID;
	}

	public String getPaperMainGroup() {
		return paperMainGroup;
	}

	public void setPaperMainGroup(String paperMainGroup) {
		this.paperMainGroup = paperMainGroup;
	}

	public String getPaperGrouping() {
		return paperGrouping;
	}

	public void setPaperGrouping(String paperGrouping) {
		this.paperGrouping = paperGrouping;
	}

	public List<GroupPaper> getGroupPapers() {
		return groupPapers;
	}

	public void setGroupPapers(List<GroupPaper> groupPapers) {
		this.groupPapers = groupPapers;
	}

	public int getGroupPapersSize() {
		return groupPapersSize;
	}

	public void setGroupPapersSize(int groupPapersSize) {
		this.groupPapersSize = groupPapersSize;
	}

	public String getEntityId() {
		return EntityId;
	}

	public void setEntityId(String entityId) {
		EntityId = entityId;
	}

	public String getCosValue() {
		return CosValue;
	}

	public void setCosValue(String cosValue) {
		CosValue = cosValue;
	}

	public String getReasonCode() {
		return ReasonCode;
	}

	public void setReasonCode(String reasonCode) {
		ReasonCode = reasonCode;
	}

	public String getCalled() {
		return Called;
	}

	public void setCalled(String called) {
		Called = called;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getExamCenter1() {
		return examCenter1;
	}

	public void setExamCenter1(String examCenter1) {
		this.examCenter1 = examCenter1;
	}

	public String getExamCenter2() {
		return examCenter2;
	}

	public void setExamCenter2(String examCenter2) {
		this.examCenter2 = examCenter2;
	}

	public String getExamCenter3() {
		return examCenter3;
	}

	public void setExamCenter3(String examCenter3) {
		this.examCenter3 = examCenter3;
	}

	public String getStaffWardFlag() {
		return staffWardFlag;
	}

	public void setStaffWardFlag(String staffWardFlag) {
		this.staffWardFlag = staffWardFlag;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getExamCenter1Name() {
		return examCenter1Name;
	}

	public void setExamCenter1Name(String examCenter1Name) {
		this.examCenter1Name = examCenter1Name;
	}

	public String getPaperMainGroupDesc() {
		return paperMainGroupDesc;
	}

	public void setPaperMainGroupDesc(String paperMainGroupDesc) {
		this.paperMainGroupDesc = paperMainGroupDesc;
	}

	public String getFormNumber() {
		return formNumber;
	}

	public void setFormNumber(String formNumber) {
		this.formNumber = formNumber;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getAdmitCardGenerated() {
		return admitCardGenerated;
	}

	public void setAdmitCardGenerated(String admitCardGenerated) {
		this.admitCardGenerated = admitCardGenerated;
	}

	public String getAdmitCardPath() {
		return admitCardPath;
	}

	public void setAdmitCardPath(String admitCardPath) {
		this.admitCardPath = admitCardPath;
	}

	public List<AcademicDetail> getAcademicDetails() {
		return academicDetails;
	}

	public void setAcademicDetails(List<AcademicDetail> academicDetails) {
		this.academicDetails = academicDetails;
	}

	public List<AcademicDetail> getGateAcademicDetails() {
		return gateAcademicDetails;
	}

	public void setGateAcademicDetails(List<AcademicDetail> gateAcademicDetails) {
		this.gateAcademicDetails = gateAcademicDetails;
	}

	public List<CommonBean> getEducationModes() {
		return educationModes;
	}

	public void setEducationModes(List<CommonBean> educationModes) {
		this.educationModes = educationModes;
	}

	public String getSelectedEducationMode() {
		return selectedEducationMode;
	}

	public void setSelectedEducationMode(String selectedEducationMode) {
		this.selectedEducationMode = selectedEducationMode;
	}

	public String getStudyCenterCode() {
		return studyCenterCode;
	}

	public void setStudyCenterCode(String studyCenterCode) {
		this.studyCenterCode = studyCenterCode;
	}

	public String getStudyCenterName() {
		return studyCenterName;
	}

	public void setStudyCenterName(String studyCenterName) {
		this.studyCenterName = studyCenterName;
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

	public String getCcActivities() {
		return ccActivities;
	}

	public void setCcActivities(String ccActivities) {
		this.ccActivities = ccActivities;
	}

	public String getEverExpelled() {
		return everExpelled;
	}

	public void setEverExpelled(String everExpelled) {
		this.everExpelled = everExpelled;
	}

	public String getHostelRequired() {
		return hostelRequired;
	}

	public void setHostelRequired(String hostelRequired) {
		this.hostelRequired = hostelRequired;
	}

	public String getPreferredBranchAvailable() {
		return preferredBranchAvailable;
	}

	public void setPreferredBranchAvailable(String preferredBranchAvailable) {
		this.preferredBranchAvailable = preferredBranchAvailable;
	}

	public String getSelectedEducationModeName() {
		return selectedEducationModeName;
	}

	public void setSelectedEducationModeName(String selectedEducationModeName) {
		this.selectedEducationModeName = selectedEducationModeName;
	}

	public String getBranch1Name() {
		return branch1Name;
	}

	public void setBranch1Name(String branch1Name) {
		this.branch1Name = branch1Name;
	}

	public String getBranch2Name() {
		return branch2Name;
	}

	public void setBranch2Name(String branch2Name) {
		this.branch2Name = branch2Name;
	}

	public String getBranch3Name() {
		return branch3Name;
	}

	public void setBranch3Name(String branch3Name) {
		this.branch3Name = branch3Name;
	}

	public String getDeiStudent() {
		return deiStudent;
	}

	public void setDeiStudent(String deiStudent) {
		this.deiStudent = deiStudent;
	}

	public String getLastExamFrom() {
		return lastExamFrom;
	}

	public void setLastExamFrom(String lastExamFrom) {
		this.lastExamFrom = lastExamFrom;
	}

	public String getCo_ed() {
		return co_ed;
	}

	public void setCo_ed(String coEd) {
		co_ed = coEd;
	}

	public void setSelectedOnlineMode(String selectedOnlineMode) {
		this.selectedOnlineMode = selectedOnlineMode;
	}

	public String getSelectedOnlineMode() {
		return selectedOnlineMode;
	}

	public void setSelectedOnlineModeName(String selectedOnlineModeName) {
		this.selectedOnlineModeName = selectedOnlineModeName;
	}

	public String getSelectedOnlineModeName() {
		return selectedOnlineModeName;
	}

	public void setSelectedRegularMode(String selectedRegularMode) {
		this.selectedRegularMode = selectedRegularMode;
	}

	public String getSelectedRegularMode() {
		return selectedRegularMode;
	}

	public void setRegStudyCenters(List<CommonBean> regStudyCenters) {
		this.regStudyCenters = regStudyCenters;
	}

	public List<CommonBean> getRegStudyCenters() {
		return regStudyCenters;
	}

	public void setSelectedDistanceMode(String selectedDistanceMode) {
		this.selectedDistanceMode = selectedDistanceMode;
	}

	public String getSelectedDistanceMode() {
		return selectedDistanceMode;
	}

	public String getBranch4Name() {
		return branch4Name;
	}

	public void setBranch4Name(String branch4Name) {
		this.branch4Name = branch4Name;
	}

	public String getBranch5Name() {
		return branch5Name;
	}

	public void setBranch5Name(String branch5Name) {
		this.branch5Name = branch5Name;
	}

	public String getPasskey() {
		return passkey;
	}

	public void setPasskey(String passkey) {
		this.passkey = passkey;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	

	
	
		
}
