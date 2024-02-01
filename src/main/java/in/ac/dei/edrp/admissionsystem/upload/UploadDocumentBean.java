package in.ac.dei.edrp.admissionsystem.upload;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UploadDocumentBean implements Serializable{
private String applicationNumber;
private String documentPath;
private String applicationStatus;
private String form_number;
private String sourcePath;
private String targetPath;
private String docId;
private String file_name;
private String studentID;
private String tabStatus;
private String photoSize;
private String signSize;
private String documentType;

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
 * @return the tabStatus
 */
public String getTabStatus() {
	return tabStatus;
}
/**
 * @param tabStatus the tabStatus to set
 */
public void setTabStatus(String tabStatus) {
	this.tabStatus = tabStatus;
}
/**
 * @return the photoSize
 */
public String getPhotoSize() {
	return photoSize;
}
/**
 * @param photoSize the photoSize to set
 */
public void setPhotoSize(String photoSize) {
	this.photoSize = photoSize;
}
/**
 * @return the signSize
 */
public String getSignSize() {
	return signSize;
}
/**
 * @param signSize the signSize to set
 */
public void setSignSize(String signSize) {
	this.signSize = signSize;
}
/**
 * @return the documentType
 */
public String getDocumentType() {
	return documentType;
}
/**
 * @param documentType the documentType to set
 */
public void setDocumentType(String documentType) {
	this.documentType = documentType;
}
}
