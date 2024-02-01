package in.ac.dei.edrp.admissionsystem.common.beans;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;

import java.util.List;

public class Program {

	private String programID;
	private String programName;
	private String applied;
	private String selectedPaper;
	private String paperOptAvailable="N";
	private List<CommonBean> paperOptions;
	private int noOfPprOptions;
	private String theOnlyPprCode;
	private String theOnlyPprDesc;
	private String validGender;//Added for Gender Eligibility by Arjun Added on 04-04-2016
	private String ageLimit;//Added for Age Eligibility by Arjun Added on 04-04-2016
	private String programFee;
	
	public Program()
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
	public List<CommonBean> getPaperOptions() {
		return paperOptions;
	}
	public void setPaperOptions(List<CommonBean> paperOptions) {
		this.paperOptions = paperOptions;
	}

	public String getPaperOptAvailable() {
		return paperOptAvailable;
	}

	public void setPaperOptAvailable(String paperOptAvailable) {
		this.paperOptAvailable = paperOptAvailable;
	}

	public String getSelectedPaper() {
		return selectedPaper;
	}

	public void setSelectedPaper(String selectedPaper) {
		this.selectedPaper = selectedPaper;
	}

	public String getApplied() {
		return applied;
	}

	public void setApplied(String applied) {
		this.applied = applied;
	}

	public int getNoOfPprOptions() {
		return noOfPprOptions;
	}

	public void setNoOfPprOptions(int noOfPprOptions) {
		this.noOfPprOptions = noOfPprOptions;
	}

	public String getTheOnlyPprCode() {
		return theOnlyPprCode;
	}

	public void setTheOnlyPprCode(String theOnlyPprCode) {
		this.theOnlyPprCode = theOnlyPprCode;
	}

	public String getTheOnlyPprDesc() {
		return theOnlyPprDesc;
	}

	public void setTheOnlyPprDesc(String theOnlyPprDesc) {
		this.theOnlyPprDesc = theOnlyPprDesc;
	}

	public String getValidGender() {
		return validGender;
	}

	public void setValidGender(String validGender) {
		this.validGender = validGender;
	}

	public String getAgeLimit() {
		return ageLimit;
	}

	public void setAgeLimit(String ageLimit) {
		this.ageLimit = ageLimit;
	}

	public String getProgramFee() {
		return programFee;
	}

	public void setProgramFee(String programFee) {
		this.programFee = programFee;
	}

		
	
	
}
