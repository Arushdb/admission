package in.ac.dei.edrp.admissionsystem.paymentDetails;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PaymentDocumentBean implements Serializable{
private String applicationNumber;
private String documentPath;
private String applicationStatus;
private String form_number;
private String sourcePath;
private String targetPath;
private String docId;
private String file_name;
private String studentID;
private String bankAccountNumber;
private String bankAccountName;
private String city;
private String firstName;
private String programName;
private String sessionEndDate;
private String fee;
private String VoucherNote;
private String dob;

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
 * @return the documentPath
 */
public String getDocumentPath() {
	return documentPath;
}
/**
 * @param documentPath the documentPath to set
 */
public void setDocumentPath(String documentPath) {
	this.documentPath = documentPath;
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
 * @return the form_number
 */
public String getForm_number() {
	return form_number;
}
/**
 * @param formNumber the form_number to set
 */
public void setForm_number(String formNumber) {
	form_number = formNumber;
}
/**
 * @return the sourcePath
 */
public String getSourcePath() {
	return sourcePath;
}
/**
 * @param sourcePath the sourcePath to set
 */
public void setSourcePath(String sourcePath) {
	this.sourcePath = sourcePath;
}
/**
 * @return the targetPath
 */
public String getTargetPath() {
	return targetPath;
}
/**
 * @param targetPath the targetPath to set
 */
public void setTargetPath(String targetPath) {
	this.targetPath = targetPath;
}
/**
 * @return the docId
 */
public String getDocId() {
	return docId;
}
/**
 * @param docId the docId to set
 */
public void setDocId(String docId) {
	this.docId = docId;
}
/**
 * @return the file_name
 */
public String getFile_name() {
	return file_name;
}
/**
 * @param fileName the file_name to set
 */
public void setFile_name(String fileName) {
	file_name = fileName;
}
/**
 * @return the studentID
 */
public String getStudentID() {
	return studentID;
}
/**
 * @param studentID the studentID to set
 */
public void setStudentID(String studentID) {
	this.studentID = studentID;
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
 * @return the city
 */
public String getCity() {
	return city;
}
/**
 * @param city the city to set
 */
public void setCity(String city) {
	this.city = city;
}
/**
 * @return the firstName
 */
public String getFirstName() {
	return firstName;
}
/**
 * @param firstName the firstName to set
 */
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
/**
 * @return the programName
 */
public String getProgramName() {
	return programName;
}
/**
 * @param programName the programName to set
 */
public void setProgramName(String programName) {
	this.programName = programName;
}
/**
 * @return the sessionEndDate
 */
public String getSessionEndDate() {
	return sessionEndDate;
}
/**
 * @param sessionEndDate the sessionEndDate to set
 */
public void setSessionEndDate(String sessionEndDate) {
	this.sessionEndDate = sessionEndDate;
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
 * @return the voucherNote
 */
public String getVoucherNote() {
	return VoucherNote;
}
/**
 * @param voucherNote the voucherNote to set
 */
public void setVoucherNote(String voucherNote) {
	VoucherNote = voucherNote;
}
/**
 * @return the dob
 */
public String getDob() {
	return dob;
}
/**
 * @param dob the dob to set
 */
public void setDob(String dob) {
	this.dob = dob;
}
}
