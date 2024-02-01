package in.ac.dei.edrp.admissionsystem.common.beans;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;

import java.util.ArrayList;
import java.util.List;

public class ApplicationForm 
{
	//private List<Component> components = new ArrayList<Component>();
	private List<GroupWiseComponent> groupWiseComponents = new ArrayList<GroupWiseComponent>();
	//private List<Component> gateComponents = new ArrayList<Component>();
	private List<CommonBean> boards = new ArrayList<CommonBean>();
	private List<ProgramForm> programForms = new ArrayList<ProgramForm>();
	
	private String branchAvailability = "N";
	private String examCenterAvailability;
	private String papersAvailability="N";
	
	private List<ProgramWiseDetail> programWiseDetails = new ArrayList<ProgramWiseDetail>();
	
	private String formNumber;
	
	public ApplicationForm()
	{
		
	}

	
	public List<GroupWiseComponent> getGroupWiseComponents() {
		return groupWiseComponents;
	}


	public void setGroupWiseComponents(List<GroupWiseComponent> groupWiseComponents) {
		this.groupWiseComponents = groupWiseComponents;
	}


		public String getBranchAvailability() {
		return branchAvailability;
	}

	public void setBranchAvailability(String branchAvailability) {
		this.branchAvailability = branchAvailability;
	}

	public String getExamCenterAvailability() {
		return examCenterAvailability;
	}

	public void setExamCenterAvailability(String examCenterAvailability) {
		this.examCenterAvailability = examCenterAvailability;
	}

	public String getPapersAvailability() {
		return papersAvailability;
	}

	public void setPapersAvailability(String papersAvailability) {
		this.papersAvailability = papersAvailability;
	}

	public List<ProgramWiseDetail> getProgramWiseDetails() {
		return programWiseDetails;
	}

	public void setProgramWiseDetails(List<ProgramWiseDetail> programWiseDetails) {
		this.programWiseDetails = programWiseDetails;
	}

	public List<CommonBean> getBoards() {
		return boards;
	}

	public void setBoards(List<CommonBean> boards) {
		this.boards = boards;
	}

	public String getFormNumber() {
		return formNumber;
	}

	public void setFormNumber(String formNumber) {
		this.formNumber = formNumber;
	}

	public List<ProgramForm> getProgramForms() {
		return programForms;
	}

	public void setProgramForms(List<ProgramForm> programForms) {
		this.programForms = programForms;
	}
	
	
	
}
