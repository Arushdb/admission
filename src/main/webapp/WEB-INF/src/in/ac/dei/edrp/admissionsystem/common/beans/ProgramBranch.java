package in.ac.dei.edrp.admissionsystem.common.beans;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;

import java.util.ArrayList;
import java.util.List;

public class ProgramBranch
{
	private CommonBean selectedBranch;
	private List<CommonBean> branches = new ArrayList<CommonBean>();;
	
	public ProgramBranch()
	{
		
	}
	
	
	
	
	public CommonBean getSelectedBranch() {
		return selectedBranch;
	}




	public void setSelectedBranch(CommonBean selectedBranch) {
		this.selectedBranch = selectedBranch;
	}




	public List<CommonBean> getBranches() {
		return branches;
	}
	public void setBranches(List<CommonBean> branches) {
		this.branches = branches;
	}
	
	
	
}
