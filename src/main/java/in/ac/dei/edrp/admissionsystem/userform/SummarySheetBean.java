/*
 * @(#) SummarySheetBean.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
package in.ac.dei.edrp.admissionsystem.userform;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

@SuppressWarnings("serial")
public class SummarySheetBean implements Serializable {
    private String sessionStartDate;
    private String sessionEndDate;
    
    private String registrationNumber;
    private String universityId;
    private String entityId; 
    private int count; //added by atul dayal
    private String registeredInSession;   //added By atul dayal
    private String entityCode;
    private String facultyRegNo;
    private String programId;
    private String universityName;
    private String entityName;
    private String programName;
    private String studentId;
    private String firstName;
    private String offered_by; //added by atul dayal
    private String middleName;
    private String lastName;
    private String fatherFirstName;
    private String fatherMiddleName;
    private String fatherLastName;
    private String motherFirstName;
    private String motherMiddleName;
    private String motherLastName;
    private String primaryEmail;
    private String secondaryEmail;
    private String dob;
    private String gender;
    private String category;
    private String phoneNumber;
    private String otherPhone;
    private String receiptNo;//added by dhruv
    private String receiptAmt;//added by dhruv
    private String ddNo;
    private String ddAmount;
    private String ddDate;
    private String bankName;
    private String addressLine1;
    private String addressLine2;
    private String CCA;//added by atul dayal
    private String city;
    private String state;
    private String pincode;
    private String componentId;
    
    private String componentDescription;
    private String componentType;
    private String componentWeightage;
    private String weightageFlag;
    private String boardFlag;
    private String specialWeightageFlag;
    private String componentCriteriaFlag;
    private String ugPg;
    private String totalMarks;
    private String marksObtained;
    private String percentage;
    private String score;
    private String board;
    private Boolean weightage;
    private String ruleNumber;
    private String sequenceNumber;
    private String paperCode;
    private String paperDescription;
    private String grouping;
    private String docId;
    private String docName;
    private String docPath;
    private String userId;
    private String photoPath;
    private String formNumber;
    private String firstDegreeCode;
    private List<String> programList;
    private List<String> programNameList;
    private List<SummarySheetBean> paperGroupList;
    private List<SummarySheetBean> academicList;
    private List<SummarySheetBean> attachmentList;
    private List<SummarySheetBean> degreeList;
    private List<SummarySheetBean> cosCodeList;
    private List<String> entityIdList;
    private String cosCode;
    private String cosDescription;
    private String userEmailId;
    private Boolean staffWeightage;
    private String universityNickName;
    private String centerCode;
    private String centerDescription;
    private List<SummarySheetBean> centerCodeList;
    private String nationality;
    private String religion;
    private String guardian;
    private String facRegNumber;
    private List<String> registrationNumList;
    private String fileSeparator;
    private String minorityDesc;
    private String maritalStatusDesc;
    private String minority;
    private String maritalStatus;
    private String flag1;//added by dhruv
    private String prefferedChoiceAllowed;
    private String branchId;
    private String branchName;
    
    private String old_program_id; //entered by atul dayal
    private String new_program_id; //entered by atul dayal
    private String old_registration_number; //entered by atul dayal
    private String new_registration_number; //entered by atul dayal
    
// Properties Added By Dheeraj on June 1, 2013
    private String menuItemName;
    private String menuItemId;
    
    private String preferredChoice1;
    private String preferredChoice2;
    private String preferredChoice3;
    
    // Added by Jitendra Feb 24, 2014
	private String boardId;
    
   //added by manpreet on 10-12-13
    private String cocurriActivities;
    private String applicationNumber;
    private String applicationStatus;
    private String verificationStatus;
    private String fee;
    private String voucherNumber;
    private String bankAccountNumber;
    private String bankAccountName;
    private String voucherNote;
    private String oldSequenceNumber;
    
    private List<String> preferredChoiceArray1;//added by manpreet 14-2-2014
    private List<String> preferredChoiceArray2;//added by manpreet 14-2-2014
    private List<String> preferredChoiceArray3;//added by manpreet 14-2-2014
    private List<String> preferredChoiceArray4;//added by manpreet 14-2-2014
    private List<String> preferredChoiceArray5;//added by manpreet 14-2-2014
        private String dei_student;//added by Arjun on 28/10/2013
        private String staff_ward;//added by Arjun on 28/10/2013
    private String applicationType;// added by manpreet
    private String handicaped;// added by manpreet
    private String fatherIncome;// added by manpreet
    private String motherIncome;// added by manpreet
    private String guardianIncome;// added by manpreet
    private String locality;// added by manpreet
    private String hostelRequired;// added by manpreet
    private String everExpelled;// added by manpreet
    private String mainGroup;// added by manpreet
    private String minChoice; // added by manpreet
    private String maxChoice; // added by manpreet
    private String employeeCode; //added by manpreet 7-4-2014
    private String mainGroupName;// added by manpreet
    private String otherBoardName;// added by manpreet
    private String country;//added by Manpreet

    private String gateYear;
    private String gateRank;
    private String gateTotalStudent;
    private String gateBranch;
    private String district;
    private List<String> feeList;
    private String transactionId;
 	private String passingYearFlag;
    private String passingYear;
    private String resutAwaitedFlag;
    private String resultAwaited;
    
    //Dharna
   	private String netQualified;
       private String net;
       private String journals;
       private String journalDescription;
       private String conferences;
       private String conferenceDescription;
       private String retQualified;
       private String retRemarks;
       private String fellowship;
       private String fellowshipDescription;

    
    public String getResutAwaitedFlag() {
		return resutAwaitedFlag;
	}

	public void setResutAwaitedFlag(String resutAwaitedFlag) {
		this.resutAwaitedFlag = resutAwaitedFlag;
	}

	public List<String> getEntityIdList() {
		return entityIdList;
	}

	public void setEntityIdList(List<String> entityIdList) {
		this.entityIdList = entityIdList;
	}

	public String getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(String marksObtained) {
        this.marksObtained = marksObtained;
        
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public Boolean getWeightage() {
        return weightage;
    }

    public void setWeightage(Boolean weightage) {
        this.weightage = weightage;
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

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getFacultyRegNo() {
        return facultyRegNo;
    }

    public void setFacultyRegNo(String facultyRegNo) {
        this.facultyRegNo = facultyRegNo;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherFirstName() {
        return fatherFirstName;
    }

    public void setFatherFirstName(String fatherFirstName) {
        this.fatherFirstName = fatherFirstName;
    }

    public String getFatherMiddleName() {
        return fatherMiddleName;
    }

    public void setFatherMiddleName(String fatherMiddleName) {
        this.fatherMiddleName = fatherMiddleName;
    }

    public String getFatherLastName() {
        return fatherLastName;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    public String getMotherFirstName() {
        return motherFirstName;
    }

    public void setMotherFirstName(String motherFirstName) {
        this.motherFirstName = motherFirstName;
    }

    public String getMotherMiddleName() {
        return motherMiddleName;
    }

    public void setMotherMiddleName(String motherMiddleName) {
        this.motherMiddleName = motherMiddleName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }

    public String getDdNo() {
        return ddNo;
    }

    public void setDdNo(String ddNo) {
        this.ddNo = ddNo;
    }

    public String getDdAmount() {
        return ddAmount;
    }

    public void setDdAmount(String ddAmount) {
        this.ddAmount = ddAmount;
    }

    public String getDdDate() {
        return ddDate;
    }

    public void setDdDate(String ddDate) {
        this.ddDate = ddDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getComponentWeightage() {
        return componentWeightage;
    }

    public void setComponentWeightage(String componentWeightage) {
        this.componentWeightage = componentWeightage;
    }

    public String getWeightageFlag() {
        return weightageFlag;
    }

    public void setWeightageFlag(String weightageFlag) {
        this.weightageFlag = weightageFlag;
    }

    public String getBoardFlag() {
        return boardFlag;
    }

    public void setBoardFlag(String boardFlag) {
        this.boardFlag = boardFlag;
    }

    public String getSpecialWeightageFlag() {
        return specialWeightageFlag;
    }

    public void setSpecialWeightageFlag(String specialWeightageFlag) {
        this.specialWeightageFlag = specialWeightageFlag;
    }

    public String getComponentCriteriaFlag() {
        return componentCriteriaFlag;
    }

    public void setComponentCriteriaFlag(String componentCriteriaFlag) {
        this.componentCriteriaFlag = componentCriteriaFlag;
    }

    public String getUgPg() {
        return ugPg;
    }

    public void setUgPg(String ugPg) {
        this.ugPg = ugPg;
    }

    public String getRuleNumber() {
        return ruleNumber;
    }

    public void setRuleNumber(String ruleNumber) {
        this.ruleNumber = ruleNumber;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public String getPaperDescription() {
        return paperDescription;
    }

    public void setPaperDescription(String paperDescription) {
        this.paperDescription = paperDescription;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public List<String> getProgramList() {
        return programList;
    }

    public void setProgramList(List<String> programList) {
        this.programList = programList;
    }

    public List<SummarySheetBean> getPaperGroupList() {
        return paperGroupList;
    }

    public void setPaperGroupList(List<SummarySheetBean> paperGroupList) {
        this.paperGroupList = paperGroupList;
    }

    public List<SummarySheetBean> getAcademicList() {
        return academicList;
    }

    public void setAcademicList(List<SummarySheetBean> academicList) {
        this.academicList = academicList;
    }

    public List<SummarySheetBean> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<SummarySheetBean> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setFirstDegreeCode(String firstDegreeCode) {
        this.firstDegreeCode = firstDegreeCode;
    }

    public String getFirstDegreeCode() {
        return firstDegreeCode;
    }

	public void setProgramNameList(List<String> programNameList) {
		this.programNameList = programNameList;
	}

	public List<String> getProgramNameList() {
		return programNameList;
	}

	public void setDegreeList(List<SummarySheetBean> degreeList) {
		this.degreeList = degreeList;
	}

	public List<SummarySheetBean> getDegreeList() {
		return degreeList;
	}

	public String getCosCode() {
		return cosCode;
	}

	public void setCosCode(String cosCode) {
		this.cosCode = cosCode;
	}

	public String getCosDescription() {
		return cosDescription;
	}

	public void setCosDescription(String cosDescription) {
		this.cosDescription = cosDescription;
	}

	public List<SummarySheetBean> getCosCodeList() {
		return cosCodeList;
	}

	public void setCosCodeList(List<SummarySheetBean> cosCodeList) {
		this.cosCodeList = cosCodeList;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public Boolean getStaffWeightage() {
		return staffWeightage;
	}

	public void setStaffWeightage(Boolean staffWeightage) {
		this.staffWeightage = staffWeightage;
	}

	public String getUniversityNickName() {
		return universityNickName;
	}

	public void setUniversityNickName(String universityNickName) {
		this.universityNickName = universityNickName;
	}

	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	public String getCenterDescription() {
		return centerDescription;
	}

	public void setCenterDescription(String centerDescription) {
		this.centerDescription = centerDescription;
	}

	public List<SummarySheetBean> getCenterCodeList() {
		return centerCodeList;
	}

	public void setCenterCodeList(List<SummarySheetBean> centerCodeList) {
		this.centerCodeList = centerCodeList;
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

	public String getGuardian() {
		return guardian;
	}

	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}

	public String getFacRegNumber() {
		return facRegNumber;
	}

	public void setFacRegNumber(String facRegNumber) {
		this.facRegNumber = facRegNumber;
	}

	public List<String> getRegistrationNumList() {
		return registrationNumList;
	}

	public void setRegistrationNumList(List<String> registrationNumList) {
		this.registrationNumList = registrationNumList;
	}

	public String getFileSeparator() {
		return fileSeparator;
	}

	public void setFileSeparator(String fileSeparator) {
		this.fileSeparator = fileSeparator;
	}

	/**
	 * @return the minorityDesc
	 */
	public String getMinorityDesc() {
		return minorityDesc;
	}

	/**
	 * @param minorityDesc the minorityDesc to set
	 */
	public void setMinorityDesc(String minorityDesc) {
		this.minorityDesc = minorityDesc;
	}

	/**
	 * @return the maritalStatusDesc
	 */
	public String getMaritalStatusDesc() {
		return maritalStatusDesc;
	}

	/**
	 * @param maritalStatusDesc the maritalStatusDesc to set
	 */
	public void setMaritalStatusDesc(String maritalStatusDesc) {
		this.maritalStatusDesc = maritalStatusDesc;
	}

	/**
	 * @return the minority
	 */
	public String getMinority() {
		return minority;
	}

	/**
	 * @param minority the minority to set
	 */
	public void setMinority(String minority) {
		this.minority = minority;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the receiptNo
	 */
	public String getReceiptNo() {
	return receiptNo;
	}
	
	/**
	* @param receiptNo the receiptNo to set
	*/
	public void setReceiptNo(String receiptNo) {
	this.receiptNo = receiptNo;
	}
	
	/**
	* @return the receiptAmt
	*/
	public String getReceiptAmt() {
		return receiptAmt;
	}
	
	/**
	* @param receiptAmt the receiptAmt to set
	*/
	public void setReceiptAmt(String receiptAmt) {
	this.receiptAmt = receiptAmt;
	}
	
	/**
	* @return the flag1
	*/
	public String getFlag1() {
	return flag1;
	}
	
	/**
	* @param flag1 the flag1 to set
	*/
	public void setFlag1(String flag1) {
	this.flag1 = flag1;
	}
	/**
	 * @return the menuItemName
	 */
	public String getMenuItemName() {
		return menuItemName;
	}

	/**
	 * @param menuItemName the menuItemName to set
	 */
	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}

	/**
	 * @return the menuItemId
	 */
	public String getMenuItemId() {
		return menuItemId;
	}

	/**
	 * @param menuItemId the menuItemId to set
	 */
	public void setMenuItemId(String menuItemId) {
		this.menuItemId = menuItemId;
	}

	/**
	 * @return the prefferedChoiceAllowed
	 */
	public String getPrefferedChoiceAllowed() {
		return prefferedChoiceAllowed;
	}

	/**
	 * @param prefferedChoiceAllowed the prefferedChoiceAllowed to set
	 */
	public void setPrefferedChoiceAllowed(String prefferedChoiceAllowed) {
		this.prefferedChoiceAllowed = prefferedChoiceAllowed;
	}

	/**
	 * @return the branchId
	 */
	public String getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	
	/**
	 * @return the cocurriActivities
	 */
	public String getCocurriActivities() {
		return cocurriActivities;
	}

	/**
	 * @param cocurriActivities the cocurriActivities to set
	 */
	public void setCocurriActivities(String cocurriActivities) {
		this.cocurriActivities = cocurriActivities;
	}

	/**
	 * @return the applicationNumber
	 */
	public String getApplicationNumber() {
		return applicationNumber;
	}

	/**
	 * @param applicationNumber the applicationNumber to set
	 */
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	/**
	 * @return the applicationStatus
	 */
	public String getApplicationStatus() {
		return applicationStatus;
	}

	/**
	 * @param applicationStatus the applicationStatus to set
	 */
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	/**
	 * @return the verificationStatus
	 */
	public String getVerificationStatus() {
		return verificationStatus;
	}

	/**
	 * @param verificationStatus the verificationStatus to set
	 */
	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	/**
	 * @return the fee
	 */
	public String getFee() {
		return fee;
	}

	/**
	 * @param fee the fee to set
	 */
	public void setFee(String fee) {
		this.fee = fee;
	}

	/**
	 * @return the voucherNumber
	 */
	public String getVoucherNumber() {
		return voucherNumber;
	}

	/**
	 * @param voucherNumber the voucherNumber to set
	 */
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	/**
	 * @return the bankAccountNumber
	 */
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	/**
	 * @param bankAccountNumber the bankAccountNumber to set
	 */
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	/**
	 * @return the bankAccountName
	 */
	public String getBankAccountName() {
		return bankAccountName;
	}

	/**
	 * @param bankAccountName the bankAccountName to set
	 */
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	/**
	 * @return the voucherNote
	 */
	public String getVoucherNote() {
		return voucherNote;
	}

	/**
	 * @param voucherNote the voucherNote to set
	 */
	public void setVoucherNote(String voucherNote) {
		this.voucherNote = voucherNote;
	}

	/**
	 * @return the dei_student
	 */
	public String getDei_student() {
		return dei_student;
	}

	/**
	 * @param dei_student the dei_student to set
	 */
	public void setDei_student(String dei_student) {
		this.dei_student = dei_student;
	}

	/**
	 * @return the staff_ward
	 */
	public String getStaff_ward() {
		return staff_ward;
	}

	/**
	 * @param staff_ward the staff_ward to set
	 */
	public void setStaff_ward(String staff_ward) {
		this.staff_ward = staff_ward;
	}

	/**
	 * @return the oldSequenceNumber
	 */
	public String getOldSequenceNumber() {
		return oldSequenceNumber;
	}

	/**
	 * @param oldSequenceNumber the oldSequenceNumber to set
	 */
	public void setOldSequenceNumber(String oldSequenceNumber) {
		this.oldSequenceNumber = oldSequenceNumber;
	}

	/**
	 * @return the preferredChoiceArray1
	 */
	public List<String> getPreferredChoiceArray1() {
		return preferredChoiceArray1;
	}

	/**
	 * @param preferredChoiceArray1 the preferredChoiceArray1 to set
	 */
	public void setPreferredChoiceArray1(List<String> preferredChoiceArray1) {
		this.preferredChoiceArray1 = preferredChoiceArray1;
	}

	/**
	 * @return the preferredChoiceArray2
	 */
	public List<String> getPreferredChoiceArray2() {
		return preferredChoiceArray2;
	}

	/**
	 * @param preferredChoiceArray2 the preferredChoiceArray2 to set
	 */
	public void setPreferredChoiceArray2(List<String> preferredChoiceArray2) {
		this.preferredChoiceArray2 = preferredChoiceArray2;
	}

	/**
	 * @return the preferredChoiceArray3
	 */
	public List<String> getPreferredChoiceArray3() {
		return preferredChoiceArray3;
	}

	/**
	 * @param preferredChoiceArray3 the preferredChoiceArray3 to set
	 */
	public void setPreferredChoiceArray3(List<String> preferredChoiceArray3) {
		this.preferredChoiceArray3 = preferredChoiceArray3;
	}

	/**
	 * @return the preferredChoice1
	 */
	public String getPreferredChoice1() {
		return preferredChoice1;
	}

	/**
	 * @param preferredChoice1 the preferredChoice1 to set
	 */
	public void setPreferredChoice1(String preferredChoice1) {
		this.preferredChoice1 = preferredChoice1;
	}

	/**
	 * @return the preferredChoice2
	 */
	public String getPreferredChoice2() {
		return preferredChoice2;
	}

	/**
	 * @param preferredChoice2 the preferredChoice2 to set
	 */
	public void setPreferredChoice2(String preferredChoice2) {
		this.preferredChoice2 = preferredChoice2;
	}

	/**
	 * @return the preferredChoice3
	 */
	public String getPreferredChoice3() {
		return preferredChoice3;
	}

	/**
	 * @param preferredChoice3 the preferredChoice3 to set
	 */
	public void setPreferredChoice3(String preferredChoice3) {
		this.preferredChoice3 = preferredChoice3;
	}

	/**
	 * @return the preferredChoiceArray4
	 */
	public List<String> getPreferredChoiceArray4() {
		return preferredChoiceArray4;
	}

	/**
	 * @param preferredChoiceArray4 the preferredChoiceArray4 to set
	 */
	public void setPreferredChoiceArray4(List<String> preferredChoiceArray4) {
		this.preferredChoiceArray4 = preferredChoiceArray4;
	}

	/**
	 * @return the preferredChoiceArray5
	 */
	public List<String> getPreferredChoiceArray5() {
		return preferredChoiceArray5;
	}

	/**
	 * @param preferredChoiceArray5 the preferredChoiceArray5 to set
	 */
	public void setPreferredChoiceArray5(List<String> preferredChoiceArray5) {
		this.preferredChoiceArray5 = preferredChoiceArray5;
	}

	/**
	 * @return the applicationType
	 */
	public String getApplicationType() {
		return applicationType;
	}

	/**
	 * @param applicationType the applicationType to set
	 */
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	/**
	 * @return the handicaped
	 */
	public String getHandicaped() {
		return handicaped;
	}

	/**
	 * @param handicaped the handicaped to set
	 */
	public void setHandicaped(String handicaped) {
		this.handicaped = handicaped;
	}

	/**
	 * @return the fatherIncome
	 */
	public String getFatherIncome() {
		return fatherIncome;
	}

	/**
	 * @param fatherIncome the fatherIncome to set
	 */
	public void setFatherIncome(String fatherIncome) {
		this.fatherIncome = fatherIncome;
	}

	/**
	 * @return the motherIncome
	 */
	public String getMotherIncome() {
		return motherIncome;
	}

	/**
	 * @param motherIncome the motherIncome to set
	 */
	public void setMotherIncome(String motherIncome) {
		this.motherIncome = motherIncome;
	}

	/**
	 * @return the guardianIncome
	 */
	public String getGuardianIncome() {
		return guardianIncome;
	}

	/**
	 * @param guardianIncome the guardianIncome to set
	 */
	public void setGuardianIncome(String guardianIncome) {
		this.guardianIncome = guardianIncome;
	}

	/**
	 * @return the locality
	 */
	public String getLocality() {
		return locality;
	}

	/**
	 * @param locality the locality to set
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}

	/**
	 * @return the hostelRequired
	 */
	public String getHostelRequired() {
		return hostelRequired;
	}

	/**
	 * @param hostelRequired the hostelRequired to set
	 */
	public void setHostelRequired(String hostelRequired) {
		this.hostelRequired = hostelRequired;
	}

	/**
	 * @return the everExpelled
	 */
	public String getEverExpelled() {
		return everExpelled;
	}

	/**
	 * @param everExpelled the everExpelled to set
	 */
	public void setEverExpelled(String everExpelled) {
		this.everExpelled = everExpelled;
	}

	/**
	 * @return the mainGroup
	 */
	public String getMainGroup() {
		return mainGroup;
	}

	/**
	 * @param mainGroup the mainGroup to set
	 */
	public void setMainGroup(String mainGroup) {
		this.mainGroup = mainGroup;
	}

	/**
	 * @return the minChoice
	 */
	public String getMinChoice() {
		return minChoice;
	}

	/**
	 * @param minChoice the minChoice to set
	 */
	public void setMinChoice(String minChoice) {
		this.minChoice = minChoice;
	}

	/**
	 * @return the maxChoice
	 */
	public String getMaxChoice() {
		return maxChoice;
	}

	/**
	 * @param maxChoice the maxChoice to set
	 */
	public void setMaxChoice(String maxChoice) {
		this.maxChoice = maxChoice;
	}

	/**
	 * @return the employeeCode
	 */
	public String getEmployeeCode() {
		return employeeCode;
	}

	/**
	 * @param employeeCode the employeeCode to set
	 */
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	/**
	 * @return the mainGroupName
	 */
	public String getMainGroupName() {
		return mainGroupName;
	}

	/**
	 * @param mainGroupName the mainGroupName to set
	 */
	public void setMainGroupName(String mainGroupName) {
		this.mainGroupName = mainGroupName;
	}

	/**
	 * @return the otherBoardName
	 */
	public String getOtherBoardName() {
		return otherBoardName;
	}

	/**
	 * @param otherBoardName the otherBoardName to set
	 */
	public void setOtherBoardName(String otherBoardName) {
		this.otherBoardName = otherBoardName;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * @return the gateYear
	 */
	public String getGateYear() {
		return gateYear;
	}

	/**
	 * @param gateYear the gateYear to set
	 */
	public void setGateYear(String gateYear) {
		this.gateYear = gateYear;
	}

	/**
	 * @return the gateRank
	 */
	public String getGateRank() {
		return gateRank;
	}

	/**
	 * @param gateRank the gateRank to set
	 */
	public void setGateRank(String gateRank) {
		this.gateRank = gateRank;
	}

	/**
	 * @return the gateTotalStudent
	 */
	public String getGateTotalStudent() {
		return gateTotalStudent;
	}

	/**
	 * @param gateTotalStudent the gateTotalStudent to set
	 */
	public void setGateTotalStudent(String gateTotalStudent) {
		this.gateTotalStudent = gateTotalStudent;
	}

	/**
	 * @return the gateBranch
	 */
	public String getGateBranch() {
		return gateBranch;
	}

	/**
	 * @param gateBranch the gateBranch to set
	 */
	public void setGateBranch(String gateBranch) {
		this.gateBranch = gateBranch;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the feeList
	 */
	public List<String> getFeeList() {
		return feeList;
	}

	/**
	 * @param feeList the feeList to set
	 */
	public void setFeeList(List<String> feeList) {
		this.feeList = feeList;
	}

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the passingYearFlag
	 */
	public String getPassingYearFlag() {
		return passingYearFlag;
	}

	/**
	 * @param passingYearFlag the passingYearFlag to set
	 */
	public void setPassingYearFlag(String passingYearFlag) {
		this.passingYearFlag = passingYearFlag;
	}

	/**
	 * @return the passingYear
	 */
	public String getPassingYear() {
		return passingYear;
	}

	/**
	 * @param passingYear the passingYear to set
	 */
	public void setPassingYear(String passingYear) {
		this.passingYear = passingYear;
	}

	/**
	 * @return the resultAwaited
	 */
	public String getResultAwaited() {
		return resultAwaited;
	}

	/**
	 * @param resultAwaited the resultAwaited to set
	 */
	public void setResultAwaited(String resultAwaited) {
		this.resultAwaited = resultAwaited;
	}
	//Dharna
	  public String getNetQualified() {
			return netQualified;
		}

		public void setNetQualified(String netQualified) {
			this.netQualified = netQualified;
		}

		public String getNet() {
			return net;
		}

		public void setNet(String net) {
			this.net = net;
		}

		public String getJournals() {
			return journals;
		}

		public void setJournals(String journals) {
			this.journals = journals;
		}

		public String getJournalDescription() {
			return journalDescription;
		}

		public void setJournalDescription(String journalDescription) {
			this.journalDescription = journalDescription;
		}

		public String getConferences() {
			return conferences;
		}

		public void setConferences(String conferences) {
			this.conferences = conferences;
		}

		public String getConferenceDescription() {
			return conferenceDescription;
		}

		public void setConferenceDescription(String conferenceDescription) {
			this.conferenceDescription = conferenceDescription;
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

		public String getFellowship() {
			return fellowship;
		}

		public void setFellowship(String fellowship) {
			this.fellowship = fellowship;
		}

		public String getFellowshipDescription() {
			return fellowshipDescription;
		}

		public void setFellowshipDescription(String fellowshipDescription) {
			this.fellowshipDescription = fellowshipDescription;
		}

		public String getRegisteredInSession() {
			return registeredInSession;
		}

		public void setRegisteredInSession(String registeredInSession) {
			this.registeredInSession = registeredInSession;
		}

		public String getCCA() {
			return CCA;
		}

		public void setCCA(String cCA) {
			CCA = cCA;
		}

		public String getOld_program_id() {
			return old_program_id;
		}

		public void setOld_program_id(String old_program_id) {
			this.old_program_id = old_program_id;
		}

		public String getNew_program_id() {
			return new_program_id;
		}

		public void setNew_program_id(String new_program_id) {
			this.new_program_id = new_program_id;
		}

		public String getOld_registration_number() {
			return old_registration_number;
		}

		public void setOld_registration_number(String old_registration_number) {
			this.old_registration_number = old_registration_number;
		}

		public String getNew_registration_number() {
			return new_registration_number;
		}

		public void setNew_registration_number(String new_registration_number) {
			this.new_registration_number = new_registration_number;
		}

		public String getOffered_by() {
			return offered_by;
		}

		public void setOffered_by(String offered_by) {
			this.offered_by = offered_by;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		

	
}

