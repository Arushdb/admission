package in.ac.dei.edrp.admissionsystem.common.beans;

import java.util.ArrayList;
import java.util.List;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;

public class ProgramExamCenter 
{
	private CommonBean selectedExamCenter;
	private List<CommonBean> examCenters = new ArrayList<CommonBean>();
	
	public ProgramExamCenter()
	{
		
	}

	public CommonBean getSelectedExamCenter() {
		return selectedExamCenter;
	}

	public void setSelectedExamCenter(CommonBean selectedExamCenter) {
		this.selectedExamCenter = selectedExamCenter;
	}

	public List<CommonBean> getExamCenters() {
		return examCenters;
	}

	public void setExamCenters(List<CommonBean> examCenters) {
		this.examCenters = examCenters;
	}
	
	
	
}
