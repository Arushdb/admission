package in.ac.dei.edrp.admissionsystem.common.beans;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;

import java.util.ArrayList;
import java.util.List;

public class ProgramPaper 
{
	private String programID;
	private String programName;
	private String universityID;
	private String grouping;
	private String mainGroup;
	private String mainGroupName;
	private int maxChoice;
	private int minChoice;
	private String applicationNumber;
	private List<GroupPaper> groupPapers = new ArrayList<GroupPaper>();
	private int groupPapersSize;
	
	public ProgramPaper()
	{
		
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

	public String getGrouping() {
		return grouping;
	}

	public void setGrouping(String grouping) {
		this.grouping = grouping;
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

	public List<GroupPaper> getGroupPapers() {
		return groupPapers;
	}

	public void setGroupPapers(List<GroupPaper> groupPapers) {
		this.groupPapers = groupPapers;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public int getGroupPapersSize() {
		return groupPapersSize;
	}

	public void setGroupPapersSize(int groupPapersSize) {
		this.groupPapersSize = groupPapersSize;
	}

	public String getMainGroupName() {
		return mainGroupName;
	}

	public void setMainGroupName(String mainGroupName) {
		this.mainGroupName = mainGroupName;
	}
	
	
}
