package in.ac.dei.edrp.admissionsystem.common.beans;
import in.ac.dei.edrp.admissionsystem.common.CommonBean;

import java.util.List;


public class ProgramForm 
{
	private String programID;
	private String programName;
	private List<CommonBean> branches;
	private List<Paper> papers;
	
	public ProgramForm() 
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

	

	public List<Paper> getPapers() {
		return papers;
	}

	public void setPapers(List<Paper> papers) {
		this.papers = papers;
	}

	public List<CommonBean> getBranches() {
		return branches;
	}

	public void setBranches(List<CommonBean> branches) {
		this.branches = branches;
	}

	
	
	}
