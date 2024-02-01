package in.ac.dei.edrp.admissionsystem.common.beans;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;

import java.util.ArrayList;
import java.util.List;

public class GroupPaper 
{
	
	private String programID;
	
	private String universityID;
	private String grouping;
	private String mainGroup;
	private String mainGroupName;
	private int maxChoice;
	private int minChoice;
	private int allPaperSize;
	private String applicationNumber;
	private String paperGroupID;
	
	private List<CommonBean> selectedPapers = new ArrayList<CommonBean>();
	private List<CommonBean> allPapers = new ArrayList<CommonBean>();
	
	public GroupPaper()
	{
		
	}

	public String getGrouping() {
		return grouping;
	}

	public void setGrouping(String grouping) {
		this.grouping = grouping;
	}

	public List<CommonBean> getSelectedPapers() {
		return selectedPapers;
	}

	public void setSelectedPapers(List<CommonBean> selectedPapers) {
		this.selectedPapers = selectedPapers;
	}

	
	public List<CommonBean> getAllPapers() {
		return allPapers;
	}

	public void setAllPapers(List<CommonBean> allPapers) {
		this.allPapers = allPapers;
	}

	public String getProgramID() {
		return programID;
	}

	public void setProgramID(String programID) {
		this.programID = programID;
	}

	public String getUniversityID() {
		return universityID;
	}

	public void setUniversityID(String universityID) {
		this.universityID = universityID;
	}

	public String getMainGroup() {
		return mainGroup;
	}

	public void setMainGroup(String mainGroup) {
		this.mainGroup = mainGroup;
	}

	public int getMaxChoice() {
		return maxChoice;
	}

	public void setMaxChoice(int maxChoice) {
		this.maxChoice = maxChoice;
	}

	public int getMinChoice() {
		return minChoice;
	}

	public void setMinChoice(int minChoice) {
		this.minChoice = minChoice;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	
	public int getAllPaperSize() {
		return allPaperSize;
	}

	public void setAllPaperSize(int allPaperSize) {
		this.allPaperSize = allPaperSize;
	}

	public String getPaperGroupID() {
		return paperGroupID;
	}

	public void setPaperGroupID(String paperGroupID) {
		this.paperGroupID = paperGroupID;
	}

	public String getMainGroupName() {
		return mainGroupName;
	}

	public void setMainGroupName(String mainGroupName) {
		this.mainGroupName = mainGroupName;
	}

	
	
	
		
	
}
