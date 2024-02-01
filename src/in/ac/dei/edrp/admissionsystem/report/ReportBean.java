package in.ac.dei.edrp.admissionsystem.report;

public class ReportBean 
{
	private String programId;
	private String programName;
	private String applicationNumber;
	private String category;
	private String gender;
	private String name;
	private String fatherName;
	private String session;
	private int afterCount;
	private String exlStatus;
	
	public String getExlStatus() {
		return exlStatus;
	}
	public void setExlStatus(String exlStatus) {
		this.exlStatus = exlStatus;
	}
	public String getPdfStatus() {
		return pdfStatus;
	}
	public void setPdfStatus(String pdfStatus) {
		this.pdfStatus = pdfStatus;
	}
	private String pdfStatus;
	
	public int getBeforeCount() {
		return beforeCount;
	}
	public void setBeforeCount(int beforeCount) {
		this.beforeCount = beforeCount;
	}
	private int beforeCount;
	              
	
	public int getAfterCount() {
		return afterCount;
	}
	public void setAfterCount(int afterCount) {
		this.afterCount = afterCount;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getApplicationNumber() {
		return applicationNumber;
	}
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	
	
}
