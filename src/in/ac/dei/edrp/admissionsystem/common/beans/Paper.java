package in.ac.dei.edrp.admissionsystem.common.beans;
public class Paper 
{
	private String paperCode;
	private String paperName;
	private String programID;
	private String universityID;
	private String grouping;
	private String mainGroup;
	private int maxChoice;
	private int minChoice;
	
	
	public Paper()
	{
	
	}

	public String getPaperCode() {
		return paperCode;
	}

	public void setPaperCode(String paperCode) {
		this.paperCode = paperCode;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
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
	
	
	
	
}
