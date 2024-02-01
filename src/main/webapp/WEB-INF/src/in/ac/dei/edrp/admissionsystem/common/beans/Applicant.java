package in.ac.dei.edrp.admissionsystem.common.beans;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;

import java.util.ArrayList;
import java.util.List;

public class Applicant 
{
	private String applicantName;
	private String primaryEmailID;
	private String secondaryEmailID;
	private String dob;
	private String dd;
	private String mm;
	private String yyyy;
	private String gender;
	private String category;
	private String fatherName;
	private String motherName;
	private String guardianName;
	private String fatherIncome;
	private String motherIncome;
	private String guardianIncome;
	private String nationality;
	private String religion;
	private String minority;
	private String maritalStatus;
	private String pwd;
	private String userID;
	private String locality;
	
	private String categoryDesc;
	private String genderDesc;
	private String dobIST;
	
	private String corAddressL1;
	private String corAddressL2;
	private String corState;
	private String corCity;
	private String corDistrict;
	private String corPincode;
	
	private String perAddressL1;
	private String perAddressL2;
	private String perState;
	private String perCity;
	private String perDistrict;
	private String perPincode;
	
	private String homePhone;
	private String otherPhone;
	private String corCountry;
	private String perCountry;
	private String district;
	private String universityID;
	private String registeredSession;
	private String applicationNumber;
	private String applicationStatus;
	private String password;
	private String tabStatus;
	private String sessionStartDate;
	private String sessionEndDate;
	private String sessionCombined;
	
	private String question;
	private String answer;
	private String applicationFee;
	
	private ApplicationForm applicationForm = new ApplicationForm();
	private List<String> programs = new ArrayList<String>();
	private List<String> optedPapers = new ArrayList<String>();
	
	private List<CommonBean> allCategories = new ArrayList<CommonBean>();
	private List<CommonBean> allGenders = new ArrayList<CommonBean>();
	private List<CommonBean> allBloodGroups = new ArrayList<CommonBean>();
	
	private List<CommonBean> examinationCenters = new ArrayList<CommonBean>();
	private String examCenter1;
	private String examCenter2;
	private String examCenter3;
	private String staffWardFlag;
	private String staffCode;
	
	private String examCenter1Name;
	
	private String verificationStatus;
	private String phaseOptionToShow;
	private String forceEditing;
	private String globalEditing;
	private String phase2Edited;
	
	
	private String publishedPapers;
	private String journalDesc;
	private String conferences;
	private String conferencesDesc;
	private String fellowship;
	private String fellowshipDesc;
	private String retQualified;
	private String retRemarks;
	private String retYear;
	private String retRollNumber;
	private String instituteTeacher;
	private String jrfQualified;
	private String deiScholor;
	private String deiMphil;
	private String deiMedalWinner;
	private String deiPG;
	private String cgpa9;
	private String recipientType;
	
	private String applicationDocumentPath;
	
	private String ageOnJuly; //Added Age Eligibility by Arjun Added on 04-04-2016 
	private String aadharNumber;
	private String bloodGroup;
	private String feePassword;
	
	private String ews;
	private String kashmiriPandit;
	private String typeOfId;
	private String idProofNumber;
	private List<CommonBean> idProofs = new ArrayList<CommonBean>();
	
	private String applicantNameHi;
	private String fatherNameHi;
	private String motherNameHi;
	
	private String cuetFlag, cuetAppNumber, cuetRollNumber;
	
