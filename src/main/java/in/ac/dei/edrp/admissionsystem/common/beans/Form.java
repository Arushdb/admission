package in.ac.dei.edrp.admissionsystem.common.beans;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;

import java.util.List;

public class Form {

	private String formNumber;
	private String formName;
	private String applicationNumber;
	private String universityID;
	private List<Program> programs;
	private String inputCategory; //Added Age Eligibility (Category Wise) by Arjun Added on 04-04-2016
	
	public Form()
	{
		
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

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getUniversityID() {
		return universityID;
	}

	public void setUniversityID(String universityID) {
		this.universityID = universityID;
	}

	public String getInputCategory() {
		return inputCategory;
	}

	public void setInputCategory(String inputCategory) {
		this.inputCategory = inputCategory;
	}

		
	
	
}