	public Applicant() {
		CommonBean idProofOpt1 = new CommonBean();
		idProofOpt1.setCode("ADH");
		idProofOpt1.setDescription("AADHAAR");
		
		CommonBean idProofOpt2 = new CommonBean();
		idProofOpt2.setCode("VTR");
		idProofOpt2.setDescription("VOTER CARD");
		
		CommonBean idProofOpt3 = new CommonBean();
		idProofOpt3.setCode("DRL");
		idProofOpt3.setDescription("DRIVING LICENCE");
		
		CommonBean idProofOpt4 = new CommonBean();
		idProofOpt4.setCode("PSP");
		idProofOpt4.setDescription("PASSPORT");
		
		idProofs.add(idProofOpt1);
		idProofs.add(idProofOpt2);
		idProofs.add(idProofOpt3);
		idProofs.add(idProofOpt4);
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getPrimaryEmailID() {
		return primaryEmailID;
	}
	public void setPrimaryEmailID(String primaryEmailID) {
		this.primaryEmailID = primaryEmailID;
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
	public String getGuardianName() {
		return guardianName;
	}
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	
	public String getCorAddressL1() {
		return corAddressL1;
	}
	public void setCorAddressL1(String corAddressL1) {
		this.corAddressL1 = corAddressL1;
	}
	public String getCorAddressL2() {
		return corAddressL2;
	}
	public void setCorAddressL2(String corAddressL2) {
		this.corAddressL2 = corAddressL2;
	}
	public String getCorState() {
		return corState;
	}
	public void setCorState(String corState) {
		this.corState = corState;
	}
	public String getCorCity() {
		return corCity;
	}
	public void setCorCity(String corCity) {
		this.corCity = corCity;
	}
	public String getCorPincode() {
		return corPincode;
	}
	public void setCorPincode(String corPincode) {
		this.corPincode = corPincode;
	}
	public String getPerAddressL1() {
		return perAddressL1;
	}
	public void setPerAddressL1(String perAddressL1) {
		this.perAddressL1 = perAddressL1;
	}
	public String getPerAddressL2() {
		return perAddressL2;
	}
	public void setPerAddressL2(String perAddressL2) {
		this.perAddressL2 = perAddressL2;
	}
	public String getPerState() {
		return perState;
	}
	public void setPerState(String perState) {
		this.perState = perState;
	}
	public String getPerCity() {
		return perCity;
	}
	public void setPerCity(String perCity) {
		this.perCity = perCity;
	}
	public String getPerPincode() {
		return perPincode;
	}
	public void setPerPincode(String perPincode) {
		this.perPincode = perPincode;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getOtherPhone() {
		return otherPhone;
	}
	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}
	
	public String getCorCountry() {
		return corCountry;
	}
	public void setCorCountry(String corCountry) {
		this.corCountry = corCountry;
	}
	public String getPerCountry() {
		return perCountry;
	}
	public void setPerCountry(String perCountry) {
		this.perCountry = perCountry;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getUniversityID() {
		return universityID;
	}
	public void setUniversityID(String universityID) {
		this.universityID = universityID;
	}
	public String getRegisteredSession() {
		return registeredSession;
	}
	public void setRegisteredSession(String registeredSession) {
		this.registeredSession = registeredSession;
	}
	public String getApplicationNumber() {
		return applicationNumber;
	}
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public ApplicationForm getApplicationForm() {
		return applicationForm;
	}
	public void setApplicationForm(ApplicationForm applicationForm) {
		this.applicationForm = applicationForm;
	}
	public List<String> getPrograms() {
		return programs;
	}
	public void setPrograms(List<String> programs) {
		this.programs = programs;
	}
	public List<String> getOptedPapers() {
		return optedPapers;
	}
	public void setOptedPapers(List<String> optedPapers) {
		this.optedPapers = optedPapers;
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
	
	
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getTabStatus() {
		return tabStatus;
	}
	public void setTabStatus(String tabStatus) {
		this.tabStatus = tabStatus;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDd() {
		return dd;
	}
	public void setDd(String dd) {
		this.dd = dd;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getYyyy() {
		return yyyy;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}
	public List<CommonBean> getAllCategories() {
		return allCategories;
	}
	public void setAllCategories(List<CommonBean> allCategories) {
		this.allCategories = allCategories;
	}
	public List<CommonBean> getAllGenders() {
		return allGenders;
	}
	public void setAllGenders(List<CommonBean> allGenders) {
		this.allGenders = allGenders;
	}
	public List<CommonBean> getExaminationCenters() {
		return examinationCenters;
	}
	public void setExaminationCenters(List<CommonBean> examinationCenters) {
		this.examinationCenters = examinationCenters;
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
	public String getSessionCombined() {
		return sessionCombined;
	}
	public void setSessionCombined(String sessionCombined) {
		this.sessionCombined = sessionCombined;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public String getGenderDesc() {
		return genderDesc;
	}
	public void setGenderDesc(String genderDesc) {
		this.genderDesc = genderDesc;
	}
	public String getDobIST() {
		return dobIST;
	}
	public void setDobIST(String dobIST) {
		this.dobIST = dobIST;
	}
	public String getCorDistrict() {
		return corDistrict;
	}
	public void setCorDistrict(String corDistrict) {
		this.corDistrict = corDistrict;
	}
	public String getPerDistrict() {
		return perDistrict;
	}
	public void setPerDistrict(String perDistrict) {
		this.perDistrict = perDistrict;
	}
	public String getApplicationFee() {
		return applicationFee;
	}
	public void setApplicationFee(String applicationFee) {
		this.applicationFee = applicationFee;
	}
	public String getVerificationStatus() {
		return verificationStatus;
	}
	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}
	public String getPhaseOptionToShow() {
		return phaseOptionToShow;
	}
	public void setPhaseOptionToShow(String phaseOptionToShow) {
		this.phaseOptionToShow = phaseOptionToShow;
	}
	public String getForceEditing() {
		return forceEditing;
	}
	public void setForceEditing(String forceEditing) {
		this.forceEditing = forceEditing;
	}
	public String getGlobalEditing() {
		return globalEditing;
	}
	public void setGlobalEditing(String globalEditing) {
		this.globalEditing = globalEditing;
	}
	public String getPhase2Edited() {
		return phase2Edited;
	}
	public void setPhase2Edited(String phase2Edited) {
		this.phase2Edited = phase2Edited;
	}
	public String getPublishedPapers() {
		return publishedPapers;
	}
	public void setPublishedPapers(String publishedPapers) {
		this.publishedPapers = publishedPapers;
	}
	public String getJournalDesc() {
		return journalDesc;
	}
	public void setJournalDesc(String journalDesc) {
		this.journalDesc = journalDesc;
	}
	public String getConferences() {
		return conferences;
	}
	public void setConferences(String conferences) {
		this.conferences = conferences;
	}
	public String getConferencesDesc() {
		return conferencesDesc;
	}
	public void setConferencesDesc(String conferencesDesc) {
		this.conferencesDesc = conferencesDesc;
	}
	public String getFellowship() {
		return fellowship;
	}
	public void setFellowship(String fellowship) {
		this.fellowship = fellowship;
	}
	public String getFellowshipDesc() {
		return fellowshipDesc;
	}
	public void setFellowshipDesc(String fellowshipDesc) {
		this.fellowshipDesc = fellowshipDesc;
	}
	public String getRetQualified() {
		return retQualified;
	}
	public void setRetQualified(String retQualified) {
		this.retQualified = retQualified;
	}
	public String getRetRemarks() {
		return retRemarks;
	}
	public void setRetRemarks(String retRemarks) {
		this.retRemarks = retRemarks;
	}
	public String getRetYear() {
		return retYear;
	}
	public void setRetYear(String retYear) {
		this.retYear = retYear;
	}
	
	public String getRetRollNumber() {
		return retRollNumber;
	}
	public void setRetRollNumber(String retRollNumber) {
		this.retRollNumber = retRollNumber;
	}
	public String getInstituteTeacher() {
		return instituteTeacher;
	}
	public void setInstituteTeacher(String instituteTeacher) {
		this.instituteTeacher = instituteTeacher;
	}
	public String getJrfQualified() {
		return jrfQualified;
	}
	public void setJrfQualified(String jrfQualified) {
		this.jrfQualified = jrfQualified;
	}
	public String getDeiScholor() {
		return deiScholor;
	}
	public void setDeiScholor(String deiScholor) {
		this.deiScholor = deiScholor;
	}
	public String getDeiMphil() {
		return deiMphil;
	}
	public void setDeiMphil(String deiMphil) {
		this.deiMphil = deiMphil;
	}
	public String getDeiMedalWinner() {
		return deiMedalWinner;
	}
	public void setDeiMedalWinner(String deiMedalWinner) {
		this.deiMedalWinner = deiMedalWinner;
	}
	public String getDeiPG() {
		return deiPG;
	}
	public void setDeiPG(String deiPG) {
		this.deiPG = deiPG;
	}
	public String getCgpa9() {
		return cgpa9;
	}
	public void setCgpa9(String cgpa9) {
		this.cgpa9 = cgpa9;
	}
	public String getRecipientType() {
		return recipientType;
	}
	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}
	public String getApplicationDocumentPath() {
		return applicationDocumentPath;
	}
	public void setApplicationDocumentPath(String applicationDocumentPath) {
		this.applicationDocumentPath = applicationDocumentPath;
	}
	public String getAgeOnJuly() {
		return ageOnJuly;
	}
	public void setAgeOnJuly(String ageOnJuly) {
		this.ageOnJuly = ageOnJuly;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public List<CommonBean> getAllBloodGroups() {
		return allBloodGroups;
	}
	public void setAllBloodGroups(List<CommonBean> allBloodGroups) {
		this.allBloodGroups = allBloodGroups;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getFeePassword() {
		return feePassword;
	}
	public void setFeePassword(String feePassword) {
		this.feePassword = feePassword;
	}
	public String getEws() {
		return ews;
	}
	public void setEws(String ews) {
		this.ews = ews;
	}
	public String getKashmiriPandit() {
		return kashmiriPandit;
	}
	public void setKashmiriPandit(String kashmiriPandit) {
		this.kashmiriPandit = kashmiriPandit;
	}
	
	public List<CommonBean> getIdProofs() {
		return idProofs;
	}
	public void setIdProofs(List<CommonBean> idProofs) {
		this.idProofs = idProofs;
	}
	public String getApplicantNameHi() {
		return applicantNameHi;
	}
	public void setApplicantNameHi(String applicantNameHi) {
		this.applicantNameHi = applicantNameHi;
	}
	public String getFatherNameHi() {
		return fatherNameHi;
	}
	public void setFatherNameHi(String fatherNameHi) {
		this.fatherNameHi = fatherNameHi;
	}
	public String getMotherNameHi() {
		return motherNameHi;
	}
	public void setMotherNameHi(String motherNameHi) {
		this.motherNameHi = motherNameHi;
	}
	public String getIdProofNumber() {
		return idProofNumber;
	}
	public void setIdProofNumber(String idProofNumber) {
		this.idProofNumber = idProofNumber;
	}
	public String getTypeOfId() {
		return typeOfId;
	}
	public void setTypeOfId(String typeOfId) {
		this.typeOfId = typeOfId;
	}
	public String getCuetFlag() {
		return cuetFlag;
	}
	public void setCuetFlag(String cuetFlag) {
		this.cuetFlag = cuetFlag;
	}
	public String getCuetAppNumber() {
		return cuetAppNumber;
	}
	public void setCuetAppNumber(String cuetAppNumber) {
		this.cuetAppNumber = cuetAppNumber;
	}
	public String getCuetRollNumber() {
		return cuetRollNumber;
	}
	public void setCuetRollNumber(String cuetRollNumber) {
		this.cuetRollNumber = cuetRollNumber;
	}
	
	

}
